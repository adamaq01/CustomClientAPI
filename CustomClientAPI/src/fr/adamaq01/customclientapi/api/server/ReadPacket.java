package fr.adamaq01.customclientapi.api.server;

import java.io.IOException;
import java.security.GeneralSecurityException;

import fr.adamaq01.customclientapi.api.entities.OurPlayer;
import fr.adamaq01.customclientapi.api.enums.State;
import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.api.packets.PacketScanner;
import fr.adamaq01.customclientapi.network.auth.AuthenticationException;
import fr.adamaq01.customclientapi.network.auth.LegacyAuthService;
import fr.adamaq01.customclientapi.network.packets.login.clientbound.DisconnectPacket;
import fr.adamaq01.customclientapi.network.packets.login.clientbound.EncryptionRequestPacket;
import fr.adamaq01.customclientapi.network.packets.login.clientbound.LoginSuccesPacket;
import fr.adamaq01.customclientapi.network.packets.login.clientbound.SetCompressionPacket;
import fr.adamaq01.customclientapi.network.packets.login.serverbound.EncryptionResponsePacket;
import fr.adamaq01.customclientapi.network.packets.play.clientbound.JoinGamePacket;
import fr.adamaq01.customclientapi.utils.EncryptionUtil;

public class ReadPacket {

	private ServerConnection server;

	public ReadPacket(ServerConnection server) {
		this.server = server;
	}

	public void run() {
		Packet packet = new PacketScanner(server).nextPacket();
		if (packet instanceof SetCompressionPacket) {
			server.sendPacket(new SetCompressionPacket(-1));
		} else if (packet instanceof EncryptionRequestPacket) {
			if (!server.getClient().isOffline()) {
				LegacyAuthService authService = new LegacyAuthService();
				authService.validateSession(server.getClient().getSession());
				try {
					authService.login(server.getClient().getUsername(), server.getClient().getPassword());
				} catch (AuthenticationException | IOException e) {
					e.printStackTrace();
				}
			}
			try {
				server.sendPacket(new EncryptionResponsePacket(EncryptionUtil.generateSecretKey(),
						EncryptionUtil.generatePublicKey(((EncryptionRequestPacket) packet).getPublicKey()),
						((EncryptionRequestPacket) packet).getVerifyToken()));
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			}
		} else if (packet instanceof LoginSuccesPacket) {
			server.setUuid(((LoginSuccesPacket) packet).getUUID());
			server.setName(((LoginSuccesPacket) packet).getName());
			State.setState(State.PLAY);
		} else if (packet instanceof JoinGamePacket) {
			server.setOurPlayer(
					new OurPlayer(((JoinGamePacket) packet).getEntityId(), server.getUuid(), server.getName()));
		} else if (packet instanceof DisconnectPacket) {
			System.out.println(((DisconnectPacket) packet).getChat());
		}
	}

}
