package com.concreteminecraftian.soupstorebot.economy.crafting;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Craft extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "craft")) {
			
			if(args.length < 2) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Incorrect Usage");
				error.addField("Correct Usage:-", Main.PREFIX + "craft <recipe>", false);
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				if(Recipe.getRecipeById(args[1]) != null) {
					Recipe.getRecipeById(args[1]).craft(event.getMember().getIdLong(), event.getChannel());
				} else {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("This recipe does not exist");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				}
				
			}
			
		}
		
	}

}
