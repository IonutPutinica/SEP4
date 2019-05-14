/*
 * CO2_sensor.c
 *
 * Created: 13-05-2019 19:33:15
 *  Author: Christian
 */ 
#include <ATMEGA_FreeRTOS.h>
#include <stdio.h>
#include <stdio_driver.h>
#include "CO2_sensor.h"
#include "semphr.h"
#include "plantdata.h"
#include "mh_z19.h"

void co2SensorTask(void *pvParamters) {
	(void)pvParamters;

	while(1) {
		vTaskDelay(1000);
		int r = mh_z19_take_meassuring();
		if(r != MHZ19_OK) {
			printf("CO2 sensor: %d", r);			
		}
		vTaskDelay(9000);
	}

	vTaskDelete(NULL);
}

void co2Callback(uint16_t ppm) {
	///////////////////semaphore:
	xSemaphoreTake(semaphore, portMAX_DELAY);
	plantdata.co2 = ppm;
	//printf("CO2 level: %u\n", ppm);
	
	xSemaphoreGive(semaphore);
}
