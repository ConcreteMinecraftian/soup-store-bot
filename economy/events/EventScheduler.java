package com.concreteminecraftian.soupstorebot.economy.events;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.concreteminecraftian.soupstorebot.Global;

public class EventScheduler {

	private static Random r = new Random();
	private static Timer eventClock = new Timer();
	
	public static void changeEvent() {
		
		for(Event.EventRarity rarity : Event.EventRarity.values()) {
			if(r.nextInt(100) < rarity.getChance()) {
				Event e = Global.randomIn(r, Event.sortByRarity(rarity));
				if(e == null) break;
				EventManager.current = e;
				System.out.println(e.getName());
				return;
			}
		}
		EventManager.current = Event.NULL;
	}
	
	public static void startTickingEventClock() {
		
		eventClock.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				changeEvent();
			}
		}, 0, 1000/*milliseconds to seconds*/*60/*seconds to minutes*/*60/*minutes to hours*/*6/*6 hours*/);
	}
	
}
