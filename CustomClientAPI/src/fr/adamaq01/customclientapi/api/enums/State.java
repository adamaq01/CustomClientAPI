package fr.adamaq01.customclientapi.api.enums;

public enum State {
	
	HANDSHAKE,
	STATUS,
	LOGIN,
	PLAY;
	
	private static State state;
	
	public static void setState(State state) {
		State.state = state;
		System.out.println("Changed State to: " + state);
	}
	
	public static State getState() {
		return state;
	}

}
