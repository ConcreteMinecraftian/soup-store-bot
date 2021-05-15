package com.concreteminecraftian.soupstorebot.economy.events.moon;

import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;
import com.concreteminecraftian.soupstorebot.economy.Item;
import com.concreteminecraftian.soupstorebot.economy.crafting.Recipe;
import com.concreteminecraftian.soupstorebot.economy.events.Event;
import com.concreteminecraftian.soupstorebot.economy.events.EventCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Mix extends EventCommand {
	
	private Recipe[] soupMixRecipes = new Recipe[] {
			new Recipe(Item.MoonSoup111,"",Item.SoupMix11,Item.SoupMix11).setExclusiveTo(Event.BlueMoon),
			new Recipe(Item.MoonSoup122,"",Item.SoupMix12,Item.SoupMix12).setExclusiveTo(Event.BlueMoon),
			new Recipe(Item.MoonSoup133,"",Item.SoupMix13,Item.SoupMix13).setExclusiveTo(Event.BlueMoon),
			new Recipe(Item.MoonSoup112,"",Item.SoupMix11,Item.SoupMix12).setExclusiveTo(Event.BlueMoon),
			new Recipe(Item.MoonSoup123,"",Item.SoupMix12,Item.SoupMix13).setExclusiveTo(Event.BlueMoon),
			new Recipe(Item.MoonSoup113,"",Item.SoupMix11,Item.SoupMix13).setExclusiveTo(Event.BlueMoon),
			new Recipe(Item.MoonSoup211,"",Item.SoupMix21,Item.SoupMix21).setExclusiveTo(Event.BloodMoon),
			new Recipe(Item.MoonSoup222,"",Item.SoupMix22,Item.SoupMix22).setExclusiveTo(Event.BloodMoon),
			new Recipe(Item.MoonSoup233,"",Item.SoupMix23,Item.SoupMix23).setExclusiveTo(Event.BloodMoon),
			new Recipe(Item.MoonSoup212,"",Item.SoupMix21,Item.SoupMix22).setExclusiveTo(Event.BloodMoon),
			new Recipe(Item.MoonSoup223,"",Item.SoupMix22,Item.SoupMix23).setExclusiveTo(Event.BloodMoon),
			new Recipe(Item.MoonSoup213,"",Item.SoupMix21,Item.SoupMix23).setExclusiveTo(Event.BloodMoon),
	}; 

	@Override
	public void run(GuildMessageReceivedEvent event, String[] args) {
		
		if(args.length < 4) {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Incorrect usage!");
			error.addField("Correct Usage:- ", ".e mix <soup mix 1> <soup mix 2>", false);
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
		} else if(Item.getItemByName("Ancient_"+args[2]+"_Soup_Mix") == null || Item.getItemByName("Ancient_"+args[3]+"_Soup_Mix") == null) {
			System.out.println(args[2]);
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Incorrect usage!");
			error.addField("Correct Usage:- ", ".e mix <soup mix 1> <soup mix 2> (You dont have to write Ancient_Soup_Mix for soup mix)", false);
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
		} else if(!Main.accounts.get(event.getMember().getIdLong()).getInventory().contains(Item.getItemByName("Ancient_"+args[2]+"_Soup_Mix"))) {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have that soup mix!");
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
		} else if(!Main.accounts.get(event.getMember().getIdLong()).getInventory().contains(Item.getItemByName("Ancient_"+args[3]+"_Soup_Mix"))) {
			EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You dont have that soup mix!");
			event.getChannel().sendMessage(error.build()).queue();
			error.clear();
		} else {
			
			Item SoupMix1 = Item.getItemByName("Ancient_"+args[2]+"_Soup_Mix");
			Item SoupMix2 = Item.getItemByName("Ancient_"+args[3]+"_Soup_Mix");
			int eventnum = Integer.parseInt(""+SoupMix1.name().charAt(7));
			if(eventnum != Integer.parseInt(""+SoupMix2.name().charAt(7))) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You cant combine these soup mixes!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
				return;
			}
			int soupnum = Integer.parseInt(""+SoupMix1.name().charAt(8));
			int soupnum2 = Integer.parseInt(""+SoupMix2.name().charAt(8));
			for(Recipe r : soupMixRecipes) {
				if(r.getYield().name().equalsIgnoreCase("MoonSoup"+eventnum+soupnum+soupnum2) || r.getYield().name().equalsIgnoreCase("MoonSoup"+eventnum+soupnum2+soupnum)) {
					r.craft(event.getMember().getIdLong(), event.getChannel());
				}
			}
			System.out.println("over");
			
		}
		
	}
	
	public Mix() { this.setId("mix"); this.setHelp("Mix 2 ancient soup mixes together!"); }
	
}
