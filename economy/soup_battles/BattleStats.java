package com.concreteminecraftian.soupstorebot.economy.soup_battles;

import java.awt.Color;
import java.util.HashMap;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.economy.Item;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class BattleStats extends BattleCommand {

	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		if(args.length < 3) {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Insufficient arguments!");
			error.addField("Correct usage:- ", ".b stats <item>", false);
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
		} else if(Item.getItemByName(args[2]) == null) {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Third argument should be an item!");
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
		} else if(Item.getStats(Item.getItemByName(args[2])) == null) {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Item should be a soup item!");
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
		} else {
			EmbedBuilder list = IEmbedBuilder.createDefaultListBuild(Color.GREEN, "Item stats:- ");
			
			HashMap<String, Integer> statmap = Item.getStats(Item.getItemByName(args[2])).asMap();
			for(String s : statmap.keySet()) {
				list.addField(s, "" + statmap.get(s),true);
			}
			
			event.getChannel().sendMessage(list.build()).queue();
			list.clear();
		}
		
	}
	
	public BattleStats() {
		this.setId("stats");
	}

}
