
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jan
 */
public class Punkte {

    private int punktezaehler = 0;
    private int zielZaehler = 0;
    private Timer tmr;
    private int timecounter = 0;

    public void ZielGefressen() {
        TimerStop();
        zielZaehler++;
        tmr = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            zaehleZeit();
            }
        });
    }

    public void TimerStart() {
        if (tmr != null) {
            tmr.start();
        }
    }

    public void TimerStop() {
        if (tmr != null) {
            tmr.stop();
        }
    }

    private void zaehleZeit() {
        timecounter++;
    }

    private int PunkteBerechnen() {
        int erg = 0;

        return erg;
    }

    public int getPunktezaehler() {
        return punktezaehler;
    }

    public int getZielZaehler() {
        return zielZaehler;
    }
}
