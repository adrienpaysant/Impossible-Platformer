
package ch.hearc.wp2.p2.jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.items.Caractere.Player;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.menus.MainMenu;
import ch.hearc.wp2.p2.jeu.tools.Chrono;
import ch.hearc.wp2.p2.jeu.tools.Keyboard;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

@SuppressWarnings("serial")
public class Map extends JPanel {

	public static final int BLOCK_WH = 50;

	private Game game;
	private JButton buttonExit;

	private ArrayList<Bloc> listBloc = new ArrayList<Bloc>();

	private static Map map = null;

	private Player player;
	private int dX;

	public static Map getInstance() {
		if (map == null)
			map = new Map();
		return map;
	}

	// constructor
	private Map() {

//		// ??
//		this.setFocusable(true);
//		this.requestFocusInWindow(true);
//		this.setRequestFocusEnabled(true);
		//

		this.game = Game.getInstance();
		this.buttonExit = new JButton("Back to Menu");
		this.player = new Player(game.getWidth() / 2, game.getHeight() / 3, 25, 55, true);
		this.dX = 0;

		// listeners
		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.setSize(game.getWidth() + 1, game.getHeight() + 1);
				game.setContentPane(MainMenu.getInstance());
				game.setSize(game.getWidth() - 1, game.getHeight() - 1);
			}
		});

		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				if (listBloc.isEmpty())
					setBlocList();
				else {
					listBloc.clear();
					setBlocList();

					// only for test
					for (int i = 0; i < 100; i++) {
						if (player.getRect().x > game.getWidth())
							setdX(-game.getWidth() / 3);
						else
							setdX(1);
					}
					//
				}
			}

			private void setBlocList() {
				int alea = 5 + (int)(Math.random() * ((20 - 5) + 1));
				// initial path
				for (int i = 0; i < game.getWidth() / 50; i++) {
					if (i % alea != 0) {
						// path = 1st Layer
						listBloc.add(new Bloc(BLOCK_WH * i, game.getHeight() / 2, BLOCK_WH, BLOCK_WH, true,
								ShopImage.PATHBLOCK.getImage()));
						// 2nd Layer
						listBloc.add(new Bloc(BLOCK_WH * i, BLOCK_WH + game.getHeight() / 2, BLOCK_WH, BLOCK_WH, true,
								ShopImage.DIRTBLOCK.getImage()));

						// trap test
						if (i % alea == 2)
							listBloc.add(new Bloc(BLOCK_WH * i, -BLOCK_WH + game.getHeight() / 2, BLOCK_WH, BLOCK_WH,
									true, ShopImage.SPIKES.getImage()));
					}
					
				}

			}

		});

		// listener of keyboard
		// addKeyListener(new Keyboard());

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					setdX(-1);
					System.out.println("vk gauche");
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					setdX(1);
					System.out.println("vk droite");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setdX(0);
			}
		});

		Thread chronoMap = new Thread(new Chrono());
		chronoMap.start();
	}

	// getters & setters
	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	// painting
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		draw(g2D);
	}

	private void draw(Graphics2D g2d) {
		add(buttonExit);
		// background
		g2d.setColor(new Color(51, 204, 250));
		g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

		// test blocs
		g2d.setColor(Color.green);
		for (Bloc bloc : listBloc) {
			if (bloc.isVisible()) {
				g2d.drawImage(bloc.getTexture(), (int) bloc.getRect().x, (int) bloc.getRect().y,
						(int) (bloc.getRect().width + bloc.getRect().x),
						(int) (bloc.getRect().height + bloc.getRect().y), 0, 0, bloc.getTexture().getWidth(null),
						bloc.getTexture().getHeight(null), null);
			}
		}

		// player
		// TODO TOFIX : wrong method : c'est la map qui doit bouger
		player.moveByX(dX);
		g2d.setColor(Color.black);
		if (player.isVisible())
			g2d.draw(player.getRect());
	}
}
