package ch.hearc.wp2.p2.jeu.tools;

import java.awt.Graphics2D;

public class Design {
	/**
	 * Methode to draw a centered text in a 2D graphic context
	 * 
	 * Methode from Kyle Amburn found on :
	 * https://coderanch.com/t/336616/java/Center-Align-text-drawString
	 * 
	 * NOTE : adding Graphics2D parameter to the original methode from K.Amburn.
	 * 
	 * @param s
	 * @param width
	 * @param XPos
	 * @param YPos
	 * @param g2d
	 */
	public static void printSimpleString(String s, int width, int XPos, int YPos, Graphics2D g2d) {
		int stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
		int start = width / 2 - stringLen / 2;
		g2d.drawString(s, start + XPos, YPos);
	}
}
