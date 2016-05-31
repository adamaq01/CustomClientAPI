package fr.adamaq01.customclientapi.api.server;

import fr.adamaq01.customclientapi.api.clients.MinecraftClient;
import fr.adamaq01.customclientapi.api.enums.HandshakeState;
import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.api.packets.Packets;
import fr.adamaq01.customclientapi.network.packets.handshake.serverbound.HandshakePacket;
import fr.adamaq01.customclientapi.network.packets.login.serverbound.LoginStartPacket;
import fr.adamaq01.customclientapi.utils.PacketBuffer;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerConnection extends SimpleChannelInboundHandler<Packet> {

	private MinecraftClient client;
	private String host;
	private int port;
	private Channel channel;
	private boolean ready = false;

	public ServerConnection(MinecraftClient client, String host, int port) {
		this.client = client;
		this.host = host;
		this.port = port;
		System.out.println("Salut :D");
		login();
	}

	private void login() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (ready) {
						sendPacket(new HandshakePacket(client.getVersion(), host, (short) port, HandshakeState.LOGIN));
						sendPacket(new LoginStartPacket(client.getUsername()));
						break;
					}
				}
			}
		}).start();
	}

	public void sendPacket(Packet packet) {
		if (this.channel == null) {
			return;
		}
		PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
		buf.writeVarIntToBuffer(Packets.getId(packet.getClass()));
		packet.write(buf);
		this.channel.writeAndFlush(buf).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if (future.isSuccess()) {
					System.out.println("Success");
				} else {
					System.out.println("Non-success");
				}
			}
		});

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("JE SUIS ACTIF MDLOL");
		if (this.channel != null) {
			ctx.channel().close();
			return;
		}
		this.channel = ctx.channel();
		this.ready = true;
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("JE SUIS INACTIF IXDE");

		super.channelInactive(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Packet arg1) throws Exception {

	}

}
