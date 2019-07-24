package application;

import controllers.CriptoAlgorithmCaesar;
import controllers.CriptoAlgorithmSHA1;
import controllers.JsonReader;
import controllers.JsonSaver;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

//TODO: REMOVER COLCHETES FORMATACAO ORGANIZATEJSON -> JSONREADER -> NEW: TALVEZ REMOVER ESSE METODO
//TERMINAR JSONSENDER
//https://www.codenation.dev/acceleration/reactnative-online-1/challenge/dev-ps


public final class Core {
    
    private static Core INSTANCE = null;
    
    private Core(){
        run();
    }
    
    private void run(){
        JsonReader jr = null;
        try {
            jr = new JsonReader("https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=49539b7b95b049a8703af6705cc3042ae7ac75b6");
            JSONObject json = jr.getJson();

            if(JsonSaver.saveJson(json)){
                System.out.println("Arquivo recebido da URL e salvo!");
            }
            
            String cifrado = json.get("cifrado").toString();
            int numeroCasas = Integer.parseInt(json.get("numero_casas").toString());
            
            CriptoAlgorithmCaesar caesar = new CriptoAlgorithmCaesar(cifrado, numeroCasas);
            String decifrado = caesar.getPerformed();
            json.put("decifrado", decifrado);
            
            if(JsonSaver.saveJson(json)){
                System.out.println("Código decifrado inserido no arquivo!");
            }
            
            CriptoAlgorithmSHA1 sha1 = new CriptoAlgorithmSHA1(decifrado);
            String resumoCripto = sha1.getPerformed();
            json.put("resumo_criptografico", resumoCripto);
            
            if(JsonSaver.saveJson(json)){
                System.out.println("Resumo criptografado inserido no arquivo!");
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
