
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Schlange {

    private ArrayList<Feld> schlangenliste;
    private ArrayList<Feld> gefresseneZieleZumAnhaengen;
    private Feld sKopf;
    private Control cnt;
    private Direction direction;
    private boolean moved = false;

    public Schlange(Feld startkopf, Control cont) {
        this.sKopf = startkopf;
        this.schlangenliste = new ArrayList();
        this.schlangenliste.add(sKopf);
        this.gefresseneZieleZumAnhaengen = new ArrayList();
        this.cnt = cont;
        this.direction = Direction.HOCH;
    }

    public void setDirection(Direction dir) {
        if (!moved) {
            switch (dir) {
                case HOCH:
                    if (this.direction != Direction.RUNTER) {
                        this.direction = dir;
                        moved = true;
                    }
                    break;
                case RUNTER:
                    if (this.direction != Direction.HOCH) {
                        this.direction = dir;
                        moved = true;
                    }
                    break;
                case LINKS:
                    if (this.direction != Direction.RECHTS) {
                        this.direction = dir;
                        moved = true;
                    }
                    break;
                case RECHTS:
                    if (this.direction != Direction.LINKS) {
                        this.direction = dir;
                        moved = true;
                    }
                    break;
            }
        }
        System.out.println(dir);
    }

    public void setKopf(Feld kopf) {
        this.sKopf = kopf;
    }

    public Feld getKopf() {
        return this.sKopf;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public ArrayList<Feld> getSchlangenliste() {
        return this.schlangenliste;
    }

    public void addFeld(Feld feld) {
    }

    public boolean istzielGefressen(Feld ziel) {
        boolean erg = false;
        /*if (ziel.getX() == sKopf.getX() && ziel.getY() == sKopf.getY()) {
         erg = true;

         }*/
        if (ziel.equals(sKopf)) {
            erg = true;
        }
        return erg;
    }

    private void addGefressenesZiel(Feld ziel) {
        this.gefresseneZieleZumAnhaengen.add(ziel);
        
    }

    public void zeichneSchlange(Graphics zfl) {
        for (Feld sFeld : schlangenliste) {
            zfl.setColor(Color.GREEN);
            zfl.fillRect(sFeld.getX(), sFeld.getY(), cnt.getFeldgroese(), cnt.getFeldgroese());
        }
    }

    public void loescheAnzeigeSchlange(Graphics zfl) {
        for (Feld sFeld : schlangenliste) {
            zfl.clearRect(sFeld.getX(), sFeld.getY(), cnt.getFeldgroese(), cnt.getFeldgroese());
        }
    }

    public void move() {
        // TODO Grenzen Festlegen!!
        if (cnt.gibAnliegendesFeld(sKopf, direction).getBlocked() == false) {
            loescheAnzeigeSchlange(cnt.getZeichenflaeche());
            for (Feld sf : schlangenliste) {
                sf.deactivate();
            }
            sKopf = cnt.gibAnliegendesFeld(sKopf, direction);
            schlangenliste.add(0, sKopf);

            Feld letztesFeld = schlangenliste.get(schlangenliste.size() - 1);
            schlangenliste.remove(letztesFeld);
            if (gefresseneZieleZumAnhaengen.size() > 0) {
                if (letztesFeld.equals(gefresseneZieleZumAnhaengen.get(0))) {
                    schlangenliste.add(gefresseneZieleZumAnhaengen.get(0));
                    gefresseneZieleZumAnhaengen.remove(0);
                }
            }
            for (Feld sf : schlangenliste) {
                sf.activate();
            }
            zeichneSchlange(cnt.getZeichenflaeche());
            if (istzielGefressen(cnt.getAktuelleZiel())) {
                addGefressenesZiel(cnt.getAktuelleZiel());
                cnt.zielwurdegefressen();
            }
            moved = false;
        } else {
            cnt.gameOver();
        }
    }
}