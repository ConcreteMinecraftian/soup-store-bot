package com.concreteminecraftian.soupstorebot.economy.events.moon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;
import com.concreteminecraftian.soupstorebot.economy.Item;
import com.concreteminecraftian.soupstorebot.economy.events.EventCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Explore extends EventCommand {

	private static Random r = new Random();
	private Timer timer = new Timer();
	private ArrayList<Long> cooldown = new ArrayList<Long>();
	private int from;
	
	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		if(cooldown.contains(event.getMember().getIdLong())) {
			
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You are on cooldown! Wait 30 minutes from when you last used the command");
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
			
		} else {
			
			if(event.getMember().getIdLong() != 721287302797721690L) {	
				cooldown.add(event.getMember().getIdLong());
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						cooldown.remove(event.getMember().getIdLong());
						this.cancel();
					}
				}, 1000*30*60);
			}
			String altar = "";
			Item yield = Item.NullItem;
			
			switch(r.nextInt(3)) {
			
			case 0:
				if(from == 1) { altar="Seaside"; yield=Item.SoupMix11; }
				if(from == 2) { altar="Grassland"; yield=Item.SoupMix21; }
				break;
			case 1:
				if(from == 1) { altar="Underwater"; yield=Item.SoupMix12; }
				if(from == 2) { altar="Forest"; yield=Item.SoupMix22; }
				break;
			case 2:
				if(from == 1) { altar="Coral Reef"; yield=Item.SoupMix13; }
				if(from == 2) { altar="Cave"; yield=Item.SoupMix23; }
				break;
			
			}
			
			EmbedBuilder mix = IEmbedBuilder.createDefaultBuild((from == 1) ? Color.BLUE : Color.RED,
					"You searched the Ancient " + altar + " Soup altar!", "You found " + yield.getName());
			Main.accounts.get(event.getMember().getIdLong()).addItemToInventory(yield);
			event.getChannel().sendMessage(mix.build()).queue();
			mix.clear();
			
		}
		
	}
	
	public Explore(int from) { this.setId("explore"); this.setHelp("Explore some ancient soup altars!"); this.from = from; }
	
}
