
package ch.hearc.wp2.p2.jeu.items.Charactere;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.hearc.wp2.p2.jeu.Map;
import ch.hearc.wp2.p2.jeu.items.Item;
import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;
import ch.hearc.wp2.p2.jeu.items.blocs.actions.CheckPointBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.TrapBloc;
import ch.hearc.wp2.p2.jeu.items.blocs.traps.TypeTrap;
import ch.hearc.wp2.p2.jeu.tools.Audio;

public class Player extends Item {

	private boolean isJumping;
	private boolean isRunning;
	private boolean isWalking;
	private int spriteCmpt = 0;
	private int sleepFreq = 300;
	private Image texture;

	public Player(Item it, Image texture) {
		super(it);
		this.setJumping(false);
		this.texture = texture;
	}

	public Player(double x, double y, double w, double h, boolean v, Image texture) {
		super(x, y, w, h, v);
		this.setJumping(false);
		this.texture = texture;
	}

	// methodes
	public void setImage() {
		if (!(isWalking && isRunning)) {
			try {
				setTexture(ImageIO
						.read(getClass().getResource("/sprites/idle/adventurer-idle-0" + spriteCmpt % 3 + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			setSleepFreq(800);
		}

		if (isWalking) {
			try {
				setTexture(ImageIO
						.read(getClass().getResource("/sprites/run/adventurer-run-0" + spriteCmpt % 6 + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			setSleepFreq(500);
		}

		if (isRunning) {
			try {
				setTexture(ImageIO
						.read(getClass().getResource("/sprites/run/adventurer-run-0" + spriteCmpt % 6 + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
			setSleepFreq(300);
		}

		spriteCmpt++;

	}

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

	public boolean contactTest(Item it) {
		if (contactBottom(new Bloc(it.x, it.y - 5, it.width, it.height, it.isVisible(), ((TrapBloc) it).getTexture()))
				|| contactLeft(it) || contactRight(it) || contactTop(it))
			return true;
		return false;
	}

	public void contact(Item it) {
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
			switch (((TrapBloc) it).type) {
			case SPIKER:
				if (contactLeft(it)) {
					((TrapBloc) it).trapAction();
					respawn();
				}
				break;
			case SPIKEL:

				if (contactRight(it)) {
					((TrapBloc) it).trapAction();
					respawn();
				}
				break;
			case SPIKET:
				if (contactTop(it)) {
					((TrapBloc) it).trapAction();
					respawn();
				}
				break;
			case SPIKEG:

				if (contactBottom(
						new Bloc(it.x, it.y - 5, it.width, it.height, it.isVisible(), ((TrapBloc) it).getTexture()))) {
					((TrapBloc) it).trapAction();
					respawn();
				}
				break;
			case FALL:
				if (contactTest(it)) {
					((TrapBloc) it).trapAction();
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
			Audio.playSound("/audio/jump.wav");
			for (int i = 0; i < 3 * Map.BLOC_WH; i++) {
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

	public void respawn() {
		isJumping = false;
		Map.getInstance().setNbDeath(Map.getInstance().getNbDeath() + 1);
		CheckPointBloc last = Map.getInstance().checkLastCP();
		for (Bloc bloc : Map.getInstance().getListBloc()) {
			if (bloc instanceof TrapBloc) {
				if (((TrapBloc) bloc).getType() == TypeTrap.FALL) {
					((TrapBloc) bloc).revertAction();
				}
			}
			if (last.x >= x) {
				bloc.moveByX(-Math.abs(last.getCenterX() - getCenterX()));
			} else {
				bloc.moveByX(Math.abs(last.getCenterX() - getCenterX()));
			}

		}
		moveTo(new Point2D.Double(x, last.y - height - Map.BLOC_WH / 2));
	}

	// getter & setter
	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public boolean isWalking() {
		return this.isWalking;
	}

	public boolean isRunning() {
		return this.isRunning();
	}

	public void setWalk(boolean walk) {
		this.isWalking = walk;
	}

	public void setRun(boolean run) {
		this.isRunning = run;
	}

	public Image getTexture() {
		return texture;
	}

	public void setTexture(Image img) {
		this.texture = img;
	}

	public int getSleepFreq() {
		return sleepFreq;
	}

	public void setSleepFreq(int sleepFreq) {
		this.sleepFreq = sleepFreq;
	}
}
