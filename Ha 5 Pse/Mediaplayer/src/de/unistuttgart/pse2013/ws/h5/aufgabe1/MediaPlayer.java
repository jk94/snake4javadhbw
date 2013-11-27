package de.unistuttgart.pse2013.ws.h5.aufgabe1;

/**
 * Ein Programm um Musik und Videos abzuspielen, im Rahmen der pse Übungsgruppe
 * erstellt
 * 
 * @author Tobias hirzel 2905750
 * @author Manuel Meyer 2859246
 * @author Sebatian Menrath 2908508
 * 
 *         Erstellungsdatum: 27.11.2013
 * 
 *         Entwicklungsumgebung : Eclipse 4.2.2, Linux
 */
public class MediaPlayer {

	/**
	 * @param args
	 *            Die main- Methode wer mehr Info braucht um dies zu verstehen
	 *            hat bei einem Quellcode nichts verloren
	 */

	public static void main(String[] args) {
		Song song1 = new Song();
		Song song2 = new Song();
		Song song3 = new Song();
		song1.setLocation("mediadateien/Maschendrahtzaun.mp3");
		song1.setTitle("Maschendtahtzaun");
		song2.setLocation("mediadateien/BugsBunny.mp3");
		song2.setTitle("BugsBunny");
		song3.setLocation("mediadateien/Tour.mp3");
		song3.setTitle("Tour");

		Playlist p = new Playlist();

		p.addNewSong(song1);
		p.addNewSong(song2);
		p.addNewSong(song3);
		p.printPlaylist();

		ISTEMediaPlayer mp = new ISTEMediaPlayer();
		mp.play(song1.getLocation());
		
	}
}
