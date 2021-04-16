package com.concreteminecraftian.soupstorebot.economy;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public enum Item {

	Leaf(100,"Leaf",ItemType.Material),Potato(500,"Potato",ItemType.Material),
	BetterLeaf(1000,"Better_Leaf",ItemType.Material),Tomato(250,"Tomato",ItemType.Material),
	Chicken(250,"Chicken",ItemType.Material),Squid(500,"Squid",ItemType.Material),
	PrimeMeat(1000,"Prime_Meat",ItemType.Material),Clam(100,"Clam",ItemType.Material),
	Water(250,"Water",ItemType.Material),Tea(0,"Tea",ItemType.Collectible),BoiledChicken
	(0, "Boiled_Chicken",ItemType.Collectible),Soup(500,"Soup",ItemType.Soup),Salsa(200
	,"Salsa",ItemType.Material),Mint(50,"Mint",ItemType.Material),Pork(100,"Pork",
	ItemType.Material),Seaweed(250,"Seaweed",ItemType.Material),Spices(350,"Spices",
	ItemType.Material), ClamChowder(1700,"Clam_Chowder",ItemType.Soup),Oil(50,"Oil",
	ItemType.Material), SeaweedSoup(1400, "Seaweed_Soup", ItemType.Soup), TomatoSoup(1400,
	"Tomato_Soup",ItemType.Soup),VegetableSoup(2050,"Vegetable_Soup",ItemType.Soup),MeatSoup
	(2050,"Meat_Soup",ItemType.Soup),ForestPermit(10000,"Forest_Permit",ItemType.Usable)
	;
	
	private int sellprice;
	private String name;
	private ItemType type;
	
	public int getSellprice() {
		return sellprice;
	}
	
	public ItemType getType() {
		return type;
	}

	private Item(int sell, String name, ItemType type) {
		sellprice = sell;
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public static Item getItemByName(String name) {
		
		for(Item i : Item.values()) {
			if(i.getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		
		return null;
		
	}
	
	public static ItemInteraction getUse(Item it) {
		
		if(it.getType() != ItemType.Usable)
			return null;
		
		if(it == Item.ForestPermit) 
			return (Account a, TextChannel send) -> {
				if(a.getArea() < 2) {
					a.setArea(2);
					EmbedBuilder success = IEmbedBuilder.createDefaultBuild(Color.GREEN, "Success!", "You got into the Evergreen Forest!");
					send.sendMessage(success.build()).queue();
					success.clear();
				} else {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You are in a higher area than that!");
					send.sendMessage(error.build()).queue();
					error.clear();
				}
			};
		
		return null;
	}
	
}
