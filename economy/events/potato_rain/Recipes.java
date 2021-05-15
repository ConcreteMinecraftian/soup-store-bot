package com.concreteminecraftian.soupstorebot.economy.events.potato_rain;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.economy.events.EventCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Recipes extends EventCommand {
	
	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		EmbedBuilder list = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Event Recipes:-", "Note: craft by .craft command.");
		list.addField("Suspicious_Potato_Soup", "3x Poisonous_Potato, 2x Potato, 2x Water, 1x Oil, 1x Spices", true);
		list.addField("Potato_Soup", "3x Potato, 1x Better_Potato, 1x Soup, 1x Spices", true);
		list.addField("Glistening_Potato_Soup", "3x Potato, 1x Glistening_Potato, 2x Water, 1x Oil, 1x Spices", true);
		event.getChannel().sendMessage(list.build()).queue();
		list.clear();
		
	}
	
	public Recipes() { this.setId("recipes"); this.setHelp("Potato Soup Recipes exclusive to Potato Rain event"); }

}
