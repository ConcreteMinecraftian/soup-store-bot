package com.concreteminecraftian.soupstorebot.economy.events.potato_rain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;
import com.concreteminecraftian.soupstorebot.economy.Item;
import com.concreteminecraftian.soupstorebot.economy.events.EventCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Grab extends EventCommand {

	private Timer schedule = new Timer();
	private List<Long> oncooldown = new ArrayList<Long>();
	private Random r = new Random();
	
	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		if(oncooldown.contains(event.getMember().getIdLong())) {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You are on cooldown! Wait 5 minutes from when you last used the command");
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
		} else {
			
			if(/*event.getMember().getIdLong() != 721287302797721690L*/true) {
				oncooldown.add(event.getMember().getIdLong());
				schedule.schedule(new TimerTask() {
					@Override
					public void run() {
						oncooldown.remove(event.getMember().getIdLong());
						this.cancel();
					}
				}, 1000*5*60);
			}
			
			ArrayList<Item> get = new ArrayList<>();
			
			for(int i = 0; i < 2; i++) {
				if(r.nextInt(1000) > 100) get.add(Item.Potato);
				if(r.nextInt(1000) > 500) get.add(Item.PoisonousPotato);
				if(r.nextInt(1000) > 900) get.add(Item.BetterPotato);
				if(r.nextInt(1000) > 950) get.add(Item.GlisteningPotato);
			}
			
			EmbedBuilder list = IEmbedBuilder.createDefaultListBuild(Color.GREEN, "Grabbed Items:- ");
			list.setDescription("Event:- Potato Rain");
			for(Item i : get) {
				Main.accounts.get(event.getMember().getIdLong()).addItemToInventory(i);
				list.addField("", i.getName(), true);
			}
			
			event.getChannel().sendMessage(list.build()).queue();
			list.clear();
			
		}
		
	}
	
	public Grab() { this.setId("grab"); this.setHelp("Grab Potatoes out of mid air."); }
	
}
