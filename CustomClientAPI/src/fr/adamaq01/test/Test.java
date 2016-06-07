package fr.adamaq01.test;

import fr.adamaq01.customclientapi.CustomClientAPI;
import fr.adamaq01.customclientapi.api.clients.MinecraftClient;
import fr.adamaq01.customclientapi.api.enums.Version;
import fr.adamaq01.customclientapi.api.server.ServerConnection;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MinecraftClient client = CustomClientAPI.createClient(Version.v_1_9_4, "LOLMDR");
		ServerConnection server = client.connect("play.adamaq01.fr", 25565);
	}

}
