
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
    Timer timer = null;
    
    
    public Control(MainGUI mGUI) {
        this.mgui = mGUI;
        this.zeichenflaeche = this.mgui.getCanvas().getGraphics();
        spielfeldX = this.mgui.getCanvas().getWidth();
        spielfeldY = this.mgui.getCanvas().getHeight();
        pixelgroese = spielfeldX / 30;
        
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
    
    public void neuesSpiel() {
        if (timer != null) {
            timer.stop();
        }
        //System.out.println(spielfeldX / pixelgroese);
        zeichenflaeche.clearRect(0, 0, spielfeldX, spielfeldY);
        Spielfeld = new Feld[spielfeldX / pixelgroese - 1][spielfeldX / pixelgroese - 1];
        for (int i = 0; i < spielfeldX / pixelgroese - 1; i++) {
            for (int i2 = 0; i2 < spielfeldX / pixelgroese - 1; i2++) {
                Spielfeld[i][i2] = new Feld(i * pixelgroese, i2 * pixelgroese, false);
            }
        }
        zeichneUndsetzeRand();
        sSnake = new Schlange(Spielfeld[25][25], this);
        sSnake.zeichneSchlange(zeichenflaeche);
        aktuellesZiel = generiereNeuesZiel();
        zeichneZiel();
        //ÜBERPRÜFEN!!
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
        Font f = new Font("serif",Font.BOLD,50);
        zeichenflaeche.setFont(f);
        int x = zeichenflaeche.getFontMetrics().stringWidth("GAME OVER!");
        zeichenflaeche.drawString("GAME OVER!",mgui.getCanvas().getWidth()/2 - x/2, 100);
    }
    
    public void zielwurdegefressen() {
        aktuellesZiel = generiereNeuesZiel();
        zeichneZiel();
    }
    
    public void changeDirection(Direction dir) {
        sSnake.setDirection(dir);
    }
    
    public void start() {
        if (timer != null) {
            timer.start();
        }
    }
    
    public void pause() {
        if (timer != null) {
            timer.stop();
        }
    }
    
    public void moveSchlange(Direction dir) {
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
        /*
         for (Feld sFeld : sSnake.getSchlangenliste()) {
         if (sFeld.getX() == fld.getX() && sFeld.getY() == fld.getY()) {
         erg = false;
         break;
         }
         }
         */
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
    
    private void zeichneZiel() {
        zeichenflaeche.setColor(Color.RED);
        zeichenflaeche.fillRect(aktuellesZiel.getX(), aktuellesZiel.getY(), pixelgroese, pixelgroese);
    }
    
    private void zeichneUndsetzeRand() {
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

class SnakeRun extends TimerTask {
    
    @Override
    public void run() {
        System.out.println("Make my day.");
    }
}