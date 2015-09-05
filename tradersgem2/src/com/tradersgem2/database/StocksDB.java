package com.tradersgem2.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.tradersgem2.stock.*;

import android.content.Context;

/**
 * Model for the User's Stocks, this class is used to communicate with the DB to fetch information, manage user's stocks by providing the following
 * functionality. It allows the adding new stocks to the user's porfolio, searching for stocks, getting quantities of owned/watched stocks, and 
 * saving/loading stocks from the stock DB.
 * @author pedro + Luiz 
 *
 */
public class StocksDB<E> implements Iterable<E>
{
	/**
	 * 
	 * @param context
	 * @param userName
	 */
	public StocksDB(Context context, String userName)
	{
		fileName = userName + ".dat";
		listOfStocks = new ArrayList<E>();
		this.context = context;
		loadStocks();
		
		// Used for Stock Market DB Only
		if(/*userName.equals("stockmarket")*/ fileName.contains("stockmarket.dat"))
		{
			for(E s: listOfStocks)
				((StockMarketStock)s).getPrice();
				
				saveStocks();
				listOfStocks.clear();
				loadStocks();
		}
	}
	
	public E getStockById(int id)
	{
		Iterator<E> iterator = this.iterator();
		
		while(iterator.hasNext())
		{
			E stock = iterator.next();
			
			if(((Stock)stock).getId() == id)
				return stock;
		}
		
		return null;
	}
	
	public E getStockByName(String name)
	{
		Iterator<E> iterator = this.iterator();
		
		while(iterator.hasNext())
		{
			E stock = iterator.next();
			
			if(((Stock)stock).getName().equals(name))
				return stock;
		}
		
		return null;
	}
	/**
	 * The following stock aggregates to the list of stock .
	 * @param stock
	 * @return
	 */
	public boolean addNewStock(E stock)
	{
		listOfStocks.add(stock);
		
		// Update Stocks Database
		saveStocks();		
		
		return true;
	}
	/**
	 * The following function removes the stock from the list of stocks, 
	 * which then takes it away from the database itself;
	 * @precondition is such that stock is not null and corresponds correctly to 
	 * its type;
	 * @param stock
	 * @return true, if stock has been removed successfully, false if not;
	 */
	public boolean removeStock(E stock, boolean isOwned)
	{
		for(int i=0; i<listOfStocks.size(); i++)
		{
			Stock curStock = (Stock)listOfStocks.get(i);
			
			if(curStock.getName().equals(((Stock)stock).getName()) && curStock.getOwnedStatus() == isOwned)
			{
				listOfStocks.remove(i);
				saveStocks();
				return true;
			}
		}
		
		return false;
	}
	
	public int getQtyStocksOwned()
	{
		int count = 0;
		Iterator<E> iterator = this.iterator();
		
		while(iterator.hasNext())
		{
			Stock stock = (Stock)iterator.next();
			
			if(stock.getOwnedStatus())
				count++;
		}
		
		return count;
	}
	
	/**
	 * Gets an iterator from the current user's stock database and searches through it to find only the stocks that
	 *  the current user owns.
	 * @return - A list of stocks that the current user owns.
	 * @precondition - The current user account has been passed into this controller and stocksDB != null
	 */
	public ArrayList<E> getOwnedStocks()
	{
		// Create a list to hold all the stocks the current user owns
		ArrayList<E> ownedStocks = new ArrayList<E>();
		
		// Get an iterator for the current user's owned list of stocks.
		Iterator<E> iterator = iterator();
		
		// Iterate through teach stock in the database
		while(iterator.hasNext())
		{
			E stock = iterator.next();
			
			// If the stock if owned add it to the list of owned stocks
			if(((Stock)stock).getOwnedStatus())
				ownedStocks.add(stock);
		}	
		
		// Return the list of the owned stocks
		return ownedStocks;
	}
	
	public int getQtyStocksWatched()
	{
		int count = 0;
		Iterator<E> iterator = this.iterator();
		
		while(iterator.hasNext())
		{
			Stock stock = (Stock)iterator.next();
			
			if(!stock.getOwnedStatus())
				count++;
		}
		
		return count;
	}
	
	public void refresh()
	{
		loadStocks();
	}
	
	private void saveStocks()
	{
		try
		{
			// Clear Stocks
			//if(!fileName.contains("stockmarket.dat")) listOfStocks.clear();
			
			FileOutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			ObjectOutputStream write = new ObjectOutputStream(out);
			write.writeObject(listOfStocks);
			write.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadStocks()
	{
		// Check if file exists, if it does not create it
		try
		{
			FileInputStream inp = context.openFileInput(fileName);				
			ObjectInputStream read = new ObjectInputStream(inp);
			listOfStocks = (ArrayList<E>)read.readObject();
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
	
	@Override
	public Iterator<E> iterator() 
	{
		// TODO Auto-generated method stub
		return Collections.unmodifiableList(listOfStocks).iterator();
	}
	
	public ArrayList<E> getStocks()
	{
		return listOfStocks;
		
	}
	
	private ArrayList<E> listOfStocks;
	private Context context;
	final private String fileName;
	
}
