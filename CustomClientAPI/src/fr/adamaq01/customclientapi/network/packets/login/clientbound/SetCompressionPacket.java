package fr.adamaq01.customclientapi.network.packets.login.clientbound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class SetCompressionPacket extends Packet {

	private int threshold;
	
	public SetCompressionPacket() {
	}
	
	public SetCompressionPacket(int threshold) {
		this.threshold = threshold;
	}
	
	@Override
	public void write(DataOutputStream out) {
		try {
			PacketBuffer.writeVarInt(threshold, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void read(DataInputStream in) {
		try {
			this.threshold = PacketBuffer.readVarInt(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getThreshold() {
		return threshold;
	}

}
