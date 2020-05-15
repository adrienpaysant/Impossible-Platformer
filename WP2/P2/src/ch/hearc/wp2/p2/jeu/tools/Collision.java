package ch.hearc.wp2.p2.jeu.tools;

import ch.hearc.wp2.p2.jeu.items.Item;
import ch.hearc.wp2.p2.jeu.items.Charactere.Player;

public class Collision implements Runnable {

	private final int DELAY = 15;

	private Player player;
	private Item item;

	public Collision(Player p, Item it) {
		this.player = p;
		this.item = it;
	}

	@Override
	public void run() {
		while (true) {
			player.contact(item);
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
