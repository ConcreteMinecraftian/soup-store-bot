package com.concreteminecraftian.soupstorebot.economy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Beg extends ListenerAdapter {

	private String[] names = new String[] {"Ben Dover","Mike Hunt","Mike Hawk","Knee Grow"};
	private String[] reject = new String[] {"Go beg somewhere else.","Your going to spend it all on alcohol, so no.",
	"Get a job.","Ive got better things to spend my money on.","Im not going to waste hard-earned cash on you.",
	"Go away.","Youre too annoying. Just go away."};
	private Random rand = new Random();
	
	private ArrayList<Long> cooldown = new ArrayList<Long>();
	private Timer schedule = new Timer();
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "beg")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(cooldown.contains(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You are on cooldown. Wait 1 minute since you last used the command.");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				EmbedBuilder result = IEmbedBuilder.createDefaultBuild(Color.BLACK, "Begging results:-", "");
				if(rand.nextInt(1000) > 500) {
					result.addField("**" + this.randomStringFromArray(rand, names) + "**: " + this.randomStringFromArray(rand, reject),"",false);
				} else {
					int got = rand.nextInt(100);
					result.addField("**" + this.randomStringFromArray(rand, names) + "** gave you " + got,"",false);
					Main.accounts.get(event.getMember().getIdLong()).addMoney(got);
				}
				event.getChannel().sendMessage(result.build()).queue();
				cooldown.add(event.getMember().getIdLong());
				schedule.schedule(new TimerTask() {
					@Override
					public void run() {
						cooldown.remove(event.getMember().getIdLong());
					}
				}, 1000*60);
				
			}
			
		}
		
	}
	
	private String randomStringFromArray(Random r, String[] from) {
		return from[r.nextInt(from.length)];
	}
	
}
