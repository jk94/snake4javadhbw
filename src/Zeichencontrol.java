
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jan
 */
public class Zeichencontrol {
    
    private Graphics zeichenflaeche; 
    private int pixelgroese, spielfeldX, spielfeldY;
    
    public Zeichencontrol(Graphics zfl, int pxl, int sfX, int sfY){
        this.zeichenflaeche = zfl;
        this.pixelgroese = pxl;
        this.spielfeldX = sfX;
        this.spielfeldY = sfY;
    }
    
    public void zeichneFeld(Feld[][] Spielfeld){
        loescheAnzeigekomplett();
        zeichneUndsetzeRand(Spielfeld);
    }
    public void loescheAnzeigekomplett(){
        zeichenflaeche.clearRect(0, 0, spielfeldX, spielfeldY);
    }
    
    private void zeichneUndsetzeRand(Feld[][] Spielfeld) {
        zeichenflaeche.setColor(Color.BLACK);
        for (int i = 0; i < spielfeldX / pixelgroese - 1; i++) {
            if (i == 0 || i == Spielfeld[i].length - 1) {
                for (int i2 = 0; i2 <= Spielfeld[i].length - 1; i2++) {
                    Spielfeld[i][i2].activate();
                    zeichenflaeche.fillRect(Spielfeld[i][i2].getX(), Spielfeld[i][i2].getY(), pixelgroese, pixelgroese);
                }
            } else {
                Spielfeld[i][0].activate();
                zeichenflaeche.fillRect(Spielfeld[i][0].getX(), Spielfeld[i][0].getY(), pixelgroese, pixelgroese);
                Spielfeld[i][Spielfeld[i].length - 1].activate();
                zeichenflaeche.fillRect(Spielfeld[i][Spielfeld[i].length - 1].getX(), Spielfeld[i][Spielfeld[i].length - 1].getY(), pixelgroese, pixelgroese);
            }
        }
    }
    
    public void loescheAnzeigeSchlange(ArrayList<Feld> schlangenliste) {
        zeichenflaeche.clearRect(schlangenliste.get(schlangenliste.size() - 1).getX(), schlangenliste.get(schlangenliste.size() - 1).getY(), pixelgroese, pixelgroese);

    }
    public void zeichneSchlange(ArrayList<Feld> schlangenliste) {
        for (Feld sFeld : schlangenliste) {
            zeichenflaeche.setColor(Color.GREEN);
            zeichenflaeche.fillRect(sFeld.getX(), sFeld.getY(), pixelgroese, pixelgroese);
        }
        zeichenflaeche.setColor(Color.BLUE);
        zeichenflaeche.fillRect(schlangenliste.get(0).getX(), schlangenliste.get(0).getY(), pixelgroese, pixelgroese);
    }
    
    public void zeichneZiel(Feld aktuellesZiel) {
        zeichenflaeche.setColor(Color.RED);
        zeichenflaeche.fillRect(aktuellesZiel.getX(), aktuellesZiel.getY(), pixelgroese, pixelgroese);
    }
    
    public void schreibePunkte(Punkte pkt, Feld[][] Spielfeld) {
        loescheAnzeigePunkte(Spielfeld);
        zeichenflaeche.setColor(Color.WHITE);
        Font f = new Font("serif", Font.BOLD, 10);
        zeichenflaeche.setFont(f);
        zeichenflaeche.drawString("Punkte: " + pkt.getPunktezaehler(), Spielfeld[0][Spielfeld[0].length - 1].getX() + 10, Spielfeld[0][Spielfeld[0].length - 1].getY() + 10);
    }

    public void loescheAnzeigePunkte( Feld[][] Spielfeld) {
        zeichenflaeche.setColor(Color.BLACK);
        zeichenflaeche.fillRect(Spielfeld[0][Spielfeld[0].length - 1].getX(), Spielfeld[0][Spielfeld[0].length - 1].getY(), Spielfeld[Spielfeld[0].length - 1][Spielfeld[0].length - 1].getX(), pixelgroese);
    }
}
