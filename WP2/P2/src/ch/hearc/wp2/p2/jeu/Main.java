package ch.hearc.wp2.p2.jeu;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	public static Map map;

	public static void main(String[] args) {
		JFrame window = new JFrame("Impossible Platformer");
		window.setAlwaysOnTop(true);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setSize(640,400);

		map=new Map();
		window.setContentPane(map);
		window.setVisible(true);
	}

}
