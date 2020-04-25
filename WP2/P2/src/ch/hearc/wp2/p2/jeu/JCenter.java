
package ch.hearc.wp2.p2.jeu;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class JCenter extends Box {

	public JCenter(JComponent component) {
		super(BoxLayout.Y_AXIS);
		
		Box boxH = Box.createHorizontalBox();
		boxH.add(Box.createHorizontalGlue());
		boxH.add(component);
		boxH.add(Box.createHorizontalGlue());

		add(Box.createVerticalGlue());
		add(boxH);
		add(Box.createVerticalGlue());
	}
}
