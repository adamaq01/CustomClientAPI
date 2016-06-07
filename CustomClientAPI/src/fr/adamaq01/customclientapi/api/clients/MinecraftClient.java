package fr.adamaq01.customclientapi.api.clients;

import fr.adamaq01.customclientapi.api.enums.Version;
import fr.adamaq01.customclientapi.api.server.ServerConnection;
import fr.adamaq01.customclientapi.network.auth.LegacySession;
import fr.adamaq01.customclientapi.network.auth.OfflineSession;
import fr.adamaq01.customclientapi.network.auth.Session;

public abstract class MinecraftClient {

	private Version version;
	private String username;
	private String password;
	private boolean offline;
	private Session session;

	protected MinecraftClient(Version version, String username, String password) {
		this.version = version;
		this.username = username;
		this.password = password;
		this.offline = false;
		this.session = new LegacySession(username, password, version.getName());
	}

	protected MinecraftClient(Version version, String username) {
		this.version = version;
		this.username = username;
		this.password = "";
		this.offline = true;
		this.session = new OfflineSession(username);
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

	public Session getSession() {
		return session;
	}

}
