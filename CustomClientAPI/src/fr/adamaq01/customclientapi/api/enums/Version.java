package fr.adamaq01.customclientapi.api.enums;

public enum Version {

	v_1_8(47, "1.8"),
	v_1_9_4(110, "1.9.4");

	private int protocolVersion;
	private String name;

	private Version(int protocolVersion, String name) {
		this.protocolVersion = protocolVersion;
		this.name = name;
	}

	public int getProtocolVersion() {
		return protocolVersion;
	}

	public String getName() {
		return name;
	}

}
