
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

public class SpikeBloc extends TrapBloc {

	private boolean directionPosDown;
	private boolean groundTrueOrWall;
	private Bloc bSource;
	private boolean isOut;

	public SpikeBloc(double x, double y, double w, double h, boolean v, Image texture, boolean directionPosDown,
			boolean groundTrueOrWall, Bloc bS, TypeTrap type) {
		super(x, y, w, h, false, texture, type);
		this.directionPosDown = directionPosDown;
		this.groundTrueOrWall = groundTrueOrWall;
		this.bSource = bS;
		this.isOut=false;
	}

	@Override
	public void trapAction() {
		this.setVisible(true);
		if (!isOut) {
			if (groundTrueOrWall) {
				// ground or floor
				if (directionPosDown) {
					// stick on the ground
					moveByY(Map.BLOC_WH-5);
					setTexture(ShopImage.SPIKET);
					isOut = true;
				} else {
					// floor
					moveByY(-Map.BLOC_WH+5);
					setTexture(ShopImage.SPIKEB);
					isOut = true;
				}
			} else {
				// wall L or R
				if (directionPosDown) {
					// stick of a wall need to expand by left
					setTexture(ShopImage.SPIKEL);
					moveByX(-Map.BLOC_WH+5);
					isOut = true;
				} else {
					// stick of a wall need to expand by right
					moveByX(Map.BLOC_WH-5);
					setTexture(ShopImage.SPIKER);
					isOut = true;
				}
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
				moveTo(new Point2D.Double(bSource.x, bSource.y - 5));
			} else {
				// floor
				moveByY(Map.BLOC_WH);
				moveTo(new Point2D.Double(bSource.x, bSource.y + 5));
			}
		} else {
			// wall L or R
			if (directionPosDown) {
				// stick of a wall need to expand by left
				moveTo(new Point2D.Double(bSource.x - 5, bSource.y));
			} else {
				// stick of a wall need to expand by right
				moveTo(new Point2D.Double(bSource.x + 5, bSource.y));
			}
		}
		isOut=false;

	}

}
