package com.concreteminecraftian.soupstorebot.economy;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Share extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "share")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(event.getMessage().getMentionedMembers().size() == 0) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Mention a person to share money with!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(!Main.accounts.containsKey(event.getMessage().getMentionedMembers().get(0).getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("That person isnt registered!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(args.length < 3) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Incorrect usage!");
				error.addField("Correct Usage:- ", Main.PREFIX + "share <money> <user>", false);
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				int money = 2;
				try {
					money = Integer.parseInt(args[1]);
				} catch(NumberFormatException e) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Second value should be an integer!");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
					return;
				}
				
				if(Main.accounts.get(event.getMember().getIdLong()).getMoney() < money) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have that much money.");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} else {
					Main.accounts.get(event.getMember().getIdLong()).addMoney(-money);
					Main.accounts.get(event.getMessage().getMentionedMembers().get(0).getIdLong()).addMoney(money);
					EmbedBuilder success = IEmbedBuilder.createDefaultBuild(Color.GREEN, "Successful share!", "You gave " + money + " money!");
					event.getChannel().sendMessage(success.build()).queue();
					success.clear();
				}
				
			}
			
		}
		
	}
	
}
