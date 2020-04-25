package ch.hearc.wp2.p2.jeu;

import ch.hearc.wp2.p2.jeu.items.Item;

public class Main {
	public static void main(String[] args) {
		new Game("Impossible Platformer");
		
		//test item : 
		Item item=new Item(3,3,3,3,true);
		System.out.println(item);
	}
}
