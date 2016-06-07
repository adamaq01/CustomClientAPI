package fr.adamaq01.customclientapi.network.packets.login.serverbound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import fr.adamaq01.customclientapi.api.packets.Packet;
import fr.adamaq01.customclientapi.utils.EncryptionUtil;
import fr.adamaq01.customclientapi.utils.PacketBuffer;

public class EncryptionResponsePacket extends Packet {

	private final SecretKey secretKey;
	private final PublicKey publicKey;
	private final byte[] sharedSecret, verifyToken;

	public EncryptionResponsePacket(SecretKey secretKey, PublicKey publicKey, byte[] verifyToken) {
		this.secretKey = secretKey;
		this.publicKey = publicKey;

		try {
			sharedSecret = EncryptionUtil.cipher(1, publicKey, secretKey.getEncoded());
			this.verifyToken = EncryptionUtil.cipher(1, publicKey, verifyToken);
		} catch (GeneralSecurityException exception) {
			throw new Error("Unable to cipher", exception);
		}
	}

	@Override
	public void write(DataOutputStream out) {
		try {
			PacketBuffer.writeArray(sharedSecret, out);
			PacketBuffer.writeArray(verifyToken, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void read(DataInputStream in) {
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public byte[] getSharedSecret() {
		return sharedSecret;
	}

	public byte[] getVerifyToken() {
		return verifyToken;
	}

}
