
package ch.hearc.wp2.p2.jeu.items.blocs;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

import ch.hearc.wp2.p2.jeu.items.Item;

public class Bloc extends Item {

	private Image texture;

	public Bloc(double x, double y, double w, double h, boolean v, Image texture) {
		super(x, y, w, h, v);
		this.texture = texture;

	}

	public Bloc(Bloc it) {
		this(it.getRect().x, it.getRect().y, it.getRect().width, it.getRect().height, it.isVisible(), it.texture);
	}

	public Bloc(Rectangle2D.Double rect, boolean v, Image texture) {
		super(rect, v);
		this.texture = texture;
	}

	// getter
	public Image getTexture() {
		return texture;
	}

}
