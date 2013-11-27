package de.unistuttgart.pse2013.ws.h5.aufgabe1;

import java.io.File;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Mp3Player;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;

public class ISTEMediaPlayer {

	private Mp3Player ISTEPlayer = new Mp3Player();

	public void playPlayList(Playlist playlist) {

	}

	private static long getduration(String location) {

		long duration = 0;

		try {
			MP3File mp3File = (MP3File) AudioFileIO.read(new File(location));
			duration = (long) mp3File.getMP3AudioHeader()
					.getPreciseTrackLength();

		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("Duration in milliseconds=" + duration);
		return duration;
	}

	public void play(String location) {

		try {

			ISTEPlayer.play(location);

			if (getduration(location) != 0)
				Thread.sleep(getduration(location) * 1000);
			else
				Thread.sleep(1000);
		} catch (IOException | JavaLayerException | InterruptedException e) {

			System.err.println(e.getMessage());
		}

	}

	public void pause() {
		if (ISTEPlayer != null) {
			ISTEPlayer.pause();
		}
	}

	public void stop() {
		if (ISTEPlayer != null) {
			ISTEPlayer.stop();

		}
	}
}