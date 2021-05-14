package com.concreteminecraftian.soupstorebot.economy.soup_battles;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Battle extends BattleCommand {
	
//	private BattleInstanceManager battles = new BattleInstanceManager();
	
	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
//		if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
//			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first!");
//			event.getChannel().sendMessage(error.build()).queue();
//			error.clear();
//		} else {
//			
//			long opponent = 0L;
//			try {
//				opponent = event.getMessage().getMentionedMembers().get(0).getIdLong();
//			} catch(IndexOutOfBoundsException e) {
//				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Mention an opponent to battle!");
//				event.getChannel().sendMessage(error.build()).queue();
//				error.clear();
//				return;
//			}
//			if(!Main.accounts.containsKey(opponent)) {
//				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Your opponent isnt registered!");
//				event.getChannel().sendMessage(error.build()).queue();
//				error.clear();
//			} else {
//				start(event.getMember(),event.getMessage().getMentionedMembers().get(0),event.getChannel());
//			}
//			
//		}
//		
//	}
//
//	public void start(Member you, Member opponent, TextChannel send) {
//		
//		ItemStats stat1 = Main.accounts.get(you.getIdLong()).stats();
//		ItemStats stat2 = Main.accounts.get(opponent.getIdLong()).stats();
//		
//		send.sendMessage("Battle starts between " + you.getAsMention() + " and " + opponent.getAsMention()).queue();
//		
//		Message deal = send.sendMessage("temporary message").complete();
//		Message health = send.sendMessage("temporary message").complete();
//		
//		battles.add(new BattleInstance(stat1, stat2, send, deal, health, you.getEffectiveName(), opponent.getEffectiveName(), battles, Main.accounts.get(you.getIdLong()), Main.accounts.get(opponent.getIdLong())));
//		if(!battles.isTicking()) {
//			battles.tick();
//		}
		
	}
	
	public Battle() {
		this.setId("battle");
	}

}
