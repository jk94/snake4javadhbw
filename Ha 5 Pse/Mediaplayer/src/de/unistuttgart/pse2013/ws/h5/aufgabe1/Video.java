package de.unistuttgart.pse2013.ws.h5.aufgabe1;

/**
 * Deklariert die Videos in der Playlist
 * 
 * @param title
 *            ist der Name des Videos
 * @param location
 *            ist die Stelle an der das Video steht
 * 
 * @author Tobias hirzel 2905750
 * @author Manuel Meyer 2859246
 * @author Sebatian Menrath 2908508
 * 
 *         Erstellungsdatum: 27.11.2013
 * 
 *         Entwicklungsumgebung : Eclipse 4.2.2, Linux
 */
public class Video {
	private String title = "";
	private String location = "";

	public String getTitle() {
		return title;

	}

	public static void setTitle(String title) {

	}

	public static void setLocation(String location) {

	}

	public String getLocation() {
		return location;

	}

	public void printInfo() {
		System.out.println(title + location);

	}
}
