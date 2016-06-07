package fr.adamaq01.customclientapi.api.enums;

public enum Dimension {
	
	NETHER(-1),
	OVERWORLD(0),
	END(1);
	
	private int id;
	
	private Dimension(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static Dimension getById(int id) {
		for(Dimension d : Dimension.values()) {
			if(d.getId() == id) {
				return d;
			}
		}
		return null;
	}

}
