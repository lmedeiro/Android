package com.tradersgem2.stock;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
/**
 * The following class creates a stock with random market value and history;
 *  Since there is not enough time to create functions to bring the information directly form the internet,
 *  we are creating this randomly to represent the idea and functions;
 * Furthermore, there is relevant use of this Class in the simulation phase, as random walks through the 
 * market can be created to simulate different scenarios;
 * 
 * @author Pedro + Luiz
 *
 */
public class StockMarketStock extends Stock
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockMarketStock(int id, String name, float price, Date date, int qty, boolean isOwned) 
	{
		super(id, name, price, date, qty, isOwned);
		// TODO Auto-generated constructor stub
		
		stockPriceHistory = new float[totalHistory];
		
		// stock Price History cannot be empty
		stockPriceHistory[daysInHistory++] =  price;
		
		// Record Today's Date
		lastUpdate = date;
		
		updateStockHistory();
		
		// Make sure that today's stock prices matched with today's price
		stockPriceHistory[daysInHistory-1] =  price;
	}
	
	public float[] getStockHistory()
	{
		return stockPriceHistory.clone();
	}
	
	@Override
	public float getPrice()
	{
		updateStockHistory();
		
		return stockPriceHistory[daysInHistory-1];
		
	}
	
	private void updateStockHistory()
	{
		Date todaysDate = new Date();
		
		// If there are not 14 days of history create 14 days
		//  of history
		for(; daysInHistory<totalHistory; daysInHistory++)
			stockPriceHistory[daysInHistory] = getStockValue();
		
		// Update stock price until it is current with Today's Data
		//while(lastUpdate.getMonth() != todaysDate.getMonth() && lastUpdate.getDay() != todaysDate.getDay())
		/*while(lastUpdate.getMonth() != todaysDate.getMonth() && lastUpdate.getDay() != todaysDate.getDay() &&
				lastUpdate.getHours() != todaysDate.getHours() && lastUpdate.getMinutes() != todaysDate.getMinutes())
		{
			// Shift all the days back 1
			for(int i=0; i<totalHistory-1; i++)
				stockPriceHistory[i] = stockPriceHistory[i+1];
			
			// Update todays stock price
			stockPriceHistory[daysInHistory-1] = getStockValue();
		}*/
		
		// Shift all the days back 1
		for(int i=0; i<totalHistory-1; i++)
			stockPriceHistory[i] = stockPriceHistory[i+1];
			
		// Update todays stock price
		stockPriceHistory[daysInHistory-1] = getStockValue();
	}
	
	private float getStockValue()
	{
		Random rand = new Random();
		float value = 0;
		
		// Stocks Prices Increased
		if(rand.nextBoolean())
			value = stockPriceHistory[daysInHistory-1] + (stockPriceHistory[daysInHistory-1] * (1 + rand.nextFloat()));
		// Stock Prices Decreased
		else
			value = stockPriceHistory[daysInHistory-1] - (stockPriceHistory[daysInHistory-1] * (1 + rand.nextFloat()));
		
		// Stocks Prices Increased
		/*if(rand.nextBoolean())
			value = stockPriceHistory[daysInHistory-1] * 10;
		// Stock Prices Decreased
		else
			value = stockPriceHistory[daysInHistory-1] * 0.10f;*/
		
		if(value < 1.00) value = 1.00f;
		else if(value > 1000) value = 1000f;
		
		return value;
	}
	
	private Date lastUpdate;
	private float[] stockPriceHistory;
	private int daysInHistory = 0;
	private final int totalHistory = 14;

}
