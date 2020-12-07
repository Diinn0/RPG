package fr.mael.rpg.item.weapons;

public class Spear extends Weapon {
		
	public Spear(double damage) {
		super(damage, 0.2d, 1.1d);
	}
	
	public String toString() {
		return "Lance";
	}
	
	public String ascii_art() {
		return "";
	}
	
}
