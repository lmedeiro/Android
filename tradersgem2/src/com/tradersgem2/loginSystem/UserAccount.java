package com.tradersgem2.loginSystem;

import java.io.Serializable;

/**
 * This is the model for an individual user login. This class holds the information for an individual user account, it holds such things as
 * user name and password. This class is immutable and serialized, only the password can be changed and it can only be changed through the setPassword method.
 * @author pedro
 *
 */
public class UserAccount implements Serializable, Cloneable
{
	/**
	 * Constructor for a user account, it takes the desired 
	 * user name and password as its parameters.
	 * @param userName - A String containing the username for the user
	 * @param password - A String containing the password for the user
	 * @precondition - userName != null, password != null, username is unique (database on has on instance of it), userName != "", password != ""
	 * @postcondition - A user account will be created with the userName and password as defined in the Strings.
	 */
	public UserAccount(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}
	
	/**
	 * Gets the current value of the user name string
	 * @return - A string containing the user name.
	 */
	public String getUserName()
	{
		return new String(userName);
	}
	
	/**
	 * Gets the current value of the user's password string
	 * @return - A string containing the user's password.
	 */
	public String getPasword()
	{
		return new String(password);
	}
	
	/**
	 * Changes the current value of the user's password string the new desired value
	 * @param - A string containing the user's new password.
	 * @precondition - Confirm that the user knows the old password, wants the change the password, and knows
	 * the new password.
	 */
	public void setPassword(String password)
	{
		this.password = password; 
	}
	
	/**
	 * Creates a clone of the current user
	 */
	public Object clone()
	{
		try 
		{
			return super.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Used for serialization purposes
	private static final long serialVersionUID = 1L;
	
	// User Account variables
	private String userName;
	private String password;

}
