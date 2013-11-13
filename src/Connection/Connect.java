/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Connection.Krypter.AES;
import Connection.Krypter.Krypt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Jan Koschke
 */
/**
 *
 * @author Jan Koschke
 */
public class Connect extends Thread {

    private ClientThread c_thread;
    private BigInteger key = null;
    private Message msg;
    private int port;
    private String ip;

    public Connect(String ip, int port, Message msg) {
        //neueVerbindung("localhost", 9876);
        this.port = port;
        this.ip = ip;
        this.msg = msg;
    }

    public void run() {
        //SharedValue encryptionKey = new SharedValue();
        Socket socket = null;

        try {

            socket = new Socket(ip, port);
        } catch (IOException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (socket != null) {

            try {
                InputStream fis = socket.getInputStream();
                BufferedReader read_input;


                /*System.out.println("Länge lesen...");
                 while (true) {
                 input = read_input.readLine();
                 if (input != null) {
                        
                 break;
                 }
                 }
                 System.out.println("Länge gelesen...");*/
                byte[] encodedKey = new byte[16];

                fis.read(encodedKey);
                System.out.println("Key gelesen...");

                // generiere Key
                SecretKey aesKey = new SecretKeySpec(encodedKey, "AES");

                System.out.println("Key erstellt...");
                Connection.Krypter.Krypt crypt = new Krypt(aesKey, "AES");

                PrintWriter write_output = new PrintWriter(socket.getOutputStream());
                System.out.println("Ausgabe geöffnet...");
                read_input = new BufferedReader(new InputStreamReader(fis));
                System.out.println("Eingabe geöffnet...");
                String vermsg = crypt.encrypt(msg.makeMessage());
                System.out.println(vermsg.length());
                System.out.println(vermsg);
                write_output.println(vermsg.length());
                write_output.println(vermsg);
                write_output.flush();
                System.out.println("Message geschickt...");

                System.out.println("Input lesen...");
                read_input.readLine();
                String input = read_input.readLine();

                int leng = Integer.parseInt(input);
                input = "";
                int breaks=0;
                while (true) {
                    input = input + read_input.readLine();
                    if (input.length() >= leng - breaks) {
                        if (input != null) {
                            System.out.println(input);
                            input = crypt.decrypt(input);
                            System.out.println(input);
                            if (input.equals("ok")) {
                                break;
                            }
                        }
                    } else {
                        input = input + "\n";
                        breaks++;
                    }
                }
                write_output.close();
                read_input.close();
                socket.close();
                System.out.println("Connection closed");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }
    }
}
