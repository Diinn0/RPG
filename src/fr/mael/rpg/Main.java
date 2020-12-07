package fr.mael.rpg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import fr.mael.classe.Classe;
import fr.mael.classe.Paladin;
import fr.mael.classe.Sorcerer;
import fr.mael.classe.Warrior;
import fr.mael.race.Dwarf;
import fr.mael.race.Elf;
import fr.mael.race.Humain;
import fr.mael.race.Orc;
import fr.mael.race.Race;
import fr.mael.rpg.entity.Entity;
import fr.mael.rpg.entity.Player;
import fr.mael.rpg.entity.Monster.Gobelin;
import fr.mael.rpg.entity.Monster.Monster;
import fr.mael.rpg.entity.Obstacle.Obstacle;
import fr.mael.rpg.item.Item;
import fr.mael.rpg.item.weapons.Axe;
import fr.mael.rpg.item.weapons.Bow;
import fr.mael.rpg.item.weapons.Hammer;
import fr.mael.rpg.item.weapons.Spear;
import fr.mael.rpg.item.weapons.Sword;
import fr.mael.rpg.item.weapons.Weapon;

public class Main {

	private static Random rand = new Random();
	
	public static void main(String[] args) {

		List<Race> races = new ArrayList<Race>();
		races.add(new Dwarf());
		races.add(new Elf());
		races.add(new Humain());
		races.add(new Orc());

		List<Classe> classes = new ArrayList<Classe>();
		classes.add(new Paladin());
		classes.add(new Sorcerer());
		classes.add(new Warrior());

		List<String> actions = new ArrayList<String>();
		actions.add("Inventaire");
		actions.add("Detruire");
		actions.add("Acheter des objets");

		List<String> directions = new ArrayList<String>();
		directions.add("Haut");
		directions.add("Bas");
		directions.add("Droite");
		directions.add("Gauche");
		
		List<String> combat = new ArrayList<String>();
		combat.add("Combattre");
		combat.add("Fuir");

		Player player = null;
		Map map = new Map(40, 10);

		Scanner scanner = new Scanner(System.in);

		// Création du personnage
		if (player == null) {
			System.out.print("Nom du personnage: ");
			String name = scanner.next();
			Race race = (Race) Main.askQuestionFromList(races, "Choissisez votre race", scanner);
			Classe classe = (Classe) Main.askQuestionFromList(classes, "Choissisez votre classe", scanner);

			player = new Player(name, race, classe);
			player.setPos(0, 9);
			player.addMoney(20.0d);
		}

		// Boucle de jeu
		String input = "";
		int lastPosX = player.getPosX();
		int lastPosY = player.getPosY();
		do {
			try {
				Entity entity = map.getEntityAtPos(player.getPosX(), player.getPosY());
				if (entity != null && entity instanceof Monster) {
					int answer = Main.askQuestionFromStringList(combat, "Que voulez vous faire ?", scanner);
					
					switch (answer) {
						case 1:
							Main.combatWithMonster(player, (Monster) entity, scanner);
							break;
						case 2:
							player.setPos(lastPosX, lastPosY);
							System.out.println("Vous avez fuit le combat");
							break;
					}
					
				}
				
				if (player.getHealth() <= 0) {
					System.out.println("Vous êtes mort");
					System.out.println("Game Over");
					return;
				}
				
				map.draw(player);
				
				if (player.getPosX() == map.getWidth() - 1 && player.getPosY() == 0) {
					System.out.println("Merci d'avoir joué");
					return;
				}
				
				lastPosX = player.getPosX();
				lastPosY = player.getPosY();
				input = scanner.next();
				switch (input) {
				case "z":
					if (player.getPosY() > 0) {
						if (!(map.getEntityAtPos(player.getPosX(), player.getPosY() - 1) instanceof Obstacle)) {
							player.setPos(player.getPosX(), player.getPosY() - 1);
						}
					}
					break;
				case "s":
					if (player.getPosY() < map.getHeight() - 1) {
						if (!(map.getEntityAtPos(player.getPosX(), player.getPosY() + 1) instanceof Obstacle)) {
							player.setPos(player.getPosX(), player.getPosY() + 1);
						}
					}
					break;
				case "q":
					if (player.getPosX() > 0) {
						if (!(map.getEntityAtPos(player.getPosX() - 1, player.getPosY()) instanceof Obstacle)) {
							player.setPos(player.getPosX() - 1, player.getPosY());
						}
					}
					break;
				case "d":
					if (player.getPosX() < map.getWidth() - 1) {
						if (!(map.getEntityAtPos(player.getPosX() + 1, player.getPosY()) instanceof Obstacle)) {
							player.setPos(player.getPosX() + 1, player.getPosY());
						}
					}
					break;
				case "a":
					int action = Main.askQuestionFromStringList(actions, "Choisissez une action", scanner);
					switch (action) {
						case 1:
							Main.showInventory(player, scanner);
							break;
						case 2:
							map.draw(player);
							Main.destroyObstacle(directions, scanner, player, map);
							break;
						case 3:
							Main.showShop(player, scanner);
							break;
					}

					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (input != "l");
	}
	
	private static Shop createShop() {
		Shop shop = new Shop();

		for (int i = 0; i < 4; i++) {
			double damage = 5 + rand.nextInt(10);
			double price = 1.25D * damage;

			switch (rand.nextInt(5)) {
			case 0:
				shop.addItem(new ShopItem(new Hammer(damage), price));
				break;
			case 1:
				shop.addItem(new ShopItem(new Spear(damage), price));
				break;
			case 2:
				shop.addItem(new ShopItem(new Axe(damage), price));
				break;
			case 3:
				shop.addItem(new ShopItem(new Bow(damage), price));
				break;
			case 4:
				shop.addItem(new ShopItem(new Sword(damage), price));
				break;
			}
		}
		return shop;
	}
	
	private static void showShop(Player player, Scanner scanner) {
		Shop shop = Main.createShop();
		
		ShopItem shopItem = (ShopItem) Main.askQuestionFromList(shop.getItems(), "Que voulez vous acheter", scanner);
		if (player.getMoney() >= shopItem.getPrice()) {
			player.removeMoney(shopItem.getPrice());
			player.addInInventory(shopItem.getItem());
			shop.removeItem(shopItem);
		} else {
			System.out.println("Vous n'avez pas assez d'argent");
		}
		
	}
	
	private static void showInventory(Player player, Scanner scanner) {
		System.out.println("Votre armure:");
		if (player.weapon == null) {
			System.out.println("Main: Vide");
		} else {
			System.out.println("Main: " + player.weapon.toString());
		}
		if (player.getArmor(0) == null) {
			System.out.println("Casque: Vide");
		} else {
			System.out.println("Casque: " + player.getArmor(0).toString());
		}
		if (player.getArmor(1) == null) {
			System.out.println("Plastron: Vide");
		} else {
			System.out.println("Plastron: " + player.getArmor(1).toString());
		}
		if (player.getArmor(2) == null) {
			System.out.println("Pantalon: Vide");
		} else {
			System.out.println("Pantalon: " + player.getArmor(2).toString());
		}
		if (player.getArmor(3) == null) {
			System.out.println("Bottes: Vide");
		} else {
			System.out.println("Bottes: " + player.getArmor(3).toString());
		}
			
		if (!player.getInventory().isEmpty()) {
			System.out.println("Votre inventaire: ");
			
			for (Item item : player.getInventory()) {
				System.out.println(" - " + item.toString());
			}
		} else {
			System.out.println("Votre inventaire est vide");
		}
		
		List<String> actions = new ArrayList<String>();
		actions.add("Equiper/Déséquiper");
		actions.add("Fermer l'inventaire");
		
		List<String> slots = new ArrayList<String>();
		slots.add("Arme");
		slots.add("Casque");
		slots.add("Plastron");
		slots.add("Pantalon");
		slots.add("Bottes");
		
		int action = Main.askQuestionFromStringList(actions, "Que voulez-vous faire ?", scanner);
		Item item = null;
		switch (action) {
			case 1:
				item = Main.askQuestionFromItemList(player.getInventory(), "Que voulez-vous équiper ?", scanner);
				if (item instanceof Weapon) {
					int slot = Main.askQuestionFromStringList(slots, "Ou voulez-vous équiper votre item ?", scanner);
					switch (slot) {
						case 1:
							if (player.weapon != null)
								player.addInInventory(player.weapon);
							player.weapon = (Weapon) item;
							player.removeFromInventory(item);
							break;
						case 2:
							if (player.getArmor(0) != null)
								player.addInInventory(player.getArmor(0));
							player.setArmor(0, item);
							player.removeFromInventory(item);
							break;
						case 3:
							if (player.getArmor(1) != null)
								player.addInInventory(player.getArmor(1));
							player.setArmor(1, item);
							player.removeFromInventory(item);
							break;
						case 4:
							if (player.getArmor(2) != null)
								player.addInInventory(player.getArmor(2));
							player.setArmor(2, item);
							player.removeFromInventory(item);
							break;
						case 5:
							if (player.getArmor(3) != null)
								player.addInInventory(player.getArmor(3));
							player.setArmor(3, item);
							player.removeFromInventory(item);
							break;
					}
				} else {
					System.out.println("Vous ne pouvez pas équiper cet item");
				}
				break;
			case 2:
				return;
		}
		
	}
	
	private static void combatWithMonster(Player player, Monster monster, Scanner scanner) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		List<String> action = new ArrayList<String>();
		action.add("Attaquer");
		action.add("Regarder son inventaire");
		
		if (rand.nextBoolean()) {
			entities.add(player);
			entities.add(monster);
		} else {
			entities.add(monster);
			entities.add(player);
		}
		
		do {
			
			for (Entity entity : entities) {
				for (int i = 0; i < 10; i++) {
					System.out.print("=");
				}
				System.out.print(System.lineSeparator());
				if (entity instanceof Player) {
					int answer = Main.askQuestionFromStringList(action, "Que voulez-vous faire", scanner);
					
					switch (answer) {
						case 1:
							double damage = 0;
							if (player.weapon != null) {
								damage = player.weapon.attack(monster);
							}
							monster.damage(damage);
							System.out.println("Vous avez infligé " + damage + " dégats");
							System.out.println("Le monstre n'a plus que " + monster.getHealth() + " points de vie");
							break;
						case 2:
							break;
					}
					
				} else {
					player.damage(monster.hand.getDamage());
					System.out.println("Le monstre vous a infligé " + monster.hand.getDamage() + " dégats");
					System.out.println("Il vous reste " + player.getHealth() + " points de vie");
				}
			}
			
		} while (player.getHealth() > 0 && monster.getHealth() > 0);
		
		if (monster.getHealth() <= 0) {
			int xp = 10 + rand.nextInt(10);
			int money = 10 + rand.nextInt(10);
			player.addMoney(money).addXp(xp);
			System.out.println("Vous avez gagné " + xp + " points d'experience");
			System.out.println("Vous avez gagné " + money + " $");
			return;
		}
		
		if (player.getHealth() <= 0) {
			System.out.println("Vous êtes mort");
			System.out.println("Game Over");
			System.exit(0);
		}
	}
	
	private static void destroyObstacle(List<String> directions, Scanner scanner, Player player, Map map) {
		int direction = Main.askQuestionFromStringList(directions, "Choisissez une direction", scanner);

		Entity entity = null;
		int posX = 0;
		int posY = 0;

		switch (direction) {
		case 1: // Haut
			posX = player.getPosX();
			posY = player.getPosY() - 1;
			break;
		case 2: // Bas
			posX = player.getPosX();
			posY = player.getPosY() + 1;
			break;
		case 3: // Droite
			posX = player.getPosX() + 1;
			posY = player.getPosY();
			break;
		case 4: // Gauche
			posX = player.getPosX() - 1;
			posY = player.getPosY();
			break;
		}

		entity = map.getEntityAtPos(posX, posY);

		if (entity instanceof Obstacle) {
			Obstacle obstacle = (Obstacle) entity;
			if (obstacle.isIndestructible()) {
				System.out.println("Vous ne pouvez pas détruire cet obstacle");
			} else {
				obstacle.damage(player.weapon.attack(obstacle));
				if (obstacle.getHealth() <= 0) {
					map.setEntityAtPos(null, posX, posY);
					System.out.println("L'obstacle a été détruit");
				}
			}
		} else {
			System.out.println("Il n'y a pas d'obstacle à détruire");
		}
	}

	private static int askQuestionFromStringList(List<String> list, String name, Scanner scanner) {
		int i = 1;
		int input = 0;
		do {
			try {
				System.out.println(name);
				i = 1;
				for (Object obj : list) {
					System.out.println(String.format("%s. %s", i++, obj.toString()));
				}

				input = Integer.parseInt(scanner.next());
				if (input > 0 && input < i + 1)
					return input;
			} catch (Exception e) {
			}
		} while (true);
	}
	
	private static Item askQuestionFromItemList(List<Item> list, String name, Scanner scanner) {
		int i = 1;
		int input = 0;
		do {
			try {
				System.out.println(name);
				i = 1;
				for (Item obj : list) {
					System.out.println(String.format("%s. %s", i++, obj.toString()));
				}

				input = Integer.parseInt(scanner.next());
				if (input > 0 && input < i + 1)
					return list.get(input - 1);
			} catch (Exception e) {
			}
		} while (true);
	}

	private static Object askQuestionFromList(List list, String name, Scanner scanner) {
		int i = 1;
		int input = 0;
		do {
			try {
				System.out.println(name);
				i = 1;
				for (Object obj : list) {
					System.out.println(String.format("%s. %s", i++, obj.toString()));
				}

				input = Integer.parseInt(scanner.next());
				if (input > 0 && input < i + 1)
					return list.get(input - 1);
			} catch (Exception e) {
			}
		} while (true);
	}

}
