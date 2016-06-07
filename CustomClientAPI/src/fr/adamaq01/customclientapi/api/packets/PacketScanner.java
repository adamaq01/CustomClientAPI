package fr.adamaq01.customclientapi.api.packets;

import fr.adamaq01.customclientapi.api.server.ServerConnection;

public class PacketScanner extends PacketsScanningThread {

	public PacketScanner(ServerConnection connection) {
		super(connection);
		start();
	}
	
}
