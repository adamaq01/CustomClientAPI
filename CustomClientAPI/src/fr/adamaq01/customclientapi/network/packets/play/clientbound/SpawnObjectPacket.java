package fr.adamaq01.customclientapi.network.packets.play.clientbound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.UUID;

import fr.adamaq01.customclientapi.api.packets.Packet;

public class SpawnObjectPacket extends Packet {

	private int entityId;
	private UUID uuid;
	private int type;
	private double x, y, z, yaw, pitch;
	private int data;
	private double velocityX, velocityY, velocityZ;

	public SpawnObjectPacket() {
	}

	public SpawnObjectPacket(int entityId, UUID uuid, byte type, double x, double y, double z) {
		this.entityId = entityId;
		this.uuid = uuid;
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void write(DataOutputStream out) {
	}

	@Override
	public void read(DataInputStream in) {
	}

	public int getEntityId() {
		return entityId;
	}

	public UUID getUuid() {
		return uuid;
	}

	public int getType() {
		return type;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public double getYaw() {
		return yaw;
	}

	public double getPitch() {
		return pitch;
	}

	public int getData() {
		return data;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public double getVelocityZ() {
		return velocityZ;
	}

}
