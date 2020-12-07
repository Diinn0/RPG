package fr.mael.rpg.item.weapons;

import fr.mael.rpg.entity.Monster.Monster;
import fr.mael.rpg.entity.Obstacle.Obstacle;
import fr.mael.rpg.item.Item;

public abstract class Weapon extends Item {
	
	protected double obstacle;
	protected double monster;
	protected String name;
	protected double damage;
	
	public Weapon(String name, double damage, double obstacle, double monster) {
		this.name = name;
		this.damage = damage;
		this.obstacle = obstacle;
		this.monster = monster;
	}
	
	public Weapon(double damage, double obstacle, double monster) {
		this.name = "";
		this.damage = damage;
		this.obstacle = obstacle;
		this.monster = monster;
	}
	
	public double attack(Monster monster) {
		return this.damage * this.monster;
	}
	
	public double attack(Obstacle obstacle) {
		return this.damage * this.obstacle;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	public abstract String ascii_art();
}
