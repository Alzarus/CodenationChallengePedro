package controllers;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class JsonSaver {
    
    public static boolean saveJson(JSONObject json){
        File file = new File("./answer.json");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(json.toString());
            fw.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(JsonSaver.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }
}
