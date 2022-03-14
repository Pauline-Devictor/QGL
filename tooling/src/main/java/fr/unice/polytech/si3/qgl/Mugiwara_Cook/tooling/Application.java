package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        basicSimulation();
    }

    public static void basicSimulation() {
        Map map = new Map();
        map.run();
    }

    public static void simulation() {
        Cockpit cockpit = new Cockpit();
        cockpit.initGame("{\"goal\":{\"mode\":\"REGATTA\",\"checkpoints\":[{\"position\":{\"x\":2279.6317218053687,\"y\":2879.047015765768,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":100.0}},{\"position\":{\"x\":2256.4589017735334,\"y\":1352.1959459459492,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":100.0}},{\"position\":{\"x\":3590.3073840382,\"y\":2221.734234234234,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":80.0}},{\"position\":{\"x\":2241.8126847430635,\"y\":1345.6855292792793,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":100.0}},{\"position\":{\"x\":1132.5943610732181,\"y\":2342.236768018018,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":80.0}}]},\"ship\":{\"type\":\"ship\",\"position\":{\"x\":2185.3061761027725,\"y\":-227.86458333333408,\"orientation\":1.5882496193148399},\"name\":\"Mugiwara_Cook\",\"deck\":{\"width\":3,\"length\":4},\"entities\":[{\"x\":0,\"y\":0,\"type\":\"oar\"},{\"x\":0,\"y\":2,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":1,\"y\":2,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":2,\"type\":\"oar\"},{\"x\":3,\"y\":1,\"type\":\"rudder\"}],\"life\":300,\"shape\":{\"type\":\"rectangle\",\"width\":3.0,\"height\":4.0,\"orientation\":0.0}},\"sailors\":[{\"x\":0,\"y\":0,\"id\":0,\"name\":\"Luffy Pouce\"},{\"x\":0,\"y\":1,\"id\":1,\"name\":\"Edward Teach\"},{\"x\":0,\"y\":2,\"id\":2,\"name\":\"Luffy Teach\"},{\"x\":1,\"y\":0,\"id\":3,\"name\":\"Tom Teach\"},{\"x\":1,\"y\":1,\"id\":4,\"name\":\"Jack Pouce\"},{\"x\":1,\"y\":2,\"id\":5,\"name\":\"Luffy Pouce\"},{\"x\":2,\"y\":0,\"id\":6,\"name\":\"Edward Pouce\"}],\"shipCount\":1}\n");
        System.out.println("An instance of my team player: " + cockpit);
        System.out.println("When called, it returns some JSON:");
        cockpit.nextRound("{\n" +
                "  \"ship\": {\n" +
                "    \"type\": \"ship\",\n" +
                "    \"life\": 100,\n" +
                "    \"position\": {\n" +
                "      \"x\":  0,\n" +
                "      \"y\":  0,\n" +
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
    }
}
