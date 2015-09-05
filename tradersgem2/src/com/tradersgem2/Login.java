package com.tradersgem2;

import com.tradersgem2.database.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This is the controller for the login screen for current users. This class connects with the Login view to display the UI to the user and it
 * receives commands from the user including Login, Cancel, and Create new user. This Controller also connects to the User Account database to
 * make sure the user account exists and the user's credentials are accurate, if the credentials are correct pass this class passes the user 
 * account to the home screen.
 * @author pedro
 *
 */
public class Login extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);		
		
		// Connect to the Users Database this is used to check and
		//  find user accounts.
		userAccountsDB = new UserAccountsDB(getBaseContext());
				
		// Get panel buttons from the activity_login view
		btLogin = (Button) findViewById(R.id.btLogin);
		btCancel = (Button) findViewById(R.id.btCancel);
		btNewAccount = (Button) findViewById(R.id.btNewUser);
		
		// Create Action Listeners for activity_login view
		btLogin.setOnClickListener(eventHandler);
		btCancel.setOnClickListener(eventHandler);
		btNewAccount.setOnClickListener(eventHandler);		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * On click listener to keep track of user clicks on the activity_login View. 
	 */
	
	
	View.OnClickListener eventHandler = new View.OnClickListener()
	{
		public void onClick(View v)
		{
			// User clicked on the Login button
			if(((Button)v).getId() == btLogin.getId())
			{
				// Get the text from the Username and Password fields
				EditText usr = (EditText) findViewById(R.id.etUserName);
				EditText pass = (EditText) findViewById(R.id.etPassword);
				
				// Verify that the user account exists and that the password is correct
				if(userAccountsDB.verifyCredentials(usr.getText().toString(), pass.getText().toString()))
				{
					Log.d("Account Login", "Account Login Successful!!!");
					
					// Account found, start the home screen activity and pass the current user account to it. 
					Intent intent = new Intent(Login.this, Home.class).putExtra("UserAccount", userAccountsDB.getCurrentUser());
					startActivity(intent);
				}
				else
				{
					// Account login failed
					Log.d("Account Login", "Account Login Failed!!!");
					Toast.makeText(Login.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
				}
			}
			// User clicked on the Cancel button
			else if(((Button)v).getId() == btCancel.getId())
			{
				Log.d("Num Of Accounts", String.valueOf(userAccountsDB.getNumOfAccounts()));
				//userAccount.createNewAccount("Test", "1234", "1234");
				Log.d("Num Of Accounts", String.valueOf(userAccountsDB.getNumOfAccounts()));
				
				// Close Application
				finish();
				System.exit(0);		
				
			}
			// User clicked on the New User Account button
			else if(((Button)v).getId() == btNewAccount.getId())
			{
				//Log.d("New User Account", "New User Account Button Clicked");
				// Start new user account activity so the user can create a new account
				Intent intent = new Intent(Login.this, NewUser.class);
				startActivity(intent);
				
			}
		}
	};
	
	// User Credentials Variables
	UserAccountsDB userAccountsDB;
	
	// UI Variables
	private Button btLogin;
	private Button btCancel;
	private Button btNewAccount;

}
