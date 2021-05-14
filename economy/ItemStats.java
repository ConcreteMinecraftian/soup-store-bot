package com.concreteminecraftian.soupstorebot.economy;

import java.util.HashMap;

public class ItemStats {
	
	private int attack = 0;
	private int defence = 0;
	private int speed = 0;
	private int critrate = 0;
	private int stealth = 0;
	private double maxhp = 0;

	public ItemStats createStats(int atk, int def, int spd, int crit, int stlth, int hp) {
		attack = atk;
		defence = def;
		speed = spd;
		critrate = crit;
		stealth = stlth;
		maxhp = hp;
		return this;
	}
	
	public void addStats(ItemStats stats) {
		
		attack = attack + stats.getAttack();
		defence = defence + stats.getDefence();
		speed = speed + stats.getSpeed();
		critrate = critrate + stats.getCritrate();
		stealth = stealth + stats.getStealth();
		maxhp = maxhp + stats.getMaxhp();
		
	}

	public double getMaxhp() {
		if(maxhp < 0) maxhp = 0;
		return maxhp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}

	public int getSpeed() {
		return speed;
	}

	public int getCritrate() {
		return critrate;
	}

	public int getStealth() {
		return stealth;
	}
	
	public void setMaxhp(double i) {
		maxhp=i;
	}
	
	public HashMap<String, Integer> asMap() {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("Attack:- ", attack);
		map.put("Defence:- ", defence);
		map.put("Speed:- ", speed);
		map.put("Crit Rate:- ", critrate);
		map.put("Stealth:- ", stealth);
		map.put("Max HP:- ", (int) Math.round(maxhp));
		
		return map;
		
	}
	
}
