package ch.hearc.wp2.p2.jeu.menus;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ch.hearc.wp2.p2.jeu.Game;
import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.tools.Audio;
import ch.hearc.wp2.p2.jeu.tools.VolumeControl;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;
import ch.hearc.wp2.p2.jeu.tools.position.JCenter;
import ch.hearc.wp2.p2.jeu.tools.position.JCenterH;

//main menu of our game allow to start playing/checking about page/checking the leaderboard

@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	private JButtonMenu quit;
	private JButtonMenu play;
	private JButtonMenu about;
	private JButtonMenu leaderBoard;
	private Game game;
	private VolumeControl vC;
	private static MainMenu mainMenu = null;

	// singleton
	public static MainMenu getInstance() {
		if (mainMenu == null)
			mainMenu = new MainMenu();
		return mainMenu;
	}

	private MainMenu() {
		this.vC = new VolumeControl();
		this.game = Game.getInstance();
		Audio.setVolume((float) 0.5);

		Box boxV = Box.createVerticalBox();
		play = new JButtonMenu("Play");
		quit = new JButtonMenu("Quit");
		about = new JButtonMenu("About");
		leaderBoard = new JButtonMenu("LeaderBoard");
		boxV.add(new JCenterH(play));
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(new JCenterH(leaderBoard));
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(vC);
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
				PauseMenu.getInstance().getvC().getvS().setValue(vC.getvS().getValue());
			}
		});

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
		g.drawImage(ShopImage.LOGO, 0, 0, getWidth(), getHeight(), 0, 0, ShopImage.LOGO.getWidth(null),
				ShopImage.LOGO.getHeight(null), null);
	}

	// getters & setters
	public VolumeControl getvC() {
		return vC;
	}

	public void setvC(VolumeControl vC) {
		this.vC = vC;
	}
}
