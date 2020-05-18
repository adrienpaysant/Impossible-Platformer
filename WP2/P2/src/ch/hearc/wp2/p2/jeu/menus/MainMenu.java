package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.tools.Audio;
import ch.hearc.wp2.p2.jeu.tools.position.JCenter;
import ch.hearc.wp2.p2.jeu.tools.position.JCenterH;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	private Image bgImage;
	private JButtonMenu quit;
	private JButtonMenu play;
	private JButtonMenu about;
	//private JButtonMenu options;
	private JButtonMenu leaderBoard;
	private Game game;
	private static MainMenu mainMenu = null;

	// singleton
	public static MainMenu getInstance() {
		if (mainMenu == null)
			mainMenu = new MainMenu();
		return mainMenu;
	}

	private MainMenu() {

		this.game = Game.getInstance();

		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Box boxV = Box.createVerticalBox();
		play = new JButtonMenu("Play");
		quit = new JButtonMenu("Quit");
		about = new JButtonMenu("About");
		//options = new JButtonMenu("Options");
		leaderBoard = new JButtonMenu("LeaderBoard");
		boxV.add(new JCenterH(play));
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(new JCenterH(leaderBoard));
//		boxV.add(Box.createVerticalStrut(20));
//		boxV.add(new JCenterH(options));
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
			@Override
			public void actionPerformed(ActionEvent e) {
				Audio.playSound("/audio/respawn.wav");
				Game.getInstance().setCurrent("map");
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(Map.getInstance());
				// for the focus in map
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						Map.getInstance().requestFocusInWindow();
					}
				});
				game.setSize(game.getWidth() - 1, game.getHeight() - 1);
			}
		});
//		options.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Game.getInstance().setCurrent("options");
//				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
//				game.setContentPane(Options.getInstance());
//				game.setSize(game.getWidth() - 1, game.getHeight() - 1);
//			}
//		});
		leaderBoard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().setCurrent("leaderboard");
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(LeaderBoard.getInstance());
				game.setSize(game.getWidth() - 1, game.getHeight() - 1);
			}
		});
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().setCurrent("about");
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(About.getInstance());
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
