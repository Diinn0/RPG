package fr.mael.rpg.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.mael.classe.Classe;
import fr.mael.race.Race;
import fr.mael.rpg.item.Item;
import fr.mael.rpg.item.weapons.Weapon;

public class Player extends Entity {

	private String name;
	private double money = 0.0d;
	private int lvl = 0;
	private float xp = 0.0f;
	private int mana = 50;
	private int manaMax = 50;
	private int stamina = 20;
	private int staminaMax = 20;
	private Race race;
	private Classe classe;
	private Integer[] pos = new Integer[2];
	private HashMap<Integer, Item> armor = new HashMap<Integer, Item>();
	private List<Item> inventory = new ArrayList<Item>();
	public Weapon weapon = null;
	
	public Player(String name, Race race, Classe classe) {
		super(100);
		
		this.name = name;
		this.race = race;
		this.classe = classe;
		
		this.armor.put(0, null); //Helmet
		this.armor.put(1, null); //Chestplate
		this.armor.put(2, null); //Leggings
		this.armor.put(3, null); //Boots		
	}
	
	
	public Item getArmor(int slot) {
		return this.armor.get(slot);
	}
	
	public Player setArmor(int slot, Item item) {
		this.armor.replace(slot, item);
		return this;
	}
	
	public List<Item> getInventory() {
		return this.inventory;
	}
	
	public Player addInInventory(Item item) {
		this.inventory.add(item);
		return this;
	}
	
	public Player removeFromInventory(Item item) {
		this.inventory.remove(item);
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Player addMoney(double money) {
		this.money += money;
		return this;
	}
	
	public Player removeMoney(double money) {
		this.money -= money;
		return this;
	}

	public double getMoney() {
		return money;
	}
	
	public Player addLvl() {
		this.lvl++;
		return this;
	}
	
	public Player removeLvl() {
		this.lvl--;
		return this;
	}

	public int getLvl() {
		return lvl;
	}
	
	public Player addXp(double xp) {
		this.xp += xp;
		
		if (this.xp > 200 * this.lvl + 100) {
			this.addLvl();
		}
		
		return this;
	}
	
	public Player removeXp() {
		this.xp--;
		return this;
	}

	public float getXp() {
		return xp;
	}

	public int getMana() {
		return mana;
	}

	public int getManaMax() {
		return manaMax;
	}

	public int getStamina() {
		return stamina;
	}

	public int getStaminaMax() {
		return staminaMax;
	}

	public Race getRace() {
		return race;
	}

	public Classe getClasse() {
		return classe;
	}
	
	public void setPos(int x, int y) {
		this.pos[0] = x;
		this.pos[1] = y;
	}
	
	public int getPosX() {
		return this.pos[0];
	}
	
	public int getPosY() {
		return this.pos[1];
	}
	
	public Integer[] getPos() {
		return  this.pos;
	}
	
	
}
