package com.tradersgem2;

import java.util.ArrayList;

import com.tradersgem2.database.StocksDB;
import com.tradersgem2.database.TempDB;
import com.tradersgem2.stock.Stock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ItemClickMenu extends Activity implements OnClickListener
{

	/**
	 * Functions that pertain directly to the class *****
	 */
	public ItemClickMenu( )
	{
		
	}
	
	
	
	/**
	 * Automatic or android pertaining functions ****
	 * 
	 */
	
	/**
	 * automatic function that creates the activity. HEre the main things are defined, 
	 * and important elements such as the content view, are set;
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	    setContentView(R.layout.item_click_menu);
	    try
	    {
	    	/**
	    	 * WE are using the try statement to try to get exeptions;
	    	 */
	    	userName = getIntent().getStringExtra("userName");
	    	stocksDB = new StocksDB<Stock>(getBaseContext(), userName);
	    	Log.d("in ItemClickMenu","After userName and stocksDB have been defined;");
	    	Log.d("userName",userName);
	    	//stockClickedId= getIntent().getStringExtra("stockClicked");
	    	/**
	    	 * Since there was trouble using the putExtra method, we are using a file to write the information
	    	 * and thus have the availability to access it later from a different Activity;
	    	 */
	    	//listOfOrigin= getIntent().getStringExtra(listOfOrigin);
	    	TempDB<String> tempDB= new TempDB<String>(getBaseContext());
	    	ArrayList<String> info= new ArrayList<String>();
	    	tempDB.refresh();
	    	info.add(tempDB.getInfo().get(1));
	    	
	    	
	    	Log.d("listOfOrigin",info.get(0));
	    	/*int i=0;
	    	while (i<stocksDB.getStocks().size())
	    	{
	    		if(stocksDB.getStocks().get(i).getId()==stockClickedId)
	    		{
	    			stockClicked= stocksDB.getStocks().get(i);
	    		}
	    		else 
	    			i++;
	    	}*/
	    }
	    catch(NullPointerException e)
	    {
	    	e.printStackTrace();
	    	//String message=e.getLocalizedMessage().toString();
	    	//Log.d("NullPointerExcpetionThrown",message);
	    	Log.d("ItemClickMenu","Extras are null;\n"+ e.toString());
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	    
	    
    	graph= (Button) findViewById(R.id.graphButton);
    	addNote= (Button) findViewById(R.id.noteButton);
    	sim= (Button) findViewById(R.id.simButton);
    	graph.setOnClickListener(this);
    	addNote.setOnClickListener(this);
    	sim.setOnClickListener(this);
    	
    	
	   
	    
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * Below is the implementation of the click listeners, that are waiting for the information of the clicked button
	 * THese will bring about the 
	 */
	
	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.graphButton:
			
			//bring about the graph..
			Intent gI= new Intent(this,TGGraphView.class);
			
			gI.putExtra("userName", userName);
			gI.putExtra("stockClicked", stockClickedId);
			gI.putExtra("listOfOrigin", listOfOrigin);
			startActivity(gI);
			
			break;
			
		case R.id.noteButton:
			
			// bring about the note taking feature...
			Intent gII= new Intent(this, AddNote.class);
			gII.putExtra("userName", userName);
			gII.putExtra("stockClicked", stockClickedId);
			gII.putExtra("listOfOrigin", listOfOrigin);
			
			startActivity(gII);
			
			break;
			
		case R.id.simButton:
			
			//Bring about the simulation feature...
			
			break;
			
			
			
			
		
		}
		
	}
	
	
	
	/**
	 * Attributes of the class ****
	 */
	
	/**
	 * The small menu will have the primary functionas as graph, addNote and Sim;
	 * 
	 */
	private Button graph;
	private Button addNote;
	private Button sim;
	private StocksDB<Stock> stocksDB;
	private Stock stockClicked;
	private String stockClickedId;
	private String userName;
	private String listOfOrigin;
	
}
