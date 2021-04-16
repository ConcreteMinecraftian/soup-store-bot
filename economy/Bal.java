package com.concreteminecraftian.soupstorebot.economy;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Bal extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "bal")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first.");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				EmbedBuilder bal = IEmbedBuilder.createDefaultBuild(Color.CYAN, "Bal:-", ""+Main.accounts.get(event.getMember().getIdLong()).getMoney());
				event.getChannel().sendMessage(bal.build()).queue();
				bal.clear();
			}
			
		}
		
	}
	
}
