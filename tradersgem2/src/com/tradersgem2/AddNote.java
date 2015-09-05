package com.tradersgem2;

import com.tradersgem2.database.StocksDB;
import com.tradersgem2.stock.Stock;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This class controls the view and aggregation of a note to a particular stock in that the user chooses 
 * to click upon 
 * @precondition is the intent is correctly called for this class to be created;
 * 
 * @author Luiz 
 *
 */
public class AddNote extends Activity implements OnClickListener
{

	
	public AddNote()
	{
		
	}
	/**
	 * Below are the generated or Override Methods;
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_note);
		Log.d("AddNote","entered");
		/**
		 * Below we are trying to get all the extras to find out which stock to add the note to;
		 */
		
		addNoteButton= (Button)findViewById(R.id.addNoteButton);
		addNoteButton.setOnClickListener(this);
		try
	    {
        	
        	userName = getIntent().getStringExtra("userName");
        	Log.d("userName",userName);
        	
	    	stocksDB = new StocksDB<Stock>(getBaseContext(), userName);
	    	
	    	stockClickedId= getIntent().getStringExtra("stockClicked");
	    	Log.d("stockClikedId",stockClickedId);
	    	
	    	
	    	listOfOrigin= getIntent().getStringExtra(listOfOrigin);
	    	Log.d("listOfOrigin",listOfOrigin);
	    }
        catch(NullPointerException e)
        {
        	String message=e.getLocalizedMessage().toString();
	    	Log.d("NullPointerExcpetionThrown",message);
	    	Log.d("TGGraphView","Extras are null");
        }
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.addNoteButton:
			
			// add the note...
			
			finish();
			break;
		
		}
		
	}
	
	/**
	 * 
	 * Defining Attributes;
	 */
	
	private String stockClickedId;
	private String userName;
	private String listOfOrigin;
	private StocksDB<Stock> stocksDB;
	private Button addNoteButton;
	
}
