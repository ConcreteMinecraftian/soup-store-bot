package com.concreteminecraftian.soupstorebot.economy.soup_battles;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;
import com.concreteminecraftian.soupstorebot.economy.Item;
import com.concreteminecraftian.soupstorebot.economy.ItemType;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class BattleDeck extends BattleCommand {
	
	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		if(args.length > 2 && args[2].equalsIgnoreCase("set")) {
			
			if(args.length < 5) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Insufficient arguments!");
				error.addField("Correct usage:- ", ".b deck set <number> <item>", false);
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				int number = 0;
				try {
					number = Integer.parseInt(args[3]) - 1;
				} catch(NumberFormatException e) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Fourth argument should be number!");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
					return;
				}
				Item it = Item.getItemByName(args[4]);
				if(it == Item.NullItem) Main.accounts.get(event.getMember().getIdLong()).addItemToInventory(Item.NullItem);
				if(it == null) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Enter valid item name!");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} else if(!Main.accounts.get(event.getMember().getIdLong()).getInventory().contains(it)) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have that item");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} else if(it.getType() != ItemType.Soup) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("That item is not a soup!");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
					return;
				} else if(number > 6 || number < 0) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Number should be between 1 and 6, inclusive.");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
					return;
				} else {
					if(Main.accounts.get(event.getMember().getIdLong()).getDeck()[number] != Item.NullItem)
						Main.accounts.get(event.getMember().getIdLong()).addItemToInventory(Main.accounts.get(event.getMember().getIdLong()).getDeck()[number]);						
					Main.accounts.get(event.getMember().getIdLong()).getInventory().remove(it);
					Main.accounts.get(event.getMember().getIdLong()).addItemToDeck(number, it);
					EmbedBuilder success = IEmbedBuilder.createDefaultBuild(Color.GREEN, "Success!", "You successfully edited your deck!");
					event.getChannel().sendMessage(success.build()).queue();
					success.clear();
				}
				
			}
			
		} else {
			EmbedBuilder list = IEmbedBuilder.createDefaultListBuild(Color.GREEN, "Battle Deck:- ");
			
			for(int i = 0; i < Main.accounts.get(event.getMember().getIdLong()).getDeck().length; i++) {
				Item it = Main.accounts.get(event.getMember().getIdLong()).getDeck()[i];
				list.addField("Deck Item " + (i + 1), it.getName(), true);
			}
			
			event.getChannel().sendMessage(list.build()).queue();
			list.clear();
		}
		
	}
	
	public BattleDeck() { this.setId("deck"); }

}
