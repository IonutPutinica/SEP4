package model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * DTO for Plants
 */
public class PlantProfile {
	public int PlantID;
	public String PlantName;
	public double Temperature;
	public double CO2;
	public double Humidity;
	public double AmountOfWater;
	public double HoursSinceWatering;
	public int Light;

	public String toJson() {
		try {
			return JsonConverter.objectMapper.writeValueAsString(this);
		} catch(JsonProcessingException e) {
			return null;
		}
	}

	public static PlantProfile fromJson(String json) {
		try {
			return JsonConverter.objectMapper.readValue(json, PlantProfile.class);
		} catch(IOException e) {
			return null;
		}
	}
}
