package fr.mael.rpg;

import java.util.ArrayList;
import java.util.List;

public class Shop {
	
	private List<ShopItem> items = new ArrayList<ShopItem>();
	
	public Shop() {
		
	}
	
	public void addItem(ShopItem item) {
		this.items.add(item);
	}
	
	public void removeItem(ShopItem item) {
		this.items.remove(item);
	}
	
	public List<ShopItem> getItems() {
		return this.items;
	}
	

}
