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
	(2050,"Meat_Soup",ItemType.Soup),ForestPermit(5000,"Forest_Permit",ItemType.Usable),
	MysticalMushroom(150,"Mystical_Mushroom",ItemType.Material),SoupFlower(500,"Soup_Flower",
	ItemType.Material),SoupEssence(1000,"Soup_Essence",ItemType.Material),Anchovy(250,"Anchovy",
	ItemType.Material),SoupierSoup(1900,"Soupier_Soup",ItemType.Soup),SeafoodSoup(1900,"Seafood_Soup",
	ItemType.Soup),MushroomSoup(1850,"Mushroom_Soup",ItemType.Soup),NullItem(0,"No_Item",ItemType.Soup)
	, FacilityPermit(50000,"Facility_Permit",ItemType.Usable),Scrap(500,"Scrap",ItemType.Material),
	Bread(200,"Bread",ItemType.Material),FrenchBaguette(600,"French_Baguette",ItemType.Material),
	Copper(300,"Copper_Piece",ItemType.Material),SpiceOre(700,"Spice_Ore",ItemType.Material),
	SoupGem(1000,"Soup_Gem",ItemType.Material),MinerLicense(500000,"Miners_License",ItemType.Usable),
	BreadSoup(2100,"Bread_Soup",ItemType.Soup),ExtraSpiceSoup(1900,"Extra_Spice_Soup",ItemType.Soup),
	ChickenSoup(2750,"Chicken_Soup",ItemType.Soup),ExoticSoup(5000,"Exotic_Soup",ItemType.Soup),
	GoldSoup(5000,"Golden_Soup",ItemType.Soup),HomemadeSoup(1000,"Homemade_Soup",ItemType.Soup);
	
	
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
			else if(it == Item.FacilityPermit) 
				return (Account a, TextChannel send) -> {
					if(a.getArea() < 3) {
						a.setArea(3);
						EmbedBuilder success = IEmbedBuilder.createDefaultBuild(Color.GREEN, "Success!", "You got into the Facility Outskirts!");
						send.sendMessage(success.build()).queue();
						success.clear();
					} else {
						EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You are in a higher area than that!");
						send.sendMessage(error.build()).queue();
						error.clear();
					}
				};
			else if(it == Item.MinerLicense) 
				return (Account a, TextChannel send) -> {
					if(a.getArea() < 4) {
						a.setArea(4);
						EmbedBuilder success = IEmbedBuilder.createDefaultBuild(Color.GREEN, "Success!", "You got into the Soup Mines!");
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
	
	public static ItemStats getStats(Item i) {
		
		if(i.getType() != ItemType.Soup)
			return null;
		
		ItemStats ret = new ItemStats();
		if(i == Item.NullItem) return ret.createStats(0, 0, 0, 0, 0, 0);
		else if(i == Item.Soup) return ret.createStats(0, 0, 0, 0, 0, 0);
		
		else if(i == Item.ClamChowder) return ret.createStats(5, 0, 0, 0, 0, 0);
		else if(i == Item.SeaweedSoup) return ret.createStats(5, 5, -5, 0, 0, 0);
		else if(i == Item.TomatoSoup) return ret.createStats(0, -5, 10, 0, 0, 0);
		else if(i == Item.VegetableSoup) return ret.createStats(0, 5, 0, 0, 0, 0);
		else if(i == Item.MeatSoup) return ret.createStats(0, 0, 0, 0, 0, 10);
		
		else if(i == Item.SoupierSoup) return ret.createStats(5, 0, 5, 0, 0, 0);
		else if(i == Item.SeafoodSoup) return ret.createStats(5, 5, 0, 0, 0, 0);
		else if(i == Item.MushroomSoup) return ret.createStats(0, 5, 0, 0, 0, 10);
		
		else if(i == Item.BreadSoup) return ret.createStats(5, 0, 0, 0, 0, 20);
		else if(i == Item.ExtraSpiceSoup) return ret.createStats(5, 0, 10, 0, 0, 0);
		else if(i == Item.ChickenSoup) return ret.createStats(5, 10, 0, 0, 0, 0);
		
		else if(i == Item.ExoticSoup) return ret.createStats(5, 0, 0, 2, 0, 0);
		else if(i == Item.GoldSoup) return ret.createStats(0, 0, 5, 0, 2, 0);
		else if(i == Item.HomemadeSoup) return ret.createStats(0, 0, 0, 1, 1, 0);
		
		return null;
	}
	
}
