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
        Socket connection = null;
        try {
            connection = new Socket(ip, port);
        } catch (IOException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (connection != null) {
            /*c_thread = new ClientThread(connection, encryptionKey, msg);
             c_thread.start();

             while (key == null) {
             key = encryptionKey.getEncryptionKey();
             }
             System.out.println(key);
            
             System.out.println("5620731".getBytes().length);
             String thekey = key.toString();
             while(thekey.getBytes().length<32){
             thekey = thekey + key.toString();
             }
             System.out.println(thekey);
             System.out.println(thekey.getBytes().length);
             */
            try {
                KeyGenerator keygen = KeyGenerator.getInstance("AES");
                keygen.init(128);
                SecretKey aesKey = keygen.generateKey();
                PrintWriter write_output = new PrintWriter(connection.getOutputStream());
                write_output.println(aesKey);
                
                Connection.Krypter.Krypt crypt = new Krypt(aesKey, "AES");
                write_output = new PrintWriter(crypt.encryptOutputStream(connection.getOutputStream()));

                write_output.println(msg.makeMessage());
                write_output.flush();

                BufferedReader read_input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while (true) {
                    String input = read_input.readLine();
                    if (!input.equals("")) {
                        byte[] ent_intput = AES.decrypt(input.getBytes(), key.toByteArray());
                        if (ByteArrayToString(ent_intput).equals("ok")) {
                            System.out.println("ende");
                            connection.close();
                            break;
                        }
                    }
                }
                write_output.close();
                read_input.close();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }
    }

    private String ByteArrayToString(byte[] bt) {
        return new String(bt);
    }
}
