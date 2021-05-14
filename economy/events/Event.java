package com.concreteminecraftian.soupstorebot.economy.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.concreteminecraftian.soupstorebot.economy.events.soup_merchant.Buy;
import com.concreteminecraftian.soupstorebot.economy.events.soup_merchant.Store;

public enum Event {
	
	NULL(EventRarity.NO, "No Event"), SoupMerchant(EventRarity.COMMON, "Soup Merchant", new Buy(), new Store());
	
	public enum EventRarity {
		EPIC(10), RARE(15), UNCOMMON(25), COMMON(50), NO(0);
		private int chance;
		public int getChance() { return chance; }
		EventRarity(int chance) { this.chance = chance; }
	}
	
	private EventRarity rarity;
	private List<EventCommand> commands;
	private String name;
	
	Event(EventRarity rarity, String name, EventCommand...commands) {
		this.rarity = rarity;
		this.commands = Arrays.asList(commands);
		this.name = name;
	}

	public EventRarity getRarity() {
		return rarity;
	}
	
	public List<EventCommand> getCommands() {
		return commands;
	}
	
	public String getName() {
		return name;
	}

	public static ArrayList<Event> sortByRarity(EventRarity rarity) {
		
		ArrayList<Event> ret = new ArrayList<>();
		
		for(Event e : values()) {
			if(e.getRarity() == rarity) ret.add(e);
		}
		
		return ret;
	}
	
	
}
