package com.concreteminecraftian.soupstorebot.economy;

import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Register extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equals(Main.PREFIX + "register")) {
			
			if(Main.accounts.containsKey(event.getMember().getIdLong())) {
				event.getChannel().sendMessage("You have already been registered " + event.getMember().getAsMention() + "!").queue();
				return;
			} else {
				Main.addAccount(event.getAuthor());
				event.getChannel().sendMessage("You have been registered " + event.getMember().getAsMention() + "!").queue();
			}
			
		}
		
	}

}
