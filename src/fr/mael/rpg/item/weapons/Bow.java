package fr.mael.rpg.item.weapons;

public class Bow extends Weapon {
		
	public Bow(double damage) {
		super(damage, 0.2d, 0.8d);
	}
	
	public String toString() {
		return "Arc";
	}
	
	public String ascii_art() {
		return "   (\r\n" + 
				"    \\\r\n" + 
				"     )\r\n" + 
				"##-------->\r\n" + 
				"     )\r\n" + 
				"    /\r\n" + 
				"   (";
	}
}
