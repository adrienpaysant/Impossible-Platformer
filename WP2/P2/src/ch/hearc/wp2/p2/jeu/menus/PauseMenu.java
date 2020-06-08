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
import ch.hearc.wp2.p2.jeu.tools.KeyboardMenuPause;
import ch.hearc.wp2.p2.jeu.tools.VolumeControl;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;
import ch.hearc.wp2.p2.jeu.tools.position.JCenter;
import ch.hearc.wp2.p2.jeu.tools.position.JCenterH;

//pause page can be accessed when playing
@SuppressWarnings("serial")
public class PauseMenu extends JPanel {

	private JButtonMenu exit;
	private JButtonMenu resume;
	private Game game;
	private VolumeControl vC;

	// singleton
	private static PauseMenu pauseMenu = null;

	public static PauseMenu getInstance() {
		if (pauseMenu == null) {
			pauseMenu = new PauseMenu();
		}
		return pauseMenu;
	}

	private PauseMenu() {
		this.vC = new VolumeControl();
		this.game = Game.getInstance();

		Box boxV = Box.createVerticalBox();
		this.resume = new JButtonMenu("Resume");
		this.exit = new JButtonMenu("Exit To Leaderboard");
		boxV.add(new JCenterH(resume));
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(vC);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(new JCenterH(exit));
		add(new JCenter(boxV));

		resume.addActionListener(new ActionListener() {
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

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().setCurrent("leaderboard");
				LeaderBoard.getInstance().setDeathCount(Map.getInstance().getNbDeath() - 1);
				LeaderBoard.getInstance().setTextLabel("fail");
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(LeaderBoard.getInstance());
				Map.getInstance().init();
				game.setSize(game.getWidth() - 1, game.getHeight() - 1);

			}
		});

		this.addKeyListener(new KeyboardMenuPause());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// allow resizeEvent by deforming Image to the good width and height
		g.drawImage(ShopImage.MENUBG, 0, 0, getWidth(), getHeight(), 0, 0, ShopImage.MENUBG.getWidth(null),
				ShopImage.MENUBG.getHeight(null), null);
	}

	public VolumeControl getvC() {
		return vC;
	}

	public void setvC(VolumeControl vC) {
		this.vC = vC;
	}
}