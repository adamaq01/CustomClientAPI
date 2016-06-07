package fr.adamaq01.customclientapi.api.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fr.adamaq01.customclientapi.api.enums.Bound;
import fr.adamaq01.customclientapi.api.enums.State;
import fr.adamaq01.customclientapi.api.server.ServerConnection;
import fr.adamaq01.customclientapi.network.packets.PacketLengthHeader;
import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class PacketsScanningUtils {

	static Packet readPackets(ServerConnection connection) {
		DataInputStream in = connection.getInputStream();
		final PacketLengthHeader header = readHeader(in);
		if (createPacket(header, Bound.CLIENT_BOUND) == null) {
			try {
				createStreams(connection);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		Packet packet = createPacket(header, Bound.CLIENT_BOUND);
		if (header instanceof PacketLengthHeader) {
			int length = ((PacketLengthHeader) header).getLength() - PacketBuffer.varIntLength(header.getId());
			final byte[] data = new byte[length];
			try {
				in.readFully(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			in = new DataInputStream(new ByteArrayInputStream(data));
		}
		packet.read(in);
		if (header instanceof PacketLengthHeader)
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			createStreams(connection);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return packet;
	}

	static PacketLengthHeader createHeader(Packet packet, byte[] data) {
		PacketLengthHeader header = new PacketLengthHeader(Packets.getId(packet.getClass()),
				PacketBuffer.varIntLength(Packets.getId(packet.getClass())) + data.length);
		if (header.getId() > 0) {
			return null;
		}
		return header;
	}

	static PacketLengthHeader readHeader(DataInputStream in) {
		int length = 0, id = 0;
		try {
			length = PacketBuffer.readVarInt(in);
			id = PacketBuffer.readVarInt(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new PacketLengthHeader(id, length);
	}

	static Packet createPacket(PacketLengthHeader header, Bound bound) {
		try {
			return Packets.getById((byte) header.getId(), State.getState(), bound).newInstance();
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	static void createStreams(ServerConnection connection) throws IOException {
		InputStream in = connection.getSocket().getInputStream();
		connection.setInputStream(new DataInputStream(in));
		OutputStream out = connection.getSocket().getOutputStream();
		connection.setOutputStream(new DataOutputStream(out));
	}

}
