
package ch.hearc.wp2.p2.jeu.tools;

public class Chrono implements Runnable
	{

	@Override
	public void run()
		{
		while(true)
			{
			//Map.repaint()
			try
				{
				Thread.sleep(15);//60 fps
				}
			catch (InterruptedException e)
				{
				e.printStackTrace();
				}
			}

		}

	}
