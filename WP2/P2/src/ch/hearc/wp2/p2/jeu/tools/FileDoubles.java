
package ch.hearc.d_flux.file.binary.doubles;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileDoubles
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void write(double[] tab, String nameFile) throws IOException
		{
		FileOutputStream fos = new FileOutputStream(nameFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);

		for(double value:tab)
			{
			dos.writeDouble(value);
			}

		dos.close();
		bos.close();
		fos.close();
		}

	public static Double[] read(String fileName) throws IOException
		{
		FileInputStream fis = new FileInputStream(fileName);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);

		List<Double> listData = new LinkedList<Double>();

		try
			{
			while(true)
				{
				double value = dis.readDouble();
				listData.add(value);
				}
			}
		catch (EOFException e)
			{
			//rien
			}

		Double[] tabData = new Double[listData.size()];
		listData.toArray(tabData); //vide la liste dans le tableau, tableau qui a du etre creer avant

		fis.close();
		bis.close();
		dis.close();

		return tabData;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
