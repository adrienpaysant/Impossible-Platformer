
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

//player class  represent the object that the player will control

@SuppressWarnings("serial")
public class Player extends Item {

	private boolean isJumping;
	private boolean isRunning;
	private boolean isWalking;
	private int spriteCmpt = 0;
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
	
	//setImage is used to change the player texture and then creating an animation
	public void setImage() {
		if (!(isWalking && isRunning)) {
			try {
				String str = "";
				if (Map.getInstance().isLastDir()) {
					str = "right";
				} else {
					str = "left";
				}
				setTexture(
						ImageIO.read(getClass().getResource("/sprites/" + str + "/idle/" + spriteCmpt % 9 + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (isWalking) {
			try {
				String str = "";
				if (Map.getInstance().getdX() > 0) {
					str = "right";
				} else {
					str = "left";
				}
				setTexture(ImageIO.read(getClass().getResource("/sprites/" + str + "/run/" + spriteCmpt % 9 + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (isRunning) {
			try {
				String str = "";
				if (Map.getInstance().getdX() > 0) {
					str = "right";
				} else {
					str = "left";
				}
				setTexture(ImageIO.read(getClass().getResource("/sprites/" + str + "/run/" + spriteCmpt % 9 + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (isJumping) {
			try {
				String str = "";
				if (Map.getInstance().getdX() > 0) {
					str = "right";
				} else {
					str = "left";
				}
				setTexture(
						ImageIO.read(getClass().getResource("/sprites/" + str + "/jump/" + spriteCmpt % 9 + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		spriteCmpt++;
	}

	//methode to detect colisions
	public boolean contactRight(Item it) {
		return intersectsLine(it.x - 5, it.y + 5, it.x - 5, it.getMaxY());
	}

	public boolean contactLeft(Item it) {
		return intersectsLine(it.getMaxX() + 5, it.y + 5, it.getMaxX() + 5, it.getMaxY());
	}

	public boolean contactBottom(Item it) {
		return intersectsLine(it.x - 3, it.y - 4, it.getMaxX() + 3, it.y - 4);
	}

	public boolean contactTop(Item it) {
		return intersectsLine(it.x, it.getMaxY() + 1, it.getMaxX(), it.getMaxY() + 1);
	}
	
	//contactTest is used with the trap to make sure of the detection (need to redefine the tested bloc)
	public boolean contactTest(Item it) {
		return (contactBottom(
				new Bloc(it.x, it.y - 5, it.width, it.height, it.isVisible(), ((TrapBloc) it).getTexture()))
				|| contactLeft(it) || contactRight(it) || contactTop(it));
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
			case SPIKEG:// need to try contact with just the top of trap
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
			}
		}
	}

	public void jump() {
		double yT = y;
		if (!isJumping) {
			setJumping(true);
			setImage();
			Audio.playSound("/audio/jump.wav");
			boolean test = true;
			while (y >= yT - 2.5 * Map.BLOC_WH && test == true) {
				for (Bloc b : Map.getInstance().getListBloc()) {
					if (contactTop(b)) {
						trapThePlayer(b);
						test = false;
					}
				}
				if (test)
					this.moveByY(-.01);
			}
			setImage();
		}
	}

	public void respawn() {
		if (!Map.getInstance().getListCPBloc().isEmpty()) {
			isJumping = false;
			Map.getInstance().setNbDeath(Map.getInstance().getNbDeath() + 1);
			CheckPointBloc last = Map.getInstance().checkLastCP();
			for (Bloc bloc : Map.getInstance().getListBloc()) {
				if (bloc instanceof TrapBloc && ((TrapBloc) bloc).getType() == TypeTrap.FALL) {
					((TrapBloc) bloc).revertAction();
				}
				int coef = last.x >= x ? -1 : 1;
				bloc.moveByX(coef * Math.abs(last.getCenterX() - getCenterX()));
			}
			moveTo(new Point2D.Double(x, last.y - height - Map.BLOC_WH / 2));
		}
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
		return this.texture;
	}

	public void setTexture(Image img) {
		this.texture = img;
	}
}
