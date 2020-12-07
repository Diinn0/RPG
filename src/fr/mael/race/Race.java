package fr.mael.race;

public abstract class Race {

	protected String name;
	
	public Race(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}
	
}
