package ch.hearc.wp2.p2.jeu.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import ch.hearc.wp2.p2.jeu.tools.position.JCenter;
import ch.hearc.wp2.p2.jeu.tools.position.JComponents;

//component that carry the slider to manage the volume

@SuppressWarnings("serial")
public class VolumeControl extends Box {

	private VolumeSlider vS;

	public VolumeControl() {
		super(BoxLayout.Y_AXIS);
		vS = new VolumeSlider(0, 10);
		add(new JCenter(new JLabel("MASTER VOLUME")));
		Box bH = Box.createHorizontalBox();
		bH.add(new JLabel("Min"));
		bH.add(new JCenter(vS));
		bH.add(new JLabel("Max"));
		add(bH);
		JComponents.setHeight(this, 50);
		JComponents.setWidth(this, 200);
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

	public VolumeSlider getvS() {
		return vS;
	}

	public void setvS(VolumeSlider vS) {
		this.vS = vS;
	}

}
