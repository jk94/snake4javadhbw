package de.unistuttgart.pse2013.ws.h5.aufgabe1;

import java.util.ArrayList;

/**
 * Die Playlist um Songs und Videos zu verwalten
 * 
 * @param name
 *            ist der Name der Playlist
 * @param itemCount
 *            gibt die anzahl der Songs und Videos an
 * 
 * @author Tobias hirzel 2905750
 * @author Manuel Meyer 2859246
 * @author Sebatian Menrath 2908508
 * 
 *         Erstellungsdatum: 27.11.2013
 * 
 *         Entwicklungsumgebung : Eclipse 4.2.2, Linux
 */
public class Playlist {
	private String name = "";
	private int itemCount = 0;
	ArrayList<Song> VecSong = new ArrayList<Song>();
	ArrayList<Video> VecVid = new ArrayList<Video>();


	public void addNewSong(Song song) {
		VecSong.add(song);

	}

	public void removeSong(int index) {
		VecSong.remove(index);

	}

	public void addNewVideo(Video video) {
		VecVid.add(video);

	}

	public void removeVideo(int index) {
		VecVid.remove(index);

	}

	public static void getSongList() {

	}

	public static void getName() {

	}

	public static void setName(String name) {

	}

	public void printSongList() {
		System.out.println(name);

	}

	public void printVideoList() {

	}

	public void printItemCount() {
		System.out.println(itemCount);

	}

	public void printPlaylist() {
		System.out.println("Die Playlist besteht aus:");
		for (int i = 0; i < VecSong.size(); i++)
			System.out.println(i+1 + ". " + VecSong.get(i).getTitle());
	}
}
