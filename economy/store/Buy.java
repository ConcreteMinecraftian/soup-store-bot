package com.concreteminecraftian.soupstorebot.economy.store;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;
import com.concreteminecraftian.soupstorebot.economy.Item;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Buy extends ListenerAdapter {

	private List<Item> shop = Arrays.asList(Item.Soup, Item.Salsa, Item.Mint, Item.Pork, Item.Spices, Item.Seaweed, Item.Oil, Item.ForestPermit, Item.FacilityPermit);
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "buy")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first.");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(args.length < 2) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("What item do you want to buy?");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				if(Item.getItemByName(args[1]) == null) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("There is no item with name " + args[1]);
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} else if(!shop.contains(Item.getItemByName(args[1]))) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("The shop doesnt contain that item.");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} else if(Main.accounts.get(event.getMember().getIdLong()).getMoney() < (Item.getItemByName(args[1]).getSellprice() * 2)) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have enough money.");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} else {
					Main.accounts.get(event.getMember().getIdLong()).setMoney(Main.accounts.get(event.getMember().getIdLong()).getMoney() - Item.getItemByName(args[1]).getSellprice() * 2);
					Main.accounts.get(event.getMember().getIdLong()).addItemToInventory(Item.getItemByName(args[1]));
					EmbedBuilder got = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Successfull purchase", "You bought " + args[1] + " for " + Item.getItemByName(args[1]).getSellprice() * 2);
					event.getChannel().sendMessage(got.build()).queue();
					got.clear();
				}
				
			}
			
		}
		
	}
	
}
