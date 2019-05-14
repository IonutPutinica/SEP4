/*
 * light_sensor.h
 *
 * Created: 13-05-2019 20:20:18
 *  Author: Christian
 */
#include "tsl2591.h"

void lightCallback(tsl2591ReturnCode_t rc); 
void lightSensorTask(void* pvParameters);
