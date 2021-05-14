package com.concreteminecraftian.soupstorebot.economy.events;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventManager extends ListenerAdapter {

	public static Event current = Event.NULL;
	private EventCommand help = new EventHelp();
				
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		if(args[0].equalsIgnoreCase(Main.PREFIX + "event") || args[0].equalsIgnoreCase(Main.PREFIX + "e")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
				return;
			}
			
			String label = (args.length < 2) ? "help" : args[1];
			
			if(label.equalsIgnoreCase("help" )) {
				help.run(event, args);
				return;
			}
			for(EventCommand e : current.getCommands()) {
				if(e.getId().equalsIgnoreCase(label)) {
					e.run(event, args);
					return;
				}
			}
			
		}
		
	}
		
}
