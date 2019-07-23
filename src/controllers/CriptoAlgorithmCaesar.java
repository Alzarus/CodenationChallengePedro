package controllers;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriptoAlgorithmCaesar {

    private String received;
    private String processed;
//TODO PEGAR INT VINDO NO JSON -> NUMERO_CASAS
    public CriptoAlgorithmCaesar(String received) {
        this.setReceived(received);
        process();
    }

    private void process() {
        String received = this.getReceived();

        byte[] ascii = new byte[received.length()];

        for (int i = 0; i < received.length(); i++) {
            ascii[i] = (byte) received.charAt(i);
            if ((ascii[i] >= 65 && ascii[i] <= 90) || (ascii[i] >= 97 && ascii[i] <= 122)) {
                switch (ascii[i]) {
                    case 88:
                        ascii[i] = 65;
                        break;
                    case 89:
                        ascii[i] = 66;
                        break;
                    case 90:
                        ascii[i] = 67;
                        break;
                    case 120:
                        ascii[i] = 97;
                        break;
                    case 121:
                        ascii[i] = 98;
                        break;
                    case 122:
                        ascii[i] = 99;
                        break;
                    default:
                        ascii[i] += 3;
                        break;
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
