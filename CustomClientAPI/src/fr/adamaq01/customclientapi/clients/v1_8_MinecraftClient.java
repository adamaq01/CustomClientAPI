package fr.adamaq01.customclientapi.clients;

import fr.adamaq01.customclientapi.api.clients.MinecraftClient;
import fr.adamaq01.customclientapi.api.enums.Version;

public class v1_8_MinecraftClient extends MinecraftClient {

	public v1_8_MinecraftClient(String username, String password) {
		super(Version.v_1_8, username, password);
	}

	public v1_8_MinecraftClient(String username) {
		super(Version.v_1_8, username);
	}

}
