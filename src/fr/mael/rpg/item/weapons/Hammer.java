package fr.mael.rpg.item.weapons;

public class Hammer extends Weapon {
	
	public Hammer(double damage) {
		super(damage, 2.0d, 1.0d);
	}
	
	public String toString() {
		return "Marteau";
	}
	
	public String ascii_art() {
		return "";
	}
	
}
