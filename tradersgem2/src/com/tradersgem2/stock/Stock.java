package com.tradersgem2.stock;

import java.io.Serializable;
import java.util.Date;

/**
 * This is the model for an individual stock. This class holds the information for an individual user stock, it holds such things stock id,
 * stock name, price, date purchased, quantity, and if it is owned or it part of the watch list. This class is immutable and serialized.
 * @author pedro + Luiz
 *
 */
public class Stock  implements Serializable, Cloneable 
{
	/**
	 * Constructor for a stock.
	 * @param id - An integer representing the unique id of the stock.
	 * @param name - A String representing the unique user name of the stock.
	 * @param price - A float containing the individual unit price of one stock.
	 * @param date - A date containing the purchase date of the stocks.
	 * @param qty - A integer containing the quantity of the stock that was purchased.
	 * @param isOwned - A boolean indicating if the stock is owned or it just on the watch list.
	 * @precondition - The Id & name are unique, id > 0, name != null, name != "", date = today's date, qty > 0, isOwned is correct.
	 * @postcondtion - A new stock with the parameters described in the constructor.
	 */
	public Stock(int id, String name, float price, Date date, int qty, boolean isOwned)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.purchaseDate = date;
		this.quantity = qty;
		this.isOwned = isOwned;
	}
	
	/**
	 * Get the unique Id of the stock.
	 * @return - An integer containing the Id of the stock.
	 */
	public int getId() { return id; }
	
	/**
	 * Gets the unique Name of the stock
	 * @return - A string containing the name of stock
	 */
	public String getName() { return new String(name); }
	
	/**
	 * Gets the price of the stock
	 * @return - A float containing the price of stock
	 */
	public float getPrice() { return price; }
	
	/**
	 * Gets the date that the stock was purchased
	 * @return - A date containing the date that the stock was purchased
	 */
	public Date getPurchaseDate() { return (Date)purchaseDate.clone(); }
	
	/**
	 * Gets the quantity of the stock that was purchased
	 * @return - A integer indicating the amount of stock that was purchased or is on the watchlist
	 */
	public int getQuantity() { return quantity; }
	
	/**
	 * Gets the owned status of the stock
	 * @return - A boolean indicating if the stock is owned or is on the watch list
	 */
	public boolean getOwnedStatus() { return isOwned; }
	/**
	 * Here we are overriding the clone object to allow for cloning of Stock;
	 * This is because one user may have multiple instances of the same stock with different
	 * data . For example purchases at different occasions. 
	 */
	@Override
	public Object clone() 
	{ 
		try 
		{
			return (Object) super.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * Here we are overriding the equals method so to correctly check if 
	 * the Object o is a similar instance of the the current Stock object;
	 * Since every purchase generates a unique id, we check for the id;
	 */
	@Override
	public boolean equals(Object o)
	{
		Stock stock= (Stock) o;
		if (stock==null)
		{
			return false;
		}
		else if(stock.getId()==this.getId())
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	
	// Stock information variables
	private int id;
	private String name;
	private float price;
	private Date purchaseDate;
	private int quantity;
	private boolean isOwned;
	
	// Used for serialization purposes
	private static final long serialVersionUID = 1L;
}
