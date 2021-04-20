package com.concreteminecraftian.soupstorebot.economy.soup_battles;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;
import com.concreteminecraftian.soupstorebot.economy.Item;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Battle extends BattleCommand {

	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		boolean full = true;
		for(Item i : Main.accounts.get(event.getMember().getIdLong()).getDeck()) {
			if(i == Item.NullItem) full = false;
		}
		if(!full) {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have a full deck!");
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
		} else {
			
			
			
		}
		
	}
	
	public Battle() {
		this.setId("battle");
	}

}
