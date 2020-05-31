package ch.hearc.wp2.p2.jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

import ch.hearc.wp2.p2.jeu.items.Charactere.Player;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.items.blocs.actions.CheckPointBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.FallBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.SpikeBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.TrapBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.TypeTrap;
import ch.hearc.wp2.p2.jeu.items.decoration.Cloud;
import ch.hearc.wp2.p2.jeu.menus.LeaderBoard;
import ch.hearc.wp2.p2.jeu.tools.Audio;
import ch.hearc.wp2.p2.jeu.tools.Chrono;
import ch.hearc.wp2.p2.jeu.tools.ChronoPlayer;
import ch.hearc.wp2.p2.jeu.tools.ChronoTrap;
import ch.hearc.wp2.p2.jeu.tools.ExitButton;
import ch.hearc.wp2.p2.jeu.tools.Keyboard;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

@SuppressWarnings("serial")
public class Map extends JPanel {

	public static final int BLOC_WH = 60;
	private static final int SUN_WH = 150;
	private static final int CLOUD_WH = 75;
	private static final int SPEED = 3;
	private static final double PLAYER_H = 55;
	private static final double PLAYER_W = 35;
	public static final int GRAVITY = 4;
	private static final int DEATH_WH = 75;
	private static final boolean DEBUG = false;

	private Game game;
	private ExitButton exitButton;

	private ArrayList<Bloc> listBloc = new ArrayList<Bloc>();
	private ArrayList<CheckPointBloc> listCPBloc = new ArrayList<CheckPointBloc>();
	private ArrayList<Cloud> listCloud = new ArrayList<Cloud>();
	private ArrayList<TrapBloc> listTrap = new ArrayList<TrapBloc>();

	private CheckPointBloc lastCP;
	private CheckPointBloc firstCP;

	private static Map map = null;
	private Player player;

	private double dX;
	private int groundH;
	private int nbDeath;
	private boolean win;
	private boolean lastCPset;
	private boolean hasPlay;
	private boolean lastDir;

	public static Map getInstance() {
		if (map == null) {
			map = new Map();
			map.addKeyListener(new Keyboard());
		}

		return map;
	}

