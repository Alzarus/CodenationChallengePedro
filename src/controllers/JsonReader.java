package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.net.MalformedURLException;
import java.net.URL;

import java.nio.charset.Charset;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

    private JSONObject json;

    public JsonReader(String url) throws IOException, JSONException {
        this.json = readJsonFromUrl(url);
    }

    public JSONObject getJson() {
        return json;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url){
        try {
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            is.close();
            return json;
        } catch (MalformedURLException ex) {
            Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static JSONObject organizateJson(JSONObject json){
        try {
            JSONObject organizatedJson = new JSONObject();
            
//            https://stackoverflow.com/questions/4576352/remove-all-occurrences-of-char-from-string
//https://stackoverflow.com/questions/15609306/convert-string-to-json-array
            //TODO NAO ESTA ORDENANDO CORRETAMENTE
            organizatedJson.append("numero_casas", json.get("numero_casas").toString().replaceAll("\\[\\]", ""));
            organizatedJson.append("token", json.get("token").toString().replaceAll("\\[\\]", ""));
            organizatedJson.append("cifrado", json.get("cifrado").toString().replaceAll("\\[\\]", ""));
            organizatedJson.append("decifrado", json.get("decifrado").toString().replaceAll("\\[\\]", ""));
            organizatedJson.append("resumo_criptografico", json.get("resumo_criptografico").toString().replaceAll("\\[\\]", ""));
            
            return organizatedJson;
            
//            https://stackoverflow.com/questions/9151619/how-to-iterate-over-a-jsonobject
            //iterar as keys e salvar na ordem
//            Iterator<String> keys = json.keys();
//            while(keys.hasNext()){
//                String key = keys.next();
//                if(json.get(key) instanceof JSONObject){
//                    
//                }
//            }
  
        } catch (JSONException ex) {
            Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static void main(String[] args) throws IOException, JSONException {
        File answer = new File("./answer.json");
        JSONObject json = readJsonFromUrl("https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=49539b7b95b049a8703af6705cc3042ae7ac75b6");
        FileWriter fw = new FileWriter(answer);
        fw.write(organizateJson(json).toString());
        System.out.println(json.toString());
        System.out.println(json.get("cifrado"));
        fw.close();

    //https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java/4308662#4308662
    }
}
