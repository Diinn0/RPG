package fr.mael.rpg.item.weapons;

public class Axe extends Weapon {
		
	public Axe(double damage) {
		super(damage, 1.2, 0.8d);
	}
	
	public String toString() {
		return "Hache";
	}
	
	public String ascii_art() {
		return "  ,:\\      /:.\r\n" + 
				" //  \\_()_/  \\\\\r\n" + 
				"||   |    |   ||\r\n" + 
				"||   |    |   ||\r\n" + 
				"||   |____|   ||\r\n" + 
				" \\\\  / || \\  //\r\n" + 
				"  `:/  ||  \\;'\r\n" + 
				"       ||\r\n" + 
				"       ||\r\n" + 
				"       XX\r\n" + 
				"       XX\r\n" + 
				"       XX\r\n" + 
				"       XX\r\n" + 
				"       OO\r\n" + 
				"       `'";
	}
	
}
