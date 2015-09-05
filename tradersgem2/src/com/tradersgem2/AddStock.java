package com.tradersgem2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.tradersgem2.stock.*;
import com.tradersgem2.database.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class controls adding another Stock to the current list. 
 * It is a small simple view, that takes in a symbol and then adds it to the watchList. 
 * The model considers this symbol and searches for the information online. It then populates the fields
 * with the retrieved information. 
 * @author Luiz Medeiros + Pedro Miranda
 *
 */
public class AddStock extends Activity implements OnClickListener, OnItemSelectedListener
{
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_stock_view);
        
        tbPrice   = (TextView) findViewById(R.id.priceToAdd);
		        
        //stocksList = new ArrayList<StockMarketStock>();
        ArrayList<String> arrListLabels = new ArrayList<String>();
        
        stockMarketDB = new StocksDB<StockMarketStock>(this.getBaseContext(), "stockmarket");
        
        if(stockMarketDB.getQtyStocksOwned() == 0)
        {
        	stockMarketDB.addNewStock(new StockMarketStock(1 , "AAPL"  , 556.10f, new Date(), 1, true));
        	stockMarketDB.addNewStock(new StockMarketStock(2 , "BA"    , 134.25f, new Date(), 1, true));
        	stockMarketDB.addNewStock(new StockMarketStock(3 , "CAR"   ,  37.20f, new Date(), 1, true));
        	stockMarketDB.addNewStock(new StockMarketStock(4 , "MSFT"  ,  38.39f, new Date(), 1, true));
        	stockMarketDB.addNewStock(new StockMarketStock(5 , "MET"   ,  52.19f, new Date(), 1, true));
        	stockMarketDB.addNewStock(new StockMarketStock(6 , "MRO"   ,  36.14f, new Date(), 1, true));
        	stockMarketDB.addNewStock(new StockMarketStock(7 , "MINI"  ,  40.40f, new Date(), 1, true));
        	stockMarketDB.addNewStock(new StockMarketStock(8 , "MASI"  ,  28.63f, new Date(), 1, true));
        	stockMarketDB.addNewStock(new StockMarketStock(9 , "PSX"   ,  69.61f, new Date(), 1, true));
        	stockMarketDB.addNewStock(new StockMarketStock(10, "TWX"   ,  66.18f, new Date(), 1, true));
        } 
        
        Iterator<StockMarketStock> stockIter = stockMarketDB.iterator();
        while(stockIter.hasNext())
        {
        	StockMarketStock stock = stockIter.next();
        	arrListLabels.add(stock.getName());
        }
        
        if(getIntent().getExtras() != null)
        {
        	userName = getIntent().getStringExtra("userName");
        	stockDB = new StocksDB<Stock>(getBaseContext(), userName);
        }
        
        /**
         * Creating the button that controls the adding of the stock
         * Here we link the view with the Model, so work may be done on the View and vice versa. 
         */
        
        stockSpinner = (Spinner) findViewById(R.id.sp_AS_Symbol);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.stock_spin_item, arrListLabels);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		stockSpinner.setAdapter(adapter);
		stockSpinner.setOnItemSelectedListener(this);
         
        // Get panel buttons from the add_stock_view
		Button addButton= (Button) findViewById(R.id.addStock);
		
		// Create Action Listeners for add_stock_view
		addButton.setOnClickListener(this);
		
		updateSelectedStock();
    }
	/**
	 * Handling of the options; Here we puch the main menu by default into the current activity;
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
	{
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        
        return true;
    }
	
	/**
	 * Below are the Overrides and custom defined methods;
	 */
	
	
	@Override
	public void onClick( View v)
	{
		switch (v.getId())
		{
			case R.id.addStock: 
				/**
				 * Use the database to add stocks;
				 * Thus all stocks are added directly to the database.
				 * LisView then retrieves the stocks from the database. 
				 */
				//EditText symbolToAdd= (EditText) findViewById(R.id.symbolToAdd);
				//String symbol= "";
				//symbol=symbolToAdd.getText().toString();
				String symbol= "";
				symbol=stockSpinner.getSelectedItem().toString();
				Log.d("Symbol retrieved", symbol);
				
				CheckBox ownCheckBox  = (CheckBox) findViewById(R.id.ownCheck);
				EditText quantityText = (EditText) findViewById(R.id.quantity);
				
				if(!quantityText.getText().toString().matches(""))
				{
					Float price= Float.parseFloat(tbPrice.getText().toString());
					boolean own= ownCheckBox.isChecked();
					int quantity= Integer.parseInt(quantityText.getText().toString());
					
					Date date= new Date();
					int id= (int) (Math.random()*100);
					Log.d("id",""+ id);
					
					// Add Stock to the Stock Database
					//StocksDB<Stock> stocksDB = new StocksDB<Stock>(this.getBaseContext(), userName);
					Stock stock = new Stock(id,symbol,price,date,quantity,own);
					
					//if(stocksDB.addNewStock(stock))
					if(stockDB.addNewStock(stock))
					{
						finish();
					}
				}
				else
				{
					Toast.makeText(this, "Missing Quantity of\nStocks to purchase/watch", Toast.LENGTH_SHORT).show();
				}
				
				break;
		}
		
	}
	/**
	 * Here we explore the selection of the items with random input generators. 
	 * So the selected stock updates it's price. 
	 */
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) 
	{
		// TODO Auto-generated method stub
		updateSelectedStock();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateSelectedStock()
	{
		DecimalFormat decimal = new DecimalFormat();
		decimal.setMaximumFractionDigits(2);
		decimal.setMinimumFractionDigits(2);
		
		//int selectStockIndex = stockSpinner.getSelectedItemPosition();
		//tbPrice.setText(String.valueOf(stocksList.get(selectStockIndex).getPrice()));
		
		//float[] stockValues = stocksList.get(selectStockIndex).getStockHistory();
		
		String stockName = (String) stockSpinner.getSelectedItem();
		
		StockMarketStock stock = stockMarketDB.getStockByName(stockName);
		
		if(stock != null)
		{		
			//tbPrice.setText("$" + String.valueOf(decimal.format(stock.getPrice())));
			tbPrice.setText(String.valueOf(decimal.format(stock.getPrice())));
			
			/*float[] stockValues = stock.getStockHistory();
			
			Toast.makeText(this, "day[0]" + stockValues[0] + "\n" +
					"day[1]" + stockValues[1] + "\n" +
					"day[2]" + stockValues[2] + "\n" +
					"day[3]" + stockValues[3] + "\n" +
					"day[4]" + stockValues[4] + "\n" +
					"day[5]" + stockValues[5] + "\n" +
					"day[6]" + stockValues[6] + "\n" +
					"day[7]" + stockValues[7] + "\n" +
					"day[8]" + stockValues[8] + "\n" +
					"day[9]" + stockValues[9] + "\n" +
					"day[10]" + stockValues[10] + "\n" +
					"day[11]" + stockValues[11] + "\n" +
					"day[12]" + stockValues[12] + "\n" +
					"day[13]" + stockValues[13] + "\n", Toast.LENGTH_LONG).show();*/
		}
		else
		{
			Toast.makeText(this, "No Stocks Are Currently In Stock Market", Toast.LENGTH_SHORT).show();
			finish();
		}
		
	}
	
	public String userName;
	//private ArrayList<StockMarketStock> stocksList;
	private StocksDB<Stock> stockDB;
	private StocksDB<StockMarketStock> stockMarketDB;
	private Spinner stockSpinner;
	private TextView tbPrice;
	
}
