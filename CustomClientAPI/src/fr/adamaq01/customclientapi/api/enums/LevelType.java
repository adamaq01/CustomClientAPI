package fr.adamaq01.customclientapi.api.enums;

public enum LevelType {
	
	DEFAULT("default"),
	FLAT("flat"),
	LARGEBIOMES("largeBiomes"),
	AMPLIFIED("amplified"),
	DEFAULT_1_1("default_1_1");
	
	private String name;
	
	private LevelType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static LevelType getByName(String name) {
		for(LevelType l : LevelType.values()) {
			if(l.getName() == name) {
				return l;
			}
		}
		return null;
	}

}
