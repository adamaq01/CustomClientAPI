package fr.adamaq01.customclientapi.api.packets;

import fr.adamaq01.customclientapi.utils.PacketBuffer;

public abstract class Packet {

	public abstract void write(PacketBuffer out);

	public abstract void read(PacketBuffer in);
	
}
