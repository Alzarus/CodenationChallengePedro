package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import org.json.JSONObject;

public class JsonSender {
   
   private HttpClient httpClient;
//   private CloseableHttpClient httpClient;
   private URL url;
   private HttpURLConnection con;
   
   public JsonSender(String url){
       this.httpClient = HttpClientBuilder.create().build();
//     this.httpClient = HttpClients.createDefault();
       try {
           this.url = new URL(url);
           this.con = (HttpURLConnection)this.url.openConnection();
       } catch (MalformedURLException ex) {
           Logger.getLogger(JsonSender.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(JsonSender.class.getName()).log(Level.SEVERE, null, ex);
       }
   }

   public void sendJson(JSONObject json){
        File file = new File("./answer.json");
        
        try {
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "multipart/form-data; utf-8");
            
            OutputStream outputStreamToRequestBody = con.getOutputStream();
            BufferedWriter httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));
            
            httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"answer.json\""
                    + "filename= \"" + file.getName() + "\""
                    + "\nContent-Type: text/plain\n\n");
            httpRequestBodyWriter.write("<file name=\"answer.json\">");
            httpRequestBodyWriter.flush();
            
            FileInputStream inputStreamToLogFile = new FileInputStream(file);
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            while((bytesRead = inputStreamToLogFile.read(dataBuffer)) != -1) {
                outputStreamToRequestBody.write(dataBuffer, 0, bytesRead);
            }
            outputStreamToRequestBody.flush();     
            
            httpRequestBodyWriter.write("</file>");
            httpRequestBodyWriter.flush();
            
            outputStreamToRequestBody.close();
            httpRequestBodyWriter.close();
            
            BufferedReader httpResponseReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String lineRead;
            while((lineRead = httpResponseReader.readLine()) != null) {
                System.out.println(lineRead);
            }
           //https://www.techcoil.com/blog/how-to-upload-a-file-via-a-http-multipart-request-in-java-without-using-any-external-libraries/
           //https://www.baeldung.com/httpurlconnection-post
   
        } catch (ProtocolException ex) {
            Logger.getLogger(JsonSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
           Logger.getLogger(JsonSender.class.getName()).log(Level.SEVERE, null, ex);
       }
        
   }
   
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
   
}
