
package ch.hearc.wp2.p2.jeu.items.Character;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Character extends JPanel
	{

	private static final int SPRITE_WIDTH = 32;
	private static final int SPRITE_HEIGHT = 46;

	private int[][] spriteSheetCoords = { { 0, 21, SPRITE_WIDTH, SPRITE_HEIGHT }, { 32, 21, SPRITE_WIDTH, SPRITE_HEIGHT },
			{ 64, 21, SPRITE_WIDTH, SPRITE_HEIGHT }, { 96, 21, SPRITE_WIDTH, SPRITE_HEIGHT },
			{ 128, 21, SPRITE_WIDTH, SPRITE_HEIGHT }, { 160, 21, SPRITE_WIDTH, SPRITE_HEIGHT },
			{ 0, 67, SPRITE_WIDTH, SPRITE_HEIGHT } };

	private BufferedImage sprite;
	private int i = 0;

	private ActionListener actionListener = new ActionListener()
		{

		@Override
		public void actionPerformed(ActionEvent e)
			{
			i++;
			if (i == spriteSheetCoords.length)
				{
				i = 0;
				}
			revalidate();
			repaint();
			}
		};

	public Character()
		{
		Timer timer = new Timer(50, actionListener);
		timer.setInitialDelay(0);
		timer.start();
		try
			{
			this.sprite = ImageIO.read(new File("ressources/spriteSheet.jpg"));
			}
		catch (IOException e)
			{
			e.printStackTrace();
			}
		}

	@Override
	public void paintComponent(Graphics g)
		{
		Image subSprite;
		super.paintComponent(g);
		subSprite = sprite.getSubimage(spriteSheetCoords[i][0], spriteSheetCoords[i][1], spriteSheetCoords[i][2], spriteSheetCoords[i][3]);
		g.drawImage(subSprite, 140, 120, null);
		}

	@Override
	public Dimension getPreferredSize()
		{
		return new Dimension(400, 400);
		}
	}
