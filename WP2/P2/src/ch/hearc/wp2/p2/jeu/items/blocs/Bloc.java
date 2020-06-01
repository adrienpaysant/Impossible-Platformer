
package ch.hearc.wp2.p2.jeu.items.blocs;

import java.awt.Image;

import ch.hearc.wp2.p2.jeu.items.Item;

@SuppressWarnings("serial")
public class Bloc extends Item {

	private Image texture;

	public Bloc(double x, double y, double w, double h, boolean v, Image texture) {
		super(x, y, w, h, v);
		this.texture = texture;
	}

	public Bloc(Bloc it) {
		this(it.x, it.y, it.width, it.height, it.isVisible(), it.texture);
	}

	// getter &setter
	public Image getTexture() {
		return texture;
	}

	public void setTexture(Image img) {
		this.texture = img;
	}

}
