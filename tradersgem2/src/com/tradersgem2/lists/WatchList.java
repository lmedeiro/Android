package com.tradersgem2.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.content.Context;

import com.tradersgem2.loginSystem.UserAccount;
import com.tradersgem2.stock.Stock;

/**
 *  WatchList is a subClass of StockList. It takes all stocks into an array, 
 *  and makes the amendment between ListView and the Stocks in StockList
 *  Any aggregated stock will go to StockList and eventually the Database;
 * 
 * @author Luiz
 *
 */

public class WatchList extends StockList
{

	public WatchList(Context c, String  uA) 
	{
		super(c, uA);
		watchList= super.getAllStocks();
	}
	
	/**
	 * The following method returns the ArrayList watchList, that is held by this 
	 * object;
	 * @return
	 */
	@Override
	public ArrayList<Stock> getWatchList()
	{
		return watchList;	
	}
	
	@Override
	public boolean addStocks(Stock stock)
	{
		return super.addStocks(stock);
	}
	
	@Override
	public boolean deleteStocks(Stock stock)
	{
		return super.deleteStocks(stock);
	}
	
	
	@Override
	public void refresh()
	{
		watchList=super.getAllStocks();
	}
	
	
	
	
	
	
	
	private static ArrayList<Stock> watchList;


/**
 * Automatic Overrides ;
 */




	@Override
	public void add(int location, Object object) 
	{
		// TODO Auto-generated method stub		
	}


	@Override
	public boolean addAll(int arg0, Collection arg1) 
	{
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void clear() 
	{
		// TODO Auto-generated method stub		
	}


	@Override
	public boolean contains(Object object) 
	{
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean containsAll(Collection arg0) 
	{
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Object get(int location) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int indexOf(Object object) 
	{
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Iterator iterator() 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int lastIndexOf(Object object) 
	{
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public ListIterator listIterator() 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ListIterator listIterator(int location) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object remove(int location) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean removeAll(Collection arg0) 
	{
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean retainAll(Collection arg0) 
	{
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Object set(int location, Object object) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int size() {
		
		return watchList.size();
	}


	@Override
	public List subList(int start, int end) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] toArray() 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] toArray(Object[] array) 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<Stock> getOwnedStocks() 
	{
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	
}
