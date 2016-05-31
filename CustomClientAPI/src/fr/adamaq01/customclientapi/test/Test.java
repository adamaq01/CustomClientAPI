package fr.adamaq01.customclientapi.test;

import fr.adamaq01.customclientapi.CustomClientAPI;
import fr.adamaq01.customclientapi.api.clients.MinecraftClient;
import fr.adamaq01.customclientapi.api.enums.Version;
import fr.adamaq01.customclientapi.api.server.ServerConnection;

@SuppressWarnings("unused")
public class Test {

	public static void main(String[] args) {
		MinecraftClient client = CustomClientAPI.createClient(Version.v_1_9_4, "xXxBesterPvPxXx");
		ServerConnection server = client.connect("play.adamaq0.fr", 25565);
	}

}
