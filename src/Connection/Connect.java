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
        System.out.println("This is ma key" + key);
        try {
            PrintWriter pw = new PrintWriter(c_thread.getSocket().getOutputStream());
            System.out.println(ByteArrayToString(AES.encrypt(msg.makeMessage().getBytes(), key.toByteArray())));
            pw.println(ByteArrayToString(AES.encrypt(msg.makeMessage().getBytes(), key.toByteArray())));
            pw.flush();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(c_thread.getSocket().getInputStream()));
            while (true) {
                String input = bfr.readLine();
                if (!input.equals("")) {
                    byte[] ent_intput = AES.decrypt(input.getBytes(), key.toByteArray());
                    if (ByteArrayToString(ent_intput).equals("ok")) {
                        System.out.println("ende");
                        c_thread.connection.close();
                        break;
                    }
                }
            }
        } catch (Exception ex) {
        }

    }

    private String ByteArrayToString(byte[] bt) {
        StringBuilder sb = new StringBuilder();
        for (byte a : bt) {
            sb.append(a);
        }
        return sb.toString();
    }
}
