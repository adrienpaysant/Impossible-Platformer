package ch.hearc.wp2.p2.jeu.tools;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class VolumeSlider extends JSlider {

	public VolumeSlider(int min, int max) {
		super(min, max);

		this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Audio.setVolume((float) getValue() / getMaximum());
			}
		});
	}
}
