package Standardpackage;

import Controls.Control;
import Enums.EnumGameStatus;
import Zeichenobjekte.CButton;
import Zeichenobjekte.Feld;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jan
 */
public class GameStatus {

    private Enums.EnumGameStatus GS;
    private Control cnt;
    private CButton neuesSpiel, exit;

    public GameStatus(Control ccnt) {
        this.cnt = ccnt;
        this.GS = Enums.EnumGameStatus.START;
        startup();
    }

    public Enums.EnumGameStatus getGameStatus() {
        return GS;
    }

    public void clicked(java.awt.event.MouseEvent evt) {
        if (evt.getButton() == 1) {
            switch (GS) {
                case GAMEOVER:
                    if (!neuesSpiel.equals(null)) {
                        if (neuesSpiel.clicked(evt)) {
                            cnt.neuesSpiel();
                            this.GS = EnumGameStatus.PLAYING;
                            cnt.start();
                            break;
                        }
                    }
                    if (!exit.equals(null)) {
                        if (exit.clicked(evt)) {
                            System.exit(0);
                            break;
                        }
                    }
                    clickTestTon(evt);
                    break;
                case PAUSE:
                    break;
                case PLAYING:
                    clickTestTon(evt);
                    break;
                case START:
                    if (!neuesSpiel.equals(null)) {
                        if (neuesSpiel.clicked(evt)) {
                            cnt.neuesSpiel();
                            this.GS = EnumGameStatus.PLAYING;
                            cnt.start();
                        }
                        break;
                    }
                    if (!exit.equals(null)) {
                        if (exit.clicked(evt)) {
                            System.exit(0);
                        }
                        break;
                    }
                    clickTestTon(evt);
                    break;
                default:
            }
        }
    }

    private void clickTestTon(java.awt.event.MouseEvent evt) {
        Feld[][] Spielfeld = cnt.getSpielfeld();
        Point pt = new Point(Spielfeld[Spielfeld.length - 1][Spielfeld.length - 1].getX(), Spielfeld[Spielfeld.length - 1][Spielfeld.length - 1].getY());
        if (evt.getX() >= pt.x && evt.getX() <= pt.x + cnt.getFeldgroese()
                && evt.getY() >= pt.y && evt.getY() <= pt.y + cnt.getFeldgroese()) {
            cnt.toggleTon();
        }
    }

    public void GameOver() {
        cnt.stop();
        cnt.getZeichenControl().zeichneGameOverMessage(cnt.getPunkte());
        cnt.getPunkte().gameOver();
        GS = EnumGameStatus.GAMEOVER;
        neuesSpiel = getNeuesSpielButton();
        exit = getExitButton();

        cnt.getZeichenControl().zeichneNewGame(neuesSpiel);
        cnt.getZeichenControl().zeichneExit(exit);

    }

    public void startup() {
        cnt.getZeichenControl().zeichneStartUp();

        neuesSpiel = getNeuesSpielButton();
        exit = getExitButton();

        //neuesSpielKor = cnt.getZeichenControl().zeichneNewGame();
        cnt.getZeichenControl().zeichneNewGame(neuesSpiel);
        cnt.getZeichenControl().zeichneExit(exit);
    }

    private CButton getNeuesSpielButton() {
        //spielfeldX / 2 - x / 2
        CButton erg = null;

        erg = new CButton("Neues Spiel", 0, 0, 0, 0);
        erg.setFo(new Font("serif", Font.BOLD, 20));
        erg.setBorder(1);
        erg.setTextcolor(Color.WHITE);
        erg.setBordercolor(Color.WHITE);
        erg.setWidth(cnt.getZeichenflaeche().getFontMetrics(erg.getFo()).stringWidth("Neues Spiel") + 10);
        erg.setHeight(cnt.getZeichenflaeche().getFontMetrics(erg.getFo()).getHeight() + 10);
        erg.setX(cnt.getWidth() / 2 - erg.getWidth() / 2 - 5);
        erg.setY((int) (cnt.getHeight() / 2) + 50);

        return erg;
    }

    private CButton getExitButton() {
        CButton erg = null;

        erg = new CButton("Exit", 0, 0, 0, 0);
        erg.setFo(new Font("serif", Font.BOLD, 20));
        erg.setBorder(1);
        erg.setBordercolor(Color.WHITE);
        erg.setTextcolor(Color.WHITE);
        erg.setWidth(neuesSpiel.getWidth());
        erg.setHeight(cnt.getZeichenflaeche().getFontMetrics(erg.getFo()).getHeight() + 10);
        erg.setX(cnt.getWidth() / 2 - erg.getWidth() / 2 - 5);
        erg.setY((int) (cnt.getHeight() / 2) + 50 + getNeuesSpielButton().getHeight() + 10);

        return erg;
    }
}
