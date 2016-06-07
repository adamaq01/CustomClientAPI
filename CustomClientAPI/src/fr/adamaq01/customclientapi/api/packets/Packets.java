package fr.adamaq01.customclientapi.api.packets;

import java.util.HashMap;

import com.google.common.collect.Maps;

import fr.adamaq01.customclientapi.api.enums.Bound;
import fr.adamaq01.customclientapi.api.enums.State;
import fr.adamaq01.customclientapi.network.packets.handshake.serverbound.HandshakePacket;
import fr.adamaq01.customclientapi.network.packets.login.clientbound.DisconnectPacket;
import fr.adamaq01.customclientapi.network.packets.login.clientbound.EncryptionRequestPacket;
import fr.adamaq01.customclientapi.network.packets.login.clientbound.LoginSuccesPacket;
import fr.adamaq01.customclientapi.network.packets.login.clientbound.SetCompressionPacket;
import fr.adamaq01.customclientapi.network.packets.login.serverbound.EncryptionResponsePacket;
import fr.adamaq01.customclientapi.network.packets.login.serverbound.LoginStartPacket;
import fr.adamaq01.customclientapi.network.packets.play.clientbound.JoinGamePacket;
import fr.adamaq01.customclientapi.network.packets.play.clientbound.SpawnObjectPacket;

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
		register(HandshakePacket.class, 0x00, HANDSAHKING_SERVERBOUND);

		// Login
		register(LoginStartPacket.class, 0x00, LOGIN_SERVERBOUND);
		register(EncryptionResponsePacket.class, 0x01, LOGIN_SERVERBOUND);
		register(DisconnectPacket.class, 0x00, LOGIN_CLIENTBOUND);
		register(EncryptionRequestPacket.class, 0x01, LOGIN_CLIENTBOUND);
		register(LoginSuccesPacket.class, 0x02, LOGIN_CLIENTBOUND);
		register(SetCompressionPacket.class, 0x03, LOGIN_CLIENTBOUND);

		// Play
		register(SpawnObjectPacket.class, 0x00, PLAY_CLIENTBOUND);
		register(JoinGamePacket.class, 0x23, PLAY_CLIENTBOUND);
	}

	public static void register(Class<? extends Packet> packet, int id,
			HashMap<Integer, Class<? extends Packet>> type) {
		type.put(id, packet);
		PACKETS.put(packet, id);
	}

	public static Class<? extends Packet> getById(int id, State state, Bound bound) {
		switch (bound) {
		case CLIENT_BOUND:
			switch (state) {
			case HANDSHAKE:
				return null;
			case LOGIN:
				return LOGIN_CLIENTBOUND.get(id);
			case PLAY:
				return PLAY_CLIENTBOUND.get(id);
			case STATUS:
				return STATUS_CLIENTBOUND.get(id);
			default:
				return null;
			}
		case SERVER_BOUND:
			switch (state) {
			case HANDSHAKE:
				return HANDSAHKING_SERVERBOUND.get(id);
			case LOGIN:
				return LOGIN_SERVERBOUND.get(id);
			case PLAY:
				return PLAY_SERVERBOUND.get(id);
			case STATUS:
				return STATUS_SERVERBOUND.get(id);
			default:
				return null;
			}
		default:
			return null;
		}
	}

	public static int getId(Class<? extends Packet> packet) {
		return PACKETS.get(packet).byteValue();
	}

}
