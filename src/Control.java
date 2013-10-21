
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.Timer;

public class Control {

    private MainGUI mgui;
    private Schlange sSnake;
    private Feld[][] Spielfeld;
    private Graphics zeichenflaeche;
    private Feld aktuellesZiel;
    int spielfeldX, spielfeldY;
    int pixelgroese;
    private Timer timer = null;
    private Punkte pkt = null;
    private Zeichencontrol zcnt;

    public Control(MainGUI mGUI) {
        this.mgui = mGUI;
        this.zeichenflaeche = this.mgui.getCanvas().getGraphics();

        spielfeldX = this.mgui.getCanvas().getWidth();
        spielfeldY = this.mgui.getCanvas().getHeight();
        pixelgroese = spielfeldX / 30;
        zcnt = new Zeichencontrol(zeichenflaeche, pixelgroese, spielfeldX, spielfeldY);

    }

    public int getFeldgroese() {
        return this.pixelgroese;
    }

    public Schlange getSchlange() {
        return this.sSnake;
    }

    public Feld[][] getSpielfeld() {
        return this.Spielfeld;
    }

    public Graphics getZeichenflaeche() {
        return this.zeichenflaeche;
    }

    public Feld getAktuelleZiel() {
        return this.aktuellesZiel;
    }

    public Zeichencontrol getZeichenControl() {
        return zcnt;
    }

    public void neuesSpiel() {
        if (timer != null) {
            timer.stop();
        }
        //System.out.println(spielfeldX / pixelgroese);
        //zeichenflaeche.clearRect(0, 0, spielfeldX, spielfeldY);
        Spielfeld = new Feld[spielfeldX / pixelgroese - 1][spielfeldX / pixelgroese - 1];
        for (int i = 0; i < spielfeldX / pixelgroese - 1; i++) {
            for (int i2 = 0; i2 < spielfeldX / pixelgroese - 1; i2++) {
                Spielfeld[i][i2] = new Feld(i * pixelgroese, i2 * pixelgroese, false);
            }
        }
        this.zcnt.zeichneFeld(Spielfeld);
        sSnake = new Schlange(Spielfeld[(int) (Spielfeld.length / 2)][(int) (Spielfeld.length / 2)], this);
        zcnt.zeichneSchlange(sSnake.getSchlangenliste());
        aktuellesZiel = generiereNeuesZiel();
        zcnt.zeichneZiel(aktuellesZiel);
        pkt = new Punkte();
        mgui.setlbl_Punkte(0);
        
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                letSnakeRun();
            }
        });
    }

    

    public void gameOver() {
        timer.stop();
        timer = null;
        zeichenflaeche.setColor(Color.WHITE);
        //zeichenflaeche.drawChars(new String("GAME OVER!").toCharArray(), 0, 0, pixelgroese, pixelgroese);
        Font f = new Font("serif", Font.BOLD, 50);
        zeichenflaeche.setFont(f);
        int x = zeichenflaeche.getFontMetrics().stringWidth("GAME OVER!");
        zeichenflaeche.drawString("GAME OVER!", mgui.getCanvas().getWidth() / 2 - x / 2, 100);
        f = new Font("serif", Font.BOLD, 30);
        zeichenflaeche.setFont(f);
        x = zeichenflaeche.getFontMetrics().stringWidth("Erreichte Punktzahl: " + pkt.getPunktezaehler());
        zeichenflaeche.drawString("Erreichte Punktzahl: " + pkt.getPunktezaehler(), mgui.getCanvas().getWidth() / 2 - x / 2, mgui.getCanvas().getHeight() / 2);
        mgui.setlbl_Punkte(-1);
    }

    public void zielwurdegefressen() {
        aktuellesZiel = generiereNeuesZiel();
        pkt.ZielGefressen();
        mgui.setlbl_Punkte(pkt.getPunktezaehler());
        zcnt.schreibePunkte(pkt, Spielfeld);
        zcnt.zeichneZiel(aktuellesZiel);
    }

    public void changeDirection(Direction dir) {
        sSnake.setDirection(dir);
    }

    public void start() {
        if (timer != null) {
            timer.start();
        }
        if (pkt != null) {
            pkt.TimerStart();
        }
    }

    public void pause() {
        if (timer != null) {
            timer.stop();
        }
        if (pkt != null) {
            pkt.TimerStop();
        }
    }

    private Feld generiereNeuesZiel() {
        Feld erg = null;

        do {
            int rndx = (int) (Math.random() * (Spielfeld.length - 1 - 1) + 1);
            int rndy = (int) (Math.random() * (Spielfeld.length - 1 - 1) + 1);
            erg = Spielfeld[rndx][rndy];

        } while (!istZielfeldOK(erg));

        return erg;
    }

    private boolean istZielfeldOK(Feld fld) {
        boolean erg = true;
        
        for (int i = 0; i < Spielfeld.length; i++) {
            for (int i2 = 0; i2 < Spielfeld[i].length; i2++) {
                if (Spielfeld[i][i2].getX() == fld.getX() && Spielfeld[i][i2].getY() == fld.getY()) {
                    if (Spielfeld[i][i2].getBlocked()) {
                        erg = false;
                        break;
                    }
                }
            }
        }

        return erg;
    }

    public Feld gibAnliegendesFeld(Feld aFeld, Direction dir) {
        int zaehler1 = 0, zaehler2 = 0;
        boolean gefunden = false;
        Feld erg = aFeld;
        for (int i = 0; i < Spielfeld.length; i++) {
            for (int i2 = 0; i2 < Spielfeld[i].length; i2++) {
                if (Spielfeld[i][i2].equals(aFeld)) {
                    zaehler1 = i;
                    zaehler2 = i2;
                    break;
                }
            }
            if (gefunden) {
                break;
            }
        }

        switch (dir) {
            case HOCH:
                zaehler2--;
                break;
            case RUNTER:
                zaehler2++;
                break;
            case LINKS:
                zaehler1--;
                break;
            case RECHTS:
                zaehler1++;
                break;
        }
        erg = Spielfeld[zaehler1][zaehler2];
        return erg;
    }

    public void letSnakeRun() {
        getSchlange().move();
    }
}