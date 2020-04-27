package ch.hearc.wp2.p2.jeu.items.Character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelCharacter extends JPanel
	{

	private BufferedImage sprite;

	public JPanelCharacter()
		{
		try {
			this.sprite = ImageIO.read(new File(""));
		} catch (IOException e) {
			System.out.println("error: " + e);
		}
		geometry();
		control();
		appearance();
		}

	private void geometry()
		{
		// TODO
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}
	}
