package controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriptoAlgorithmSHA1 {

    private String received;
    private String processed;

    public CriptoAlgorithmSHA1(String received) {
        this.setReceived(received);
        process();
    }

    private void process() {
        String received = this.getReceived();

        MessageDigest algorithm = null;
        byte[] messageDigestPassAdmin = null;

        try {
            algorithm = MessageDigest.getInstance("SHA-1");
            messageDigestPassAdmin = algorithm.digest(received.getBytes("UTF-8"));
            StringBuilder hexStringSenhaAdmin = new StringBuilder();

            for (byte b : messageDigestPassAdmin) {
                hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
            }

            this.setPerformed(hexStringSenhaAdmin.toString().toLowerCase());

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(CriptoAlgorithmSHA1.class.getName()).log(Level.SEVERE, null, ex);
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
