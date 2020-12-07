package fr.mael.rpg;

import java.util.Random;

import fr.mael.rpg.entity.Entity;
import fr.mael.rpg.entity.Player;
import fr.mael.rpg.entity.Monster.Gobelin;
import fr.mael.rpg.entity.Monster.Monster;
import fr.mael.rpg.entity.Obstacle.Montain;
import fr.mael.rpg.entity.Obstacle.Rock;
import fr.mael.rpg.entity.Obstacle.Tree;
import fr.mael.rpg.item.weapons.Sword;

public class Map {

	private int width;
	private int height;
	private Entity[][] map;
	private Random rand = new Random();
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		this.map = new Entity[height][width];
		this.createObstacle();
		this.fillMapWithEntity();
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Entity getEntityAtPos(int x, int y) {
		return this.map[y][x];
	}
	
	public void setEntityAtPos(Entity entity, int x, int y) {
		this.map[y][x] = entity;
	}
	
	private void createObstacle() {
		//Montains
		int initPosX = rand.nextInt(this.width);
		int initPosY = rand.nextInt(this.height);
		this.map[initPosY][initPosX] = new Montain();
		int countMontain = rand.nextInt(25);
		for(int i = 0; i < countMontain; i++) {
			if (rand.nextBoolean()) {
				if (rand.nextBoolean()) {
					if (initPosX + 1 <= this.width - 1) {
						initPosX++;
					}					
				} else {
					if (initPosX - 1 >= 0) {
						initPosX--;
					}
				}
			} else {
				if (rand.nextBoolean()) {
					if (initPosY + 1 <= this.height - 1) {
						initPosY++;
					}
				} else {
					if (initPosY - 1 >= 0) {
						initPosY--;
					}
				}
			}
			if ((initPosY != map.length - 1 && initPosX != 0) || (initPosX != map[initPosY].length - 1 && initPosY != 0)) {
				this.map[initPosY][initPosX] = new Montain();
			}
		}
		
		//Forest
		
		int nbForest = rand.nextInt(5);
		for (int j = 0; j < nbForest; j++) {
			initPosX = rand.nextInt(this.width);
			initPosY = rand.nextInt(this.height);
			this.map[initPosY][initPosX] = new Tree(10 + rand.nextInt(10));
			int countTree = rand.nextInt(25);
			for(int i = 0; i < countTree; i++) {
				if (rand.nextBoolean()) {
					if (rand.nextBoolean()) {
						if (initPosX + 1 <= this.width - 1) {
							initPosX++;
						}					
					} else {
						if (initPosX - 1 >= 0) {
							initPosX--;
						}
					}
				} else {
					if (rand.nextBoolean()) {
						if (initPosY + 1 <= this.height - 1) {
							initPosY++;
						}
					} else {
						if (initPosY - 1 >= 0) {
							initPosY--;
						}
					}
				}
				if ((initPosY != map.length - 1 && initPosX != 0) || (initPosX != map[initPosY].length - 1 && initPosY != 0)) {
					if (this.map[initPosY][initPosX] == null)
						this.map[initPosY][initPosX] = new Tree(10 + rand.nextInt(10));
				}
			}
		}
		
		//Arbre / rocher
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if ((i != map.length - 1 && j != 0) || (j != map[i].length - 1 && i != 0)) {
					if (rand.nextBoolean() && map[i][j] == null) {
						int test = rand.nextInt(100);
						if (test < 15) {
							map[i][j] = new Tree(10 + rand.nextInt(10));
						} else if (test < 25) {
							map[i][j] = new Rock(10 + rand.nextInt(10));
						}
					}
				}
			}
		}
	}
	
	private void fillMapWithEntity() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (rand.nextBoolean()) {
					int test = rand.nextInt(100);
					if (test < 15) {
						Gobelin gobelin = new Gobelin(10 + rand.nextInt(10));
						gobelin.hand = new Sword(5 + rand.nextInt(15));
						map[i][j] = gobelin;
						
					}
				}
			}
		}	
	}
	
	public void draw(Player player) {
		for (int i = 0; i < this.getWidth(); i++) {
			System.out.print("=");
		}		
		System.out.print(System.lineSeparator());
		System.out.println(String.format("%s - PV: %s - %s$", player.getName(), player.getHealth(), player.getMoney()));
		for (int i = 0; i < this.getWidth(); i++) {
			System.out.print("=");
		}		
		System.out.print(System.lineSeparator());
		for (int height = 0; height < map.length; height++) {
			for (int width = 0; width < map[height].length; width++) {
				if (player.getPosX() == width && player.getPosY() == height) {
					System.out.print("P");
				} else {
					if (map[height][width] == null || map[height][width] instanceof Monster) {
						System.out.print(" ");
					} else if (map[height][width] instanceof Montain) {
						System.out.print("M");
					} else if (map[height][width] instanceof Tree) {
						System.out.print("T");
					} else if (map[height][width] instanceof Rock) {
						System.out.print("R");
					}
				}
			}
			System.out.print(System.lineSeparator());
		}
		for (int i = 0; i < this.getWidth(); i++) {
			System.out.print("=");
		}	
		System.out.print(System.lineSeparator());
	}
	
}
