package com.tradersgem2.loginSystem;

public class UserAccountsExc extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserAccountsExc() { super(); }
	public UserAccountsExc(String message) { super(message); }
	public UserAccountsExc(String message, Throwable cause) { super(message, cause); }
	public UserAccountsExc(Throwable cause) { super(cause); }
}
