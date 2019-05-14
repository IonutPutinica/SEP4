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
	
	while(1){
		vTaskDelay(1000);
			rcServoSet(0,100);
			vTaskDelay(1000);
			rcServoSet(0,-100);
			plantdata.water = xTaskGetTickCount();	
			//printf("Plant has been watered!!!!\n");
		for(int i = 0; i < 5; i++){
			vTaskDelay(10000);
		}		
	}
}
