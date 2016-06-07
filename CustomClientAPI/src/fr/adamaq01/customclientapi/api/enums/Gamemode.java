package fr.adamaq01.customclientapi.api.enums;

public enum Gamemode {

	SURVIVAL(0),
	CREATIVE(1),
	ADVENTURE(2),
	SPECTATOR(3);
	
	private int id;
	
	private Gamemode(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static Gamemode getById(int id) {
		for(Gamemode g : Gamemode.values()) {
			if(g.getId() == id) {
				return g;
			}
		}
		return null;
	}

}
