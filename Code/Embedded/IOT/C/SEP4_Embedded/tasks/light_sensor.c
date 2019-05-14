/*
 * light_sensor.c
 *
 * Created: 13-05-2019 20:19:52
 *  Author: Christian
 */ 

#include <ATMEGA_FreeRTOS.h>
#include <stdio.h>
#include <stdio_driver.h>
#include "light_sensor.h"
#include "semphr.h"
#include "tsl2591.h"
#include "plantdata.h"

void lightSensorTask(void* pvParameters) {
	(void)pvParameters;

	while(1) {
		vTaskDelay(1000);	
		int r = tsl2591FetchData();
		if(r != TSL2591_OK) {
			printf("Failed to fetch light data: %d\n", r);	
		}
		vTaskDelay(9000);
	}

	vTaskDelete(NULL);
}

void lightCallback(tsl2591ReturnCode_t rc) {
	float measure;
	if(rc != TSL2591_DATA_READY) {
		printf("Light sensor not ready\n");
		return;
	}
	if(TSL2591_OK == tsl2591GetLux(&measure)) {
		
		///////////////////semaphore:
		xSemaphoreTake(semaphore, portMAX_DELAY);
		plantdata.light = (uint16_t) measure;
		//printf("Light: %d\n", (uint16_t) measure);
		xSemaphoreGive(semaphore);
	}
	else {
		printf("Lux overflow\n");	
	}
	
}
