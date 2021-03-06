package com.concreteminecraftian.soupstorebot.economy;

import java.util.ArrayList;

public class Account {

	private int money;
	private int area;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private Item[] deck = new Item[] {Item.NullItem,Item.NullItem,Item.NullItem,Item.NullItem,Item.NullItem,Item.NullItem};
	public int win = 0;
	public int loss = 0;

	public Item[] getDeck() {
		return deck;
	}

	public void setDeck(Item[] deck) {
		this.deck = deck;
	}
	
	public boolean addItemToDeck(int pos, Item item) {
		
		if(item.getType() != ItemType.Soup) return false;
		else {
			deck[pos] = item;
			return true;
		}
		
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public void addItemToInventory(Item item) {
		inventory.add(item);
	}
	
	public void addItemsToInventory(Item item, int count) {
		for (int i = 0; i < count; i++) {
			inventory.add(item);
		}
	}

	public int getMoney() {
		return money;
	}
	
	public void addMoney(int money) {
		this.money += money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public void setArea(int area) {
		this.area = area;
	}
	
	public int getArea() {
		return area;
	}

	public static Account newacc() {

		Account a = new Account();
		a.setMoney(1000);
		a.setArea(1);
		
		return a;
	}
	
	public ItemStats stats() {
		
		ItemStats stats = new ItemStats();
		stats.createStats(20, 20, 20, 5, 5, 50);
		
		for(Item i : deck) {
			stats.addStats(Item.getStats(i));
		}
		
		return stats;
	}
	
}
