package ch.hearc.wp2.p2.jeu;

import ch.hearc.wp2.p2.jeu.items.blocs.Bloc;

public class Main {
	public static void main(String[] args) {
		new Game("Impossible Platformer");

	}

	Bloc bloc1 = new Bloc(1, 1, 5, 5, true/* , texture */);
}
