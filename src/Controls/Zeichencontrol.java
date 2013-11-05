package Controls;

import Standardpackage.Punkte;
import Zeichenobjekte.Feld;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

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

    public Zeichencontrol(Graphics zfl, int pxl, int sfX, int sfY) {
        this.zeichenflaeche = zfl;
        this.pixelgroese = pxl;
        this.spielfeldX = sfX;
        this.spielfeldY = sfY;
    }

    public void zeichneFeld(Feld[][] Spielfeld) {
        loescheAnzeigekomplett();
        zeichneUndsetzeRand(Spielfeld);
    }

    public void loescheAnzeigekomplett() {
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

    public void zeichnePunkte(Punkte pkt, Feld[][] Spielfeld) {
        loescheAnzeigePunkte(Spielfeld);
        zeichenflaeche.setColor(Color.WHITE);
        Font f = new Font("serif", Font.BOLD, 10);
        zeichenflaeche.setFont(f);
        zeichenflaeche.drawString("Punkte: " + pkt.getPunktezaehler(), Spielfeld[0][Spielfeld[0].length - 1].getX() + 10, Spielfeld[0][Spielfeld[0].length - 1].getY() + 10);
    }

    public void loescheAnzeigePunkte(Feld[][] Spielfeld) {
        zeichenflaeche.setColor(Color.BLACK);
        zeichenflaeche.fillRect(Spielfeld[0][Spielfeld[0].length - 1].getX(), Spielfeld[0][Spielfeld[0].length - 1].getY(), Spielfeld[Spielfeld[0].length - 1][Spielfeld[0].length - 1].getX(), pixelgroese);
    }

    public void zeichneGameOverMessage(Punkte pkt) {
        zeichenflaeche.setColor(Color.WHITE);
        Font f = new Font("serif", Font.BOLD, 50);
        zeichenflaeche.setFont(f);
        int x = zeichenflaeche.getFontMetrics().stringWidth("GAME OVER!");
        zeichenflaeche.drawString("GAME OVER!", spielfeldX / 2 - x / 2, 100);

        f = new Font("serif", Font.BOLD, 30);
        zeichenflaeche.setFont(f);
        x = zeichenflaeche.getFontMetrics().stringWidth("Erreichte Punktzahl: " + pkt.getPunktezaehler());
        zeichenflaeche.drawString("Erreichte Punktzahl: " + pkt.getPunktezaehler(), spielfeldX / 2 - x / 2, spielfeldY / 2 - 30);
    }

    public Point[] zeichneExit() {
        Font f = new Font("serif", Font.BOLD, 20);
        zeichenflaeche.setFont(f);
        int x = zeichenflaeche.getFontMetrics().stringWidth("Exit");
        int y = 85;
        zeichenflaeche.drawString("Exit", spielfeldX / 2 - x / 2, spielfeldY / 2 + y);
        zeichenflaeche.drawRect(spielfeldX / 2 - x / 2 - 5, spielfeldY / 2 + y - zeichenflaeche.getFontMetrics().getHeight() / 2 - 5, x + 10, zeichenflaeche.getFontMetrics().getHeight());

        Point[] erg = new Point[2];
        erg[0] = new Point(spielfeldX / 2 - x / 2 - 5, spielfeldY / 2 + y - zeichenflaeche.getFontMetrics().getHeight() / 2 - 5);
        erg[1] = new Point(erg[0].x + x + 10, erg[0].y + zeichenflaeche.getFontMetrics().getHeight());

        return erg;
    }

    public Point[] zeichneNewGame() {
        Font f = new Font("serif", Font.BOLD, 20);
        zeichenflaeche.setFont(f);
        int x = zeichenflaeche.getFontMetrics().stringWidth("Neues Spiel");

        int y = 50;
        zeichenflaeche.drawString("Neues Spiel", spielfeldX / 2 - x / 2, spielfeldY / 2 + y);
        zeichenflaeche.drawRect(spielfeldX / 2 - x / 2 - 5, spielfeldY / 2 + y - zeichenflaeche.getFontMetrics().getHeight() / 2 - 5, x + 10, zeichenflaeche.getFontMetrics().getHeight());

        Point[] erg = new Point[2];
        erg[0] = new Point(spielfeldX / 2 - x / 2 - 5, spielfeldY / 2 + y - zeichenflaeche.getFontMetrics().getHeight() / 2 - 5);
        erg[1] = new Point(erg[0].x + x + 10, erg[0].y + zeichenflaeche.getFontMetrics().getHeight());

        return erg;
    }

    public void zeichneStartUp() {
        Graphics2D g2d = (Graphics2D) zeichenflaeche;
        ImageIcon tb_src = new ImageIcon("resources//Titelbild.png");
        Image img = tb_src.getImage();
        double y = img.getHeight(null);
        double x = img.getWidth(null);
        g2d.drawImage(img, 0, 0, spielfeldX, (int) (y / x * spielfeldX), null);
    }
}
