package com.bank.bean;

public class BankUser {

	private String uname;
	private String pass;
	private String email;
	private String phone;
	private String address;
	private String idType;
	private String idvalue;
	
	private byte[] itemImage;
	
	private int account_noAProv;
	private int cheque_noAProv;
	private int  amount_Aprov;
	
	private int  tenure;
	private int  creditScore;
	
	public int getAccount_noAProv() {
		return account_noAProv;
	}
	public void setAccount_noAProv(int account_noAProv) {
		this.account_noAProv = account_noAProv;
	}
	public int getCheque_noAProv() {
		return cheque_noAProv;
	}
	public void setCheque_noAProv(int cheque_noAProv) {
		this.cheque_noAProv = cheque_noAProv;
	}
	public int getAmount_Aprov() {
		return amount_Aprov;
	}
	public void setAmount_Aprov(int amount_Aprov) {
		this.amount_Aprov = amount_Aprov;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdvalue() {
		return idvalue;
	}
	public void setIdvalue(String idvalue) {
		this.idvalue = idvalue;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	public byte[] getItemImage() {
		return itemImage;
	}
	public void setItemImage(byte[] itemImage) {
		this.itemImage = itemImage;
	}



	
}
