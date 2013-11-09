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
public class Connect {

    public Connect(Message mgs) {
        //neueVerbindung("localhost", 9876);

        SharedValue encryptionKey = new SharedValue();
        ClientThread c_thread = new ClientThread("127.0.0.1", 9876, encryptionKey);

        c_thread.start();

        BigInteger key = null;

        while (key == null) {
            key = encryptionKey.getEncryptionKey();
            System.out.println("");
        }
        try {
            PrintWriter pw = new PrintWriter(c_thread.getSocket().getOutputStream());
            pw.println(AES.encrypt(mgs.makeMessage().getBytes(), key.toByteArray()));
            pw.flush();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(c_thread.getSocket().getInputStream()));
            while (true) {
                String input = bfr.readLine();
                byte[] ent_intput = AES.decrypt(input.getBytes(), key.toByteArray());
                StringBuilder sb = new StringBuilder();
                for(byte a : ent_intput){
                    sb.append(a);
                }
                if(sb.toString().equals("ok")){
                    break;
                }
            }
        } catch (Exception ex) {

        }

        System.out.println("Encrypt:" + key.toString());

    }

}
