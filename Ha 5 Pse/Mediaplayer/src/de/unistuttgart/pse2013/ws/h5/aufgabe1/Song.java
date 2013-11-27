package de.unistuttgart.pse2013.ws.h5.aufgabe1;

/**
 * Deklariert die Songs in der Playlist
 * 
 * @param title
 *            ist der Name des Songs
 * @param location
 *            ist die Stelle an der der Song steht
 * 
 * @author Tobias hirzel 2905750
 * @author Manuel Meyer 2859246
 * @author Sebatian Menrath 2908508
 * 
 *         Erstellungsdatum: 27.11.2013
 * 
 *         Entwicklungsumgebung : Eclipse 4.2.2, Linux
 */
public class Song {

	private String title = null;
	private String location = null;

	public String getTitle() {
		return title;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;

	}

	public void printInfo() {
		System.out.println(title);

	}

}
