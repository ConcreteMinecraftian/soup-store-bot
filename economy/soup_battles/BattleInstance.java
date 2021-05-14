package com.concreteminecraftian.soupstorebot.economy.soup_battles;

import java.util.Random;

import com.concreteminecraftian.soupstorebot.economy.Account;
import com.concreteminecraftian.soupstorebot.economy.ItemStats;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class BattleInstance {
	
	// Values for battle system
	private ItemStats you, opponent;
	private TextChannel send;
	private Message deal, health;
	private String youN, opponentN;
	private Random rand = new Random();
	private BattleInstanceManager manager;
	private Account youAcc, oppAcc;
	
	public BattleInstance(ItemStats you, ItemStats opponent, TextChannel send, Message deal, Message health, String nick, String opponentNick, BattleInstanceManager manager, Account you2, Account opponent2) {
		this.you = you;
		this.opponent = opponent;
		this.deal = deal;
		this.health = health;
		this.send = send;
		youN = nick;
		opponentN = opponentNick;
		this.manager = manager;
		this.youAcc = you2;
		this.oppAcc = opponent2;
	}
	
	public void tickInstance() {
		
		if(you.getMaxhp() <= 0.0) {
			send.sendMessage(opponentN + " won the battle!").queue();
			manager.remove(this);
			oppAcc.win++;
			youAcc.loss++;
			return;
		}
		if(opponent.getMaxhp() <= 0.0) {
			send.sendMessage(youN + " won the battle!").queue();
			manager.remove(this);
			youAcc.win++;
			oppAcc.loss++;
			return;
		}
		
		if(you.getSpeed() > opponent.getSpeed()) {
			double random = (rand.nextInt(15) + 85.0) / 100.0;
			double dmg = ((random * 5 * you.getAttack()) / (opponent.getDefence()));
			dmg = 0.1 * Math.round(dmg * 10.0);
			if(rand.nextInt(100) < you.getCritrate()) dmg*=2;
			if(rand.nextInt(100) < opponent.getStealth()) dmg=0;
			opponent.setMaxhp(opponent.getMaxhp() - dmg);
			deal.editMessage(youN + " deals " + dmg + " damage!").queue();
			health.editMessage(opponentN + " is at " + opponent.getMaxhp() + " health!").queue();
			
			if(opponent.getMaxhp() <= 0.0) {
				send.sendMessage(youN + " won the battle!").queue();
				manager.remove(this);
				youAcc.win++;
				oppAcc.loss++;
				return;
			}		
			
			random = (rand.nextInt(15) + 85.0) / 100.0;
			dmg = ((random * 5 * opponent.getAttack()) / (you.getDefence()));
			dmg = 0.1 * Math.round(dmg * 10.0);
			if(rand.nextInt(100) < opponent.getCritrate()) dmg*=2;
			if(rand.nextInt(100) < you.getStealth()) dmg=0;
			you.setMaxhp(you.getMaxhp() - dmg);
			deal.editMessage(opponentN + " deals " + dmg + " damage!").queue();
			health.editMessage(youN + " is at " + you.getMaxhp() + " health!").queue();
		} else {
			double random = (rand.nextInt(15) + 85.0) / 100.0;
			double dmg = ((random * 5 * opponent.getAttack()) / (you.getDefence()));
			dmg = 0.1 * Math.round(dmg * 10.0);
			if(rand.nextInt(100) < opponent.getCritrate()) dmg*=2;
			if(rand.nextInt(100) < you.getStealth()) dmg=0;
			you.setMaxhp(you.getMaxhp() - dmg);
			deal.editMessage(opponentN + " deals " + dmg + " damage!").queue();
			health.editMessage(youN + " is at " + you.getMaxhp() + " health!").queue();
			
			if(you.getMaxhp() <= 0.0) {
				send.sendMessage(opponentN + " won the battle!").queue();
				manager.remove(this);
				oppAcc.win++;
				youAcc.loss++;
				return;
			}
			
			random = (rand.nextInt(15) + 85.0) / 100.0;
			dmg = ((random * 5 * you.getAttack()) / (opponent.getDefence()));
			dmg = 0.1 * Math.round(dmg * 10.0);
			if(rand.nextInt(100) < you.getCritrate()) dmg*=2;
			if(rand.nextInt(100) < opponent.getStealth()) dmg=0;
			opponent.setMaxhp(opponent.getMaxhp() - dmg);
			deal.editMessage(youN + " deals " + dmg + " damage!").queue();
			health.editMessage(opponentN + " is at " + opponent.getMaxhp() + " health!").queue();
		}
		
	}

}
