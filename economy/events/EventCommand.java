package com.concreteminecraftian.soupstorebot.economy.events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class EventCommand {

	private String id;
	private String help;
	private Event event = Event.NULL;
	public String getId() { return id; }
	public String getHelp() { return help; }
	public Event getEvent() { return event; }
	public void setId(String id) { this.id = id; }
	public void setHelp(String help) { this.help = help; }
	public void setEvent(Event event) { this.event = event; }
	public abstract void run(GuildMessageReceivedEvent event, String[] args);
	
}
