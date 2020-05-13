
package ch.hearc.wp2.p2.jeu.items.blocs.traps;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

public class SpikeBloc extends Bloc implements TrapBloc {

	private boolean directionPosDown;
	private boolean groundTrueOrWall;
	private double xInit;
	private double yInit;
	private boolean hasBeenRevealed;

	public SpikeBloc(double x, double y, double w, double h, boolean v, Image texture, boolean directionPosDown,
			boolean groundTrueOrWall) {
		super(x, y, w, h, false, ShopImage.SPIKES);
		this.directionPosDown = directionPosDown;
		this.groundTrueOrWall = groundTrueOrWall;
		this.xInit = x;
		this.yInit = y;
		this.hasBeenRevealed = false;
	}

	@Override
	public void trapAction() {
		hasBeenRevealed = true;
		this.setVisible(true);
		if (groundTrueOrWall) {
			// ground or floor
			if (directionPosDown) {
				// stick on the ground
				moveByY(-Map.BLOC_WH);
			} else {
				// floor
				moveByY(Map.BLOC_WH);
			}
		} else {
			// wall L or R
			if (directionPosDown) {
				// stick of a wall need to expand by left
				moveByX(-Map.BLOC_WH);
			} else {
				// stick of a wall need to expand by right
				moveByX(Map.BLOC_WH);
			}
		}
	}

	@Override
	public void revertAction() {
		hasBeenRevealed = false;
		setVisible(false);
		moveTo(new Point2D.Double(xInit, yInit));

	}
}
