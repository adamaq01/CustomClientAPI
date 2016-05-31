package fr.adamaq01.customclientapi.api.clients;

import fr.adamaq01.customclientapi.api.enums.Version;
import fr.adamaq01.customclientapi.api.server.ServerConnection;

public abstract class MinecraftClient {

	private Version version;
	private String username;
	private String password;
	private boolean offline;

	protected MinecraftClient(Version version, String username, String password) {
		this.version = version;
		this.username = username;
		this.password = password;
		this.offline = false;
	}

	protected MinecraftClient(Version version, String username) {
		this.version = version;
		this.username = username;
		this.password = "";
		this.offline = true;
	}

	public ServerConnection connect(String host, int port) {
		return new ServerConnection(this, host, port);
	}

	public Version getVersion() {
		return version;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isOffline() {
		return offline;
	}

}
