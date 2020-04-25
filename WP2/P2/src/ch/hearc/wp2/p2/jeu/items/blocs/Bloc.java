
package ch.hearc.wp2.p2.jeu.items.blocs;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import ch.hearc.wp2.p2.jeu.items.Item;

@SuppressWarnings("serial")
public class Bloc extends Item {

	private Image texture;

	public Bloc(double x, double y, double w, double h, boolean v/* , Image texture */) {
		super(x, y, w, h, v);
		// this.texture = texture;
	}

	public Bloc(Bloc b) {
		this(b.getPt0().x, b.getPt0().y, b.getPt1().x, b.getPt1().y, b.isVisible()/* , b.texture */);
	}

	public Bloc(Rectangle2D.Double rect, boolean v, Image texture) {
		super(rect, v);
		this.texture = texture;
	}
}
