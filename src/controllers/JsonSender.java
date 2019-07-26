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
   
   private HttpClient httpClient;
   private String url;
   
   public JsonSender(String url){
       this.httpClient = HttpClientBuilder.create().build();
       this.setUrl(url);
   }
   //https://stackoverflow.com/questions/7181534/http-post-using-json-in-java
//   https://stackoverflow.com/questions/2469451/upload-files-from-java-client-to-a-http-server
   
   //THIS https://www.baeldung.com/httpurlconnection-post
   //TERMINAR
   public String sendJson(JSONObject json){
        HttpPost request;
        StringEntity params;
       try {
        request = new HttpPost(this.getUrl());
        String jsonString = json.toString();
        params = new StringEntity(jsonString);
//        params = new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
        request.addHeader("content-type", "application/x-www-form-urlencoded");
        request.setEntity(params);
            try { 
                HttpResponse response = httpClient.execute(request);
                return response.toString();
            } catch (IOException ex) {
                Logger.getLogger(JsonSender.class.getName()).log(Level.SEVERE, null, ex);
            }
       } catch (UnsupportedEncodingException ex) {
           Logger.getLogger(JsonSender.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
   
}
