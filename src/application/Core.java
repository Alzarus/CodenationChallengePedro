package application;

import controllers.CriptoAlgorithmCaesar;
import controllers.CriptoAlgorithmSHA1;
import controllers.JsonReader;
import controllers.JsonSaver;
import controllers.JsonSender;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

//https://www.codenation.dev/acceleration/reactnative-online-1/challenge/dev-ps

public final class Core {
    
    private static Core INSTANCE = null;
    private static final String RECEIVELINK = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=49539b7b95b049a8703af6705cc3042ae7ac75b6";
    private static final String SENDLINK = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=49539b7b95b049a8703af6705cc3042ae7ac75b6";
    
    private Core(){
        run();
    }
    
    private void run(){
        JsonReader jsonReader = null;
        try {
            jsonReader = new JsonReader(RECEIVELINK);
            JSONObject json = jsonReader.getJson();

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
            
            JsonSender jsonSender = new JsonSender(SENDLINK);
//            String sendRequestResult = jsonSender.sendJson(json);
            jsonSender.sendJson(json);
//            System.out.println(sendRequestResult);
            
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
