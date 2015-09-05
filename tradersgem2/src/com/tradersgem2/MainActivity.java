package com.tradersgem2;



//import com.squareup.okhttp.OkHttpClient;
import com.tradersgem2.GraphView.*;
import com.tradersgem2.stock.*;



import android.os.Bundle;
import android.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Main dummy activity. USed for initial testing;
 * @author Luiz 
 *
 */
public class MainActivity extends Activity implements OnClickListener{
	@Override
	public void onClick( View v)
	{
		switch(v.getId())
		{
		case R.id.b1:
			Intent i0= new Intent( this, TGGraphView.class);
			Log.d("started Intent","The intent was started");
			startActivity(i0);
			Log.d("i0","entered activity");
			break;
			
		case R.id.callWatchList: 
			Intent i1= new Intent (this, ListView.class);
			Log.d("WatchList case", "initiated intent");
			Log.d("called", i1.toString());
			startActivity(i1);
			
			break;
			
		}
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View graphCall= (View) findViewById(R.id.b1);
        graphCall.setOnClickListener(this);
        View callWatchList= (View) findViewById(R.id.callWatchList);
        callWatchList.setOnClickListener(this);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


   
    

      
    
    
}
