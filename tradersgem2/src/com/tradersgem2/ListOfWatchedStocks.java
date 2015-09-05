package com.tradersgem2;

import com.tradersgem2.database.*;
import com.tradersgem2.stock.*;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ListOfWatchedStocks extends ListOfStocksController 
{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    
	
	    if(getIntent().getExtras() != null)
	    {
	    	userName = getIntent().getStringExtra("userName");
	    	stocksDB = new StocksDB<Stock>(getBaseContext(), userName);
	    	
	    	// Add Sample Data
	    	//stocksDB.addNewStock(new Stock(1, "Phillips", 3.51f, new Date(), 10, false));
		    
	    	watchedStockList = getWatchedStocks();
		    
	    	//Log.d("Current User: ", userName);
	    	
	    	super.loadData(watchedStockList);
	    }
	}
	
	public ArrayList<Stock> getWatchedStocks()
	{
		ArrayList<Stock> watchedStocks = new ArrayList<Stock>();
		Iterator<Stock> iterator = stocksDB.iterator();
		
		while(iterator.hasNext())
		{
			Stock stock = iterator.next();
			
			if(!stock.getOwnedStatus())
				watchedStocks.add(stock);
		}
		
		
		return watchedStocks;
	}
	
	/**
	 * This method tracks the clicking of the items;
	 * Through this method we are able to see which item has been clicked by the user;
	 */
	public void onItemClick(int mPosition)
	{
		Stock tempValues = (Stock) watchedStockList.get(mPosition);
		Intent i= new Intent(this,ItemClickMenu.class);
		i.putExtra("userName", userName);
		/**
		 * Pass the unique id to the next intent so it knows which stock has been clicked;
		 * The Comparison is the then unique to that particular stock;
		 */
		
		
		i.putExtra("com.tradersgem2.stockClicked",tempValues.getId());
		i.putExtra("listOfOrigin", "watchedList");
		startActivity(i);
		super.showClickedItem(tempValues);
	}
	
	private StocksDB<Stock> stocksDB;
	private ArrayList<Stock> watchedStockList;
	private String userName;

}
