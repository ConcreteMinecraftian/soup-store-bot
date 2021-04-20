package com.concreteminecraftian.soupstorebot.economy.soup_battles;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class BattleCommand {

	private String id;
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public abstract void run(GuildMessageReceivedEvent event, String[] args);
	
}
