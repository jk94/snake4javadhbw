/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Connection.Krypter.AES;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

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
        c_thread = new ClientThread(ip, port, encryptionKey);

        c_thread.start();

        while (key == null) {
            key = encryptionKey.getEncryptionKey();
        }
        try {
            PrintWriter pw = new PrintWriter(c_thread.getSocket().getOutputStream());
            pw.println(AES.encrypt(msg.makeMessage().getBytes(), key.toByteArray()));
            pw.flush();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(c_thread.getSocket().getInputStream()));
            while (true) {
                String input = bfr.readLine();
                byte[] ent_intput = AES.decrypt(input.getBytes(), key.toByteArray());
                StringBuilder sb = new StringBuilder();
                for (byte a : ent_intput) {
                    sb.append(a);
                }
                if (sb.toString().equals("ok")) {
                    System.out.println("ende");
                    break;
                }
            }
        } catch (Exception ex) {
        }


    }
}
