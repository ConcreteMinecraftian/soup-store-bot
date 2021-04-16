package com.concreteminecraftian.soupstorebot.economy.crafting;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Recipes extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "recipes")) {
			
			int page;
			try {
				if(args.length > 1) page=Integer.parseInt(args[1]); else page=1;
			} catch(NumberFormatException e) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Incorrect Usage.");
				error.addField("Correct Usage:- ", Main.PREFIX + "recipes [page]", true);
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
				return;
			}
			
			switch(page) {
			
			case 1:
				EmbedBuilder list = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Recipes page " + page + ":-", "Recipes:- ");
				list.addField("Tea", "2x Water, 1x Leaf", true);
				list.addField( "Boiled_Chicken", "2x Water, 1x Chicken", true);
				list.addField( "Clam_Chowder", "2x Water, 1x Pork, 1x Potato, 1x Spices, 1x Clam", true);
				list.addField("Seaweed_Soup", "1x Soup, 1x Oil, 1x Pork, 3x Seaweed", true);
				list.addField("Tomato_Soup", "1x Soup, 2x Tomato, 1x Spices", true);
				list.addField("Vegetable_Soup", "1x Water, 1x Tomato, 1x Potato, 1x Better_Leaf, 1x Oil", true);
				event.getChannel().sendMessage(list.build()).queue();
				list.clear();
				break;
			case 2:
				EmbedBuilder list2 = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Recipes page " + page + ":-", "Recipes:- ");
				list2.addField("Meat_Soup", "1x Water, 1x Chicken, 1x Soup, 1x Prime_Meat", true);
				event.getChannel().sendMessage(list2.build()).queue();
				list2.clear();
				break;
			default:
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("This page does not exist.");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
				break;
			
			}
			
		}
		
	}

}
