/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import Zeichenobjekte.Images;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ThemeControl {

    String path = "resource//theme//";
    ArrayList<Images> themelist;
    Images aktuellesTheme = null;

    public ThemeControl(String themepfad) {
        themelist = new ArrayList<>();
        path = themepfad;
        ThemesEinlesen();
        if (themelist.size() > 0) {
            aktuellesTheme = themelist.get(1);
        }
    }

    public void ThemesEinlesen() {
        File a = new File(path);
        for (String ab : a.list()) {
            File b = new File(path + "//" + ab);
            if (b.isDirectory()) {
                Images img = new Images(ab,b.getPath());
                themelist.add(img);
            }
        }

    }

    public String getPath() {
        return path;
    }

    public ArrayList<Images> getThemelist() {
        return themelist;
    }

    public Images getAktuellesTheme() {
        return aktuellesTheme;
    }

}
