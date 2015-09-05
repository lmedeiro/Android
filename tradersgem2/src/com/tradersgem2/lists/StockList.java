package com.tradersgem2.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.content.Context;
import android.util.Log;

import com.tradersgem2.database.StocksDB;
import com.tradersgem2.loginSystem.UserAccount;
import com.tradersgem2.stock.Stock;
/**
 * This StockList class is a super class to sub list classes such as watchList and Portfolio;
 * StockList holds all the stocks from all the sublists that extend it .
 * It then makes the report to the database between the classes that have been added 
 * or subtracted from the sublists. 
 * @author Luiz Medeiros
 *
 */
public abstract class StockList implements List
{

	/**
	 * This is the constructor of StockList, the superClass of the all other
	 * list related classes that contain stocks as their main dataStructure;
	 * @precondition Context c && UserAccount uA are already defined and are not null;
	 * 
	 * @param c
	 * @param uA
	 */
	public StockList(Context c, String uA)
	{
		/**
		 * Begin by adding all the stocks from database into the stocklist.
		 */
		//super();
		context=c;
		userAccount=uA;
		stocks= new ArrayList<Stock>();
		
		StocksDB stockDatabase= new StocksDB(context,userAccount);
		stockDatabase.refresh();
		stocks=stockDatabase.getStocks();
		
		
		/**
		 * Then, add any additional stocks;
		 */
		
		
	}
	/**
	 * This method adds stocks to the current stockList, which will be 
	 * utilized by the other lists;
	 * @precondition Stock is not null...
	 * @param stock
	 * @return true if everything was performed correctly;
	 */
	
	public boolean addStocks(Stock stock)
	{
		try 
		{
			stocks.add(stock);
			stockDatabase.addNewStock(stock);
			stockDatabase.refresh();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String error= "error has occured" + "\n"+ e.getMessage();
			Log.d("StockList:addStocks(Stock)",error);
			return false;
		}
		
	}
	
	/**
	 * Adding an array of stocks into the the stock list;
	 * @param stock []
	 */
	
	
	public boolean addStocks(Stock[] stock)
	{
		try
		{
			int i=0;
			while (i<stock.length)
			{
				stocks.add(stock[i]);
				stockDatabase.addNewStock(stock[i]);
				i++;
			}
			stockDatabase.refresh();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String error= "error has occured" + "\n"+ e.getMessage();
			Log.d("StockList:addStocks(Stock)",error);
			return false;
		}
		
	}
	/**
	 * The method below deletes stocks from the arrayList, who then reports to the 
	 * database. 
	 * @return true if successfully removed, false if not;
	 * @precondition Stock is not null and has all data fields correctly filled;
	 * 
	 */
	public boolean deleteStocks(Stock stock)
	{
		int i=0;
		i= stocks.size();
		/**
		 * A loop to traverse the list from the back to the front to delete a stocks;
		 * This loop is made so to avoid problem when deleting from the list; 
		 */
		while (i>0)
		{
			if(stocks.get(i).equals(stock))
			{
				stocks.remove(i);
				stockDatabase.removeStock(stock, true);
				return true;
			}
			else
			{
				i--;
			}
		}
		return false;
		
		
	}
	/**
	 * returns all the stocks from the database;
	 * @return ArrayList containing all stocks;
	 */
	public ArrayList<Stock> getAllStocks()
	{
		return stocks;
	}
	/**
	 * A method to be implemented by watchList that will return all stocks being 
	 * watched;
	 * @return all stocks being watched;
	 */
	public abstract ArrayList<Stock> getWatchList();
	/**
	 * return only owned stocks
	 * @return Owned stocks from the database;
	 */
	public abstract ArrayList<Stock> getOwnedStocks();
	/**
	 * Refresh is an abstract method to be defined by each subclass;
	 */
	public abstract void refresh();
	
	
	
	/**
	 * Declaring all the attributes 
	 */
	private static ArrayList<Stock> stocks;
	private StocksDB stockDatabase;
	private final String userAccount;
	private Context context;
	@Override
	public boolean add(Object object) {
		
		
		
		return addStocks((Stock) object); 
	}
	
	
	@Override
	public boolean addAll(Collection arg0) {
		
		int i=0;
		boolean a=false;
		while (i<stocks.size())
		{
			a=add(((ArrayList<Stock>) arg0).get(i));
			i++;
		}
		
		return a;
	}
	
	@Override
	public boolean remove(Object object) {
		
		return deleteStocks((Stock) object);
		
		 
	}
	
}
