package fr.adamaq01.customclientapi.clients;

import fr.adamaq01.customclientapi.api.clients.MinecraftClient;
import fr.adamaq01.customclientapi.api.enums.Version;

public class v1_9_4_MinecraftClient extends MinecraftClient {

	public v1_9_4_MinecraftClient(String username, String password) {
		super(Version.v_1_9_4, username, password);
	}

	public v1_9_4_MinecraftClient(String username) {
		super(Version.v_1_9_4, username);
	}

}
