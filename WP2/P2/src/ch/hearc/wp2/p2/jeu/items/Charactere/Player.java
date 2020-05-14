
package ch.hearc.wp2.p2.jeu.items.Charactere;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.Item;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.items.blocs.actions.CheckPointBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.SpikeBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.TrapBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.TypeTrap;

public class Player extends Item {

	private int heart;
	private boolean isAlive;
	private boolean isJumping;

	public Player(Item it) {
		super(it);

	}

	public Player(double x, double y, double w, double h, boolean v, int heart) {
		super(x, y, w, h, v);
		this.setHeart(heart);
		this.setAlive(true);
		this.setJumping(false);

	}

	// getters & setters
	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	// methodes

	public boolean contactRight(Item it) {
		if (intersectsLine(it.x - 5, it.y + 5, it.x - 5, it.getMaxY())) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactLeft(Item it) {
		if (intersectsLine(it.getMaxX() + 5, it.y + 5, it.getMaxX() + 5, it.getMaxY())) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactBottom(Item it) {

		if (intersectsLine(it.x - 3, it.y - 4, it.getMaxX() + 3, it.y - 4)) {
			return true;

		} else {
			return false;
		}
	}

	public boolean contactTop(Item it) {

		if (intersectsLine(it.x, it.getMaxY() + 1, it.getMaxX(), it.getMaxY() + 1)) {
			return true;

		} else {
			return false;
		}
	}

	public void contact(Bloc it) {
		// horizontal hit
		if (contactRight(it) || contactLeft(it)) {
			Map.getInstance().setdX(-Map.getInstance().getdX());
			trapThePlayer(it);
		}
		// bottom hit
		if (contactBottom(it) && isJumping) {

			this.moveByY(-Map.GRAVITY);
			setJumping(false);
			trapThePlayer(it);
		} else if (contactBottom(it)) {
			this.moveByY(-Map.GRAVITY);
			trapThePlayer(it);
		}
	}

	private void trapThePlayer(Item it) {
		if (it instanceof TrapBloc) {
			if ((((TrapBloc) it).type == TypeTrap.SPIKET) || (((TrapBloc) it).type == TypeTrap.SPIKEB)
					|| (((TrapBloc) it).type == TypeTrap.SPIKER) || (((TrapBloc) it).type == TypeTrap.SPIKEL))
				switch (((TrapBloc) it).type) {
				case SPIKER:
					if (contactLeft(it)) {
						((TrapBloc) it).trapAction();
						this.setHeart(getHeart() - 1);
						respawn();
					}
					break;
				case SPIKEL:

					if (contactRight(it)) {
						((TrapBloc) it).trapAction();
						this.setHeart(getHeart() - 1);
						respawn();
					}
					break;
				case SPIKET:
					if (contactTop(it)) {
						((TrapBloc) it).trapAction();
						this.setHeart(getHeart() - 1);
						respawn();
					}
					break;
				case SPIKEB:

					if (contactBottom(it)) {
						((TrapBloc) it).trapAction();
						this.setHeart(getHeart() - 1);
						respawn();
					}
					break;
				default:

					break;
				}

		}
	}

	public void jump() {
		if (!isJumping) {
			setJumping(true);
			for (int i = 0; i < 12 * Map.BLOC_WH / 5; i++) {
				boolean test = true;
				for (Bloc b : Map.getInstance().getListBloc()) {
					if (contactTop(b)) {
						trapThePlayer(b);
						test = false;
					}
				}
				if (test)
					this.moveByY(-1);
			}
		}

	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public void respawn() {

		CheckPointBloc last = Map.getInstance().checkLastCP();
		for (Bloc bloc : Map.getInstance().getListBloc()) {
			if (last.x >= x) {
				bloc.moveByX(-Math.abs(last.getCenterX() - getCenterX()));
			} else {
				bloc.moveByX(Math.abs(last.getCenterX() - getCenterX()));
			}

		}
		// moveByY(-Math.abs(y - Map.getInstance().getGame().getHeight() / 3));
		moveTo(new Point2D.Double(x, last.y - height - Map.BLOC_WH / 2));

	}
}
