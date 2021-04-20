package com.concreteminecraftian.soupstorebot.economy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.concreteminecraftian.soupstorebot.Global;
import com.concreteminecraftian.soupstorebot.IEmbedBuilder;
import com.concreteminecraftian.soupstorebot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JSONHashMapConverter extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "convert")) {
			
			if(!Main.accounts.containsKey(event.getMember().getIdLong())) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("Register by .register first!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				JSONObject json = (JSONObject) convertAccountToJson(Main.accounts.get(event.getMember().getIdLong()));
				event.getChannel().sendMessage(json.toJSONString()).queue();
				json.clear();
				
			}
			
		} else if(args[0].equalsIgnoreCase(Main.PREFIX + "save")) {
			
			if(!(event.getMember().getIdLong() == 721287302797721690L)) {
				EmbedBuilder error = IEmbedBuilder.createDefaultErrorBuild("You are not permitted to do that!");
				event.getChannel().sendMessage(error.build()).queue();
				error.clear();
			} else {
				
				for(Long l : Main.accounts.keySet()) {
					
					JSONObject json = (JSONObject) convertAccountToJson(Main.accounts.get(l));
					File file = new File("D:\\.json files\\SoupStoreBot\\" + l + ".json");
					try {
						file.createNewFile();
					    FileWriter wr = new FileWriter("D:\\.json files\\SoupStoreBot\\" + l + ".json");
					    wr.write(json.toJSONString());
					    wr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject convertAccountToJson(Account a) {
					
		JSONObject obj = new JSONObject();
		obj.put("area", a.getArea());
		obj.put("money", a.getMoney());
		JSONObject inv = new JSONObject();
		for(Item i : Global.RemoveDuplicates(a.getInventory())) {
			inv.put(i.getName(), Global.Amount(i, a.getInventory()));
		}
		obj.put("inventory", inv);
		JSONArray deck = new JSONArray();
		for(Item i : a.getDeck()) {
			deck.add(i.getName());
		}
		obj.put("deck", deck);
		
		return obj;
		
	}
	
	public static Account convertJsonToAccount(JSONObject json) {
		
		Account a = new Account();
		a.setMoney(Integer.parseInt(json.get("money").toString()));
		a.setArea(Integer.parseInt(json.get("area").toString()));
		for(Object i : ((JSONObject) json.get("inventory")).keySet()) {
			a.addItemsToInventory(Item.getItemByName(i.toString()),Integer.parseInt(((JSONObject) json.get("inventory")).get(i).toString()));
		}
		for(int i = 0; i < ((JSONArray) json.get("deck")).size(); i++) {
			Item it = Item.getItemByName(((String) ((JSONArray) json.get("deck")).get(i)));
			a.addItemToDeck(i, it);
		}
		
		return a;
		
	}
	
	public static HashMap<Long, Account> load() {
		
		HashMap<Long, Account> ret = new HashMap<Long, Account>();
		
		File dir = new File("D:\\.json files\\SoupStoreBot");
		JSONParser par = new JSONParser();
		
		for(File f : dir.listFiles()) {
			
			try(FileReader stream = new FileReader(f)) {
				
				BufferedReader br = new BufferedReader(stream);
				JSONObject parsed = (JSONObject) par.parse(br.readLine());
				ret.put(Long.parseLong(f.getName().replace(".json", "")), convertJsonToAccount(parsed));
				
				stream.close();
				br.close();
				
			} catch(IOException | ParseException e) {
				e.printStackTrace();
			}
			
		}
 		
		return ret;
		
	}

}
