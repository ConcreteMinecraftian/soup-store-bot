package com.concreteminecraftian.soupstorebot.economy.events.soup_merchant;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;
import com.concreteminecraftian.soupstorebot.economy.Item;
import com.concreteminecraftian.soupstorebot.economy.events.EventCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Buy extends EventCommand {

	private List<Item> shop = Arrays.asList(Item.ExoticSoup, Item.GoldSoup, Item.HomemadeSoup);
	
	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		if(args.length < 3) {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("What item u want mate?");
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
		} else {
			
			if(Item.getItemByName(args[2]) == null) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("nothing exists with that name mate!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(!shop.contains(Item.getItemByName(args[2]))) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("I dont have that mate! Me stock are limited!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(Main.accounts.get(event.getMember().getIdLong()).getMoney() < (Item.getItemByName(args[2]).getSellprice() * 2)) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Your cash aint enough mate!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				Main.accounts.get(event.getMember().getIdLong()).setMoney(Main.accounts.get(event.getMember().getIdLong()).getMoney() - Item.getItemByName(args[2]).getSellprice() * 2);
				Main.accounts.get(event.getMember().getIdLong()).addItemToInventory(Item.getItemByName(args[2]));
				EmbedBuilder got = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Here ya go mate!", "One premium-quality " + args[1] + " for " + Item.getItemByName(args[2]).getSellprice() * 2 + " just for you mate!");
				event.getChannel().sendMessage(got.build()).queue();
				got.clear();
			}
			
		}
		
	}
	
	public Buy() {this.setId("buy"); this.setHelp("Get some o' me delish goodies!");}
	
}
