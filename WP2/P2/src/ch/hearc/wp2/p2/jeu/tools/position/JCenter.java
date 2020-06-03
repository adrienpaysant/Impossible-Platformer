
package ch.hearc.wp2.p2.jeu.tools.position;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

//class to make a JComponent horizontaly & verticaly centred


@SuppressWarnings("serial")
public class JCenter extends Box {

	public JCenter(JComponent component) {
		super(BoxLayout.Y_AXIS);

		Box boxH=new Box(BoxLayout.X_AXIS);

		boxH.add(Box.createHorizontalGlue());
		boxH.add(component);
		boxH.add(Box.createHorizontalGlue());

		add(Box.createVerticalStrut(this.getHeight()/3));
		add(boxH);
		add(Box.createVerticalGlue());
	}
}
