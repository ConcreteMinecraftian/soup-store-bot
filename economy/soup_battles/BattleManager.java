package com.concreteminecraftian.soupstorebot.economy.soup_battles;

import java.util.Arrays;
import java.util.List;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BattleManager extends ListenerAdapter {

	public List<BattleCommand> commands = Arrays.asList(new BattleHelp(),new BattleDeck(),
	new Battle(), new BattleStats());
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		if(args[0].equalsIgnoreCase(Main.PREFIX + "battle") || args[0].equalsIgnoreCase(Main.PREFIX + "b")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
				return;
			}
			
			String label = (args.length < 2) ? "help" : args[1];
			
			for(BattleCommand c : commands) {
				if(c.getId().equalsIgnoreCase(label)) {
					c.run(event, args);
					return;
				}
			}
			
		}
		
	}
	
}
