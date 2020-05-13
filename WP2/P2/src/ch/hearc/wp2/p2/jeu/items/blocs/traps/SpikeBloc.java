
package ch.hearc.wp2.p2.jeu.items.blocs.traps;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

public class SpikeBloc extends Bloc implements TrapBloc {

	private boolean directionPosDown;
	private boolean groundTrueOrWall;
	private Bloc bSource;

	public SpikeBloc(double x, double y, double w, double h, boolean v, Image texture, boolean directionPosDown,
			boolean groundTrueOrWall, Bloc bS) {
		super(x, y, w, h, false, texture);
		this.directionPosDown = directionPosDown;
		this.groundTrueOrWall = groundTrueOrWall;
		this.bSource = bS;
	}

	@Override
	public void trapAction() {
		this.setVisible(true);
		if (groundTrueOrWall) {
			// ground or floor
			if (directionPosDown) {
				// stick on the ground
				moveByY(-Map.BLOC_WH);
				setTexture(ShopImage.SPIKESB);
			} else {
				// floor
				moveByY(Map.BLOC_WH);
				setTexture(ShopImage.SPIKEST);
				
			}
		} else {
			// wall L or R
			if (directionPosDown) {
				// stick of a wall need to expand by left
				setTexture(ShopImage.SPIKESL);
				moveByX(-Map.BLOC_WH);
			} else {
				// stick of a wall need to expand by right
				moveByX(Map.BLOC_WH);
				setTexture(ShopImage.SPIKESR);
			}
		}
	}

	@Override
	public void revertAction() {
		this.setVisible(false);
//		this.moveTo(new Point2D.Double(bSource.x, bSource.y - 1));
		if (groundTrueOrWall) {
			// ground or floor
			if (directionPosDown) {
				// stick on the ground
				moveTo(new Point2D.Double(bSource.x, bSource.y - 1));
			} else {
				// floor
				moveByY(Map.BLOC_WH);
				moveTo(new Point2D.Double(bSource.x, bSource.y +1));
			}
		} else {
			// wall L or R
			if (directionPosDown) {
				// stick of a wall need to expand by left
				moveTo(new Point2D.Double(bSource.x-1, bSource.y));
			} else {
				// stick of a wall need to expand by right
				moveTo(new Point2D.Double(bSource.x+1, bSource.y));
			}
		}

	}
}
