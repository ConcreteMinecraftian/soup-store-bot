package com.concreteminecraftian.soupstorebot.economy.events;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class EventHelp extends EventCommand {
	
	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		EmbedBuilder list = IEmbedBuilder.createDefaultListBuild(Color.CYAN, "Commands:- ");
		list.setDescription("Current Event:- " + EventManager.current.getName());
		list.addField(".e " + this.getId(), this.getHelp(), true);
		for(EventCommand command : EventManager.current.getCommands()) {
			list.addField(".e " + command.getId(), command.getHelp(), true);
		}
		event.getChannel().sendMessage(list.build()).queue();
		list.clear();
		
	}
	
	public EventHelp() {this.setId("help"); this.setHelp("Gives all commands for current event!");}

}
