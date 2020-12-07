package fr.mael.classe;

public abstract class Classe {

	protected String name;
	
	public Classe(String name) {
		this.name = name;
	}
	
	
	public String toString() {
		return this.name;
	}
}
