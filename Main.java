package com.concreteminecraftian.soupstorebot;

import java.util.HashMap;

import javax.security.auth.login.LoginException;

import com.concreteminecraftian.soupstorebot.economy.Account;
import com.concreteminecraftian.soupstorebot.economy.Bal;
import com.concreteminecraftian.soupstorebot.economy.Forage;
import com.concreteminecraftian.soupstorebot.economy.Inventory;
import com.concreteminecraftian.soupstorebot.economy.Item;
import com.concreteminecraftian.soupstorebot.economy.Register;
import com.concreteminecraftian.soupstorebot.economy.crafting.Craft;
import com.concreteminecraftian.soupstorebot.economy.crafting.Recipe;
import com.concreteminecraftian.soupstorebot.economy.crafting.Recipes;
import com.concreteminecraftian.soupstorebot.economy.store.Buy;
import com.concreteminecraftian.soupstorebot.economy.store.Sell;
import com.concreteminecraftian.soupstorebot.economy.store.Store;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;

public class Main {
	
	public static String PREFIX = ".";
	public static String MAINTENANCEPREFIX = PREFIX + ".";
	public static HashMap<Long,Account> accounts = new HashMap<Long, Account>();
	
	public static void main(String[] args) throws LoginException {
		
		AddRecipes();
		setPrefix(".");
		
		// TODO: add back cooldown once bot hosted
		
		JDABuilder.createDefault(--TOKEN--)
			.setStatus(OnlineStatus.ONLINE)
			.setActivity(Activity.playing(".help"))
			.addEventListeners(new Register(), new Forage(), new Inventory(), new Help(),
					new Craft(), new Bal(), new Sell(), new Recipes(), new Store(), new Buy())
			.build();
		
	}
	
	private static void AddRecipes() {
		
		Recipe.addRecipe(Item.Tea, "tea", Item.Water, Item.Water, Item.Leaf);
		Recipe.addRecipe(Item.BoiledChicken, "boiled_chicken", Item.Water, Item.Water, Item.Chicken);
		Recipe.addRecipe(Item.ClamChowder, "clam_chowder", Item.Pork, Item.Potato, Item.Spices, Item.Clam, Item.Water, Item.Water);
		Recipe.addRecipe(Item.SeaweedSoup, "seaweed_soup", Item.Soup, Item.Oil, Item.Pork, Item.Seaweed, Item.Seaweed, Item.Seaweed);
		Recipe.addRecipe(Item.TomatoSoup, "tomato_soup", Item.Soup, Item.Tomato, Item.Tomato, Item.Spices);
		Recipe.addRecipe(Item.VegetableSoup, "vegetable_soup", Item.Water, Item.Oil, Item.Potato, Item.Tomato, Item.BetterLeaf);
		Recipe.addRecipe(Item.MeatSoup, "meat_soup", Item.Water, Item.Oil, Item.Chicken, Item.Squid, Item.PrimeMeat);
		
	}

	public static void setPrefix(String pre) {
		
		PREFIX = pre;
		MAINTENANCEPREFIX = ".";
		
	}
	
	public static void addAccount(User u) {
		
		accounts.put(u.getIdLong(), Account.newacc());
		
	}
	
}
