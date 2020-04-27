package ch.hearc.wp2.p2.jeu.items.Character;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class UseCharacter {

	public static void main(String[] args)
		{
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run()
				{
				JFrame frame = new JFrame();
				Character character = new Character();
				frame.getContentPane().add(character);

				frame.pack();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				}

		});
		}
}
