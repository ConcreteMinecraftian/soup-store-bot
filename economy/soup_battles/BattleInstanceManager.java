package com.concreteminecraftian.soupstorebot.economy.soup_battles;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BattleInstanceManager {
	
	private ArrayList<BattleInstance> battles = new ArrayList<BattleInstance>();
	private Timer taskScheduler = new Timer();
	private boolean ticking;

	public ArrayList<BattleInstance> getBattles() {
		return battles;
	}

	public void add(BattleInstance instance) {
		battles.add(instance);
	}
	
	public void remove(BattleInstance instance) {
		battles.remove(instance);
	}
	
	public void tick() {
		
		if(battles.isEmpty() || !ticking) {
			ticking = false;
			return;
		}
		else ticking = true;
		
		for(int i = 0; i < battles.size(); i++) {
			battles.get(i).tickInstance();
		}
		taskScheduler.schedule(new TimerTask() {
			@Override
			public void run() {
				tick();
				this.cancel();
			}
		}, 250);
		
	}
	
	public boolean isTicking() {
		return ticking;
	}
	
}
