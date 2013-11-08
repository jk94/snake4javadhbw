/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

/**
 *
 * @author Jan Koschke
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan Koschke
 */
public class Connect {

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket so;

    public Connect() {
        neueVerbindung("localhost", 9876);
    }

    private void neueVerbindung(String addresse, int port) {
        try {
            Socket socket = new Socket(addresse, port);
            so = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            writer.append("\n").flush();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s;
                    while ((s = reader.readLine()) != null) {
                        //INCOMING
                        System.out.println(s);
                    }
                    //sSystem.out.println("3");
                } catch (IOException ex) {
                    Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    public void senden(Message msg) {
        writer.append(msg.makeMessage() + "\n").flush();
    }

    public void verbinden(String ip, int port) {
        neueVerbindung(ip, port);
    }
}
