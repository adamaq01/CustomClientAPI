package fr.adamaq01.customclientapi.network.packets.handshake.serverbound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import fr.adamaq01.customclientapi.api.enums.HandshakeState;
import fr.adamaq01.customclientapi.api.enums.Version;
import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class HandshakePacket extends Packet {

	private Version version;
	private String host;
	private short port;
	private HandshakeState state;

	public HandshakePacket(Version version, String host, short port, HandshakeState state) {
		this.version = version;
		this.host = host;
		this.port = port;
		this.state = state;
	}

	public Version getVersion() {
		return version;
	}

	public String getHost() {
		return host;
	}

	public short getPort() {
		return port;
	}

	public HandshakeState getState() {
		return state;
	}

	@Override
	public void write(DataOutputStream out) {
		try {
			PacketBuffer.writeVarInt(version.getProtocolVersion(), out);
			PacketBuffer.writeString(host, out);
			out.writeShort(port);
			PacketBuffer.writeVarInt(state.getState(), out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void read(DataInputStream in) {
	}

}
