package fr.mael.rpg;

import fr.mael.rpg.item.Item;

public class ShopItem {

	private Item item;
	private double price;
	
	public ShopItem(Item item, double price) {
		this.item = item;
		this.price = price;
	}

	public Item getItem() {
		return item;
	}

	public double getPrice() {
		return price;
	}
	
	public String toString() {
		return item.toString() + " - " + price + "$";
	}
	
}
