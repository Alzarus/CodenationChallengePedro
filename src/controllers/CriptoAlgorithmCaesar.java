package controllers;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriptoAlgorithmCaesar {

    private String received;
    private String processed;

    public CriptoAlgorithmCaesar(String received, int numberHouses) {
        this.setReceived(received);
        process(numberHouses);
    }

    private void process(int numberHouses) {
        String received = this.getReceived();
        int minus, rest;

        byte[] ascii = new byte[received.length()];

        for (int i = 0; i < received.length(); i++) {
            ascii[i] = (byte) received.charAt(i);
            if (ascii[i] >= 65 && ascii[i] <= 90)  {
                minus = 90 - ascii[i];
                if(minus >= numberHouses){
                    ascii[i] += numberHouses;
                } else {
                    if(minus == 0){
                        ascii[i] = 65;
                    } else {
                        rest = numberHouses - minus;
                        ascii[i] = (byte) (65 + rest);
                    }
                }
            }
            if (ascii[i] >= 97 && ascii[i] <= 122){
               minus = 122 - ascii[i];
                if(minus >= numberHouses){
                    ascii[i] += numberHouses;
                } else {
                    if(minus == 0){
                        ascii[i] = 97;
                    } else {
                        rest = numberHouses - minus;
                        ascii[i] = (byte) (97 + rest);    
                    }
                }               
            }
        }
        try {
            this.setPerformed(new String(ascii, "UTF-8").toLowerCase());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CriptoAlgorithmCaesar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public String getPerformed() {
        return processed;
    }

    public void setPerformed(String performed) {
        this.processed = performed;
    }

}
