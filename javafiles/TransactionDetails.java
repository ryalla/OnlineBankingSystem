package com.bank.bean;

public class TransactionDetails {
	private int account_no;
	private String transaction_date;
	private float transaction_amount;
	private String transaction_action;

	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public float getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(float transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public String getTransaction_action() {
		return transaction_action;
	}
	public void setTransaction_action(String transaction_action) {
		this.transaction_action = transaction_action;
	}
	
	
}
