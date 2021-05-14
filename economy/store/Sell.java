package com.concreteminecraftian.soupstorebot.economy.store;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.Global;
import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;
import com.concreteminecraftian.soupstorebot.economy.Item;
import com.concreteminecraftian.soupstorebot.economy.ItemType;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Sell extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "sell")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first.");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(args.length < 2) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Incorrect Usage!");
				error.addField("Correct Usage:-",".sell <item>",false);
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				if(args[1].equalsIgnoreCase("all")) {
					int prev = Main.accounts.get(event.getMember().getIdLong()).getMoney();
					for(int i = 0; i < Main.accounts.get(event.getMember().getIdLong()).getInventory().size(); i++) {
						Item it = Main.accounts.get(event.getMember().getIdLong()).getInventory().get(i);
						if(it.getType() == ItemType.Material) {
							Main.accounts.get(event.getMember().getIdLong()).getInventory().remove(it);
							Main.accounts.get(event.getMember().getIdLong()).addMoney(it.getSellprice());
							i--;
						}
					}
					EmbedBuilder success = IEmbedBuilder.createDefaultBuild(Color.GREEN, "Success!", "You successfully sold your inventory for " + (Main.accounts.get(event.getMember().getIdLong()).getMoney() - prev));
					event.getChannel().sendMessage(success.build()).queue();
					success.clear();
					return;
				}
				
				if(Item.getItemByName(args[1]) == null) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Incorrect Item Name");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} else if(!Main.accounts.get(event.getMember().getIdLong()).getInventory().contains(Item.getItemByName(args[1]))) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have that item!");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} else {
					Item i = Item.getItemByName(args[1]);
					if(i.getType() == ItemType.Collectible) {
						EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You cant sell collectibles!");
						event.getChannel().sendMessage(error.build()).queue();
						error.clear();
						return;
					}
					int numorg = 2;
					try {numorg = (args.length < 3) ? Global.Amount(i, Main.accounts.get(event.getMember().getIdLong()).getInventory()) : Integer.parseInt(args[2]); }
					catch(NumberFormatException e) {
						EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Enter a number!");
						event.getChannel().sendMessage(error.build()).queue();
						error.clear();
						return;
					}
					if(numorg >  Global.Amount(i, Main.accounts.get(event.getMember().getIdLong()).getInventory())) { 
						EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have this many!");
						event.getChannel().sendMessage(error.build()).queue();
						error.clear();
						return;
					}
					int num = numorg;
					for(int j = 0; j < Main.accounts.get(event.getMember().getIdLong()).getInventory().size(); j++) {
						Item k = Main.accounts.get(event.getMember().getIdLong()).getInventory().get(j);
						if(k.equals(i)) {
							Main.accounts.get(event.getMember().getIdLong()).getInventory().remove(k);
							j--;
							numorg--;
							if(numorg == 0) break;
						}
					}
					Main.accounts.get(event.getMember().getIdLong()).addMoney(i.getSellprice() * num);
					
					EmbedBuilder success = IEmbedBuilder.createDefaultBuild(Color.GREEN, "Success!", "You successfully sold " + num + "x " + Item.getItemByName(args[1]).getName() + "s for " + i.getSellprice() * num);
					event.getChannel().sendMessage(success.build()).queue();
					success.clear();
					
				}
				
			}
			
		}
		
	}
	
}
