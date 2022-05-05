package fr.unice.polytech.si3.qgl.Mugiwara_Cook;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Display {
    public static final Logger LOGGER = Logger.getLogger("");



    Display() {

    }


    public static void info(String string){
        LOGGER.log(Level.INFO,string);
    }
    public static void debug(String string){
        LOGGER.log(Level.FINEST,string);
    }


}
