/*
 * servo_motor.c
 *
 * Created: 13-05-2019 20:44:12
 *  Author: Christian
 */ 
#include <ATMEGA_FreeRTOS.h>
#include <stdio.h>
#include <stdio_driver.h>
#include "servo_motor.h"
#include "semphr.h"
#include "rcServo.h"
#include "plantdata.h"

void servoMotorTask(void* pvParamters){
	(void)pvParamters;
	TickType_t waterInterval = (lastWateringTime + 1000);
	
	while(1){
		vTaskDelay(1100);
			rcServoSet(0,100);
			lastWateringTime = xTaskGetTickCount();
			plantdata.water = xTaskGetTickCount();	
			//printf("Plant has been watered!!!!\n");
		vTaskDelay(9000);
	}
}
