
package ch.hearc.wp2.p2.jeu.items.Caractere;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.Item;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;

public class Player extends Item {

	private int heart;
	private boolean isAlive;

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

	// methodes

	public boolean contactRight(Item it) {
		if (intersectsLine(it.x - 5, it.y, it.x - 5, it.getMaxY())) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactLeft(Item it) {
		if (intersectsLine(it.getMaxX() + 5, it.y, it.getMaxX() + 5, it.getMaxY())) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactBottom(Item it) {

		if (intersectsLine(it.x, it.y - 4, it.getMaxX(), it.y - 4)) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactTop(Item it) {

		if (intersectsLine(it.x, it.getMaxY() + 10, it.getMaxX(), it.getMaxY() + 10)) {
			System.out.println("hit on the head");
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

		// top hit
		if (contactTop(it)) {
			//TODO 
		}
	}

	public void jump() {
		//TODO
	}

}
