/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Connection.Krypter.AES;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        SharedValue encryptionKey = new SharedValue();
        Socket connection = null;
        try {
            connection = new Socket(ip, port);
        } catch (IOException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (connection != null) {
            c_thread = new ClientThread(connection, encryptionKey, msg);

            c_thread.start();

            
        }
    }

    
}
