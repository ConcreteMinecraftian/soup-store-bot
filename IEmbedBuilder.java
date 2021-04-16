package com.concreteminecraftian.soupstorebot;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;

public class IEmbedBuilder {

	public static EmbedBuilder createDefaultErrorBuild(String error) {
		
		EmbedBuilder b = new EmbedBuilder();
		
		b.setColor(Color.RED);
		b.setTitle("**ERROR**");
		b.setDescription(error);
		
		return b;
	}

	public static EmbedBuilder createDefaultListBuild(Color c, String title) {
		 
		EmbedBuilder b = new EmbedBuilder();
		
		b.setColor(c);
		b.setTitle(title);
		b.setDescription("Items:- ");
		
		return b;
	}

	public static EmbedBuilder createDefaultBuild(Color c, String title, String description) {
		 
		EmbedBuilder b = new EmbedBuilder();
		
		b.setColor(c);
		b.setTitle(title);
		b.setDescription(description);
		
		return b;
		
	}
	
}
