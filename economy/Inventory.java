package com.concreteminecraftian.soupstorebot.economy;

import java.awt.Color;
import java.util.ConcurrentModificationException;

import com.concreteminecraftian.soupstorebot.Global;
import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Inventory extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "inventory") || args[0].equalsIgnoreCase(Main.PREFIX + "inv")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				try {
					
					EmbedBuilder list = IEmbedBuilder.createDefaultListBuild(Color.GREEN, "Inventory:- ");
					
					for(Item i : Global.RemoveDuplicates(Main.accounts.get(event.getMember().getIdLong()).getInventory())) {
						list.addField("", i.getName() + " x " + Global.Amount(i, Main.accounts.get(event.getMember().getIdLong()).getInventory()), true);
					}
					
					event.getChannel().sendMessage(list.build()).queue();
					list.clear();
					
				} catch(ConcurrentModificationException e) {}
				
			}
			
		}
		
	}
	
}
