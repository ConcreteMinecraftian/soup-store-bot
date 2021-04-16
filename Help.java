package com.concreteminecraftian.soupstorebot;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "help")) {
			
			int page;
			try {
				if(args.length > 1) page=Integer.parseInt(args[1]); else page=1;
			} catch(NumberFormatException e) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Incorrect Usage.");
				error.addField("Correct Usage:- ", Main.PREFIX + "help [page]", true);
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
				return;
			}
			
			switch(page) {
			
			case 1:
				EmbedBuilder list = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Help page " + page + ":-", "Commands:- ");
				list.addField(Main.PREFIX + "help [page]", "Generates this", true);
				list.addField(Main.PREFIX + "register", "Registers you into the soup world!", true);
				list.addField(Main.PREFIX + "inventory", "Shows your inventory!", true);
				list.addField(Main.PREFIX + "forage [area]", "A way to get resources.", true);
				list.addField(Main.PREFIX + "craft <item>", "A way to craft stuff", true);
				list.addField(Main.PREFIX + "bal", "Shows your money.", true);
				event.getChannel().sendMessage(list.build()).queue();
				list.clear();
				break;
			case 2:
				EmbedBuilder list2 = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Help page " + page + ":-", "Commands:- ");
				list2.addField(Main.PREFIX + "store [page]", "Go on a shopping spree", true);
				list2.addField(Main.PREFIX + "buy <item>", "waste some money :)", true);
				list2.addField(Main.PREFIX + "sell <item>", "Sell a thing.", true);
				list2.addField(Main.PREFIX + "use <item>", "use a thing to get more things.", true);
				list2.addField(Main.PREFIX + "recipes [page]", "Shows a recipe for a thing to craft.", true);
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
