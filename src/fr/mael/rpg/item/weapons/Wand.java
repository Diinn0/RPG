package fr.mael.rpg.item.weapons;

public class Wand extends Weapon {

	public Wand() {
		super(10, 1.0f, 1.0f);
	}
	
	public String toString() {
		return "Bâton";
	}

	@Override
	public String ascii_art() {
		return null;
	}

}
