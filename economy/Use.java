package com.concreteminecraftian.soupstorebot.economy;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Use extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "use")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(args.length < 2) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Which item do you want to use?");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(Item.getItemByName(args[1]) == null) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("This item doesnt exist!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(!Main.accounts.get(event.getMember().getIdLong()).getInventory().contains(Item.getItemByName(args[1]))) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have this item.");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(Item.getItemByName(args[1]).getType() != ItemType.Usable) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You cant use this item.");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				Main.accounts.get(event.getMember().getIdLong()).getInventory().remove(Item.getItemByName(args[1]));
				Item.getUse(Item.getItemByName(args[1])).onItemUse(Main.accounts.get(event.getMember().getIdLong()), event.getChannel());
				
			}
			
		}
		
	}
	
}
