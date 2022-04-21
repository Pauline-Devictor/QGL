package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Display;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.geometry.Position;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        simulation();
    }

    public static void basicSimulation() {
        Map map = new Map();
        map.run();
    }

    public static void simulation() {
        Cockpit cockpit = new Cockpit();
        cockpit.initGame("{\"goal\":{\"mode\":\"REGATTA\",\"checkpoints\":[{\"position\":{\"x\":10006.518904824004,\"y\":3131.510416666667,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":200.0}},{\"position\":{\"x\":3552.8031290743315,\"y\":-2532.552083333334,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":200.0}},{\"position\":{\"x\":9719.687092568467,\"y\":514.3229166666661,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":200.0}},{\"position\":{\"x\":4478.487614080851,\"y\":4980.468749999999,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":200.0}}]},\"ship\":{\"type\":\"ship\",\"position\":{\"x\":1336.3754889178724,\"y\":735.6770833333329,\"orientation\":0.0},\"name\":\"Mugiwara_Cook\",\"deck\":{\"width\":4,\"length\":8},\"entities\":[{\"x\":1,\"y\":3,\"type\":\"oar\"},{\"x\":2,\"y\":3,\"type\":\"oar\"},{\"x\":3,\"y\":3,\"type\":\"oar\"},{\"x\":4,\"y\":3,\"type\":\"oar\"},{\"x\":5,\"y\":3,\"type\":\"oar\"},{\"x\":6,\"y\":3,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":2,\"type\":\"rudder\"},{\"x\":3,\"y\":1,\"type\":\"sail\",\"openned\":false},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":3,\"type\":\"oar\"}],\"life\":1600,\"shape\":{\"type\":\"rectangle\",\"width\":4.0,\"height\":8.0,\"orientation\":0.0}},\"sailors\":[{\"x\":0,\"y\":0,\"id\":0,\"name\":\"Tom Teach\"},{\"x\":0,\"y\":1,\"id\":1,\"name\":\"Luffy Pouce\"},{\"x\":0,\"y\":2,\"id\":2,\"name\":\"Luffy Pouce\"},{\"x\":0,\"y\":3,\"id\":3,\"name\":\"Edward Teach\"},{\"x\":1,\"y\":0,\"id\":4,\"name\":\"Edward Teach\"},{\"x\":1,\"y\":1,\"id\":5,\"name\":\"Tom Teach\"},{\"x\":1,\"y\":2,\"id\":6,\"name\":\"Tom Teach\"},{\"x\":1,\"y\":3,\"id\":7,\"name\":\"Jack Teach\"},{\"x\":2,\"y\":0,\"id\":8,\"name\":\"Luffy Teach\"},{\"x\":2,\"y\":1,\"id\":9,\"name\":\"Jack Teach\"},{\"x\":2,\"y\":2,\"id\":10,\"name\":\"Tom Teach\"},{\"x\":2,\"y\":3,\"id\":11,\"name\":\"Edward Pouce\"},{\"x\":3,\"y\":0,\"id\":12,\"name\":\"Tom Pouce\"},{\"x\":3,\"y\":1,\"id\":13,\"name\":\"Jack Pouce\"},{\"x\":3,\"y\":2,\"id\":14,\"name\":\"Tom Teach\"},{\"x\":3,\"y\":3,\"id\":15,\"name\":\"Jack Teach\"}],\"shipCount\":1}");
        Display.info("An instance of my team player: " + cockpit);
        Display.info("When called, it returns some JSON:");
        cockpit.nextRound("{\"ship\":{\"type\":\"ship\",\"position\":{\"x\":2603.4537686213143,\"y\":1052.6158079084698,\"orientation\":0.27376461910862165},\"name\":\"Mugiwara_Cook\",\"deck\":{\"width\":4,\"length\":8},\"entities\":[{\"x\":1,\"y\":3,\"type\":\"oar\"},{\"x\":2,\"y\":3,\"type\":\"oar\"},{\"x\":3,\"y\":3,\"type\":\"oar\"},{\"x\":4,\"y\":3,\"type\":\"oar\"},{\"x\":5,\"y\":3,\"type\":\"oar\"},{\"x\":6,\"y\":3,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":2,\"type\":\"rudder\"},{\"x\":3,\"y\":1,\"type\":\"sail\",\"openned\":true},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":3,\"type\":\"oar\"}],\"life\":1600,\"shape\":{\"type\":\"rectangle\",\"width\":4.0,\"height\":8.0,\"orientation\":0.0}},\"visibleEntities\":[{\"type\":\"reef\",\"position\":{\"x\":3044.3285528031342,\"y\":957.0312500000006,\"orientation\":-1.1344640137963142},\"shape\":{\"type\":\"rectangle\",\"width\":400.0,\"height\":1800.0,\"orientation\":0.0}}],\"wind\":{\"orientation\":0.0,\"strength\":100.0}}");
    }
}
