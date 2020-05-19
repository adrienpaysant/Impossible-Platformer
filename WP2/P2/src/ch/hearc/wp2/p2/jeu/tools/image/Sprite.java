package ch.hearc.wp2.p2.jeu.tools.image;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private Image sprite;
	
	public Sprite() {
		try {
			this.sprite = ImageIO.read(getClass().getResource("/sprites/idle/adventurer-idle-00.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSprite(String img) {
		try {
			sprite = ImageIO.read(getClass().getResource(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image getSprite() {
		return this.sprite;
	}
}
