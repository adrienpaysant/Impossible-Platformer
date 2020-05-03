
package ch.hearc.wp2.p2.jeu.items.Caractere;

import java.awt.geom.Rectangle2D.Double;

import ch.hearc.wp2.p2.jeu.items.Item;

public class Player extends Item {

	public Player(Item it) {
		super(it);
		
	}

	public Player(Double srect, boolean v) {
		super(srect, v);
		
	}

	public Player(double x, double y, double w, double h, boolean v) {
		super(x, y, w, h, v);
		
	}
	
}
