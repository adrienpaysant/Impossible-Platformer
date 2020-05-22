package ch.hearc.wp2.p2.jeu.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
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

	public static void playSoundLoop(String path) {
		Audio s;
		try {
			s = new Audio(path);
			s.getClip().loop(Clip.LOOP_CONTINUOUSLY);
			s.play();

		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void setVolume(float f) {
		javax.sound.sampled.Port.Info source = Port.Info.SPEAKER;
		if (AudioSystem.isLineSupported(source)) {
			try {
				Port outline = (Port) AudioSystem.getLine(source);
				outline.open();
				FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
				volumeControl.setValue(f);
				outline.close();
			} catch (LineUnavailableException ex) {
				ex.printStackTrace();
			}
		}
	}

}
