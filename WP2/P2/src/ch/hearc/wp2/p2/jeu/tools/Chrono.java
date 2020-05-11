
package ch.hearc.wp2.p2.jeu.tools;

import ch.hearc.wp2.p2.jeu.Map;

public class Chrono implements Runnable {
	private final int DELAY = 15;// timer between 2 repaint 60FPS

	@Override
<<<<<<< HEAD
	public void run()
		{
		while(true)
			{
			//Map.repaint()
			try
				{
				Thread.sleep(16);//60 fps
				}
			catch (InterruptedException e)
				{
=======
	public void run() {
		while (true) {

			Map.getInstance().repaint();
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
>>>>>>> dev
				e.printStackTrace();
			}
		}

	}
}
