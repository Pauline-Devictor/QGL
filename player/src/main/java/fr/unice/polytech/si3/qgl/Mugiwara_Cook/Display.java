package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Display {
    public final static Logger LOGGER = Logger.getLogger("");



    Display() {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
        LOGGER.setLevel(Level.INFO);
    }


    public static void info(String string){
        LOGGER.log(Level.INFO,string);
    }
    public static void debug(String string){
        LOGGER.log(Level.FINEST,string);
    }


}
