package com.andteam.sep4greenhouse.model;

import java.io.Serializable;

public class PlantDTO implements Serializable {

    static final long serialVersionUID = 1L;
    public int PlantID;
    public String PlantName;
    public double Temperature;
    public double CO2;
    public double Humidity;
    public int Light;

    public PlantDTO(int plantID, String plantName, double temperature, double CO2, double humidity, int light) {
        PlantID = plantID;
        PlantName = plantName;
        Temperature = temperature;
        this.CO2 = CO2;
        Humidity = humidity;
        Light = light;
    }

    public int getPlantID() {
        return PlantID;
    }

    public String getPlantName() {
        return PlantName;
    }

    public double getTemperature() {
        return Temperature;
    }

    public double getCO2() {
        return CO2;
    }

    public double getHumidity() {
        return Humidity;
    }

    public int getLight() {
        return Light;
    }

}
