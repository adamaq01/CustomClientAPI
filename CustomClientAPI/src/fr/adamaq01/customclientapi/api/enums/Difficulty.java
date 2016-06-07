package fr.adamaq01.customclientapi.api.enums;

public enum Difficulty {
	
	PEACEFUL(0),
	EASY(1),
	NORMAL(2),
	HARD(3);
	
	private int id;
	
	private Difficulty(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static Difficulty getById(int id) {
		for(Difficulty d : Difficulty.values()) {
			if(d.getId() == id) {
				return d;
			}
		}
		return null;
	}

}
