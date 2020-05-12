
package ch.hearc.wp2.p2.jeu.items.Caractere;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.Item;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;

public class Player extends Item {

	private int heart;
	private boolean isAlive;
	private boolean isJumping;

	public Player(Item it) {
		super(it);

	}

	public Player(double x, double y, double w, double h, boolean v, int heart) {
		super(x, y, w, h, v);
		this.setHeart(heart);
		this.setAlive(true);
		this.setJumping(false);

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

	// methodes

	public boolean contactRight(Item it) {
		if (intersectsLine(it.x - 5, it.y+5, it.x - 5, it.getMaxY())) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactLeft(Item it) {
		if (intersectsLine(it.getMaxX() + 5, it.y+5, it.getMaxX() + 5, it.getMaxY())) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactBottom(Item it) {

		if (intersectsLine(it.x - 3, it.y - 4, it.getMaxX() + 3, it.y - 4)) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactTop(Item it) {

		if (intersectsLine(it.x, it.getMaxY() + 1, it.getMaxX(), it.getMaxY() + 1)) {
			return true;

		} else {
			return false;
		}
	}

	public void contact(Item it) {
		// horizontal hit
		if (contactRight(it) || contactLeft(it)) {
			Map.getInstance().setdX(-Map.getInstance().getdX());
		}
		// bottom hit
		if (contactBottom(it)) {// jumping over item
			this.moveByY(-Map.GRAVITY);
		}
	}

	public void contact(Item it, Graphics2D g2d) {
		// horizontal hit
		if (contactRight(it) || contactLeft(it)) {
			Map.getInstance().setdX(-Map.getInstance().getdX());
		}
		// bottom hit
		if (contactBottom(it) && isJumping) {
			//Map.getInstance().setGroundAfterJump((int) it.y);
			
			setJumping(false);
		} else if (contactBottom(it)) {// jumping over item
			this.moveByY(-Map.GRAVITY);
		}

	}

	public void jump() {
		if (!isJumping) {
			setJumping(true);
			for (int i = 0; i < 8 * Map.BLOC_WH / 5; i++) {
				boolean test = true;
				for (Bloc b : Map.getInstance().getListBloc()) {
					if (contactTop(b)) {
						test = false;
					}
				}
				if (test)
					this.moveByY(-1);
			}
		}

	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

}
