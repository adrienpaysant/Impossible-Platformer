
package ch.hearc.wp2.p2.jeu.items.Caractere;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.Item;

public class Player extends Item {

	private int heart;
	private boolean isAlive;
	private boolean isWalking;
	private boolean run = false;
	

	public Player(Item it) {
		super(it);

	}

	public Player(double x, double y, double w, double h, boolean v, int heart) {
		super(x, y, w, h, v);
		this.setHeart(heart);
		this.setAlive(true);
	}

	// getters 
	public int getHeart() { return heart; }
	
	public boolean isAlive() { return isAlive; }
	
	public boolean isWalking() { return this.isWalking; }
	
	public boolean isRunning() { return this.isRunning(); }
	

	
	//setters
	public void setHeart(int heart) { this.heart = heart; }

	public void setAlive(boolean isAlive) {	this.isAlive = isAlive; }

	public void setWalk(boolean walk) { this.isWalking = walk; }
	
	public void setRun(boolean run) { this.run = run; }
	
	public void jump() {
		for (int i = 0; i < 8 * Map.BLOC_WH / 5; i++) {
			this.moveByY(-1);
		}
	}

}
