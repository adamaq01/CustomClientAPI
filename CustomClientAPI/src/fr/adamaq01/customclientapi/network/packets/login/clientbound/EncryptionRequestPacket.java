package fr.adamaq01.customclientapi.network.packets.login.clientbound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class EncryptionRequestPacket extends Packet {

	private String serverId;
	private byte[] publicKey;
	private byte[] verifyToken;
	
	public EncryptionRequestPacket() {
	}

	public EncryptionRequestPacket(String serverId, byte[] publicKey, byte[] verifyToken) {
		this.serverId = serverId;
		this.publicKey = publicKey;
		this.verifyToken = verifyToken;
	}

	@Override
	public void write(DataOutputStream out) {
	}

	@Override
	public void read(DataInputStream in) {
		try {
			serverId = PacketBuffer.readString(in);
			publicKey = PacketBuffer.readArray(in);
			verifyToken = PacketBuffer.readArray(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getServerId() {
		return serverId;
	}

	public byte[] getPublicKey() {
		return publicKey;
	}

	public byte[] getVerifyToken() {
		return verifyToken;
	}

}
