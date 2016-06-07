package fr.adamaq01.customclientapi.api.server;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import fr.adamaq01.customclientapi.api.clients.MinecraftClient;
import fr.adamaq01.customclientapi.api.entities.OurPlayer;
import fr.adamaq01.customclientapi.api.enums.HandshakeState;
import fr.adamaq01.customclientapi.api.enums.State;
import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.api.packets.Packets;
import fr.adamaq01.customclientapi.network.packets.PacketHeader;
import fr.adamaq01.customclientapi.network.packets.PacketLengthHeader;
import fr.adamaq01.customclientapi.network.packets.handshake.serverbound.HandshakePacket;
import fr.adamaq01.customclientapi.network.packets.login.serverbound.LoginStartPacket;
import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class ServerConnection {

	private MinecraftClient client;
	private String host;
	private int port;
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private OurPlayer player;
	private UUID uuid;
	private String name;

	public ServerConnection(MinecraftClient client, String host, int port) {
		this.client = client;
		this.host = host;
		this.port = port;
		System.out.println("Client on !");
		try {
			socket = new Socket(host, port);
			ConnectionUtils.createStreams(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		State.setState(State.HANDSHAKE);
		System.out.println("Sending packets... !");
		login();
		System.out.println("Packets sent !");
		new ReadPacket(ServerConnection.this).run();
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
			}
		}, 0, 1);
	}

	void login() {
		sendPacket(new HandshakePacket(client.getVersion(), host, (short) port, HandshakeState.LOGIN));
		State.setState(State.LOGIN);
		sendPacket(new LoginStartPacket(client.getUsername()));
	}

	public void sendPacket(Packet packet) {
		try {
			ConnectionUtils.createStreams(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		packet.write(new DataOutputStream(byteOutputStream));
		byte[] data = byteOutputStream.toByteArray();
		DataOutputStream out = outputStream;
		PacketHeader header = createHeader(packet, data);
		try {
			header.write(out);
			out.write(data);
			out.flush();
			System.out.println("Sent packet: " + packet.getClass().getSimpleName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public OurPlayer getOurPlayer() {
		return player;
	}

	public MinecraftClient getClient() {
		return client;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public DataInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(DataInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public DataOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(DataOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClient(MinecraftClient client) {
		this.client = client;
	}

	public void setOurPlayer(OurPlayer player) {
		this.player = player;
	}

	private PacketLengthHeader createHeader(Packet packet, byte[] data) {
		PacketLengthHeader header = new PacketLengthHeader(Packets.getId(packet.getClass()),
				PacketBuffer.varIntLength(Packets.getId(packet.getClass())) + data.length);
		if (header.getId() > 0) {
			return null;
		}
		return header;
	}

}
