package model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * DTO for user accounts
 */
public class Account {
	public String Username;
	public String Password;

	public String toJson() {
		try {
			return JsonConverter.objectMapper.writeValueAsString(this);
		} catch(JsonProcessingException e) {
			return null;
		}
	}

	public static Account fromJson(String json) {
		try {
			return JsonConverter.objectMapper.readValue(json, Account.class);
		} catch(IOException e) {
			return null;
		}
	}
}
