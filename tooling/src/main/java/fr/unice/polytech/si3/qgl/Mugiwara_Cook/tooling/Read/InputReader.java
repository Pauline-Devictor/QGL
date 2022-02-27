package fr.unice.polytech.si3.qgl.Mugiwara_Cook.tooling.Read;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class InputReader {
    public static ArrayList<ArrayList<String>> readInput(){
        String filename = System.getProperty("user.dir")+"\\src\\yo.txt";
        ArrayList<ArrayList<String>> records = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String currentLine = line;
                ArrayList<String> list = new ArrayList<>(Arrays.asList(currentLine));
                records.add(list);
            }
            reader.close();
            return records;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }

    public static void writeOutput(String out){
        String filename = "output.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write(out);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
