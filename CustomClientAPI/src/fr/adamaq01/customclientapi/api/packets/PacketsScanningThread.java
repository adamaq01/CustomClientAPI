package fr.adamaq01.customclientapi.api.packets;

import fr.adamaq01.customclientapi.api.server.ServerConnection;

public abstract class PacketsScanningThread extends Thread {

	private Packet scannedPacket;

	private ServerConnection connection;

	public PacketsScanningThread(ServerConnection connection) {
		
		if (connection == null)
            throw new IllegalArgumentException("PacketsScanningThread: connection can't be null!");
		
		this.connection = connection;
	}

	public void setScannedPacket(Packet scannedPacket) {
		this.scannedPacket = scannedPacket;
	}

	public void onPacketReceived() {
		if (connection == null)
			return;
		Packet packet = PacketsScanningUtils.readPackets(connection);
		if (scannedPacket != null)
			return;
		setScannedPacket(packet);
	}

	public final Packet nextPacket() {
		while (scannedPacket == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Packet packet = scannedPacket;
		scannedPacket = null;
		return packet;
	}

	public ServerConnection getConnection() {
		return connection;
	}

	@Override
	public void run() {
		interrupt();
	}

}