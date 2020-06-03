
package ch.hearc.wp2.p2.jeu.items.blocs.traps;

import java.awt.Image;
import java.awt.geom.Point2D;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.tools.Audio;
import ch.hearc.wp2.p2.jeu.tools.image.ShopImage;

//class that define the type of trap "spike"
@SuppressWarnings("serial")
public class SpikeBloc extends TrapBloc {

	private Bloc bSource;
	private boolean isOut;

	public SpikeBloc(double x, double y, double w, double h, boolean v, Image texture, TypeTrap type) {
		super(x, y, w, h, false, texture, type);
		
		//add a bloc to hide the trap
		switch (type) {
		case SPIKER:
			bSource = new Bloc(x, y, Map.BLOC_WH, Map.BLOC_WH, true, ShopImage.PATHBLOCK);
			this.x = x + 5;
			break;
		case SPIKEL:
			bSource = new Bloc(x, y, Map.BLOC_WH, Map.BLOC_WH, true, ShopImage.PATHBLOCK);
			this.x = x - 5;
			break;
		case SPIKET:
			bSource = new Bloc(x, y, Map.BLOC_WH, Map.BLOC_WH, true, ShopImage.PATHBLOCK);
			this.x = x + 5;
			this.width = width - 10;
			this.y = y + 5;
			break;
		case SPIKEG:
			bSource = new Bloc(x, y, Map.BLOC_WH, Map.BLOC_WH, true, ShopImage.PATHBLOCK);
			this.x = x + 5;
			this.width = width - 10;
			this.y = y - 5;
			break;
		default:
			break;
		}
		Map.getInstance().getListBloc().add(bSource);
		this.isOut = false;
	}

	@Override
	public void trapAction() {
		Audio.playSound("/audio/die.wav");
		this.setVisible(true);
		if (!isOut) {
			switch (type) {
			case SPIKER:// spike expanding by right
				moveByX(Map.BLOC_WH - 5);
				setTexture(ShopImage.SPIKER);
				isOut = true;
				break;
			case SPIKEL:// spike expanding by left
				setTexture(ShopImage.SPIKEL);
				moveByX(-Map.BLOC_WH + 5);
				isOut = true;
				break;
			case SPIKET: // spike expanding down
				moveByY(Map.BLOC_WH - 5);
				setTexture(ShopImage.SPIKET);
				isOut = true;
				break;
			case SPIKEG:// spike expanding up
				moveByY(-Map.BLOC_WH + 5);
				setTexture(ShopImage.SPIKEG);
				isOut = true;
				break;
			default:
				break;
			}

		}

	}

	@Override
	public void revertAction() {
		this.setVisible(false);
		switch (type) {
		case SPIKER:
			moveTo(new Point2D.Double(bSource.x + 5, bSource.y));
			break;
		case SPIKEL:
			moveTo(new Point2D.Double(bSource.x, bSource.y));
			break;
		case SPIKET:
			moveTo(new Point2D.Double(bSource.x + 5, bSource.y + 5));
			break;
		case SPIKEG:
			moveTo(new Point2D.Double(bSource.x + 5, bSource.y - 5));
			break;
		default:
			break;
		}
		isOut = false;
	}

}
