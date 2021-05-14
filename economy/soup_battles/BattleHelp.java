package com.concreteminecraftian.soupstorebot.economy.soup_battles;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class BattleHelp extends BattleCommand {
	
	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		int page = 1;
		try {
			page = (args.length < 3) ? 1 : Integer.parseInt(args[2]);
		} catch(NumberFormatException e) {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Enter a number!");
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
			return;
		}
				
		switch(page) {
		
		case 1:
			EmbedBuilder list = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Battle Commands List:-", "");
			list.addField(Main.PREFIX + "battle help [page]", "Shows this", true);
			list.addField(Main.PREFIX + "battle deck", "Shows battle deck", true);
			list.addField(Main.PREFIX + "battle deck set <place> <soup>", "Sets <place> item of deck to <soup>", true);
			list.addField("Note:-", ".battle deck set <place> no_item doesnt require you to have the item.", false);
			list.addField(Main.PREFIX + "battle stats <item>", "Shows the battle stats of any soup.", true);
			list.addField(Main.PREFIX + "battle battle <person>", "Actually fight a person", true);
			list.addField("Note:-", ".battle battle is disabled because of lag issues", false);
			event.getChannel().sendMessage(list.build()).queue();
			list.clear();
			break;
			
		default:
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Invalid page!");
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
			break;
		
		}
		
	}
	
	public BattleHelp() {
		this.setId("help");
	}
	
}
