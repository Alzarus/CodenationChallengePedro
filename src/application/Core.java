package application;

import controllers.JsonReader;
import controllers.JsonSaver;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public final class Core {
    
    private static Core INSTANCE = null;
    
    private Core(){
        JsonReader jr = null;
        try {
            jr = new JsonReader("https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=49539b7b95b049a8703af6705cc3042ae7ac75b6");
            JSONObject json = jr.getJson();
            if(JsonSaver.saveJson(json)){
                System.out.println("BOA!");
            }
        } catch (IOException | JSONException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static Core getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Core();
        }
        return INSTANCE;
    }
}
