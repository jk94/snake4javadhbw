package Standardpackage;

import Enums.EnumGameStatus;
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
                        }
                    }
                    break;

                case PAUSE:
                    break;
                case PLAYING:
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
                        }
                    }
                    break;
                default:
            }
        }
    }

    public void GameOver() {
        cnt.stop();
        cnt.getZeichenControl().zeichneGameOverMessage(cnt.getPunkte());
        GS = EnumGameStatus.GAMEOVER;
        neuesSpielKor = cnt.getZeichenControl().zeichneNewGame();
        exitKor = cnt.getZeichenControl().zeichneExit();
    }
    
    public void startup(){
        cnt.getZeichenControl().zeichneStartUp();
        cnt.getZeichenflaeche().setColor(Color.WHITE);
        neuesSpielKor = cnt.getZeichenControl().zeichneNewGame();
        exitKor = cnt.getZeichenControl().zeichneExit();
    }
}
