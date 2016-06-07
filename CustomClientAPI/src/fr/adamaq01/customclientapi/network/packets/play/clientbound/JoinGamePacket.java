package fr.adamaq01.customclientapi.network.packets.play.clientbound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import fr.adamaq01.customclientapi.api.enums.Difficulty;
import fr.adamaq01.customclientapi.api.enums.Dimension;
import fr.adamaq01.customclientapi.api.enums.Gamemode;
import fr.adamaq01.customclientapi.api.enums.LevelType;
import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class JoinGamePacket extends Packet {

	private int entityId;
	private Gamemode gamemode;
	private Dimension dimension;
	private Difficulty difficulty;
	private int maxPlayers;
	private LevelType levelType;
	private boolean reducedDebugInfo;

	public JoinGamePacket() {
	}

	public JoinGamePacket(int entityId, Gamemode gamemode, Dimension dimension, Difficulty difficulty, int maxPlayers,
			LevelType levelType, boolean reducedDebugInfo) {
		this.entityId = entityId;
		this.gamemode = gamemode;
		this.dimension = dimension;
		this.difficulty = difficulty;
		this.maxPlayers = maxPlayers;
		this.levelType = levelType;
		this.reducedDebugInfo = reducedDebugInfo;
	}

	@Override
	public void write(DataOutputStream out) {
	}

	@Override
	public void read(DataInputStream in) {
		try {
			this.entityId = PacketBuffer.readVarInt(in);
			this.gamemode = Gamemode.getById(PacketBuffer.readVarInt(in));
			this.dimension = Dimension.getById(PacketBuffer.readVarInt(in));
			this.difficulty = Difficulty.getById(PacketBuffer.readVarInt(in));
			this.maxPlayers = PacketBuffer.readVarInt(in);
			this.levelType = LevelType.getByName(PacketBuffer.readString(in));
			this.reducedDebugInfo = in.readBoolean();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getEntityId() {
		return entityId;
	}

	public Gamemode getGamemode() {
		return gamemode;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public LevelType getLevelType() {
		return levelType;
	}

	public boolean isReducedDebugInfo() {
		return reducedDebugInfo;
	}

}
