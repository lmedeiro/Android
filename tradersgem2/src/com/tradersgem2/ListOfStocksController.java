package com.tradersgem2;

import com.tradersgem2.stock.*;
import com.tradersgem2.lists.*;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

/**
 * This is the controller for the general purpose ListOfStocksController. This class connects with the stock list view to display the UI to the user 
 * and it receives commands from the user such as a individual stock click or a return to previous screen click. This Controller receives the list 
 * of stocks from its subclass sends it to the stock list view to be displayed to the user. StockList is general class to implement a general 
 * functionality for a list of stocks, the on click controller for this class abstract the subclass must implement the action for the user clicks. 
 * @author pedro
 *
 */
public abstract class ListOfStocksController extends Activity 
{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_stocklist);
	    // TODO Auto-generated method stub
	}
	
	/**
	 * Receives an array list of stocks that will be displayed to the user in the activity_stocklist.
	 * @param stockList - The list of stocks to be displayed to the user in the activity_stocklist. 
	 */
	public void loadData(ArrayList<Stock> stockList)
	{
		listView = (ListView) findViewById(R.id.listOfStocks);
	    
		// Create a customer adapter to manage the list of stocks
		//CustomAdapter adapter = new CustomAdapter(CustomListView, customListViewValuesArr, res);
		StockListAdapter adapter = new StockListAdapter(this, stockList, getResources());
		listView.setAdapter(adapter);
	}
	
	/**
	 * Used for to show which stock was click on the screen, stock to be displayed must be passed in from the subclass that implements
	 *  the onClick action for the stock list.
	 * @param stock - A stock to be displayed on the screen.
	 */
	public void showClickedItem(Stock stock)
	{
		Toast.makeText(this, 
				""+stock.getName() + "\n"
				  +"Id:"+stock.getId() + "\n"
				  +"Price:"+stock.getPrice(), Toast.LENGTH_SHORT).show();
		
	}
	
	/**
	 * An abstract method to be implemented by the subclass to perform an action when a specific stock is clicked.
	 * @param mPosition - The index of the stock of the stock list item that was clicked.
	 */
	public abstract void onItemClick(int mPosition);
	
	
	
	// StockList UI Variables
	private ListView listView;
}
