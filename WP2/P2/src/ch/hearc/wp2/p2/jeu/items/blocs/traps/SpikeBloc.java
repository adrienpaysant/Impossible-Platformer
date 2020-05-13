
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
		super(x, y, w, h, false, ShopImage.SPIKES);
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
			} else {
				// floor
				moveByY(Map.BLOC_WH);
				AffineTransform backup = g2d.getTransform();
			    //rx is the x coordinate for rotation, ry is the y coordinate for rotation, and angle
			    //is the angle to rotate the image. If you want to rotate around the center of an image,
			    //use the image's center x and y coordinates for rx and ry.
			    AffineTransform a = AffineTransform.getRotateInstance(angle, rx, ry);
			    //Set our Graphics2D object to the transform
			    g2d.setTransform(a);
			    //Draw our image like normal
			    g2d.drawImage(image, x, y, null);
			    //Reset our graphics object so we can draw with it again.
			    g2d.setTransform(backup);
				
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
		this.setVisible(false);
		this.moveTo(new Point2D.Double(bSource.x, bSource.y - 1));

	}
}
