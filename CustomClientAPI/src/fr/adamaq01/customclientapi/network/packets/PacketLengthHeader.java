package fr.adamaq01.customclientapi.network.packets;

import java.io.DataOutputStream;
import java.io.IOException;

import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class PacketLengthHeader extends PacketHeader {
	
	private final int length;

	public PacketLengthHeader(int id, int length) {
		super(id);

		this.length = length;
	}

	public final int getLength() {
		return length;
	}

	@Override
	public void write(DataOutputStream out) throws IOException {
		PacketBuffer.writeVarInt(getLength(), out);
		PacketBuffer.writeVarInt(getId(), out);
	}

	@Override
	public String toString() {
		return "PacketLengthHeader{id=0x" + Integer.toHexString(getId()).toUpperCase() + ",length=" + getLength() + "}";
	}
}