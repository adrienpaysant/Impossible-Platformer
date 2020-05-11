
package ch.hearc.wp2.p2.jeu;

import java.awt.Dimension;

import ch.hearc.wp2.p2.jeu.menus.MainMenu;

public class Main {

	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static final int HEIGHT = (int)dimension.getHeight();
	public static final int WIDTH  = (int)dimension.getWidth();


	public static void main(String[] args) {
		Game game = Game.getInstance();
		game.setContentPane(MainMenu.getInstance());
		game.setVisible(true);
//		Bloc bloc1 = new Bloc(1, 1, 5, 5, true/* , texture */);
//		System.out.println(bloc1);
//		System.out.println(bloc1.getWidth());
//		System.out.println(bloc1.getHeight());
//		System.out.println(bloc1.getPt0());
//		System.out.println(bloc1.getPt1());
//		System.out.println("je move");
//		bloc1.moveTo(new Point2D.Double(10, 10));
//		System.out.println(bloc1);
//		System.out.println(bloc1.getWidth());
//		System.out.println(bloc1.getHeight());
//		System.out.println(bloc1.getPt0());
//		System.out.println(bloc1.getPt1());

	}

}
