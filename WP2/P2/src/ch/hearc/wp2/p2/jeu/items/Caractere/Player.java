
package ch.hearc.wp2.p2.jeu.items.Caractere;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.Item;

public class Player extends Item {

	private int heart;
	private boolean isAlive;
	private boolean isWalking;
	private boolean run = false;
	private Thread animator;
	private int spriteCmpt = 0;
	
	public Player(Item it) {
		super(it);
		
		try {
			ImageIO.read(getClass().getResource("/sprites/idle/adventurer-idle-00.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		animator = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println("setting image");
					setImage();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		animator.start();

	}

	public Player(double x, double y, double w, double h, boolean v, int heart) {
		super(x, y, w, h, v);
		this.setHeart(heart);
		this.setAlive(true);
	}

	// getters
	public int getHeart() {
		return heart;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isWalking() {
		return this.isWalking;
	}

	public boolean isRunning() {
		return this.isRunning();
	}

	// setters
	public void setHeart(int heart) {
		this.heart = heart;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void setWalk(boolean walk) {
		this.isWalking = walk;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public void setImage() {
		if (!(isWalking && run)) {try {
				ImageIO.read(getClass().getResource("/sprites/idle/adventurer-idle-0" + spriteCmpt % 3 +".png"));
				System.out.println("/sprites/idle/adventurer-idle-0" + spriteCmpt % 3 +".png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		spriteCmpt++;
	}

	public void jump() {
		for (int i = 0; i < 8 * Map.BLOC_WH / 5; i++) {
			this.moveByY(-1);
		}
	}

}