	// constructor
	private Map() {
		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				groundH = 2 * getGame().getHeight() / 3;
				if (listBloc.isEmpty() && listCloud.isEmpty())
					setBlocList();
				else {
					listCloud.clear();
					setBlocList();
				}
			}

		});
		this.game = Game.getInstance();
		this.exitButton = new ExitButton("Pause", "PauseMenu");
		this.player = new Player(getGame().getWidth() / 2, getGame().getHeight() / 3, PLAYER_W, PLAYER_H, true,
				ShopImage.BASEPLAYER);
		this.dX = 0;
		this.nbDeath = 0;
		this.groundH = 2 * getGame().getHeight() / 3;
		this.win = false;
		this.lastCPset = false;
		this.hasPlay = false;
		this.setLastDir(true);

		new Thread(new Chrono()).start();
		new Thread(new ChronoTrap()).start();
		new Thread(new ChronoPlayer()).start();
		
	}

	// painting
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		draw(g2D);
	}

	private void draw(Graphics2D g2d) {

		if (!win) {
			add(exitButton);
			// background
			g2d.setColor(new Color(51, 204, 250));

			g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));

			// test & collisions
			for (Bloc bloc : listBloc) {
				player.contact(bloc);
			}

			// test statement player
			if (player.getMaxY() >= getGame().getHeight()) {
				player.setJumping(true);
				Audio.playSound("/audio/die.wav");
				player.respawn();
			}

			updateLastCP();

			// test the victory
			if (lastCPset)
				if (player.getMaxX() >= lastCP.getCenterX() && player.getMaxY() <= lastCP.y) {
					win = true;
				}
			// number of death
			g2d.setColor(Color.black);
			g2d.drawImage(ShopImage.DEATH, 0, 0, DEATH_WH, DEATH_WH, 0, 0, ShopImage.DEATH.getWidth(null),
					ShopImage.DEATH.getHeight(null), null);
			g2d.setFont(new Font("Monospaced", Font.BOLD, 45));
			g2d.drawString(":" + (nbDeath - 1) + " DEATHS", DEATH_WH, 2 * DEATH_WH / 3);

			player.moveByY(GRAVITY);
			g2d.setColor(Color.black);
			if (player.isVisible()) {
//				g2d.fill(player);
				g2d.drawImage(player.getTexture()/*
													 * .getScaledInstance((int)player.width, (int)player.height,
													 * Image.SCALE_DEFAULT)
													 */, (int) player.x + 10, (int) player.y,
						(int) (player.width + player.x), (int) (player.height + player.y), 0, 0,
						player.getTexture().getWidth(null) * 2, player.getTexture().getHeight(null), null);

			}
			// blocs
			g2d.setColor(Color.green);
			for (Bloc bloc : listBloc) {
				// - dX to move with the player
				bloc.moveByX(-dX * SPEED);

				if (DEBUG) {
					// debug mode
					g2d.drawRect((int) bloc.x, (int) bloc.y, (int) bloc.width, (int) bloc.height);
				} else {
					if (bloc.isVisible()) {
						g2d.drawImage(bloc.getTexture(), (int) bloc.x, (int) bloc.y, (int) (bloc.width + bloc.x),
								(int) (bloc.height + bloc.y), 0, 0, bloc.getTexture().getWidth(null),
								bloc.getTexture().getHeight(null), null);
					}
				}
			}

			// cloud
			for (Cloud cld : listCloud) {
				// - dX to move with the player
				cld.moveByX(-dX * SPEED);
				if (cld.isVisible()) {
					g2d.drawImage(cld.getTexture(), (int) cld.x, (int) cld.y, (int) (cld.width + cld.x),
							(int) (cld.height + cld.y), 0, 0, cld.getTexture().getWidth(null),
							cld.getTexture().getHeight(null), null);

				}
			}
			// sun
			g2d.drawImage(ShopImage.SUN, game.getWidth() - SUN_WH, 0, game.getWidth(), SUN_WH, 0, 0,
					ShopImage.SUN.getWidth(null), ShopImage.SUN.getHeight(null), null);

		} else {
			LeaderBoard.getInstance().setTextLabel("win");
			setActivePageLeaderBoard();
		}

	}

	private void setActivePageLeaderBoard() {
		LeaderBoard.getInstance().setDeathCount(nbDeath - 1);
		init();
		Game.getInstance().setCurrent("leaderboard");
		Game.getInstance().setSize(Game.getInstance().getWidth() + 1, Game.getInstance().getHeight() + 1);
		Game.getInstance().setContentPane(LeaderBoard.getInstance());
		Game.getInstance().setSize(Game.getInstance().getWidth() - 1, Game.getInstance().getHeight() - 1);
	}

	public CheckPointBloc checkLastCP() {

		if (!listCPBloc.isEmpty()) {
			CheckPointBloc last = new CheckPointBloc(getListCPBloc().get(0));
			for (CheckPointBloc cp : getListCPBloc()) {
				if (cp.isCheck()) {
					last = new CheckPointBloc(cp);
				}

			}
			return last;
		}
		return null;
	}

	private void updateLastCP() {
		// Update of last checkpoint
		for (CheckPointBloc cpBloc : listCPBloc) {
			if (player.getCenterX() >= cpBloc.x && player.getMaxY() <= cpBloc.y) {
				cpBloc.setCheck(true);
			}
			if (listCPBloc.indexOf(cpBloc) == 0)
				cpBloc.setCheck(true);
		}
	}

	public void init() {
		nbDeath = 0;
		win = false;
		setdX(0);
		boolean first = true;
		for (CheckPointBloc cpBloc : listCPBloc) {
			if (first) {
				cpBloc.setCheck(true);
				first = false;
			} else {
				cpBloc.setCheck(false);
			}
		}
		for (TrapBloc trap : listTrap) {
			trap.revertAction();
		}
		player.respawn();
		LeaderBoard.getInstance().setHasPlayedSound(false);
	}

	// tools to set up the bloc
	private void addPlainBloc(int coefX, int coefY) {
		listBloc.add(new Bloc(-BLOC_WH / 4 + BLOC_WH * coefX, groundH + BLOC_WH * coefY, BLOC_WH, BLOC_WH, true,
				ShopImage.STONEPATHBLOCK));
	}

	private void addSpikeBloc(int coefX, int coefY, Image img, TypeTrap type) {
		SpikeBloc tBloc = new SpikeBloc(-BLOC_WH / 4 + BLOC_WH * coefX, coefY * BLOC_WH + groundH, BLOC_WH, BLOC_WH,
				false, img, type);
		listBloc.add(tBloc);
		listTrap.add(tBloc);

	}

	private void addFallBloc(int coefX, int coefY) {
		FallBloc tBloc = new FallBloc(-BLOC_WH / 4 + BLOC_WH * coefX, groundH + BLOC_WH * coefY, BLOC_WH, BLOC_WH, true,
				ShopImage.STONEPATHBLOCK, TypeTrap.FALL);
		listBloc.add(tBloc);
		listTrap.add(tBloc);

	}

	private void addCheckPoint(int coefX, int coefY) {
		CheckPointBloc b2 = new CheckPointBloc(-BLOC_WH / 4 + BLOC_WH * coefX, BLOC_WH * coefY + groundH, BLOC_WH,
				BLOC_WH, true, ShopImage.ICEBLOCKTOP);// CheckPoint
		listBloc.add(b2);
		listCPBloc.add(b2);

	}

	// creating the map
	private void setBlocList() {

		// first checkpoint
		firstCP = new CheckPointBloc(-BLOC_WH / 4, groundH, BLOC_WH, BLOC_WH, true, ShopImage.ICEDIRTBLOCK);
		listBloc.add(firstCP);
		listCPBloc.add(firstCP);
		firstCP.setCheck(true);

		addPlainBloc(1, 0);
		addFallBloc(2, 0);
		addFallBloc(3, 0);
		addSpikeBloc(4, -1, ShopImage.SPIKEL, TypeTrap.SPIKEL);
		addCheckPoint(6, 2);
		addFallBloc(7, 2);
		addPlainBloc(8, 2);
		addSpikeBloc(9, 1, ShopImage.SPIKEL, TypeTrap.SPIKEL);
		addPlainBloc(10, 1);
		addPlainBloc(11, 0);
		addSpikeBloc(11, 4, ShopImage.SPIKER, TypeTrap.SPIKER);
		addFallBloc(12, 4);
		addPlainBloc(13, -2);
		addPlainBloc(13, 0);
		addSpikeBloc(13, -1, ShopImage.SPIKEL, TypeTrap.SPIKEL);
		addSpikeBloc(13, 1, ShopImage.SPIKET, TypeTrap.SPIKET);
		addPlainBloc(13, 4);
		addCheckPoint(14, 4);
		addPlainBloc(15, 3);
		addSpikeBloc(16, 2, ShopImage.SPIKEL, TypeTrap.SPIKEL);
		addFallBloc(17, 1);
		addFallBloc(17, 3);
		addSpikeBloc(18, 0, ShopImage.SPIKEL, TypeTrap.SPIKEL);
		addSpikeBloc(18, -1, ShopImage.SPIKEL, TypeTrap.SPIKEL);
		addSpikeBloc(18, -2, ShopImage.SPIKEL, TypeTrap.SPIKEL);
		addPlainBloc(18, 3);
		addCheckPoint(19, 3);
		addPlainBloc(20, 2);
		addFallBloc(21, 1);
		addSpikeBloc(22, 0, ShopImage.SPIKEL, TypeTrap.SPIKEL);
		addPlainBloc(23, -1);
		addSpikeBloc(24, -1, ShopImage.SPIKEG, TypeTrap.SPIKEG);
		addFallBloc(25, -1);
		addFallBloc(26, -1);
		addSpikeBloc(27, -1, ShopImage.SPIKEG, TypeTrap.SPIKEG);
		addPlainBloc(28, -1);
		addSpikeBloc(29, -2, ShopImage.SPIKEL, TypeTrap.SPIKEL);
		addCheckPoint(30, -3);
		addSpikeBloc(31, -4, ShopImage.SPIKER, TypeTrap.SPIKER);
		addSpikeBloc(32, 1, ShopImage.SPIKET, TypeTrap.SPIKET);
		addFallBloc(33, 1);
		addSpikeBloc(33, -6, ShopImage.SPIKET, TypeTrap.SPIKET);
		addSpikeBloc(33, -5, ShopImage.SPIKEL, TypeTrap.SPIKEL);
		addPlainBloc(33, -4);
		addPlainBloc(34, 1);
		addPlainBloc(35, 0);
		addFallBloc(36, -1);
		addSpikeBloc(37, -2, ShopImage.SPIKET, TypeTrap.SPIKET);
		addSpikeBloc(38, -3, ShopImage.SPIKER, TypeTrap.SPIKER);
		addPlainBloc(37, 1);
		addPlainBloc(38, 1);
		addPlainBloc(39, 0);
		addFallBloc(40, -1);
		addSpikeBloc(41, -2, ShopImage.SPIKER, TypeTrap.SPIKER);
		addPlainBloc(42, -3);
		addSpikeBloc(43, -2, ShopImage.SPIKET, TypeTrap.SPIKET);
		addFallBloc(44, -1);

		// last cp
		lastCP = new CheckPointBloc(-BLOC_WH / 4 + BLOC_WH * 5/*45 en vrai*/, groundH, BLOC_WH, BLOC_WH, true, ShopImage.SANDBLOCK);
		listBloc.add(lastCP);
		listCPBloc.add(lastCP);
		lastCPset = true;

		player.respawn();
		for (int i = 0; i < 3 * getGame().getWidth() / 50; i++) {
			int alea = 5 + (int) (Math.random() * ((15 - 5) + 1));

			if (i % alea == 3) {// cloud between 151 & 221 on y parameter
				listCloud.add(new Cloud(CLOUD_WH * i, groundH / 4 + alea * 7 - CLOUD_WH / 3 + 6, CLOUD_WH, CLOUD_WH,
						true, ShopImage.CLOUD));
			}
		}
	}

//getters & setters
	public Player getPlayer() {
		return player;
	}

	public int getGroundH() {

		return (int) this.groundH;

	}

	public double getdX() {
		return dX;
	}

	public void setdX(double d) {
		this.dX = d;
	}

	public ArrayList<Bloc> getListBloc() {
		return listBloc;
	}

	public ArrayList<CheckPointBloc> getListCPBloc() {
		return listCPBloc;
	}

	public Game getGame() {
		return game;
	}

	public ArrayList<TrapBloc> getListTrap() {

		return listTrap;
	}

	public int getNbDeath() {
		return nbDeath;
	}

	public void setNbDeath(int nbDeath) {
		this.nbDeath = nbDeath;
	}

	public boolean isHasPlay() {
		return hasPlay;
	}

	public void setHasPlay(boolean hasPlay) {
		this.hasPlay = hasPlay;
	}

	public boolean isLastDir() {
		return lastDir;
	}

	public void setLastDir(boolean lastDir) {
		this.lastDir = lastDir;
	}

}
