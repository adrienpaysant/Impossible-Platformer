
package ch.hearc.wp2.p2.jeu.items.Character.type;

import java.awt.Image;

import ch.hearc.wp2.p2.jeu.tools.Direction;

public class Player
	{

		private int width;
		private int height;
		private int posX;
		private int posY;
		private boolean walk;
		private boolean run = false; //a sa création le joueur ne cours pas
		private Direction direction; //orientation du joueur

		public Player(int x, int y, int width, int height) {
			this.posX = x;
			this.posY = y;
			this.width = width;
			this.height = height;

			this.walk = false; // Le personnage est à l'arret au début
			this.direction = Direction.RIGHT; //on oriente le personnage sur la droite de l'ecran

		}

		//get

		public int getX() { return this.posX; }
		public int getY() { return this.posY; }
		public int getWidth() { return this.width; }
		public int getHeight() { return this.height; }
		public boolean isWalking() { return this.walk; }
		public boolean isRunning() { return this.run; }
		public Direction getDirection() {
			switch(direction) {
			case RIGHT:
				return Direction.RIGHT;
			case LEFT:
				return Direction.LEFT;
			case UP:
				return Direction.UP;
			case DOWN:
				return Direction.DOWN;
			default:
				return Direction.RIGHT;
			}
		}

		//set

		public void setX(int x) { this.posX = x; }
		public void setY(int y) { this.posY = y; }
		public void setWidth(int width) { this.width = width; }
		public void setHeight(int height) { this.height = height; }
		public void setWalk(boolean toRight) { this.walk = toRight; }
		public void setRun(boolean run) { this.run = run; }
		public void setDirection(Direction direction) { this.direction = direction; }

		//methodes

		public Image walk(int frequency)
		{
			if(!(this.walk && this.run)) {

			}
		}

	}

