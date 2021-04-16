package com.concreteminecraftian.soupstorebot.economy;

import net.dv8tion.jda.api.entities.TextChannel;

@FunctionalInterface
public interface ItemInteraction {

	public void onItemUse(Account a, TextChannel send);
	
}
