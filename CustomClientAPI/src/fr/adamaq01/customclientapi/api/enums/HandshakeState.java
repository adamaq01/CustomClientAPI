package fr.adamaq01.customclientapi.api.enums;

public enum HandshakeState {

	STATUS(1),
	LOGIN(2);
	
	private int state;
	
	private HandshakeState(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	
}
