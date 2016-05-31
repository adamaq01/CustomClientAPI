package fr.adamaq01.customclientapi.network.packets.login.serverbound;

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
	public void write(PacketBuffer out) {
		out.writeString(name);
	}

	@Override
	public void read(PacketBuffer in) {

	}

}
