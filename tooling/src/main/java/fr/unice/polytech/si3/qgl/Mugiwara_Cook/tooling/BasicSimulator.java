package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.game.InitGame;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.shipmaster.captainNextMove.allmoves.Moves;

public class BasicSimulator {
    Cockpit cockpit = new Cockpit();
    double x = 0;
    double y = 0;
    double orientation = 0;

    public BasicSimulator() {
        cockpit.initGame("{\n" +
                "  \"goal\": {\n" +
                "    \"mode\": \"REGATTA\",\n" +
                "    \"checkpoints\": [\n" +
                "      {\n" +
                "        \"position\": {\n" +
                "          \"x\": 0,\n" +
                "          \"y\": -500,\n" +
                "          \"orientation\": 0\n" +
                "        },\n" +
                "        \"shape\": {\n" +
                "          \"type\": \"circle\",\n" +
                "          \"radius\": 50\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        \"position\": {\n" +
                "          \"x\": 600,\n" +
                "          \"y\": -80,\n" +
                "          \"orientation\": 0\n" +
                "        },\n" +
                "        \"shape\": {\n" +
                "          \"type\": \"circle\",\n" +
                "          \"radius\": 50\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"ship\": {\n" +
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
                "  },\n" +
                "  \"sailors\": [\n" +
                "    {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 0,\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Edward Teach\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 1,\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Edward Pouce\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"x\": 0,\n" +
                "      \"y\": 2,\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Tom Pouce\"\n" +
                "    },\n" +
				"    {\n" +
				"      \"x\": 1,\n" +
				"      \"y\": 0,\n" +
				"      \"id\": 3,\n" +
				"      \"name\": \"Jack Teach\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"x\": 1,\n" +
				"      \"y\": 1,\n" +
				"      \"id\": 4,\n" +
				"      \"name\": \"Jack Teach\"\n" +
				"    },\n" +
                "    {\n" +
                "      \"x\": 1,\n" +
                "      \"y\": 2,\n" +
                "      \"id\": 5,\n" +
                "      \"name\": \"Tom Pouce\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"shipCount\": 1\n" +
                "}"); System.out.println("An instance of my team player: " + cockpit);
        System.out.println("When called, it returns some JSON:");
    }

    public void simu() {
        Moves moves = cockpit.nextRoundSimu("{\n" +
                "  \"ship\": {\n" +
                "    \"type\": \"ship\",\n" +
                "    \"life\": 100,\n" +
                "    \"position\": {\n" +
                "      \"x\":  "+x+",\n" +
                "      \"y\":  "+y+",\n" +
                "      \"orientation\": "+orientation+"\n" +
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
                "  },\n" +
                "  \"visibleEntities\": [\n" +
                "    {\n" +
                "      \"type\": \"stream\",\n" +
                "      \"position\": {\n" +
                "        \"x\": 500,\n" +
                "        \"y\": 0,\n" +
                "        \"orientation\": 0\n" +
                "      },\n" +
                "      \"shape\": {\n" +
                "        \"type\": \"rectangle\",\n" +
                "        \"width\": 50,\n" +
                "        \"height\": 500,\n" +
                "        \"orientation\": 0\n" +
                "      },\n" +
                "      \"strength\": 40\n" +
                "    }\n" +
                "  ],\n" +
                "  \"wind\": {\n" +
                "    \"orientation\": 0,\n" +
                "    \"strength\": 110\n" +
                "  }\n" +
                "}");
        x = moves.getX();
        y = moves.getY();
        orientation = moves.getOrientation();

        System.out.print(" ["+x+","+y+","+orientation+"],");
    }
}
