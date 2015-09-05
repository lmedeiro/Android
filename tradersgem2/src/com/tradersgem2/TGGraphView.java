package com.tradersgem2;

import com.tradersgem2.GraphView.BarGraphView;
import com.tradersgem2.GraphView.GraphView;
import com.tradersgem2.GraphView.GraphViewData;
import com.tradersgem2.GraphView.GraphViewSeries;
import com.tradersgem2.GraphView.LineGraphView;
import com.tradersgem2.database.StocksDB;
import com.tradersgem2.stock.Stock;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
/**
 *  This class takes care of setting up the graph view. It takes information from Stock and shows it in 
 *  a graphical form. 
 * @author Luiz Medeiros
 *
 */
public class TGGraphView extends Activity
{
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graphview_layout);
		Log.d("TGGraphView","entered");
		 GraphViewSeries exampleSeries= 
			       new GraphViewSeries (new GraphViewData[]
			        		{
			        			new GraphViewData(1,2.0d),
			        			new GraphViewData(2,1.5d),
			        			new GraphViewData(3,2.5d),
			        			new GraphViewData(4,4.3d),
			        			new GraphViewData(5,5.3d)
			        			
			        		});
		 
		 
	        //GraphViewStyle colorStyle= new GraphViewStyle(200,200,200);
	        
	        /**
	         * All the data setting before this are just for testing; 
	         * This class should only perform the setting of the graph itself
	         * Beyond that, only picking up the data, and pasing it into a graphical view
	         */
		 	GraphView graphView= new BarGraphView (this, "Bar Demo"); // creating the type of graph
	        graphView.addSeries(exampleSeries); // adding the data to the view 
	        graphView.setHorizontalLabels(null);
	        graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
	        graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
	        //graphView.setScrollable(true);
	        //graphView.setScalable(true);
	        
	        GraphView graphView2= new LineGraphView( this, "Line Demo");
	        graphView2.addSeries(exampleSeries);
	        graphView2.setHorizontalLabels(null);
	        graphView2.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
	        graphView2.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
	        //graphView2.setScrollable(true);
	        //graphView2.setScalable(true);
	        
	        
	        View graph0= findViewById(R.id.graph0);
	        ((ViewGroup) graph0).addView(graphView);
	        Log.d("TGGraphView","graph0 has already been setup");
	        View graph1= findViewById(R.id.graph1);
	        ((ViewGroup) graph1).addView(graphView2);
	        //View graph2= new View(this.getBaseContext());
	        //this.addContentView(graph2,null);
	         
	        /**
	         * Defining the attributes whose information derive from the Intent;
	         */
	        
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
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	   
	 	private String stockClickedId;
		private String userName;
		private String listOfOrigin;
		private StocksDB<Stock> stocksDB;
}
