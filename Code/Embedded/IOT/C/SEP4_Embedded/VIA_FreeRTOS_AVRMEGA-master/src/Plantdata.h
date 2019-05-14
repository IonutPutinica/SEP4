
/*
 * taskplan.h
 *
 * Created: 09/05/2019 11:55:20
 *  Author: Batiste
 */
#include <semphr.h>

#ifndef SharedRes
#define SharedRes

typedef struct Plantdata{
	int temperature;
	int humidity;
	uint16_t co2;
	uint16_t light;
	uint32_t water;
	}Plantdata;
	
Plantdata plantdata;

SemaphoreHandle_t semaphore;

TickType_t lastWateringTime;

#endif