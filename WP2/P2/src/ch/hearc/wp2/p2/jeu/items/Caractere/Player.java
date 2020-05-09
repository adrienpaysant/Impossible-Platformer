
package ch.hearc.wp2.p2.jeu.items.Caractere;

import java.awt.geom.Rectangle2D.Double;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.Item;

public class Player extends Item {
	
	private int heart;
	private boolean isAlive;
	
	public Player(Item it) {
		super(it);

	}

	public Player(double x, double y, double w, double h, boolean v,int heart) {
		super(x, y, w, h, v);
		this.setHeart(heart);
		this.setAlive(true);
	}

	//getters & setters
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

	public void jump() {
		for (int i = 0; i <3*Map.BLOC_WH/2; i++) {
			this.moveByY(-1);
		}
		
	}

}
