
package ch.hearc.wp2.p2.jeu.items.Caractere;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.Item;

public class Player extends Item {

	private int heart;
	private boolean isAlive;
	private boolean isWalking;
	private boolean isJumping;

	public Player(Item it) {
		super(it);

	}

	public Player(double x, double y, double w, double h, boolean v, int heart) {
		super(x, y, w, h, v);
		this.setHeart(heart);
		this.setAlive(true);

	}

	// getters & setters
	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isWalking() {
		return isWalking;
	}

	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	// methodes
	

	
	public boolean contactRight(Item it) {
		if (intersectsLine(it.x, it.y, it.x, it.getMaxY())) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactLeft(Item it) {
		if (intersectsLine(it.getMaxX(), it.y, it.getMaxX(), it.getMaxY())) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactBottom(Item it) {
		if (intersectsLine(it.x, it.y-1, it.getMaxX(), it.y-1)) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactTop(Item it) {
		if (intersectsLine(it.x, it.y+2, it.getMaxX(),it.y+2)) {
			return true;

		} else {
			return false;
		}
	}

	public void contact(Item it) {
		this.setWalking(true);
		// horizontal hit
		if (contactRight(it) || contactLeft(it)) {
			Map.getInstance().setdX(-Map.getInstance().getdX());
			this.setWalking(false);
		}
		// bottom hit
		if (contactBottom(it)) {// jumping over item
			this.moveByY(-Map.GRAVITY);
		}

		// top hit
		if (contactTop(it)) {
			Map.getInstance().setRoof((int)it.getMaxY()+5);
		} else if (contactTop(it) && isJumping) {
			this.setJumping(false);
		}
	}

	public void jump() {
		for (int i = 0; i < 8 * Map.BLOC_WH / 5; i++) {
			if(this.y<Map.getInstance().getRoof() && isJumping) {
				this.moveByY(-1);
			}
		}
	}

}
