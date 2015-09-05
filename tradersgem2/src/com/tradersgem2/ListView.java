package com.tradersgem2;

import java.util.ArrayList;

import com.tradersgem2.lists.*;
import com.tradersgem2.loginSystem.UserAccount;
import com.tradersgem2.stock.Stock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class ListView extends Activity
{
	
	public ListView()
	{
		//portfolio= new Portfolio(this,);
	}
	/**
	 *  the layout int of the listview
	 * @return integer with the value of the layout listview;
	 */
	public static int getLayout()
	{
		int l=R.layout.listview;
		return l;
	}
	/**
	 * gets the current activity;
	 * @return
	 */
	public static Activity getActivity()
	{
		Activity activity= getActivity();
		
		return activity;
		
	}
	/**
	 * This method takes in the arrayList and TableLayout to set and sets the tableLayout
	 * based on the stock information provided; 
	 * @param stocks
	 * @param listTable
	 * @precondition Assumes all variables being passed as parameters are not null
	 * 
	 */
	public void setTableView(ArrayList<Stock> stocks, TableLayout listTable )
	{
		TextView[] info= new TextView[3];
		TableRow row= new TableRow(this);
		row.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		int i=0,j=0;
		while(i<stocks.size())
        {
        	if (i>0)
        	{
        		// this is a check so to renew the components if the first stock has
        		// already been added to the parent layout view;
        		row= new TableRow(this);
        		info=new TextView[3];
        	}
        	j=0;
        	info[j].setText(""+stocks.get(i).getName());
        	info[j+1].setText(""+stocks.get(i).getPrice());
        	info[j+2].setText(""+stocks.get(i).getPrice());
        	while (j<3)
        	{
        		row.addView(info[j]);
        		j++; // going to the next spot in the array for text View;
        	}
        	listTable.addView(row);
        	i++; // going to the next stock in portfolio;
        }
		
		
		
	}
	/**
	 * The following series of methods refresh the view based on their parameters
	 * The first two are specific to portfolio and watchlist, and then the third 
	 * takes in both and refresh the view for both of them;
	 * @param p
	 * @param listTable
	 */
	public void refreshView(Portfolio p, TableLayout listTable)
	{
		p.refresh();
		setTableView(p.getStocks(),listTable);
	}
	
	public void refreshView(WatchList w, TableLayout listTable)
	{
		w.refresh();
		setTableView(w.getWatchList(),listTable);
	}
	
	public void refreshView (Portfolio p, WatchList w, TableLayout listTableP, TableLayout listTableW, LinearLayout parent)
	{
		p.refresh();
		setTableView(p.getStocks(),listTableP);
		w.refresh();
		setTableView(w.getWatchList(),listTableW);
		
	}
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        bundle= savedInstanceState;
        userName=getIntent().getExtras().getString("userName");
        Log.d("Extras(userName)",""+ userName);
        portfolio= new Portfolio(this,userName);
        watchList= new WatchList(this,userName);
        portfolioView= new TableLayout(this);
        watchListView= new TableLayout(this);
        portfolio.refresh();
        watchList.refresh();
        
        setTableView(portfolio.getStocks(),portfolioView);
        setTableView(watchList.getWatchList(),watchListView);
        
        LinearLayout parent= (LinearLayout) findViewById(R.id.parent);
        
        parent.addView(portfolioView);
        parent.addView(watchListView);
        parent.refreshDrawableState();
        
    }
	
/*	@Override
	protected void onResume()
	{
		
		
	}
	
	@Override
	protected void onRestart()
	{
		
	}
	
	@Override
	protected void onStart()
	{
		
	}*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.listview_menu, menu);
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected( MenuItem item) 
    {
    	switch (item.getItemId())
    	{
    	case R.id.addStockMenuItem:
    		Intent ii= new Intent(this,AddStock.class).putExtra("userName", userName);
    		
    		startActivity ( ii);
    		break;
    		
    	case R.id.graphMenuItem:
    		Intent iii= new Intent(this, TGGraphView.class );
    		
    		startActivity (iii);
    		break;
    		
    	}
    	
    	return true;
    }
    
    
    private Bundle bundle;
    private TableLayout portfolioView;
    private TableLayout watchListView;
    private String userName;
    private Portfolio portfolio;
    private WatchList watchList;

}
