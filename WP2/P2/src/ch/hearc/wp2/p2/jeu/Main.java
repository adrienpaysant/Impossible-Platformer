package ch.hearc.wp2.p2.jeu;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	public static Scene scene;

	public static void main(String[] args) {
		JFrame window = new JFrame("Impossible Platformer");
		window.setAlwaysOnTop(true);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setSize(640,400);

		scene=new Scene();
		window.setContentPane(scene);
		window.setVisible(true);
	}

}
