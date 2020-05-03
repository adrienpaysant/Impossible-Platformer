package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.tools.JCenter;
import ch.hearc.wp2.p2.jeu.tools.JCenterH;

@SuppressWarnings("serial")
public class About extends Box {

	private Game game;
	private JButtonMenu buttonExit;
	private Image bgImage;
	private static About about=null;
	public static About getInstance() {
		if(about==null)
			about = new About();
		return about;
	}
	private About() {
		super(BoxLayout.Y_AXIS);
		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.game = Game.getInstance();
		buttonExit = new JButtonMenu("Back to Menu");
		add(new JCenterH(buttonExit));
		add(Box.createVerticalGlue());
		add(new JCenter(new JLabel("I am the About page-P2 HEARC 2020")));
		
		
		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(MainMenu.getInstance());
				game.setSize(game.getWidth() - 1, game.getHeight() - 1);
			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// allow resizeEvent by deforming Image to the good width and height
		g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), 0, 0, bgImage.getWidth(null), bgImage.getHeight(null),
				null);
	}
}
