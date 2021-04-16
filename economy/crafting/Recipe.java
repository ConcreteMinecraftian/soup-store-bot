package com.concreteminecraftian.soupstorebot.economy.crafting;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;
import com.concreteminecraftian.soupstorebot.economy.Item;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class Recipe {

	public static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
	
	private ArrayList<Item> ingredients = new ArrayList<Item>();
	private Item yield;
	private String id;
	
	public static void addRecipe(Recipe r) {
		recipes.add(r);
	}
	
	public static void addRecipe(ArrayList<Item> ingredients, Item yield, String id) {
		recipes.add(new Recipe(ingredients, yield, id));
	}
	
	public static void addRecipe(Item yield, String id, Item... ingredients) {
		ArrayList<Item> ing = new ArrayList<Item>();
		ing.addAll(Arrays.asList(ingredients));
		recipes.add(new Recipe(ing, yield, id));
	}
	
	public static Recipe getRecipeById(String id) {
		
		for(Recipe r : recipes) {
			if(r.getId().equals(id)) return r;
		}
		
		return null;
		
	}
		
	public void craft(Long id, TextChannel send) {
		
		if(ingredients.isEmpty())
			throw new NullPointerException("Ingredients can't be empty!");
		if(yield == null)
			throw new NullPointerException("yield can't be null!");
		
		ArrayList<Item> cleared = new ArrayList<Item>();
		
		boolean done = true;
		INGLOOP:
		for(Item i : ingredients) {
			
			if(!Main.accounts.containsKey(id)) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first");
				send.sendMessage(error.build()).queue();
				error.clear();
				return;
			}
			
			if(Main.accounts.get(id).getInventory().contains(i)) {
				
				Main.accounts.get(id).getInventory().remove(i);
				cleared.add(i);
				
			} else {
				
				done = false;
				for(Item j : cleared) {
					Main.accounts.get(id).getInventory().add(j);
				}
				cleared.clear();
				break INGLOOP;
			}
			
		}
		
		if(done) {
			Main.accounts.get(id).addItemToInventory(yield);
			EmbedBuilder success = IEmbedBuilder.createDefaultBuild(Color.GREEN, "Success", yield.getName() + " successfully crafted!");
			send.sendMessage(success.build()).queue();
			success.clear();
		} else {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have the necessary materials!");
			send.sendMessage(error.build()).queue();
			error.clear();
		}
		
	}
	
	public Recipe(ArrayList<Item> ingredients, Item yield, String id) {
		this.ingredients = ingredients;
		this.yield = yield;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
}
