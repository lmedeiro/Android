package com.tradersgem2;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.tradersgem2.database.StocksDB;
import com.tradersgem2.stock.*;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class RemoveStock extends Activity implements OnClickListener, OnItemSelectedListener
{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_removestock);
	    
	    ArrayList<String> arrListLabels = new ArrayList<String>();
	
	    // Check that user account has been sent in from the home screen
	    if(getIntent().getExtras() != null)
	    {
	    	// The user account has been sent in, retrive it.
	    	String userName = getIntent().getStringExtra("userName");
	    	
	    	// Load the stocks pertaining to the current user from the
	    	//  user's Stock Database
	    	stocksDB = new StocksDB<Stock>(getBaseContext(), userName);
	    	stockMarketDB = new StocksDB<StockMarketStock>(getBaseContext(), "stockmarket");
	    	
	    	// Add Sample Data
	    	//stocksDB.addNewStock(new Stock(1, "Masmio", 3.51f, new Date(), 10, true));
		    
	    	// From the list of the stocks that is in the current users Stock Database, retrieve
	    	//  only the ones that the user ownes.
	    	ownedStockList = stocksDB.getOwnedStocks();
	    	
	    	// Get the stock names
	    	for(Stock s: ownedStockList)
	    		arrListLabels.add(s.getName());
	    	
	    }
        
        /**
         * Creating the button that controls the adding of the stock
         * Here we link the view with the Model, so work may be done on the View and vice versa. 
         */
        
	    // Get panel buttons from the add_stock_view
	    stockSpinner = (Spinner)    findViewById(R.id.sp_RS_Symbol);
	    tvPrice      = (TextView)   findViewById(R.id.tv_RS_PurchasePrice);
 		tvQtyOwned   = (TextView)   findViewById(R.id.tv_RS_Quantity);
 		tvTotalValue = (TextView)   findViewById(R.id.tv_RS_totalValue);
 		tvProfit     = (TextView)   findViewById(R.id.tv_RS_Profit);
 		Button addButton = (Button) findViewById(R.id.bt_RS_Sell);
 		
 		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.stock_spin_item, arrListLabels);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		stockSpinner.setAdapter(adapter);
		stockSpinner.setOnItemSelectedListener(this);
         
        
		// Create Action Listeners for add_stock_view
		addButton.setOnClickListener(this);
		
		updateSelectedStock();
	    
	}
	
	@Override
	public void onClick( View v)
	{
		switch (v.getId())
		{
			case R.id.bt_RS_Sell: 
				/**
				 * Use the database to add stocks;
				 * Thus all stocks are added directly to the database.
				 * LisView then retrieves the stocks from the database. 
				 */
				String symbol = stockSpinner.getSelectedItem().toString();
				
				// Remove Stock Market Stocks (Note 1 Stock must be owned to reach this stage)
				/*ArrayList<StockMarketStock> sl = new ArrayList<StockMarketStock>();
				for(StockMarketStock s: stockMarketDB)
					sl.add(s);
				
				for(StockMarketStock s: sl)
					stockMarketDB.removeStock(s);
				
				finish();*/
				
				if(!symbol.equals(""))
				{
					Log.d("Symbol retrieved", symbol);
					
					Stock stock = stocksDB.getStockByName(symbol);
					
					if(stock != null)
					{				
						if(stocksDB.removeStock(stock, true))
							finish();
						else
							Toast.makeText(this, "Error: Removing Stock", Toast.LENGTH_SHORT).show();
					}
				}
				
				break;
		}
		
	}
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
		// TODO Auto-generated method stub
		updateSelectedStock();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	private void updateSelectedStock()
	{
		DecimalFormat decimal = new DecimalFormat();
		decimal.setMaximumFractionDigits(2);
		decimal.setMinimumFractionDigits(2);
		
		// Get The Selected Stock
		String stockName = (String) stockSpinner.getSelectedItem();
		
		// Find the stock in the both the users Porfolio and the Stock Market
		Stock stock = stocksDB.getStockByName(stockName);
		StockMarketStock marketStock = stockMarketDB.getStockByName(stockName);
		
		if(stock != null && marketStock != null)
		{
			// Get value invested and the returns
			float totalValue = stock.getPrice() * stock.getQuantity();
			float profit = (marketStock.getPrice() - stock.getPrice()) * stock.getQuantity(); 
			
			// Updates the UI with the stock information
			tvPrice.setText("$" + String.valueOf(decimal.format(stock.getPrice())));
			tvQtyOwned.setText(String.valueOf(stock.getQuantity()));
	 		tvTotalValue.setText("$" + String.valueOf(decimal.format(totalValue)));
	 		
	 		if(profit < 0)
	 		{
	 			tvProfit.setTextColor(Color.RED);
	 			tvProfit.setText(String.valueOf("$" + decimal.format(profit)));
	 		}
	 		else if(profit == 0)
	 		{
	 			tvProfit.setTextColor(Color.YELLOW);
	 			tvProfit.setText(String.valueOf("$" + decimal.format(profit)));
	 		}
	 		else
	 		{
	 			tvProfit.setTextColor(Color.GREEN);
	 			tvProfit.setText("+ " + String.valueOf("$" + decimal.format(profit)));
	 		}
		}
		else
		{
			Toast.makeText(this, "No Stocks Are Currently Owned", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
	
	private ArrayList<Stock> ownedStockList;
	private StocksDB<Stock> stocksDB;
	private StocksDB<StockMarketStock> stockMarketDB;
	
	private Spinner stockSpinner;
	private TextView tvPrice;
	private TextView tvQtyOwned;
	private TextView tvTotalValue;
	private TextView tvProfit;

}
