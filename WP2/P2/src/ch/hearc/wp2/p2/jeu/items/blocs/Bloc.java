
package ch.hearc.wp2.p2.jeu.items.blocs;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

import ch.hearc.wp2.p2.jeu.items.Item;

public class Bloc extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image texture;

	public Bloc(double x, double y, double w, double h, boolean v, Image texture) {
		super(x, y, w, h, v);
		this.texture = texture;

	}

	public Bloc(Bloc it) {
		this(it.x, it.y, it.width, it.height, it.isVisible(), it.texture);
	}

	// getter
	public Image getTexture() {
		return texture;
	}
	public void setTexture(Image img) {
		this.texture=img;
	}

}
