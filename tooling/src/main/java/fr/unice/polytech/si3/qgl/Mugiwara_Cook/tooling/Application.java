package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling;

import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Cockpit;
import fr.unice.polytech.si3.qgl.Mugiwara_Cook.Display;
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
        cockpit.initGame("{\"goal\":{\"mode\":\"REGATTA\",\"checkpoints\":[{\"position\":{\"x\":5060.86956521738,\"y\":5309.446254071656,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":150.0}},{\"position\":{\"x\":7886.95652173912,\"y\":10594.462540716599,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":250.0}},{\"position\":{\"x\":6408.695652173904,\"y\":7817.5895765472305,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":200.0}}]},\"ship\":{\"type\":\"ship\",\"position\":{\"x\":1865.2173913043503,\"y\":1148.2084690553752,\"orientation\":0.0},\"name\":\"Mugiwara_Cook\",\"deck\":{\"width\":3,\"length\":8},\"entities\":[{\"x\":7,\"y\":1,\"type\":\"rudder\"},{\"x\":3,\"y\":1,\"type\":\"sail\",\"openned\":false},{\"x\":1,\"y\":2,\"type\":\"oar\"},{\"x\":2,\"y\":2,\"type\":\"oar\"},{\"x\":3,\"y\":2,\"type\":\"oar\"},{\"x\":4,\"y\":2,\"type\":\"oar\"},{\"x\":5,\"y\":2,\"type\":\"oar\"},{\"x\":6,\"y\":2,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":2,\"type\":\"oar\"},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":0,\"y\":1,\"type\":\"watch\"}],\"life\":1200,\"shape\":{\"type\":\"rectangle\",\"width\":3.0,\"height\":8.0,\"orientation\":0.0}},\"sailors\":[{\"x\":0,\"y\":0,\"id\":0,\"name\":\"Edward Pouce\"},{\"x\":0,\"y\":1,\"id\":1,\"name\":\"Luffy Teach\"},{\"x\":0,\"y\":2,\"id\":2,\"name\":\"Edward Teach\"},{\"x\":1,\"y\":0,\"id\":3,\"name\":\"Luffy Teach\"},{\"x\":1,\"y\":1,\"id\":4,\"name\":\"Edward Pouce\"},{\"x\":1,\"y\":2,\"id\":5,\"name\":\"Tom Pouce\"},{\"x\":2,\"y\":0,\"id\":6,\"name\":\"Tom Pouce\"},{\"x\":2,\"y\":1,\"id\":7,\"name\":\"Tom Teach\"},{\"x\":2,\"y\":2,\"id\":8,\"name\":\"Luffy Teach\"},{\"x\":3,\"y\":0,\"id\":9,\"name\":\"Tom Teach\"},{\"x\":3,\"y\":1,\"id\":10,\"name\":\"Jack Teach\"},{\"x\":3,\"y\":2,\"id\":11,\"name\":\"Tom Teach\"},{\"x\":4,\"y\":0,\"id\":12,\"name\":\"Edward Teach\"},{\"x\":4,\"y\":1,\"id\":13,\"name\":\"Luffy Teach\"},{\"x\":4,\"y\":2,\"id\":14,\"name\":\"Luffy Teach\"},{\"x\":5,\"y\":0,\"id\":15,\"name\":\"Edward Pouce\"},{\"x\":5,\"y\":1,\"id\":16,\"name\":\"Tom Pouce\"}],\"shipCount\":1}");
        Display.info("An instance of my team player: " + cockpit);
        Display.info("When called, it returns some JSON:");
        cockpit.nextRound("{\"ship\":{\"type\":\"ship\",\"position\":{\"x\":1865.2173913043503,\"y\":1148.2084690553752,\"orientation\":0.0},\"name\":\"Mugiwara_Cook\",\"deck\":{\"width\":3,\"length\":8},\"entities\":[{\"x\":7,\"y\":1,\"type\":\"rudder\"},{\"x\":3,\"y\":1,\"type\":\"sail\",\"openned\":false},{\"x\":1,\"y\":2,\"type\":\"oar\"},{\"x\":2,\"y\":2,\"type\":\"oar\"},{\"x\":3,\"y\":2,\"type\":\"oar\"},{\"x\":4,\"y\":2,\"type\":\"oar\"},{\"x\":5,\"y\":2,\"type\":\"oar\"},{\"x\":6,\"y\":2,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":2,\"type\":\"oar\"},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":0,\"y\":1,\"type\":\"watch\"}],\"life\":1200,\"shape\":{\"type\":\"rectangle\",\"width\":3.0,\"height\":8.0,\"orientation\":0.0}},\"visibleEntities\":[],\"wind\":{\"orientation\":0.0,\"strength\":50.0}}");
        cockpit.nextRound("{\"ship\":{\"type\":\"ship\",\"position\":{\"x\":3735.694671325941,\"y\":1944.3139688899532,\"orientation\":-0.7770433975806597},\"name\":\"Mugiwara_Cook\",\"deck\":{\"width\":3,\"length\":8},\"entities\":[{\"x\":7,\"y\":1,\"type\":\"rudder\"},{\"x\":3,\"y\":1,\"type\":\"sail\",\"openned\":false},{\"x\":1,\"y\":2,\"type\":\"oar\"},{\"x\":2,\"y\":2,\"type\":\"oar\"},{\"x\":3,\"y\":2,\"type\":\"oar\"},{\"x\":4,\"y\":2,\"type\":\"oar\"},{\"x\":5,\"y\":2,\"type\":\"oar\"},{\"x\":6,\"y\":2,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":2,\"type\":\"oar\"},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":0,\"y\":1,\"type\":\"watch\"}],\"life\":610,\"shape\":{\"type\":\"rectangle\",\"width\":3.0,\"height\":8.0,\"orientation\":0.0}},\"visibleEntities\":[{\"type\":\"reef\",\"position\":{\"x\":2973.9130434782596,\"y\":2907.166123778503,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":675.0}},{\"type\":\"reef\",\"position\":{\"x\":4304.347826086958,\"y\":1734.5276872964187,\"orientation\":-0.41887902047863906},\"shape\":{\"type\":\"rectangle\",\"width\":450.0,\"height\":1200.0,\"orientation\":0.0}}],\"wind\":{\"orientation\":0.0,\"strength\":50.0}}");
        cockpit.nextRound("{\"ship\":{\"type\":\"ship\",\"position\":{\"x\":5417.911760601362,\"y\":6762.208447037328,\"orientation\":-0.7326538431631286},\"name\":\"Mugiwara_Cook\",\"deck\":{\"width\":3,\"length\":8},\"entities\":[{\"x\":7,\"y\":1,\"type\":\"rudder\"},{\"x\":3,\"y\":1,\"type\":\"sail\",\"openned\":false},{\"x\":1,\"y\":2,\"type\":\"oar\"},{\"x\":2,\"y\":2,\"type\":\"oar\"},{\"x\":3,\"y\":2,\"type\":\"oar\"},{\"x\":4,\"y\":2,\"type\":\"oar\"},{\"x\":5,\"y\":2,\"type\":\"oar\"},{\"x\":6,\"y\":2,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":2,\"type\":\"oar\"},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":0,\"y\":1,\"type\":\"watch\"}],\"life\":1200,\"shape\":{\"type\":\"rectangle\",\"width\":3.0,\"height\":8.0,\"orientation\":0.0}},\"visibleEntities\":[{\"type\":\"reef\",\"position\":{\"x\":5713.04347826087,\"y\":5276.87296416938,\"orientation\":1.5707963267948966},\"shape\":{\"type\":\"rectangle\",\"width\":450.0,\"height\":1125.0,\"orientation\":0.0}},{\"type\":\"reef\",\"position\":{\"x\":6208.695652173914,\"y\":6803.745928338774,\"orientation\":2.5307274153917776},\"shape\":{\"type\":\"rectangle\",\"width\":450.0,\"height\":2250.0,\"orientation\":0.0}},{\"type\":\"reef\",\"position\":{\"x\":4617.391304347825,\"y\":6400.651465798053,\"orientation\":0.0},\"shape\":{\"type\":\"rectangle\",\"width\":450.0,\"height\":1125.0,\"orientation\":0.0}},{\"type\":\"reef\",\"position\":{\"x\":5386.956521739133,\"y\":6083.061889250812,\"orientation\":2.356194490192345},\"shape\":{\"type\":\"rectangle\",\"width\":450.0,\"height\":1125.0,\"orientation\":0.0}}],\"wind\":{\"orientation\":0.0,\"strength\":50.0}} ");
        cockpit.nextRound("{\"ship\":{\"type\":\"ship\",\"position\":{\"x\":4008.0599176134133,\"y\":6841.523710131652,\"orientation\":-0.20708993600372272},\"name\":\"Mugiwara_Cook\",\"deck\":{\"width\":3,\"length\":8},\"entities\":[{\"x\":7,\"y\":1,\"type\":\"rudder\"},{\"x\":3,\"y\":1,\"type\":\"sail\",\"openned\":false},{\"x\":1,\"y\":2,\"type\":\"oar\"},{\"x\":2,\"y\":2,\"type\":\"oar\"},{\"x\":3,\"y\":2,\"type\":\"oar\"},{\"x\":4,\"y\":2,\"type\":\"oar\"},{\"x\":5,\"y\":2,\"type\":\"oar\"},{\"x\":6,\"y\":2,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":2,\"type\":\"oar\"},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":0,\"y\":1,\"type\":\"watch\"}],\"life\":1200,\"shape\":{\"type\":\"rectangle\",\"width\":3.0,\"height\":8.0,\"orientation\":0.0}},\"visibleEntities\":[{\"type\":\"reef\",\"position\":{\"x\":4617.391304347825,\"y\":6400.651465798053,\"orientation\":0.0},\"shape\":{\"type\":\"rectangle\",\"width\":450.0,\"height\":1125.0,\"orientation\":0.0}},{\"type\":\"reef\",\"position\":{\"x\":5386.956521739133,\"y\":6083.061889250812,\"orientation\":2.356194490192345},\"shape\":{\"type\":\"rectangle\",\"width\":450.0,\"height\":1125.0,\"orientation\":0.0}}],\"wind\":{\"orientation\":0.0,\"strength\":50.0}}");
        cockpit.nextRound("{\"ship\":{\"type\":\"ship\",\"position\":{\"x\":3359.1938996044337,\"y\":4809.169285538738,\"orientation\":0.5742703665418476},\"name\":\"Mugiwara_Cook\",\"deck\":{\"width\":3,\"length\":8},\"entities\":[{\"x\":7,\"y\":1,\"type\":\"rudder\"},{\"x\":3,\"y\":1,\"type\":\"sail\",\"openned\":false},{\"x\":1,\"y\":2,\"type\":\"oar\"},{\"x\":2,\"y\":2,\"type\":\"oar\"},{\"x\":3,\"y\":2,\"type\":\"oar\"},{\"x\":4,\"y\":2,\"type\":\"oar\"},{\"x\":5,\"y\":2,\"type\":\"oar\"},{\"x\":6,\"y\":2,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":2,\"type\":\"oar\"},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":0,\"y\":1,\"type\":\"watch\"}],\"life\":1200,\"shape\":{\"type\":\"rectangle\",\"width\":3.0,\"height\":8.0,\"orientation\":0.0}},\"visibleEntities\":[{\"type\":\"reef\",\"position\":{\"x\":4147.826086956523,\"y\":5276.872964169381,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":450.0}},{\"type\":\"reef\",\"position\":{\"x\":4565.217391304346,\"y\":4140.879478827364,\"orientation\":0.0},\"shape\":{\"type\":\"rectangle\",\"width\":450.0,\"height\":1125.0,\"orientation\":0.0}}],\"wind\":{\"orientation\":0.0,\"strength\":50.0}}");
        cockpit.nextRound("{\"ship\":{\"type\":\"ship\",\"position\":{\"x\":3074.9129374723,\"y\":4745.97896864111,\"orientation\":-6.180633411788013},\"name\":\"Mugiwara_Cook\",\"deck\":{\"width\":3,\"length\":8},\"entities\":[{\"x\":7,\"y\":1,\"type\":\"rudder\"},{\"x\":3,\"y\":1,\"type\":\"sail\",\"openned\":false},{\"x\":1,\"y\":2,\"type\":\"oar\"},{\"x\":2,\"y\":2,\"type\":\"oar\"},{\"x\":3,\"y\":2,\"type\":\"oar\"},{\"x\":4,\"y\":2,\"type\":\"oar\"},{\"x\":5,\"y\":2,\"type\":\"oar\"},{\"x\":6,\"y\":2,\"type\":\"oar\"},{\"x\":1,\"y\":0,\"type\":\"oar\"},{\"x\":2,\"y\":0,\"type\":\"oar\"},{\"x\":3,\"y\":0,\"type\":\"oar\"},{\"x\":4,\"y\":0,\"type\":\"oar\"},{\"x\":5,\"y\":0,\"type\":\"oar\"},{\"x\":6,\"y\":0,\"type\":\"oar\"},{\"x\":7,\"y\":2,\"type\":\"oar\"},{\"x\":7,\"y\":0,\"type\":\"oar\"},{\"x\":0,\"y\":1,\"type\":\"watch\"}],\"life\":1200,\"shape\":{\"type\":\"rectangle\",\"width\":3.0,\"height\":8.0,\"orientation\":0.0}},\"visibleEntities\":[{\"type\":\"reef\",\"position\":{\"x\":4147.826086956523,\"y\":5276.872964169381,\"orientation\":0.0},\"shape\":{\"type\":\"circle\",\"radius\":450.0}}],\"wind\":{\"orientation\":0.0,\"strength\":50.0}}");
    }
}