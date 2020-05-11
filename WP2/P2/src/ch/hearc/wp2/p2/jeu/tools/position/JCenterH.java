package ch.hearc.wp2.p2.jeu.tools.position;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class JCenterH extends Box
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JCenterH(JComponent component)
		{
		super(BoxLayout.X_AXIS);

		this.component = component;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		add(Box.createHorizontalGlue());
		add(component);
		add(Box.createHorizontalGlue());
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JComponent component;

	// Tools

	}
