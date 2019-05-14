package model;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Container for static ObjectMapper shared by model classes
 */
public class JsonConverter {
	public static ObjectMapper objectMapper = new ObjectMapper();
}
