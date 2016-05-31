package fr.adamaq01.customclientapi.api.packets;

import java.util.HashMap;

import com.google.common.collect.Maps;

import fr.adamaq01.customclientapi.network.packets.handshake.serverbound.HandshakePacket;
import fr.adamaq01.customclientapi.network.packets.login.serverbound.LoginStartPacket;

public class Packets {

	public static final HashMap<Class<? extends Packet>, Integer> PACKETS = Maps.newHashMap();
	public static final HashMap<Integer, Class<? extends Packet>> HANDSAHKING_SERVERBOUND = Maps.newHashMap();
	public static final HashMap<Integer, Class<? extends Packet>> PLAY_CLIENTBOUND = Maps.newHashMap();
	public static final HashMap<Integer, Class<? extends Packet>> PLAY_SERVERBOUND = Maps.newHashMap();
	public static final HashMap<Integer, Class<? extends Packet>> STATUS_CLIENTBOUND = Maps.newHashMap();
	public static final HashMap<Integer, Class<? extends Packet>> STATUS_SERVERBOUND = Maps.newHashMap();
	public static final HashMap<Integer, Class<? extends Packet>> LOGIN_CLIENTBOUND = Maps.newHashMap();
	public static final HashMap<Integer, Class<? extends Packet>> LOGIN_SERVERBOUND = Maps.newHashMap();

	static {
		// Handshake
		register(HandshakePacket.class, 0, HANDSAHKING_SERVERBOUND);

		// Login
		register(LoginStartPacket.class, 0, LOGIN_SERVERBOUND);
	}

	public static void register(Class<? extends Packet> packet, int id,
			HashMap<Integer, Class<? extends Packet>> type) {
		type.put(id, packet);
		PACKETS.put(packet, id);
	}

	public static Class<? extends Packet> getById(int id, HashMap<Integer, Class<? extends Packet>> type) {
		return type.get(id);
	}

	public static int getId(Class<? extends Packet> packet) {
		return PACKETS.get(packet);
	}

}
