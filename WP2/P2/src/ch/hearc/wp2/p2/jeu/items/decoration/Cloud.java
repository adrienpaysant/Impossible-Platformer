package ch.hearc.wp2.p2.jeu.items.decoration;

import java.awt.Image;

import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

@SuppressWarnings("serial")
public class Cloud extends Bloc {

	public Cloud(Bloc it) {
		super(it.x, it.y, it.width, it.height, true, ShopImage.CLOUD);

	}

	public Cloud(double x, double y, double w, double h, boolean v, Image texture) {
		super(x, y, w, h, true, ShopImage.CLOUD);

	}

}
