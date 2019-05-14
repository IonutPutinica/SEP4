/*
 * app.c
 *
 * Created: 05/04/2019 07.51.28
 * Author : kup
 */ 
///////////////////
///Tick Speed 62///
///////////////////

//////////////////////////////////////////////////////////////////////////

//library files
#include <ATMEGA_FreeRTOS.h>
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>
#include <stdio.h>
#include <stdio_driver.h>
#include <semphr.h>
#include <task.h>
#include <serial/serial.h>

//drivers
#include "hih8120.h"
#include "mh_z19.h"
#include "serial/serial.h"
#include "tsl2591.h"
#include "ihal.h"
#include "lora_driver.h"
#include "Plantdata.h"
#include "rcServo.h"

//header files for tasks
#include "tasks/CO2_sensor.h"
#include "tasks/temp_sensor.h"
#include "tasks/light_sensor.h"
#include "tasks/servo_motor.h"
#include "tasks/LoRaWAN.h"

//////////////////////////////////////////////////////////////////////////

//defining priority 
#define LED_TASK_PRIORITY   (configMAX_PRIORITIES - 1)
#define TEMP_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define CO2_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define LIGHT_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define WATER_TASK_PRIORITY (configMAX_PRIORITIES - 3)
#define SERVO_TASK_PRIORITY (configMAX_PRIORITIES - 3)

//task setup
TaskHandle_t tempSensorHandle = NULL;
TaskHandle_t co2SensorHandle = NULL;
TaskHandle_t lightSensorHandle = NULL;
TaskHandle_t WaterHandle = NULL;
TaskHandle_t ServoMotorHandle = NULL;
TaskHandle_t loRaWanHandle = NULL;

int main() {
	
	//sets all variables in plantdata to 0.
	plantdata.co2 = 0;
	plantdata.humidity = 0;
	plantdata.light = 0;
	plantdata.temperature = 0;
	plantdata.water = 0;
	
	stdioCreate(0);
	
	//setup for loRaWAN
	hal_create(LED_TASK_PRIORITY);
	lora_driver_create(ser_USART1);
	
	//creating tasks
	xTaskCreate(tempSensorTask, "Temperature measurement", configMINIMAL_STACK_SIZE, NULL, TEMP_TASK_PRIORITY, &tempSensorHandle);
	xTaskCreate(co2SensorTask, "CO2 measurement", configMINIMAL_STACK_SIZE, NULL, CO2_TASK_PRIORITY, &co2SensorHandle);
	xTaskCreate(lightSensorTask, "Light measurement", configMINIMAL_STACK_SIZE, NULL, LIGHT_TASK_PRIORITY, &lightSensorHandle);
	xTaskCreate(loRaWanTask, "Led", configMINIMAL_STACK_SIZE, NULL,LED_TASK_PRIORITY, &loRaWanHandle);
	xTaskCreate(servoMotorTask, "Servo Motor", configMINIMAL_STACK_SIZE, NULL, SERVO_TASK_PRIORITY,&ServoMotorHandle);
	
	semaphore = xSemaphoreCreateMutex();
	
	//setup temperature/humidity sensor
	if(HIH8120_OK != hih8120Create()) {
		printf("Failed to initialize temperature sensor\n");
		return 1;
	}
	//setup servoMotor
	rcServoCreate();

	//setup co2 sensor
	mh_z19_create(ser_USART3, co2Callback);

	//setup light sensor
	int r = tsl2591Create(lightCallback);
	if(r != TSL2591_OK) {
		printf("Failed to initialize light sensor: %d\n", r);
	}

	r = tsl2591Enable();
	if(r != TSL2591_OK) {
		printf("Failed to enable light sensor %d\n", r);
	}
	
	vTaskStartScheduler();
	
	while(1) {
		;
	}
}

