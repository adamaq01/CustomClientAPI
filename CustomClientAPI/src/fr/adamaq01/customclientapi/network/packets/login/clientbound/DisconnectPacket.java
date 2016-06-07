package fr.adamaq01.customclientapi.network.packets.login.clientbound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class DisconnectPacket extends Packet {

	private String chat;
	
	public DisconnectPacket() {
	}
	
	public DisconnectPacket(String chat) {
		this.chat = chat;
	}
	
	@Override
	public void write(DataOutputStream out) {
	}

	@Override
	public void read(DataInputStream in) {
		try {
			this.chat = PacketBuffer.readString(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getChat() {
		return chat;
	}

}
