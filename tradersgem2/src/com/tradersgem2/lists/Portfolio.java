package com.tradersgem2.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.content.Context;

import com.tradersgem2.loginSystem.UserAccount;
import com.tradersgem2.stock.Stock;

/**
 * The subClass Portfolio extends the StockList.  This class is the junction between
 * the models that manage the storage of the stocks and the view which will
 * display the stock;
 * The class ListView, that will display all the sublists of StockList, will 
 * be getting the information from this list; 
 * 
 * @author Luiz
 *
 */

public class Portfolio extends StockList
{

	public Portfolio(Context c, String uA) 
	{
		super(c, uA);
		portfolio= getOwnedStocks();
		
	}
	
	public boolean addStocks(Stock stock)
	{
		portfolio.add(stock);
		return super.addStocks(stock);
	}
	
	public boolean deleteStocks(Stock stock)
	{
		
		return super.deleteStocks(stock);
	}
	@Override
	public void refresh()
	{
		portfolio= getOwnedStocks();
	}
	public ArrayList<Stock> getStocks()
	{
		refresh();
		return portfolio;
	}
	
	
	
	/**
	 * return only owned stocks; THis is the implementation from the abstract 
	 * method in the super class;
	 * @return Owned stocks from the database;
	 */
	
	public ArrayList<Stock> getOwnedStocks()
	{
		ArrayList<Stock> ownedStocks= new ArrayList<Stock>();
		int i=0;
		while (i<super.getAllStocks().size())
		{
			if (super.getAllStocks().get(i).getOwnedStatus())
			{
				ownedStocks.add(super.getAllStocks().get(i));
				i++;
			}
			else
			{
				i++;
			}
			
			
		}
		return ownedStocks;
		
	}

	
	
	private static ArrayList<Stock> portfolio;

/**
 * automatic overrides; possible Manual OVerride in the future;
 */

	@Override
	public void add(int location, Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(int arg0, Collection arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(int location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(int location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object set(int location, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		
		
		return portfolio.size();
	}

	@Override
	public List subList(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] array) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Stock> getWatchList() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
