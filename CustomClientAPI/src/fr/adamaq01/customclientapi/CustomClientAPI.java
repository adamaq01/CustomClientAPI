package fr.adamaq01.customclientapi;

import fr.adamaq01.customclientapi.api.clients.MinecraftClient;
import fr.adamaq01.customclientapi.api.enums.Version;
import fr.adamaq01.customclientapi.clients.v1_8_MinecraftClient;
import fr.adamaq01.customclientapi.clients.v1_9_4_MinecraftClient;

public class CustomClientAPI {

	public static MinecraftClient createClient(Version version, String username, String password) {
		switch (version) {
		case v_1_8:
			return new v1_8_MinecraftClient(username, password);
		case v_1_9_4:
			return new v1_9_4_MinecraftClient(username, password);
		default:
			return new v1_9_4_MinecraftClient(username, password);
		}
	}

	public static MinecraftClient createClient(Version version, String username) {
		switch (version) {
		case v_1_8:
			return new v1_8_MinecraftClient(username);
		case v_1_9_4:
			return new v1_9_4_MinecraftClient(username);
		default:
			return new v1_9_4_MinecraftClient(username);
		}
	}

}
