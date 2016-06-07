package fr.adamaq01.customclientapi.api.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectionUtils {

	static void createStreams(ServerConnection connection) throws IOException {
		InputStream in = connection.getSocket().getInputStream();
		connection.setInputStream(new DataInputStream(in));
		OutputStream out = connection.getSocket().getOutputStream();
		connection.setOutputStream(new DataOutputStream(out));
	}

}
