package ch.hearc.wp2.p2.jeu.tools;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

//sound managing of the project
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

//source : Project: Lyst-Master  File: VolumeControl.Java-https://www.javatips.net/api/javax.sound.sampled.floatcontrol
	public static void setVolume(float volume) {
		javax.sound.sampled.Mixer.Info[] mixers = AudioSystem.getMixerInfo();
		for (int i = 0; i < mixers.length; i++) {
			Mixer.Info mixerInfo = mixers[i];
			Mixer mixer = AudioSystem.getMixer(mixerInfo);
			Line.Info[] lineinfos = mixer.getTargetLineInfo();
			for (Line.Info lineinfo : lineinfos) {
				try {
					Line line = mixer.getLine(lineinfo);
					line.open();
					if (line.isControlSupported(FloatControl.Type.VOLUME)) {
						FloatControl control = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
						// Sets everything here.
						control.setValue((float) volume);
					}
				} catch (LineUnavailableException e) {
				}
			}
		}
	}

}
