
package ch.hearc.wp2.p2.jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Map extends JPanel {
	private Game game;
	private JButton buttonExit;

	public Map(Game game) {
		this.game = game;
		this.buttonExit = new JButton("EXIT");
	}

	public Map() {
		this.buttonExit = new JButton("EXIT");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		draw(g2D);
	}

	private void draw(Graphics2D g2d) {
		Box boxV = Box.createVerticalBox();
		Box boxH = Box.createHorizontalBox();
		boxH.add(Box.createHorizontalGlue());
		boxH.add(buttonExit);
		boxV.add(boxH);
		boxV.add(Box.createVerticalGlue());
		add(boxV);

		// green
		g2d.setColor(new Color(51, 204, 51));
		g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), 2 * getHeight() / 3));
		// brown
		g2d.setColor(new Color(153, 102, 51));
		g2d.fill(new Rectangle2D.Double(0, 2 * getHeight() / 3, getWidth(), getHeight() / 3));
	}
}
