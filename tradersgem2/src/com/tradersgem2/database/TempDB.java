package com.tradersgem2.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import android.content.Context;


/**
 * The idea behind this class is to store all the necessary temp information, such as user name, stock id,
 *  and from which list this stock was clicked from;
 * 
 * @author Luiz 
 * @param <E>
 *
 */

public class TempDB<E> 
{
	public TempDB(Context c)
	{
		context=c;
		fileName="tempDB.dat";
		tempInfo= new ArrayList<E>();
	}
	
	public void refresh()
	{
		loadInfo();
	}
	
	private void saveInfo()
	{
		try
		{
			// Clear Stocks
			//if(!fileName.contains("stockmarket.dat")) listOfStocks.clear();
			
			FileOutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			ObjectOutputStream write = new ObjectOutputStream(out);
			write.writeObject(tempInfo);
			write.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadInfo()
	{
		// Check if file exists, if it does not create it
		try
		{
			FileInputStream inp = context.openFileInput(fileName);				
			ObjectInputStream read = new ObjectInputStream(inp);
			tempInfo = (ArrayList<E>)read.readObject();
			read.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	public boolean addInfo(E info)
	{
		if (info!=null)
		{
			tempInfo.add(info);
			saveInfo();
			return true;
		}
		else
			return false;
	
	}
	
	public ArrayList<E> getInfo()
	{
		return tempInfo;
		
	}
	
	private ArrayList<E> tempInfo;
	private Context context;
	private String fileName;
	
}