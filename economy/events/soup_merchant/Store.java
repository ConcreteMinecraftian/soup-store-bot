package com.concreteminecraftian.soupstorebot.economy.events.soup_merchant;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.economy.Item;
import com.concreteminecraftian.soupstorebot.economy.events.EventCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Store extends EventCommand {
	
	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		EmbedBuilder list = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Me goodies:-", "");
		list.addField("Exotic_Soup:- " + Item.ExoticSoup.getSellprice() * 2, "", false);
		list.addField("Golden_Soup:- " + Item.GoldSoup.getSellprice() * 2, "", false);
		list.addField("Homemade_Soup:- " + Item.HomemadeSoup.getSellprice() * 2, "", false);
		event.getChannel().sendMessage(list.build()).queue();
		list.clear();
		
	}
		
	public Store() {this.setId("store"); this.setHelp("Get a look at me fresh stock!");}
	
}
