package com.tradersgem2;

import com.tradersgem2.Home;
import com.tradersgem2.R;
import com.tradersgem2.database.UserAccountsDB;
import com.tradersgem2.loginSystem.UserAccountsExc;

//import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This is the controller for the new user screen. This class connects with the new user view to display the UI for creating a new user account
 * and it receives commands from the user including Create Account and Cancel. This Controller also connects to the User Account database to
 * make sure the new user account does not exists and the user's credentials are acccurate, if the new account is created this class passes the
 * new user account to the home screen.
 * @author pedro + Luiz 
 *
 */
public class NewUser extends Activity 
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);	
	    setContentView(R.layout.activity_newuser);
	    
	    // Connect to the Users Database this is used to check and
	    //  find user accounts.
	    userAccountsDB = new UserAccountsDB(getBaseContext());
	    
	    // Get panel buttons from the activity_newuser view
		btCreate = (Button) findViewById(R.id.NU_BtCreate);
	    btCancel = (Button) findViewById(R.id.NU_BtCancel);
	    
	    // Create Action Listeners for activity_login view
		btCreate.setOnClickListener(eventHandler);
	    btCancel.setOnClickListener(eventHandler);
	}
	
	/**
	 * On click listener to keep track of user clicks on the activity_newuser View. 
	 */
	View.OnClickListener eventHandler = new View.OnClickListener()
	{
		/**
		 *  here is the implementation of the ananonimous function. 
		 */
		public void onClick(View v)
		{			
			// User clicked on the Create button
			if(((Button)v).getId() == btCreate.getId())
			{
				// Get the text from the Username, Password, and Confirm Password  fields
				EditText usr = (EditText) findViewById(R.id.NU_EtUserName);
				EditText pass = (EditText) findViewById(R.id.NU_EtPassword);
				EditText passConf = (EditText) findViewById(R.id.NU_EtConfPassword);
				
				try 
				{
					// Verify that the user account exists and that the password is correct, if it is create the new account
					if(userAccountsDB.createNewAccount(usr.getText().toString(), pass.getText().toString(), passConf.getText().toString()))
					{
						Log.d("New User", "Account Creation Successful!!!");
						Log.d("Num Of Accounts", String.valueOf(userAccountsDB.getNumOfAccounts()));
						
						// New Account Created, start the home screen activity and pass the new user account to it. 
						Intent intent = new Intent(NewUser.this, Home.class).putExtra("UserAccount", userAccountsDB.getCurrentUser());
						startActivity(intent);
					}
					else
					{
						// New Account creation failed
						Log.d("New User", "Account Creation Failed!!!");
						Log.d("Information", "Account Creation Failed!!!");
						Log.d("cration", "FAILEDDDD!!");// :'( 
						
					}
				} catch (UserAccountsExc e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(NewUser.this, "Username already exist or passwords do not match!", Toast.LENGTH_SHORT).show();
				}
				Log.d("Create Clicked", usr.getText().toString() + "\n" + pass.getText().toString() + "\n" + passConf.getText().toString());
				
			}
			// User clicked on the Cancel button
			else if(((Button)v).getId() == btCancel.getId())
			{
				// Close Application
				Log.d("Num Of Accounts", String.valueOf(userAccountsDB.getNumOfAccounts()));
				Log.d("Cancel Clicked", "Cancel Button Clicked");
				finish();		
				
			}
		}
	};
	
	private UserAccountsDB userAccountsDB;	
	
	private Button btCreate;
	private Button btCancel;

}
