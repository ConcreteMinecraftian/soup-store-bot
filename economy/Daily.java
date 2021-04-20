package com.concreteminecraftian.soupstorebot.economy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Daily extends ListenerAdapter {
	
	private List<Long> cooldown = new ArrayList<>();
	private Timer schedule = new Timer();
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "daily")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				if(cooldown.contains(event.getMember().getIdLong())) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You are on cooldown. Wait one day since you last claimed daily.");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
				} else {
					Main.accounts.get(event.getMember().getIdLong()).addMoney(1500);
					EmbedBuilder success = IEmbedBuilder.createDefaultBuild(Color.CYAN, "You claimed your daily!", "1500 coins have been added!");
					event.getChannel().sendMessage(success.build()).queue();
					success.clear();
					cooldown.add(event.getMember().getIdLong());
					schedule.schedule(new TimerTask() {
						@Override
						public void run() {
							cooldown.remove(event.getMember().getIdLong());
						}
					}, 1000*60*60*24);
				}
				
			}
			
		}
		
	}
	
}
