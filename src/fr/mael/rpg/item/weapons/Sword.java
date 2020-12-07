package fr.mael.rpg.item.weapons;

public class Sword extends Weapon {
		
	public Sword(double damage) {
		super(damage, 0.5d, 1.3d);
	}
	
	public String toString() {
		return "Epée";
	}
	
	public String ascii_art() {
		return "      /| ________________\r\n" + 
				"O|===|* >________________>\r\n" + 
				"      \\|";
	}
	
}
