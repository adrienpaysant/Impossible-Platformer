
package ch.hearc.wp2.p2.jeu.tools.image;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

//Useful class to have an easy access to our images
public class ShopImage extends Object {

	private static ShopImage shopImage = null;

	public static ShopImage getInstance() {
		if (shopImage == null)
			shopImage = new ShopImage();
		return shopImage;
	}

	private ShopImage() {
	}

	public static Image function(String fName) {
		try {
			return ImageIO.read(getInstance().getClass().getResource(fName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	// blocks

	public static final Image PATHBLOCK = function("/Blocks/stonePathBlock.png");
	public static final Image DIRTBLOCK = function("/Blocks/dirtBlock.png");
	public static final Image FORESTBLOCK = function("/Blocks/forestBlock.png");
	public static final Image CAVEBLOCK = function("/Blocks/caveBlock.png");
	public static final Image DARKDIRTBLOCK = function("/Blocks/darkDirtBlock.png");
	public static final Image DARKGRASSDIRTBLOCK = function("/Blocks/darkGrassDirtBlock.png");
	public static final Image DARKSKYBLOCK = function("/Blocks/darkSkyBlock.png");
	public static final Image DARKSTONEBLOCK = function("/Blocks/darkStoneBlock.png");
	public static final Image GRASSDIRTBLOCK = function("/Blocks/grassDirtBlock.png");
	public static final Image ICEBLOCK = function("/Blocks/iceBlock.png");
	public static final Image ICEBLOCKTOP = function("/Blocks/iceBlockTop.png");
	public static final Image ICEDIRTBLOCK = function("/Blocks/iceDirtBlock.png");
	public static final Image ICESTONEPATHBLOCK = function("/Blocks/iceStonePathBlock.png");
	public static final Image LEAVESBLOCK = function("/Blocks/leavesBlock.png");
	public static final Image PURPLEBLOCK = function("/Blocks/purpleBlock.png");
	public static final Image SANDBLOCK = function("/Blocks/sandBlock.png");
	public static final Image SKYBLOCK = function("/Blocks/skyBlock.png");
	public static final Image STONEBLOCK = function("/Blocks/stoneBlock.png");
	public static final Image STONEPATHBLOCK = function("/Blocks/stonePathBlock.png");
	public static final Image WATERBLOCK = function("/Blocks/waterBlock.png");
	public static final Image WATERTOPBLOCK = function("/Blocks/waterTopBlock.png");

	// elements
	public static final Image COIN = function("/Blocks/coin.png");
	public static final Image DEATH = function("/Blocks/death.png");
	public static final Image SPIKEG = function("/Blocks/spikesB.png");
	public static final Image SPIKER = function("/Blocks/spikesR.png");
	public static final Image SPIKEL = function("/Blocks/spikesL.png");
	public static final Image SPIKET = function("/Blocks/spikesT.png");

	// decorations
	public static final Image CLOUD = function("/Blocks/cloud.png");
	public static final Image MENUBG = function("/images/menubg.jpg");
	public static final Image PAUSE = function("/images/pause.png");
	public static final Image SUN = function("/Blocks/sun.png");
	public static final Image BASEPLAYER = function("/sprites/right/idle/0.png");
	public static final Image LOGO = function("/images/logo.png");
	
}
