package ch.hearc.wp2.p2.jeu.menus;

import javax.swing.Box;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class Options extends Box{
	private static Options options=null;
	public static Options getInstance() {
		if(options==null) 
			options = new Options();
		return options;
	}
	private Options() {
		super(BoxLayout.Y_AXIS);
	}
}
