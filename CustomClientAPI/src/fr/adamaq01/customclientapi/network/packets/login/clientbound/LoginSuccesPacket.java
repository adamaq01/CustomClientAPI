package fr.adamaq01.customclientapi.network.packets.login.clientbound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class LoginSuccesPacket extends Packet {

	private UUID uuid;
	private String name;

	public LoginSuccesPacket() {
	}
	
	public LoginSuccesPacket(UUID uuid, String name) {
		this.name = name;
	}

	public UUID getUUID() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void write(DataOutputStream out) {
	}

	@Override
	public void read(DataInputStream in) {
		try {
			this.uuid = UUID.fromString(PacketBuffer.readString(in));
			this.name = PacketBuffer.readString(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
