package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import org.json.JSONObject;

public class JsonSender {
   
   HttpClient httpClient; 
   
   public JsonSender(){
       httpClient = HttpClientBuilder.create().build();
   }
   //https://stackoverflow.com/questions/7181534/http-post-using-json-in-java
   //TERMINAR
   public boolean sendJson(JSONObject json){
        HttpPost request;
        StringEntity params;
       try {
        request = new HttpPost("https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=49539b7b95b049a8703af6705cc3042ae7ac75b6");
        String jsonString = json.toString();
        params = new StringEntity(jsonString);
//        params = new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
        request.addHeader("content-type", "application/x-www-form-urlencoded");
        request.setEntity(params);
            try { 
                HttpResponse response = httpClient.execute(request);
            } catch (IOException ex) {
                Logger.getLogger(JsonSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        return true;
       } catch (UnsupportedEncodingException ex) {
           Logger.getLogger(JsonSender.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
   
}
