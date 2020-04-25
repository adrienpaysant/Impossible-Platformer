package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.tools.JCenter;
import ch.hearc.wp2.p2.jeu.tools.JCenterH;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	private Image bgImage;
	private JButtonMenu quit;
	private JButtonMenu play;
	private JButtonMenu about;
	private JButtonMenu options;
	private Game game;

	public MainMenu(Game game) {

		this.game = game;

		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Box boxV = Box.createVerticalBox();
		play = new JButtonMenu("Play");
		quit = new JButtonMenu("Quit");
		about = new JButtonMenu("About");
		options = new JButtonMenu("Options");
		// setLayout(new FlowLayout(FlowLayout.CENTER));
		boxV.add(new JCenterH(play));
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(new JCenterH(options));
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(new JCenterH(about));
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(new JCenterH(quit));
		add(new JCenter(boxV));

		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		play.addActionListener(new ActionListener() {
			// demander au profs
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				game.resize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(new Map(game));
				game.resize(game.getWidth() - 1, game.getHeight() - 1);
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
