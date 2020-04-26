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
public class PauseMenu extends JPanel {

	private Image bgImage;
	private JButtonMenu exit;
	private JButtonMenu resume;
	private JButtonMenu options;
	private Game game;
	//singleton
	private static PauseMenu pauseMenu=null;
	public static PauseMenu getInstance() {
		if (pauseMenu==null)
			pauseMenu = new PauseMenu();
		return pauseMenu;
	}
	private PauseMenu() {

		this.game = Game.getInstance();

		try {
			bgImage = ImageIO.read(getClass().getResource("/images/menubg2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Box boxV = Box.createVerticalBox();
		resume = new JButtonMenu("Resume");
		exit = new JButtonMenu("Exit To Menu");
		options = new JButtonMenu("Options");
		boxV.add(new JCenterH(resume));
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(new JCenterH(options));
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(new JCenterH(exit));
		add(new JCenter(boxV));
		
		resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(Map.getInstance());
				game.setSize(game.getWidth() - 1, game.getHeight() - 1);
			}
		});
		options.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

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