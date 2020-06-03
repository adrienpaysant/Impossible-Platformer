
package ch.hearc.wp2.p2.jeu.items.blocs.traps;

import java.awt.Image;
import java.awt.geom.Point2D;

import ch.hearc.wp2.p2.jeu.Main;
import ch.hearc.wp2.p2.jeu.Map;

//class that define the type of trap "fall"

@SuppressWarnings("serial")
public class FallBloc extends TrapBloc {

	private boolean status;
	private double yOrig;

	public FallBloc(double x, double y, double w, double h, boolean v, Image texture, TypeTrap type) {
		super(x, y, w, h, v, texture, type);
		this.status = false;
		this.yOrig = y;
	}

	@Override
	public void trapAction() {
		if (!status) {
			while (y <= Main.HEIGHT + 4 * Map.BLOC_WH) {
				moveByY(1);
			}
		}
		status = true;

	}

	@Override
	public void revertAction() {
		if (status) {
			moveTo(new Point2D.Double(x, yOrig));
		}
		status = false;
	}

}
