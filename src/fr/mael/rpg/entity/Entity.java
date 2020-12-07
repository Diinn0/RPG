package fr.mael.rpg.entity;

public abstract class Entity {

	protected double health;
	
	public Entity(double health) {
		this.health = health;
	}
	
	public void damage(double damage) {	
		this.health -= damage;
	}

	public double getHealth() {
		return health;
	}
	
	
}
