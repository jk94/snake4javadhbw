/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Connection.Krypter.Hasher;

/**
 *
 * @author Jan Koschke
 */
public class Message {

    private String benutzer, passwort;
    private int punkte;

    public Message(String benutzer, String passwort, int punkte) {
        this.benutzer = benutzer;
        this.passwort = Hasher.ToMD5(passwort);
        this.punkte = punkte;
    }

    public String makeMessage() {
        String erg = "";
        erg = "benutzer:\"" + benutzer + "\",passwort:\"" + passwort + "\",punkte:\"" + punkte + "\"";
        String erg2 = erg + ",\"" + Hasher.ToMD5(erg) + "\"";
        return erg2;
    }

}
