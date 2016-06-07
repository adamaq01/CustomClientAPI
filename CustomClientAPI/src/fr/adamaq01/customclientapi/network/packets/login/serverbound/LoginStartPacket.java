package fr.adamaq01.customclientapi.network.packets.login.serverbound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class LoginStartPacket extends Packet {

	private String name;

	public LoginStartPacket(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void write(DataOutputStream out) {
		try {
			PacketBuffer.writeString(name, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void read(DataInputStream in) {
	}

}
