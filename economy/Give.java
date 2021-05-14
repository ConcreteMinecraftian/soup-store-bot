package com.concreteminecraftian.soupstorebot.economy;

import java.awt.Color;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Give extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "give")) {
			
			if(args.length < 3) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Insuffifient arguments.");
				error.addField("Correct usage:- ", Main.PREFIX + "give <item> [number] <person>", false);
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(Item.getItemByName(args[1]) == null) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("That item doesnt exist.");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first.");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(!Main.accounts.get(event.getMember().getIdLong()).getInventory().contains(Item.getItemByName(args[1]))) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have that item!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(event.getMessage().getMentionedMembers().size() == 0) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You need to mention a member to give to!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else if(!Main.accounts.containsKey(event.getMessage().getMentionedMembers().get(0).getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("The person you are giving to isnt registered!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				Item item = Item.getItemByName(args[1]);
				Long person = event.getMessage().getMentionedMembers().get(0).getIdLong();
				int nbt = 2;
				try {
					nbt = (args.length > 3) ? Integer.parseInt(args[2]) : 1;
				} catch(NumberFormatException e) {
					EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Third argument should be a number.");
					event.getChannel().sendMessage(error.build()).queue();
					error.clear();
					return;
				}
				
				for(int i = 0; i < nbt; i++) {
					if(!Main.accounts.get(event.getMember().getIdLong()).getInventory().contains(item))
						break;
					Main.accounts.get(event.getMember().getIdLong()).getInventory().remove(item);
					Main.accounts.get(person).addItemToInventory(item);
				}
				
				EmbedBuilder success = IEmbedBuilder.createDefaultBuild(Color.GREEN, "Successful gift!", "You gave " + nbt + "x " + item.getName() + "s");
				event.getChannel().sendMessage(success.build()).queue();
				success.clear();
				
			}
			
		}
		
	}
	
}
