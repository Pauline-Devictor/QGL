package fr.unice.polytech.si3.qgl.mugiwara_cook.tooling;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si3.qgl.mugiwara_cook.Cockpit;
import fr.unice.polytech.si3.qgl.mugiwara_cook.ship.Deck;

public class Application {
	
	public static void main(String [] args) {
		Cockpit cockpit = new Cockpit();
		cockpit.initGame("{\n" +
				"    \"type\": \"ship\",\n" +
				"    \"life\": 100,\n" +
				"    \"position\": {\n" +
				"      \"x\": 0,\n" +
				"      \"y\": 0,\n" +
				"      \"orientation\": 0\n" +
				"    },\n" +
				"    \"name\": \"Les copaings d'abord!\",\n" +
				"    \"deck\": {\n" +
				"      \"width\": 3,\n" +
				"      \"length\": 6\n" +
				"    },\n" +
				"    \"entities\": [\n" +
				"      {\n" +
				"        \"x\": 1,\n" +
				"        \"y\": 0,\n" +
				"        \"type\": \"oar\"\n" +
				"      },\n" +
				"      {\n" +
				"        \"x\": 1,\n" +
				"        \"y\": 2,\n" +
				"        \"type\": \"oar\"\n" +
				"      },\n" +
				"      {\n" +
				"        \"x\": 3,\n" +
				"        \"y\": 0,\n" +
				"        \"type\": \"oar\"\n" +
				"      },\n" +
				"      {\n" +
				"        \"x\": 3,\n" +
				"        \"y\": 2,\n" +
				"        \"type\": \"oar\"\n" +
				"      },\n" +
				"      {\n" +
				"        \"x\": 4,\n" +
				"        \"y\": 0,\n" +
				"        \"type\": \"oar\"\n" +
				"      },\n" +
				"      {\n" +
				"        \"x\": 4,\n" +
				"        \"y\": 2,\n" +
				"        \"type\": \"oar\"\n" +
				"      },\n" +
				"      {\n" +
				"        \"x\": 2,\n" +
				"        \"y\": 1,\n" +
				"        \"type\": \"sail\",\n" +
				"        \"openned\": false\n" +
				"      },\n" +
				"      {\n" +
				"        \"x\": 5,\n" +
				"        \"y\": 0,\n" +
				"        \"type\": \"rudder\"\n" +
				"      }\n" +
				"    ],\n" +
				"    \"shape\": {\n" +
				"      \"type\": \"rectangle\",\n" +
				"      \"width\": 3,\n" +
				"      \"height\": 6,\n" +
				"      \"orientation\": 0\n" +
				"    }\n" +
				"  \n" +
				"}");
		System.out.println("An instance of my team player: " + cockpit);
		System.out.println("When called, it returns some JSON: " + cockpit.nextRound(""));
	}
}
