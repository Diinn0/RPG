package fr.mael.rpg.entity.Monster;

import fr.mael.rpg.entity.Entity;
import fr.mael.rpg.item.weapons.Weapon;

public class Monster extends Entity {

	public Weapon hand; 
	
	public Monster(double health) {
		super(health);
	}
	
}
