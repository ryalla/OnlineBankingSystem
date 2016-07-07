package com.app.bankDao;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.bank.bean.BankUser;
import com.bank.bean.TransactionDetails;

public interface BankDao {
	
	public String checkLoginCredentials(String userName,String password,HttpSession session);
	public int registerUser(String name,String password,String email,String phone,String address, String idType,String idValue);
	public List<BankUser> populateUserApprove();
	public int chequeDeposit(String accountNo, String cheqNo,
			String ammoutTransfer, InputStream inputStream);
	public List<TransactionDetails> transactionHistory(int acc_no);
	public String amountTransfer(int account_number, String account_holder,
			float amount_transfer,int user_acc_no);
	public List<TransactionDetails> userDashboard(int accno);
	public int userLoanRequest(int accno, int tenure, float amount_transfer);
	public List<BankUser> chequApproveList();
	public String approveUser(String uname);
	public List<BankUser> loanApproveList();
	String rejectUser(String uname);
	String approveCheque(int cnum,HttpSession session);
	String rejectCheque(int cnum);
}
