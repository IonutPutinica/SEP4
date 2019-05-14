/*
 * temp_sensor.c
 *
 * Created: 13-05-2019 20:13:07
 *  Author: Christian
 */
#include <ATMEGA_FreeRTOS.h>
#include <stdio.h>
#include <stdio_driver.h>
#include "temp_sensor.h"
#include "semphr.h"
#include "hih8120.h"
#include "plantdata.h"

void tempSensorTask(void* pvParameters) {
	(void)pvParameters;
		
	//Do temperature measurement
	while(1) {
		vTaskDelay(1000);
		
		int r = hih8120Wakeup();
		if(r != HIH8120_OK && r != HIH8120_TWI_BUSY) {
			printf("temp-wake error: %d\n", r);
		}
		
		vTaskDelay(100);
		r = hih8120Meassure();
		if(r != HIH8120_OK && r != HIH8120_TWI_BUSY) {
			printf("Temp-read error: %d\n", r);

		}
		vTaskDelay(100);
		///////////////////semaphore:
		xSemaphoreTake(semaphore, portMAX_DELAY);
		plantdata.humidity = hih8120GetHumidity();
		plantdata.temperature = hih8120GetTemperature();
		//printf("Hum: %d  Temp: %d\n", plantdata.humidity, plantdata.temperature);
		xSemaphoreGive(semaphore);

	}

	
	vTaskDelete(NULL);
}
 
