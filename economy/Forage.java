package com.concreteminecraftian.soupstorebot.economy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Forage extends ListenerAdapter {
	
	private Timer schedule = new Timer();
	private List<Long> oncooldown = new ArrayList<Long>();
	
	@SuppressWarnings("unused")
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "forage")) {
			
			if(oncooldown.contains(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You are on cooldown! Wait 5 minutes from when you last used the command");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				if(/*event.getMember().getIdLong() != 721287302797721690L*/false) {
					oncooldown.add(event.getMember().getIdLong());
					schedule.schedule(new TimerTask() {
						@Override
						public void run() {
							oncooldown.remove(event.getMember().getIdLong());
						}
					}, 1000*5*60);
				}
				
				ArrayList<Item> get = new ArrayList<Item>();
				Random r = new Random();
				
				switch(Main.accounts.get(event.getMember().getIdLong()).getArea()) {
				
				case 1:
					if(r.nextInt(1000) > 100) get.add(Item.Leaf);
					if(r.nextInt(1000) > 500) get.add(Item.Potato);
					if(r.nextInt(1000) > 900) get.add(Item.Tomato);
					if(r.nextInt(1000) > 950) get.add(Item.BetterLeaf);
				
					if(r.nextInt(1000) > 100) get.add(Item.Water);
					if(r.nextInt(1000) > 500) get.add(Item.Water);
					if(r.nextInt(1000) > 950) get.add(Item.Water);
				
					if(r.nextInt(1000) > 100) get.add(Item.Clam);
					if(r.nextInt(1000) > 500) get.add(Item.Squid);
					if(r.nextInt(1000) > 900) get.add(Item.Chicken);
					if(r.nextInt(1000) > 950) get.add(Item.PrimeMeat);
					break;
					
				case 2:
					if(r.nextInt(1000) > 100) get.add(Item.Leaf);
					if(r.nextInt(1000) > 500) get.add(Item.Potato);
					if(r.nextInt(1000) > 900) get.add(Item.Tomato);
					if(r.nextInt(1000) > 950) get.add(Item.BetterLeaf);
				
					if(r.nextInt(1000) > 100) get.add(Item.Water);
					if(r.nextInt(1000) > 500) get.add(Item.Water);
					if(r.nextInt(1000) > 950) get.add(Item.Water);
				
					if(r.nextInt(1000) > 100) get.add(Item.Clam);
					if(r.nextInt(1000) > 500) get.add(Item.Squid);
					if(r.nextInt(1000) > 900) get.add(Item.Chicken);
					if(r.nextInt(1000) > 950) get.add(Item.PrimeMeat);
					
					if(r.nextInt(1000) > 100) get.add(Item.MysticalMushroom);
					if(r.nextInt(1000) > 500) get.add(Item.SoupFlower);
					if(r.nextInt(1000) > 900) get.add(Item.Anchovy);
					if(r.nextInt(1000) > 950) get.add(Item.SoupEssence);
					break;
				
				}
				
				EmbedBuilder list = IEmbedBuilder.createDefaultListBuild(Color.GREEN, "Foraged Items:- ");
				for(Item i : get) {
					Main.accounts.get(event.getMember().getIdLong()).addItemToInventory(i);
					list.addField("", i.getName(), true);
				}
				
				event.getChannel().sendMessage(list.build()).queue();
				list.clear();
				
			}
			
		}
		
	}
	
}
