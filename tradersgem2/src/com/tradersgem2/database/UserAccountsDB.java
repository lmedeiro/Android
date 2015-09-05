package com.tradersgem2.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.tradersgem2.loginSystem.UserAccount;
import com.tradersgem2.loginSystem.UserAccountsExc;

import android.content.Context;

/**
 * Model for the User Accounts, this class is used to communicate with the DB to fetch information, manage users accounts and a whole and also each
 * user account individually. It allows the creation of new accounts, and provides methods to change the password.
 * @author Pedro Miranda
 *
 */

public class UserAccountsDB implements Iterable<UserAccount>
{
	/**
	 * Construction for creating/loading the user accounts DB.
	 * @param context - The context of the activity that calls this class.
	 * @precondition - The DB is created/loaded from a class that extends activity.
	 * @postcondition - The DB with all users will be loaded and can be accessed through
	 * this class.
	 */
	public UserAccountsDB(Context context)
	{
		listOfUserAccounts = new ArrayList<UserAccount>();
		this.context = context;
		curUser = null;
		loadAccounts();		
	}
	
	/**
	 * Verifies that there is a user account that exists matching the user name and password entered by the user.
	 * @param userName - A String containing the user name the current user.
	 * @param password - A String containing the password the users account.
	 * @return - A boolean value  of true if the user name and password match an account or false if the is no account
	 * that matches the entered credentials. 
	 */
	public boolean verifyCredentials(String userName, String password)
	{
		// Get an iterator for all the accounts in the database
		Iterator<UserAccount> iterator = this.iterator();
		UserAccount account;
		
		// Iterate through each user account searching for a match
		while(iterator.hasNext())
		{
			// Get the new account in the Users Database
			account = iterator.next();
			
			// Check is the credentials match the user account
			if(account.getUserName().equals(userName) && account.getPasword().equals(password))
			{
				// The account was found, stop searching, get the user account, and return true.
				curUser = account;
				return true;
			}
		}
		
		// The account was not found or the password was incorrect, return false.
		return false;
	}
	
	/**
	 * Gets a immutable copy of the current user's account who has logged into the system.
	 * @return - A immutable copy of the current user's account.
	 * @precondtion - verifyCredentials has been called and returned true, meaning there is a user logged into the system.
	 */
	public UserAccount getCurrentUser()
	{
		return (UserAccount)curUser.clone();
	}
	
	/**
	 * Creates a new user account as adds it to the database, granted the user name does not already exist and that
	 * the password and confirm password match.
	 * @param userName - A String containing the desired user name.
	 * @param password - A String containing the desired password.
	 * @param confPassword - A String containing the confirmation of the password entered above. 
	 * @return - A message stating if the new user account was created successfully, the passwords do not match, or the username
	 * already exists.
	 * @throws UserAccountsExc - An exception if an error occurs during the password creation.
	 */
	public boolean createNewAccount(String userName, String password, String confPassword) throws UserAccountsExc
	{
		// Ensure All Fields are filled
		if(userName.equals("") || password.equals("") || confPassword.equals("")) 
			throw new UserAccountsExc("Blank Field!");
		// Ensure password & confirm password match
		else if(!password.equals(confPassword)) 
			throw new UserAccountsExc("Passwords Do Not Match!");
		
		// Make sure username does not already exist
		Iterator<UserAccount> iterator = this.iterator();
		UserAccount account;
		
		while(iterator.hasNext())
		{
			account = iterator.next();
			
			if(account.getUserName().equals(userName) || account.getUserName().equals("stockmarket"))
			{
				throw new UserAccountsExc("User Names Equal Each Other!");
			}	
		}
		
		// New user account is valid add to the list
		curUser = new UserAccount(userName, password);
		listOfUserAccounts.add(curUser);
		
		// Update Account Database
		saveAccounts();		
		
		return true;
	}
	
	/**
	 * Get the number of user accounts in the Database
	 * @return - An integer containing the number of accounts in the Database
	 */
	public int getNumOfAccounts()
	{
		/**
		 * In order to prevent a null size, or incorrect current size
		 * we refresh prior to getting the number of accounts;
		 * 
		 */
		this.refresh();
		return listOfUserAccounts.size();
	}
	
	/**
	 * Reloads all the accounts in the database.
	 */
	public void refresh()
	{
		loadAccounts();
	}
	
	/**
	 * Saves all the accounts in the Database, this it watch allows the data to persist when the user logs off and/or exists the system.
	 */
	private void saveAccounts()
	{
		try
		{
			//ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream("UserAccounts.dat", Context.MODE_PRIVATE));
			//ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream("UserAccounts.dat", Context.MODE_PRIVATE));
			//write.writeObject(listOfUserAccounts);
			//write.close();
			
			FileOutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			ObjectOutputStream write = new ObjectOutputStream(out);
			write.writeObject(listOfUserAccounts);
			write.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Loads all the user accounts in the Database, if the database does not exist is creates a new one.
	 */
	@SuppressWarnings("unchecked")
	private void loadAccounts()
	{
		// Check if file exists, if it does not create it
		try
		{
			FileInputStream inp = context.openFileInput(fileName);				
			ObjectInputStream read = new ObjectInputStream(inp);
			listOfUserAccounts = (ArrayList<UserAccount>)read.readObject();
			//UserAccount[] userAccountArr = (UserAccount[]) read.readObject();
			
			//for(int i=0; i<userAccountArr.length; i++) listOfUserAccounts.add(userAccountArr[i]);
			
			read.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public Iterator<UserAccount> iterator() 
	{
		// TODO Auto-generated method stub
		return Collections.unmodifiableList(listOfUserAccounts).iterator();
	}
	
	// Variables used by the UserAccountsDB
	private ArrayList<UserAccount> listOfUserAccounts;
	private Context context;
	private UserAccount curUser;
	final private String fileName = "UserAccounts.dat";

}
