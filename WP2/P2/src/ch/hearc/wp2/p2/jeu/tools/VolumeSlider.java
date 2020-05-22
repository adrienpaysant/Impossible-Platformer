package ch.hearc.wp2.p2.jeu.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
