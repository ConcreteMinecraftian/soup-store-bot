package com.concreteminecraftian.soupstorebot;

import java.util.ArrayList;

import com.concreteminecraftian.soupstorebot.economy.Item;

public class Global {
	
	public static int Amount(Item i, ArrayList<Item> list) {
		
		int count = 0;
		for(Item j : list) {
			if(i.equals(j)) ++count;
		}
		return count;
		
	}

	public static ArrayList<Item> RemoveDuplicates(ArrayList<Item> inventory) {
		
		ArrayList<Item> duplicates = new ArrayList<Item>();
		ArrayList<Item> without = new ArrayList<Item>();
		
		for(Item i : inventory) {
			if(without.contains(i)) duplicates.add(i); else without.add(i);
		}
		
		duplicates.clear();
		return without;
		
	}

}
