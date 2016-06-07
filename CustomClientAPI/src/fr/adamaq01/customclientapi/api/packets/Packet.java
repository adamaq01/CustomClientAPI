package fr.adamaq01.customclientapi.api.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class Packet {
	
	public Packet() {
	}
	
	public abstract void write(DataOutputStream out);

	public abstract void read(DataInputStream in);
	
}
