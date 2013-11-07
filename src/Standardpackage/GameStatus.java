package Standardpackage;

import Controls.Control;
import Enums.EnumGameStatus;
import Zeichenobjekte.Feld;
import java.awt.Color;
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
    private Point[] neuesSpielKor;
    private Point[] exitKor;

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
                    if (evt.getX() >= neuesSpielKor[0].x && evt.getX() <= neuesSpielKor[1].x
                            && evt.getY() >= neuesSpielKor[0].y && evt.getY() <= neuesSpielKor[1].y) {
                        cnt.neuesSpiel();
                        this.GS = EnumGameStatus.PLAYING;
                        cnt.start();
                    } else {
                        if (evt.getX() >= exitKor[0].x && evt.getX() <= exitKor[1].x
                                && evt.getY() >= exitKor[0].y && evt.getY() <= exitKor[1].y) {
                            System.exit(0);
                        } else {
                            clickTestTon(evt);
                        }
                    }

                    break;

                case PAUSE:
                    break;
                case PLAYING:
                    clickTestTon(evt);
                    break;
                case START:
                    if (evt.getX() >= neuesSpielKor[0].x && evt.getX() <= neuesSpielKor[1].x
                            && evt.getY() >= neuesSpielKor[0].y && evt.getY() <= neuesSpielKor[1].y) {
                        cnt.neuesSpiel();
                        this.GS = EnumGameStatus.PLAYING;
                        cnt.start();
                    } else {
                        if (evt.getX() >= exitKor[0].x && evt.getX() <= exitKor[1].x
                                && evt.getY() >= exitKor[0].y && evt.getY() <= exitKor[1].y) {
                            System.exit(0);
                        } else {
                            clickTestTon(evt);
                        }
                    }
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
        GS = EnumGameStatus.GAMEOVER;
        neuesSpielKor = cnt.getZeichenControl().zeichneNewGame();
        exitKor = cnt.getZeichenControl().zeichneExit();
    }

    public void startup() {
        cnt.getZeichenControl().zeichneStartUp();
        cnt.getZeichenflaeche().setColor(Color.WHITE);
        neuesSpielKor = cnt.getZeichenControl().zeichneNewGame();
        exitKor = cnt.getZeichenControl().zeichneExit();
        Feld[][] Spielfeld = cnt.getSpielfeld();
//        cnt.getZeichenControl().zeichneTonIcon(new Point(Spielfeld[Spielfeld.length - 1][Spielfeld.length - 1].getX(), Spielfeld[Spielfeld.length - 1][Spielfeld.length - 1].getY()));
    }
}
