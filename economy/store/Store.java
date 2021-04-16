package com.concreteminecraftian.soupstorebot.economy.store;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;
import com.concreteminecraftian.soupstorebot.economy.Item;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Store extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "store")) {
			
			int page;
			try {
				if(args.length > 1) page=Integer.parseInt(args[1]); else page=1;
			} catch(NumberFormatException e) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Incorrect Usage.");
				error.addField("Correct Usage:- ", Main.PREFIX + "store [page]", true);
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
				return;
			}
			
			switch(page) {
			
			case 1:
				EmbedBuilder list = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Store page " + page + ":-", "Commands:- ");
				list.addField("Soup - " + Item.Soup.getSellprice() * 2, "mmm soup.", true);
				list.addField("Salsa - " + Item.Salsa.getSellprice() * 2, "spicy stuff. real shit.", true);
				list.addField("Mint - " + Item.Mint.getSellprice() * 2, "mint.", true);
				list.addField("Pork - " + Item.Pork.getSellprice() * 2, "not prime meat.", true);
				list.addField("Seaweed - " + Item.Seaweed.getSellprice() * 2, "weed from spongbob's garden.", true);
				list.addField("Spices - " + Item.Spices.getSellprice() * 2, "flavor pog.", true);
				event.getChannel().sendMessage(list.build()).queue();
				list.clear();
				break;
			case 2:
				EmbedBuilder list2 = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Store page " + page + ":-", "Commands:- ");
				list2.addField("Oil - " + Item.Oil.getSellprice() * 2, "dont call the usa.", true);
				list2.addField("Forest Permit - " + Item.ForestPermit.getSellprice() * 2, "gives you access to evergreen forest area of .forage on .use.", true);
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
