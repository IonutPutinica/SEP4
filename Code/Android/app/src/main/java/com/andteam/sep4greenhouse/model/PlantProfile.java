package com.andteam.sep4greenhouse.model;

import java.io.Serializable;

public class PlantProfile implements Serializable {


    private int profileId;
    private String name;
    private double water;
    private int wInterval;
    private PlantDTO plant;

    public PlantProfile(int profileId, String name, double water, int wInterval, PlantDTO plant) {
        this.profileId = profileId;
        this.name = name;
        this.water = water;
        this.wInterval = wInterval;
        this.plant = plant;
    }


    public String getName() {
        return name;
    }

    public double getWater() {
        return water;
    }

    public int getwInterval() {
        return wInterval;
    }


    public PlantDTO getPlant() {
        return plant;
    }

    public int getProfileId() {
        return profileId;
    }
}
