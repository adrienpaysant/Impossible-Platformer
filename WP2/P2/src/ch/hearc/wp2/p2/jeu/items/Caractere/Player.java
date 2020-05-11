
package ch.hearc.wp2.p2.jeu.items.Caractere;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.Item;

public class Player extends Item {

	private int heart;
	private boolean isAlive;
	private boolean isWalking;
	private boolean isWalkingToRight;
	private boolean isJumping;
	private int stepCount;

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

	public boolean isWalkingToRight() {
		return isWalkingToRight;
	}

	public void setWalkingToRight(boolean isWalkingToRight) {
		this.isWalkingToRight = isWalkingToRight;
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
		if ((this.x > it.getMaxX()) || (this.getMaxX() > it.x + 5) || (this.getMaxY() <= it.y)
				|| (this.y >= it.getMaxY())) {
			return false;

		} else {
			return true;
		}
	}

	public boolean contactLeft(Item it) {
		if ((this.x > it.getMaxX()) || (this.getMaxX() < it.getMaxX() - 5) || (this.getMaxY() <= it.y)
				|| this.y >= it.getMaxY()) {
			return false;

		} else {
			return true;
		}
	}

	public boolean contactBottom(Item it) {
		if ((this.getMaxX() < it.x + 5) || (this.x > it.getMaxX() - 5) || (this.y < it.getMaxY())
				|| (this.y > it.getMaxY() + 5)) {
			return false;

		} else {
			return true;
		}
	}

	public boolean contactTop(Item it) {
		if ((this.getMaxX() < it.x + 5) || (this.x > it.getMaxX() - 5) || (this.y < it.getMaxY())
				|| (this.y > it.getMaxY() + 5)) {
			return false;

		} else {
			return true;
		}
	}

	public void contact(Item it) {
		// horizontal hit
		if ((contactRight(it) && this.isWalkingToRight) || (contactLeft(it) && (!isWalkingToRight))) {
			Map.getInstance().setdX(0);
			this.setWalking(false);
		}
		// bottom hit
		if (contactBottom(it) && this.isJumping) {// jumping over item
			Map.getInstance().setyGround((int) it.y);
		} else if (!contactBottom(it)) {// getting to base ground
			if (!isJumping) {
				this.moveTo(new Point2D.Double(this.x, Map.getInstance().getGroundH()));
			}
		}

		// top hit
		if (contactTop(it)) {
			Map.getInstance().setTopGroundH(it.getMaxY());//the bottom of it become de top floor
		}
		else if (contactTop(it)&&!isJumping) {
			Map.getInstance().setTopGroundH(0);
		}
	}

	public void jump() {
		for (int i = 0; i < 8 * Map.BLOC_WH / 5; i++) {
			this.moveByY(-1);
		}
	}

}
