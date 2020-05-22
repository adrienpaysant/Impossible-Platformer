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

	private static VolumeSlider volumeSlider = null;

	public static VolumeSlider getInstance() {
		if (volumeSlider == null) {
			volumeSlider = new VolumeSlider(0, 100);
		}
		return volumeSlider;
	}

	public VolumeSlider(int min, int max) {
		super(min, max);

		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				Audio.setVolume((float) getValue() / getMaximum());
			}
		});
		JComponents.setHeight(this, 25);
		JComponents.setWidth(this, 200);
	}

	public VolumeSlider getVolumeSlider() {
		return this;
	}

	protected void paintComponent(Graphics g) {
		// round borders
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
		g2d.clip(r2d);

		g2d.setPaint(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		super.paintComponent(g);
	}
}
