package fr.mael.rpg.entity.Obstacle;

import fr.mael.rpg.entity.Entity;

public class Obstacle extends Entity{

	private boolean indestructible = false;
	public Obstacle(double health) {
		super(health);
	}
	
	public void setIndestructible() {
		this.indestructible = true;
	}
	
	public boolean isIndestructible() {
		return this.indestructible;
	}

}
