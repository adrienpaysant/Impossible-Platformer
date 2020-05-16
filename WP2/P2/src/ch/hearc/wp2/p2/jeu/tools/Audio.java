package ch.hearc.wp2.p2.jeu.tools;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
	private Clip clip;

	public Audio(String path) throws UnsupportedAudioFileException, IOException {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(path));
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// getter
	public Clip getClip() {
		return clip;
	}

	// methodes
	public void play() {
		clip.start();
	}

	public void stop() {
		clip.stop();
	}

	public static void playSound(String path) {
		Audio s;
		try {
			s = new Audio(path);
			s.play();
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
