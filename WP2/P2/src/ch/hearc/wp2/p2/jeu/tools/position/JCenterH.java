package ch.hearc.wp2.p2.jeu.tools.position;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

//class to make a JComponent horizontaly centred

@SuppressWarnings("serial")
public class JCenterH extends Box {

	public JCenterH(JComponent component) {
		super(BoxLayout.X_AXIS);
		add(Box.createHorizontalGlue());
		add(component);
		add(Box.createHorizontalGlue());
	}
}
