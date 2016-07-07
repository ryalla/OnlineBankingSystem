package com.app.bankDao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.app.bankDao.BankDao;
import com.bank.bean.BankUser;
import com.bank.bean.TransactionDetails;

public class BankDaoImpl implements BankDao{
	
	GetConnection getConnection=new GetConnection();
	Connection connection;

	String loginQuery="select * from users where username=? and pass=?";
	String registerQuery=" INSERT INTO registration_details (username,pass,email,phone,address,idType,id) VALUES (?,?,?,?,?,?,?)";
	String userapproveQuery="select * from registration_details";
	String transactionHistoryQuery="select * from transaction_details where account_no=?";
	String selectBalanceQuery="select balance from account_details where account_no=?";
	String amountcreditQuery="Update account_details set balance=? where account_no=?";
	String amountdebitQuery="Update account_details set balance=? where account_no=?";
	String updateTransactionUserQuery="INSERT INTO transaction_details (account_no,transaction_date,transaction_amount,transaction_action) VALUES (?,?,?,?)";
	String loanRequestQuery="insert into loan_details(account_no,loan_amount,tenure,status) values(?,?,?,?)";
	String chequeApproveList="select * from cheque_details";
	String loanApproveList="select l.account_no, l.loan_amount, l.tenure, a.score from loan_details l,account_details a where a.account_no=l.account_no and l.status=?";
	String registrationApproveUserQuery="insert into users(username,pass,role) values(?,?,?)";
	String registrationApproveAccountQuery="insert into account_details(account_no,balance) values(?,?)";
	String selectCreditQuery="select credit_score from account_details where account_no=?";
	String scopeIdentityQuery="select max(account_no) from users";
	String deleteRegistration="delete from registration_details where username=?";
	String userPasswordQuery="select pass from registration_details where username=?";
	String chequeDetails="select account_no, amount from cheque_details where cheque_no=? ";
	String deletechequeDetails="delete from cheque_details where cheque_no=? ";
	String updateLoanDetails="update loan_details set status=? where account_no=? ";
	
	
	@Override
	public String checkLoginCredentials(String userName, String password,HttpSession session){
		// TODO Auto-generated method stub
		String role=""; 
		int acc_no=0;

		try {
			connection=getConnection.getConnection();
			PreparedStatement ps=connection.prepareStatement(loginQuery);
			ps.setString(1,userName);
			ps.setString(2,password);			

			ResultSet rs= ps.executeQuery();
			while (rs.next()){
				role= rs.getString(4);
				acc_no=rs.getInt(3);
				session.setAttribute("acc_no",acc_no);
				session.setAttribute("role", role);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(role+"||"+acc_no);

		return role;
	}
	@Override
	public int registerUser(String name, String password, String email,
			String phone, String address, String idType, String idValue) {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			connection=getConnection.getConnection();
			 
			PreparedStatement ps=connection.prepareStatement(registerQuery);

			ps.setString(1,name);		
			ps.setString(2,password);
			ps.setString(3,email);
			ps.setString(4,phone);
			ps.setString(5,address);
			ps.setString(6,idType);
			ps.setString(7,idValue);
			//ps.setString(8,req.getParameter("role").toString());
			count=ps.executeUpdate();
			System.out.println("updated :"+count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public List<BankUser> populateUserApprove() {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		ArrayList<BankUser> bankuserList=new ArrayList<BankUser>();
		BankUser user=null;


		try {
			connection=getConnection.getConnection();
			PreparedStatement ps=connection.prepareStatement(userapproveQuery);

			rs= ps.executeQuery();
			while (rs.next()){
				user=new BankUser();
				user.setUname(rs.getString(1));
				user.setPass(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPhone(rs.getString(4));
				user.setAddress(rs.getString(5));
				user.setIdType(rs.getString(6));
				user.setIdvalue(rs.getString(7));
				
				bankuserList.add(user);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return bankuserList;
	}

	@Override
	public int chequeDeposit(String accountNo, String cheqNo,
			String ammoutTransfer, InputStream inputStream) {
		// TODO Auto-generated method stub

		Connection con=null;

		PreparedStatement pstmt=null;
		int count=0;
		try
		{
			connection=getConnection.getConnection();
			System.out.println("a connection");
			String sql = "INSERT INTO cheque_details (account_no, cheque_no, amount, image) values (?,?, ?, ?)";

			pstmt=connection.prepareStatement(sql);


			System.out.println("kdjfkdj");
			pstmt.setInt(1, Integer.parseInt(accountNo));
			pstmt.setInt(2, Integer.parseInt(cheqNo));
			pstmt.setFloat(3, Float.parseFloat(ammoutTransfer));
			pstmt.setBlob(4, inputStream);
			System.out.println("Stream :"+inputStream);
			System.out.println("user name "+accountNo);

			count=pstmt.executeUpdate();
			System.out.println(count);

			System.out.println("before result set");

		}
		catch(Exception e)
		{
			System.out.println("Error Msg:"+e.getMessage());

		}


		return count;

	}

	@Override
	public List<TransactionDetails> transactionHistory(int acc_no) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		ArrayList<TransactionDetails> transactionDetails=new ArrayList<TransactionDetails>();
		TransactionDetails trDetails=null;


		try {
			connection=getConnection.getConnection();
			PreparedStatement ps=connection.prepareStatement(transactionHistoryQuery);
			ps.setInt(1,acc_no);
			rs= ps.executeQuery();
			while (rs.next()){
				trDetails=new TransactionDetails();
				trDetails.setAccount_no(rs.getInt(1));
				trDetails.setTransaction_date(rs.getString(2));
				trDetails.setTransaction_amount(rs.getFloat(3));
				trDetails.setTransaction_action(rs.getString(4));			
				transactionDetails.add(trDetails);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return transactionDetails;
	}
	@Override
	public String amountTransfer(int account_number, String account_holder,
			float amount_transfer,int user_acc_no) {
		// TODO Auto-generated method stub
		Date date=new Date();
		float amount=0;
		try {
			connection=getConnection.getConnection();
			
			PreparedStatement ps4=connection.prepareStatement(selectBalanceQuery);

			ps4.setInt(1,user_acc_no);		
			//ps.setString(2,account_holder);
			//ps4.setString(1,amount_transfer);
			
			//ps.setString(8,req.getParameter("role").toString());
			ResultSet rs=ps4.executeQuery();
			while(rs.next())
			 amount=rs.getFloat(1);
			if(amount<amount_transfer)
				return "Fund Transfer Failed";
			
			PreparedStatement ps=connection.prepareStatement(amountdebitQuery);

			ps.setInt(2,user_acc_no);		
			//ps.setString(2,account_holder);
			amount=amount-amount_transfer;
			
			ps.setFloat(1,amount);
			
			//ps.setString(8,req.getParameter("role").toString());
			ps.executeUpdate();
			
			PreparedStatement ps5=connection.prepareStatement(selectBalanceQuery);

			ps5.setInt(1,account_number);		
			//ps.setString(2,account_holder);
			//ps4.setString(1,amount_transfer);
			
			//ps.setString(8,req.getParameter("role").toString());
			ResultSet rs1=ps5.executeQuery();
			while(rs1.next())
				 amount=rs1.getFloat(1);
				
			
	
		//	System.out.println("updated :"+i);
			
			PreparedStatement ps1=connection.prepareStatement(amountcreditQuery);

			ps1.setInt(2,account_number);		
			amount=amount+amount_transfer;
			//ps1.setString(2,account_holder);
			ps1.setFloat(1,amount);
			
			//ps.setString(8,req.getParameter("role").toString());
			ps1.executeUpdate();
			//System.out.println("updated :"+i);
			
			PreparedStatement ps2=connection.prepareStatement(updateTransactionUserQuery);
			
			ps2.setInt(1,user_acc_no);		
			ps2.setString(2,date.toString());
			ps2.setFloat(3,amount_transfer);
			ps2.setString(4,"debit");
			
			//ps.setString(8,req.getParameter("role").toString());
			ps2.executeUpdate();
		//	System.out.println("updated :"+i);
			
			
		PreparedStatement ps3=connection.prepareStatement(updateTransactionUserQuery);
			
		ps3.setInt(1,account_number);		
		ps3.setString(2,date.toString());	
		ps3.setFloat(3,amount_transfer);
		ps3.setString(4,"credit");
			
			//ps.setString(8,req.getParameter("role").toString());
		ps3.executeUpdate();
			
			
			return "Fund Transfer Successful";
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Amount Transfer Failed";

		
	}
	@Override
	public List<TransactionDetails> userDashboard(int accno) {
		// TODO Auto-generated method stub
		
		ResultSet rs=null;
		List<TransactionDetails> transactionDetails=new ArrayList<TransactionDetails>();
		TransactionDetails trDetails=null;


		try {
			connection=getConnection.getConnection();
			PreparedStatement ps=connection.prepareStatement(selectBalanceQuery);
			ps.setInt(1,accno);
			rs= ps.executeQuery();
			while (rs.next()){
				trDetails=new TransactionDetails();
				trDetails.setTransaction_amount(rs.getFloat(1));
				trDetails.setAccount_no(accno);
				transactionDetails.add(trDetails);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return transactionDetails;
	}
	@Override
	public int userLoanRequest(int accno, int tenure, float amount_transfer) {
		// TODO Auto-generated method stub

		Connection con=null;

		PreparedStatement pstmt=null;
		int count=0;
		try
		{
			connection=getConnection.getConnection();
			System.out.println("a connection");

			pstmt=connection.prepareStatement(loanRequestQuery);


			System.out.println("kdjfkdj");
			pstmt.setInt(1, accno);
			pstmt.setFloat(2, amount_transfer);
			pstmt.setInt(3, tenure);
			pstmt.setString(4, "request");

			count=pstmt.executeUpdate();
			System.out.println(count);

			System.out.println("before result set");

		}
		catch(Exception e)
		{
			System.out.println("Error Msg:"+e.getMessage());

		}


		return count;

	}

	@Override
    public List<BankUser> chequApproveList() {
           // TODO Auto-generated method stub
           ResultSet rs=null;
           ArrayList<BankUser> bankuserList=new ArrayList<BankUser>();
           BankUser user=null;


           try {
        	   connection=getConnection.getConnection();
                  PreparedStatement ps=connection.prepareStatement(chequeApproveList);

                  rs= ps.executeQuery();
                  while (rs.next()){
                        user=new BankUser();
                        user.setAccount_noAProv(rs.getInt(1));
                        user.setCheque_noAProv(rs.getInt(2));
                        user.setAmount_Aprov(rs.getInt(3));
                        bankuserList.add(user);
                  }
           } catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           }


           return bankuserList;
    }
	
	@Override
    public List<BankUser> loanApproveList() {
           // TODO Auto-generated method stub
           ResultSet rs=null;
           ArrayList<BankUser> bankuserList=new ArrayList<BankUser>();
           BankUser user=null;


           try {
        	   connection=getConnection.getConnection();
                  PreparedStatement ps=connection.prepareStatement(loanApproveList);
                  ps.setString(1, "request");
             
                  rs= ps.executeQuery();
                  while (rs.next()){
                        user=new BankUser();
                        user.setAccount_noAProv(rs.getInt(1));
                        user.setAmount_Aprov(rs.getInt(2));
                        user.setTenure(rs.getInt(3));
                        user.setCreditScore(rs.getInt(4));
                        bankuserList.add(user);
                  }
           } catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           }


           return bankuserList;
    }
	
	
	@Override
	public String approveUser(String uname) {
		// TODO Auto-generated method stub
		Connection con=null;
String password="";
		PreparedStatement pstmt=null;
		int count=0;
		try
		{
			connection=getConnection.getConnection();
			System.out.println("a connection");
			
			PreparedStatement ps=connection.prepareStatement(userPasswordQuery);
			ps.setString(1, uname);

			ResultSet rs= ps.executeQuery();
			while (rs.next()){
				password=rs.getString(1);
			}
			pstmt=connection.prepareStatement(registrationApproveUserQuery);

			System.out.println("a connection1");
			
			pstmt.setString(1, uname);
			pstmt.setString(2, password);
			pstmt.setString(3, "user");
			//pstmt.setInt(4,213123 );
			System.out.println("a connection2");
			count=pstmt.executeUpdate();
			System.out.println("a connection3");
			System.out.println(count);
			
			pstmt=connection.prepareStatement(scopeIdentityQuery);
			
			int accountNo=0;
			rs=pstmt.executeQuery();
			
			while (rs.next()){
				
				accountNo=rs.getInt(1);
				System.out.println(accountNo);
				
			}

			System.out.println("before result set");
			pstmt=connection.prepareStatement(registrationApproveAccountQuery);
			pstmt.setInt(1, accountNo);
			pstmt.setInt(2, 0);
			count=pstmt.executeUpdate();
			
			pstmt=connection.prepareStatement(deleteRegistration);
			pstmt.setString(1, uname);
			count=pstmt.executeUpdate();
			
			
		}
		catch(Exception e)
		{
			System.out.println("Error Msg:"+e.getMessage());

		}


	
		return null;
	}
	
	@Override
	public String rejectUser(String uname) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
			try
		{
			connection=getConnection.getConnection();
			System.out.println("a connection");
			
			
			pstmt=connection.prepareStatement(deleteRegistration);
			pstmt.setString(1, uname);
			pstmt.executeUpdate();
			
			
		}
		catch(Exception e)
		{
			System.out.println("Error Msg:"+e.getMessage());

		}

		return null;
	}

	
	@Override
	public String approveCheque(int cnum, HttpSession session) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		Float amount_transfer=(float) 0;
		Float amount=(float) 0;
		int account_no=0;
				try
		{
			connection=getConnection.getConnection();
			
			PreparedStatement ps=connection.prepareStatement(chequeDetails);
			ps.setInt(1, cnum);
			
			ResultSet rs= ps.executeQuery();
			while (rs.next()){
				account_no=rs.getInt(1);
				amount_transfer=rs.getFloat(2);
			}
			
			PreparedStatement ps4=connection.prepareStatement(selectBalanceQuery);

			ps4.setInt(1,account_no);		
			//ps.setString(2,account_holder);
			//ps4.setString(1,amount_transfer);
			
			//ps.setString(8,req.getParameter("role").toString());
		    rs=ps4.executeQuery();
			while(rs.next())
			 amount=rs.getFloat(1);
			
			PreparedStatement ps1=connection.prepareStatement(amountcreditQuery);

			ps1.setInt(2,account_no);		
			amount=amount+amount_transfer;
			//ps1.setString(2,account_holder);
			ps1.setFloat(1,amount);
			
			//ps.setString(8,req.getParameter("role").toString());
			ps1.executeUpdate();
			//System.out.println("updated :"+i);
			rejectCheque(cnum);
			
		PreparedStatement pst=connection.prepareStatement(updateTransactionUserQuery);
		Date date=new Date();
			pst.setInt(1,account_no);		
			pst.setString(2,date.toString());
			pst.setFloat(3,amount_transfer);
			pst.setString(4,"cheque credit");
			
			//ps.setString(8,req.getParameter("role").toString());
			pst.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println("Error Msg:"+e.getMessage());

		}

		return null;
	}

	@Override
	public String rejectCheque(int cnum) {
		// TODO Auto-generated method stub

			try
		{
			connection=getConnection.getConnection();
			
			PreparedStatement ps=connection.prepareStatement(deletechequeDetails);
			ps.setInt(1, cnum);
			
			 ps.executeUpdate();
				
			
		}
		catch(Exception e)
		{
			System.out.println("Error Msg:"+e.getMessage());

		}

		return null;
	}
	
	
	public void approveLoan(int accno) {
		// TODO Auto-generated method stub
		
		try
		{
			connection=getConnection.getConnection();
			
			PreparedStatement ps=connection.prepareStatement(updateLoanDetails);
			ps.setString(1, "Approved");
			ps.setInt(2, accno);
			
			 ps.executeUpdate();
				
			
		}
		catch(Exception e)
		{
			System.out.println("Error Msg:"+e.getMessage());

		}

		
		
	}
	
	public void rejectLoan(int accno) {
		// TODO Auto-generated method stub
		
		try
		{
			connection=getConnection.getConnection();
			
			PreparedStatement ps=connection.prepareStatement(updateLoanDetails);
			ps.setString(1, "Rejected");
			ps.setInt(2, accno);
			
			 ps.executeUpdate();
				
			
		}
		catch(Exception e)
		{
			System.out.println("Error Msg:"+e.getMessage());

		}

		
		
	}

	
}
