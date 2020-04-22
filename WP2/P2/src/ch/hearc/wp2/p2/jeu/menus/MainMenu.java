package ch.hearc.wp2.p2.jeu.menus;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	private Image bgImage;
	private JButtonMenu quit;
	private JButtonMenu play;
	private JButtonMenu about;
	private JButtonMenu options;
	
	public MainMenu() {
		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Box boxV = Box.createVerticalBox();
		play = new JButtonMenu("Play");
		quit = new JButtonMenu("Quit");
		about = new JButtonMenu("About");
		options = new JButtonMenu("Options");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		boxV.add(Box.createVerticalStrut(HEIGHT/3));
		boxV.add(play);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(options);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(about);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(quit);
		boxV.add(Box.createVerticalStrut(HEIGHT/3));
		add(boxV);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//allow resizeEvent by deforming Image to the good width and height
		g.drawImage(bgImage, 0, 0,getWidth(),getHeight(),0,0,bgImage.getWidth(null),bgImage.getHeight(null), null);
	}
}
