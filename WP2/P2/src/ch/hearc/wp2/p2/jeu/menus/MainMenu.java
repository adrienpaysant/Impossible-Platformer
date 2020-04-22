package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	private Image bgImage;

	public MainMenu() {
		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg.png"));
			System.out.println("Reussi");
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//ImageObserver observer = null;
		g.drawImage(bgImage, 0, 0,getWidth(),getHeight(),0,0,bgImage.getWidth(null),bgImage.getHeight(null), null);
	}
}
