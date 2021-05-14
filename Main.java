package com.concreteminecraftian.soupstorebot;

import java.util.HashMap;

import javax.security.auth.login.LoginException;

import com.concreteminecraftian.soupstorebot.economy.Account;
import com.concreteminecraftian.soupstorebot.economy.Bal;
import com.concreteminecraftian.soupstorebot.economy.Beg;
import com.concreteminecraftian.soupstorebot.economy.Daily;
import com.concreteminecraftian.soupstorebot.economy.Forage;
import com.concreteminecraftian.soupstorebot.economy.Give;
import com.concreteminecraftian.soupstorebot.economy.Inventory;
import com.concreteminecraftian.soupstorebot.economy.Item;
import com.concreteminecraftian.soupstorebot.economy.JSONHashMapConverter;
import com.concreteminecraftian.soupstorebot.economy.Register;
import com.concreteminecraftian.soupstorebot.economy.Share;
import com.concreteminecraftian.soupstorebot.economy.Use;
import com.concreteminecraftian.soupstorebot.economy.crafting.Craft;
import com.concreteminecraftian.soupstorebot.economy.crafting.Recipe;
import com.concreteminecraftian.soupstorebot.economy.crafting.Recipes;
import com.concreteminecraftian.soupstorebot.economy.events.EventManager;
import com.concreteminecraftian.soupstorebot.economy.events.EventScheduler;
import com.concreteminecraftian.soupstorebot.economy.soup_battles.BattleManager;
import com.concreteminecraftian.soupstorebot.economy.store.Buy;
import com.concreteminecraftian.soupstorebot.economy.store.Sell;
import com.concreteminecraftian.soupstorebot.economy.store.Store;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;

public class Main {
	
	public static String PREFIX = ".";
	public static String MAINTENANCEPREFIX = PREFIX + ".";
	public static HashMap<Long,Account> accounts = new HashMap<Long, Account>();
	public static JDA reference;
	
	public static void main(String[] args) throws LoginException {
		
		// Pre-Init
		AddRecipes();
		setPrefix(".");
		accounts = JSONHashMapConverter.load();
		
		// TODO: add back cooldown once bot hosted
		
		// Init
		reference = JDABuilder.createDefault("ODI5OTgzNDM4MjYxMDU5NjA0.YHAEUA.9hcjeEZH4IbAMrvU1HzH-kuEz-I")
			.setStatus(OnlineStatus.ONLINE)
			.setActivity(Activity.playing(".help"))
			.addEventListeners(new Register(), new Forage(), new Inventory(), new Help(),
					new Craft(), new Bal(), new Sell(), new Recipes(), new Store(), new Buy()
					, new Use(), new JSONHashMapConverter(), new BattleManager(), new Daily(),
					new Beg(), new Give(), new Share(), new EventManager())
			.build();
		
		// Post-Init
		EventScheduler.startTickingEventClock();
		
	}
	
	private static void AddRecipes() {
		
		Recipe.addRecipe(Item.Tea, "tea", Item.Water, Item.Water, Item.Leaf);
		Recipe.addRecipe(Item.BoiledChicken, "boiled_chicken", Item.Water, Item.Water, Item.Chicken);
		Recipe.addRecipe(Item.ClamChowder, "clam_chowder", Item.Pork, Item.Potato, Item.Spices, Item.Clam, Item.Water, Item.Water);
		Recipe.addRecipe(Item.SeaweedSoup, "seaweed_soup", Item.Soup, Item.Oil, Item.Pork, Item.Seaweed, Item.Seaweed, Item.Seaweed);
		Recipe.addRecipe(Item.TomatoSoup, "tomato_soup", Item.Soup, Item.Tomato, Item.Tomato, Item.Spices);
		Recipe.addRecipe(Item.VegetableSoup, "vegetable_soup", Item.Water, Item.Oil, Item.Potato, Item.Tomato, Item.BetterLeaf);
		Recipe.addRecipe(Item.MeatSoup, "meat_soup", Item.Water, Item.Oil, Item.Chicken, Item.Squid, Item.PrimeMeat);
		Recipe.addRecipe(Item.SoupierSoup, "soupier_soup", Item.Water, Item.SoupEssence, Item.SoupFlower, Item.MysticalMushroom);
		Recipe.addRecipe(Item.SeafoodSoup, "seafood_soup", Item.Water, Item.Water, Item.Anchovy, Item.Seaweed, Item.Seaweed, Item.Squid, Item.Spices, Item.Oil);
		Recipe.addRecipe(Item.MushroomSoup, "mushroom_soup", Item.Soup, Item.Anchovy, Item.MysticalMushroom, Item.Oil, Item.Oil, Item.Spices);
		Recipe.addRecipe(Item.FrenchBaguette, "baguette", Item.Bread, Item.Bread, Item.Bread);
		Recipe.addRecipe(Item.MinerLicense, "miners_license", Item.Scrap, Item.Scrap, Item.Scrap, Item.Scrap, Item.Scrap);
		Recipe.addRecipe(Item.BreadSoup, "bread_soup", Item.SoupGem, Item.SpiceOre, Item.FrenchBaguette);
		Recipe.addRecipe(Item.ExtraSpiceSoup, "extra_spice_soup", Item.SpiceOre, Item.Spices, Item.Spices, Item.Soup);
		Recipe.addRecipe(Item.ChickenSoup, "chicken_soup", Item.SoupGem, Item.BoiledChicken, Item.BoiledChicken, Item.SpiceOre);
		
	}

	public static void setPrefix(String pre) {
		
		PREFIX = pre;
		MAINTENANCEPREFIX = ".";
		
	}
	
	public static void addAccount(User u) {
		
		accounts.put(u.getIdLong(), Account.newacc());
		
	}
	
}
