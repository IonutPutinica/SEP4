/*
 * LoRaWAN.c
 *
 * Created: 13-05-2019 21:04:10
 *  Author: Christian
 */ 
#include <stddef.h>
#include <stdio.h>

#include <ATMEGA_FreeRTOS.h>

#include <lora_driver.h>

#include "LoRaWAN.h"
#include "semphr.h"
#include "plantdata.h"

#define LORA_appEUI "c53e8f9f10801fc4"
#define LORA_appKEY "018cc25f724a8517cbfd763dc1126614"

char _out_buff[100];

void _loRa_setup(void){
	
	e_LoRa_return_code_t rc;
	
	//For factory reset.
	printf("FactoryRest >%s<\n",
	lora_driver_map_return_code_to_text(lora_driver_rn2483_factory_reset()));
	
	//Configure to EU868 LoRaWAN standards.
	printf("Configure to EU868 >%s<\n",
	lora_driver_map_return_code_to_text(lora_driver_configure_to_eu868()));
	
	//Get the transceivers HW EUI
	rc = lora_driver_get_rn2483_hweui(_out_buff);
	printf("Get HWEUI: %s >%s< \n", lora_driver_map_return_code_to_text(rc), _out_buff );
	
	
	// Set the HWEUI as DevEUI in the LoRaWAN software stack in the transceiver
	printf("Set DevEUI: %s >%s<\n", _out_buff, lora_driver_map_return_code_to_text(lora_driver_set_device_identifier(_out_buff)));

	// Set Over The Air Activation parameters to be ready to join the LoRaWAN
	printf("Set OTAA Identity appEUI:%s appKEY:%s devEUI:%s >%s<\n", LORA_appEUI, LORA_appKEY, _out_buff, lora_driver_map_return_code_to_text(lora_driver_set_otaa_identity(LORA_appEUI,LORA_appKEY,_out_buff)));

	// Save all the MAC settings in the transceiver
	printf("Save mac >%s<\n",lora_driver_map_return_code_to_text(lora_driver_save_mac()));

	// Enable Adaptive Data Rate
	printf("Set Adaptive Data Rate: ON >%s<\n", lora_driver_map_return_code_to_text(lora_driver_set_adaptive_data_rate(LoRa_ON)));

	// Join the LoRaWAN
	uint8_t maxJoinTriesLeft = 5;
	do {
		rc = lora_driver_join(LoRa_OTAA);
		printf("Join Network TriesLeft:%d >%s<\n", maxJoinTriesLeft, lora_driver_map_return_code_to_text(rc));
		if ( rc == LoRa_ACCEPTED){
			break;
		}

	} while (--maxJoinTriesLeft);

}

void loRaWanTask(void* pvParamters){
	(void)pvParamters;

	//for resetting the LoRaWAN hardware.
	lora_driver_reset_rn2483(1);
	vTaskDelay(150);
	lora_driver_reset_rn2483(0);
	vTaskDelay(150);
	lora_driver_flush_buffers();
	
	_loRa_setup();
	vTaskDelay(200);
	
	lora_payload_t _uplink_payload;
	
	_uplink_payload.len = 7;
	_uplink_payload.port_no = 2;
	
	
	while(1){
		
		///////////////////semaphore:
		xSemaphoreTake(semaphore, portMAX_DELAY);
		_uplink_payload.bytes[0] = plantdata.humidity;
		_uplink_payload.bytes[1] = plantdata.temperature;
		_uplink_payload.bytes[2] = plantdata.co2 >> 8;
		_uplink_payload.bytes[3] = plantdata.co2 & 0xFF;
		_uplink_payload.bytes[4] = plantdata.light >> 8;
		_uplink_payload.bytes[5] = plantdata.light& 0xFF;
		_uplink_payload.bytes[6] = (xTaskGetTickCount() - plantdata.water) * configTICK_RATE_HZ / 3600000;
		
		printf("Upload Message >%s<\n", lora_driver_map_return_code_to_text(lora_driver_sent_upload_message(false, &_uplink_payload)));
		xSemaphoreGive(semaphore);
		vTaskDelay(10000);
		
	}
	
	vTaskDelete(NULL);
}

