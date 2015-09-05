package com.tradersgem2;

import com.tradersgem2.loginSystem.UserAccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * This is the controller for the home screen for the current user. This class connects with the Home view to display the UI to the user and it
 * receives commands from the user including My Stocks, Watch List, Sell Stocks and Statistics. This Controller receives the current user account
 * information from their the Login or NewUser Controller.
 * @author pedro + Luiz 
 *
 */
public class Home extends Activity 
{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	    setContentView(R.layout.activity_homescreen);
	    
	    // Check if current user account has been sent in from either the Login or NewUser controller.
	    if(getIntent().getExtras() != null)
	    {
	    	// The serialized account information has been sent in, retrieve the account.
	    	curUserAccount = (UserAccount) getIntent().getSerializableExtra("UserAccount");
	    	Log.d("Current User: ", curUserAccount.getUserName());
	    }
	    
	    // Get panel buttons from the activity_home view
		btMyStocks = (Button) findViewById(R.id.btMyStocks);
	    btWatchList = (Button) findViewById(R.id.btWatchStocks);
	    btBuyStocks = (Button) findViewById(R.id.btBuyStocks);
	    btSellStocks = (Button) findViewById(R.id.btSellStocks);
	    btStatistics = (Button) findViewById(R.id.btStatistics);
	    
	    // Create Action Listeners for activity_home view
		btMyStocks.setOnClickListener(eventHandler);
	    btWatchList.setOnClickListener(eventHandler);
	    btBuyStocks.setOnClickListener(eventHandler);
	    btSellStocks.setOnClickListener(eventHandler);
	    btStatistics.setOnClickListener(eventHandler);
	}
	
	/**
	 * On click listener to keep track of user clicks on the activity_home View. 
	 */
	View.OnClickListener eventHandler = new View.OnClickListener()
	{
		public void onClick(View v)
		{
			// User clicked on the MyStocks button
			if(((Button)v).getId() == btMyStocks.getId())
			{
				// Pass the current user account to the OwnedStockList controller.
				//Intent intent = new Intent(Home.this, StockList.class).putExtra("String", "MyStocks");
				Intent intent = new Intent(Home.this, ListOfMyStocks.class).putExtra("userName", curUserAccount.getUserName());
				//Intent intent = new Intent(Home.this, CustomListViewAndroidExample.class).putExtra("String", curUserAccount.getUserName());
				startActivity(intent);
			}
			// User clicked on the WatchList button
			else if(((Button)v).getId() == btWatchList.getId())
			{
				// Pass the current user account to the WatchList controller.
				//Intent intent = new Intent(Home.this, ListView.class).putExtra("userName", curUserAccount.getUserName());
				Intent intent = new Intent(Home.this, ListOfWatchedStocks.class).putExtra("userName", curUserAccount.getUserName());
				startActivity(intent);
			}
			// User clicked on the Buy Stocks button
			else if(((Button)v).getId() == btBuyStocks.getId())
			{
				// Pass the current user account to the WatchList controller.
				//Intent intent = new Intent(Home.this, ListView.class).putExtra("userName", curUserAccount.getUserName());
				Intent intent = new Intent(Home.this, AddStock.class).putExtra("userName", curUserAccount.getUserName());
				startActivity(intent);
			}
			// User clicked on the SellStocks button
			else if(((Button)v).getId() == btSellStocks.getId())
			{
				// Pass the current user account to the Sell Stocks controller.
				Intent intent = new Intent(Home.this, RemoveStock.class).putExtra("userName", curUserAccount.getUserName());
				startActivity(intent);
			}
			// User clicked on the Statistics button
			else if(((Button)v).getId() == btStatistics.getId())
			{
				Intent i= new Intent(Home.this,TGGraphView.class).putExtra("userName", curUserAccount.getUserName());
				startActivity(i);
				// Pass the current user account to the Statistics controller.
			}
		}
	};
	
	// User Account variables
	private UserAccount curUserAccount;
	
	// User Interface Variables
	private Button btMyStocks;
	private Button btWatchList;
	private Button btBuyStocks;
	private Button btSellStocks;
	private Button btStatistics;
}
