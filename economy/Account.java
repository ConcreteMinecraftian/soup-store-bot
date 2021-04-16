package com.concreteminecraftian.soupstorebot.economy;

import java.util.ArrayList;

public class Account {

	private int money;
	private int area;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	//private ArrayList<Soup> soup;
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public void addItemToInventory(Item item) {
		inventory.add(item);
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
	
}
