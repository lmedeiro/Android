package com.tradersgem2.lists;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.tradersgem2.*;
import com.tradersgem2.database.*;
import com.tradersgem2.stock.*;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * This class manages the adaptation and indirect implementation of the stock list view;
 * It's look and color definition are made through this class
 * @author Luiz + Pedro
 *
 */
public class StockListAdapter extends BaseAdapter implements OnClickListener
{
	/**
	 * This is the Constructor that initiates all the process.
	 * @precondition none of the parameters are null; 
	 * @param a
	 * @param d
	 * @param resLocal
	 * @postcondition attributes activity, data, res and inflater have being defined;
	 */
	public StockListAdapter (Activity a, ArrayList<Stock> d, Resources resLocal)
	{
		activity = a;
		data = d;
		res = resLocal;
		
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	/**
	 * getter for the count of the data List size;
	 *@precondition data is not null;
	 *@return int with size of data list;
	 * 
	 */
	@Override
	public int getCount() 
	{
		if(data.size() <= 0) return 0;
		return data.size();
	}

	/**
	 * Getter for the position of an item;
	 */
	@Override
	public Object getItem(int position) 
	{
		return position;
	}

	@Override
	public long getItemId(int position) 
	{
		// TODO Auto-generated method stub
		return position;
	}
	/**
	 * A inner class that works as a container to the view of data.
	 * @author Pedro + Luiz
	 *
	 */
	public static class ViewHolder
	{
		public TextView text;
		public TextView text1;
		public TextView text2;
		public ImageView image;
	}
	
	/**
	 * Here we get the view and manipulate it. 
	 * We check for the values and based on whether they are positive or not, we 
	 * toggle through the colors of the text;
	 *@precondition all parameters are not null; furthermore , they pertain to the 
	 * target View;
	 * @param position 
	 * @param converView
	 * @param parent
	 * @postcondition view has been properly manipulated and returned;
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View vi = convertView;
		ViewHolder holder;
		
		if(convertView == null)
		{
			vi = inflater.inflate(R.layout.stockitem, null);
			
			holder = new ViewHolder();
			holder.text = (TextView) vi.findViewById(R.id.SI_text);
			holder.text1 = (TextView) vi.findViewById(R.id.SI_text1);
			holder.text2 = (TextView) vi.findViewById(R.id.SI_text2);
			
			vi.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) vi.getTag();
		}
		
		if(data.size() <= 0)
		{
			holder.text.setText("No Data");
		}
		else
		{
			DecimalFormat decimal = new DecimalFormat();
			decimal.setMaximumFractionDigits(2);
			decimal.setMinimumFractionDigits(2);
			
			StocksDB<StockMarketStock> marketStock = new StocksDB<StockMarketStock>(activity.getBaseContext(), "stockmarket");
			
			//Stock stock = null;
			Stock stock = (Stock) data.get(position);
			float purPrice = stock.getPrice();
			float curPrice = marketStock.getStockByName(stock.getName()).getPrice();
						
			holder.text.setText(stock.getName());
			
			if(curPrice - purPrice > 0)
			{
				holder.text1.setTextColor(Color.GREEN);
				holder.text2.setTextColor(Color.GREEN);
				
				holder.text1.setText("+");
				holder.text2.setText("(" + String.valueOf(decimal.format((float)(curPrice/purPrice))) + ")");
			}
			else
			{
				holder.text1.setTextColor(Color.RED);
				holder.text2.setTextColor(Color.RED);
				
				holder.text1.setText("-");
				holder.text2.setText("(" + String.valueOf(decimal.format((float)(purPrice/curPrice))) + ")");
			}
			
			
						
			vi.setOnClickListener(new OnItemClickListener(position));			
		}
		
		// TODO Auto-generated method stub
		return vi;
	}
	
	/**
	 * Defining the actions to be performed onClick;
	 * 
	 */
	@Override
	public void onClick(View arg0) 
	{
		
	}
	/**
	 * 
	 * We define a private class OnItemClickListener, that implements
	 * an onClickListener
	 * We used this to retrieved positions of a clicked item;
	 * @author Pedro + Luiz 
	 *
	 */
	private class OnItemClickListener implements OnClickListener
	{
		private int mPosition;
		
		OnItemClickListener(int position)
		{
			mPosition = position;
		}

		@Override
		public void onClick(View v) 
		{
			ListOfStocksController stockList = (ListOfStocksController) activity;
			
			stockList.onItemClick(mPosition);			
		}
		
	}
	
	private Activity activity;
	private ArrayList<Stock> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	int i=0;
}