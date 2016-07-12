package com.app.helloworldcontroller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.app.bankDao.BankDao;
import com.app.bankDao.impl.BankDaoImpl;
import com.bank.bean.BankUser;
import com.bank.bean.TransactionDetails;

@Controller
public class HelloWorldController {
	//private int acc_no;
	@Autowired(required=true)
	@Qualifier(value="bankDao")
	private BankDao bankDao;
//String role=null;


	@RequestMapping(value="index")
	public String indexPage(Model model)
	{
		System.out.println("In Controller");
		String msg="This is MY Spring FIRST APPLICATION HELLO WORLD PROGRAM";
		model.addAttribute("passvaluetojsp",msg);
		return "index";

	}

	@RequestMapping(value="login")
	public String loginPage(Model model)
	{

		return "login";

	}
	
	@RequestMapping(value="logout")
	public String logout(Model model,HttpSession session)
	{
		session.setAttribute("role",null);
		session.setAttribute("acc_no",null);
System.out.println("RRRRRRRRRRRRRRRRRRRr"+session.getAttribute("role"));


	return "logout";

	}

	@RequestMapping(value="register")
	public String registerPage(Model model)
	{
		return "register";

	}

	@RequestMapping(value="contact")
	public String contactPage(Model model)
	{

		return "contact";

	}

        @RequestMapping(value="cheqeRetreive")
	public @ResponseBody String cheqeRetreive(Model model,HttpServletRequest req,HttpSession session)
	{
		
		int chqNO=Integer.parseInt(req.getParameter("checqueNO"));
		
		String encodeImage=bankDao.getChequeDao(chqNO);
		return encodeImage;

	}
	
	@RequestMapping(value="cheque")
	public String chequePage(Model model)
	{
		return "cheque";

	}

	@RequestMapping(value="amTransfer")
	public String amTransferLoad(Model model)
	{
		return "amTransfer";

	}
	
	@RequestMapping(value="loan")
	public String loanPage(Model model)
	{
		return "loan";

	}
	
        @RequestMapping(value="image")
	public String chequeImagePage(Model model)
	{
		System.out.println("Image Controller");
		return "image";

	}

	//Funtionality
	
	
	@RequestMapping(value="view_balance")
	public String viewBalance(Model model,HttpSession session)
	{
		List<TransactionDetails> transactionDetails=new ArrayList<TransactionDetails>();
		int accno=(int) session.getAttribute("acc_no");
		System.out.println("accono......."+accno);
		transactionDetails=bankDao.userDashboard(accno);
		model.addAttribute("transactionDetails", transactionDetails);
		
		
		return "userPage";

	}
	
	@RequestMapping(value="registrationApproval")
	public String registrationApproval(Model model,HttpSession session)
	{
		List<BankUser> listUser=new ArrayList<BankUser>();
		listUser=bankDao.populateUserApprove();
		model.addAttribute("userApproveList",listUser);
		return "adminLandingPage";

	}
	
	
	@RequestMapping(value="user_Login")
	public String userLogin(Model model,HttpServletRequest req,HttpServletResponse resp,HttpSession session)
	{
		List<BankUser> listUser=new ArrayList<BankUser>();
		List<TransactionDetails> transactionDetails=new ArrayList<TransactionDetails>();
		
		System.out.println("inside userLogin");
		String uname=req.getParameter("userName");
		String pass=req.getParameter("password");
		System.out.println(uname+"||"+pass);
		BankDaoImpl banImpl=new BankDaoImpl();
		String role=banImpl.checkLoginCredentials(uname, pass,session);
		System.out.println("role :"+role);	
		
		if(role==null || "".equals(role))
		{
			model.addAttribute("ERRMSG","UserName or Password is incorrect");
			return "login";
		}
      //  session.putValue("role", role);
      //  session.setAttribute("role", role);		
		String view="";
		if(role.equalsIgnoreCase("admin"))
		{			
			listUser=bankDao.populateUserApprove();
			model.addAttribute("userApproveList",listUser);
			view="adminLandingPage";			
		}
		else if(role.equalsIgnoreCase("user"))
		{
			
			int accno=(int) session.getAttribute("acc_no");
			System.out.println("accono......."+accno);
			transactionDetails=bankDao.userDashboard(accno);
			model.addAttribute("transactionDetails", transactionDetails);
			view="userPage";
		}
		return view;
		
		//return "userPage";
		//return "dummytable";

	}
	
	
	
	@RequestMapping(value="user_register")
	public String userRegistration(Model model,HttpServletRequest req,HttpServletResponse resp)
	{	
		System.out.println(req.getParameter("name")+"||"+req.getParameter("email")+"||"+req.getParameter("phone")+"||"+req.getParameter("address")+"||"+req.getParameter("idType"));
		BankDaoImpl banImpl=new BankDaoImpl();
		String name=req.getParameter("name");
		String password=req.getParameter("pass");
		String email=req.getParameter("email");
		String phone=req.getParameter("phone");
		String address=req.getParameter("address");
		String idType=req.getParameter("idType");
		String idValue=req.getParameter("idValue");
		
		
		
		int  count=banImpl.registerUser(name, password, email, phone, address, idType, idValue);
		
		if(count>0)
		{
			model.addAttribute("SUCCMSG","SuccessFully Registered");
		}
		else
		{
			model.addAttribute("ERRORMSG","Error while Registering ");
		}
		return "register";
	}

	@RequestMapping(value="adminLandingPage")
	public String adminLandingPage(Model model)
	{
		List<BankUser> listUser=new ArrayList<BankUser>();

		try{

		listUser=bankDao.populateUserApprove();

		model.addAttribute("userApproveList",listUser);

		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return "adminLandingPage";

	}
	
	@RequestMapping(value="chequeDeposit")
	public String chequeDeposit(Model model,HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
	{
		String succMsg="";
		String errorMsg="";
		MultipartHttpServletRequest partreq=(MultipartHttpServletRequest) req;
		MultipartFile filePart=partreq.getFile("photo");
		
		String accountNo = req.getParameter("accountNo");
		String cheqNo = req.getParameter("cheqNo");
		String ammoutTransfer = req.getParameter("ammoutTransfer");
		try{

			InputStream inputStream = null;


			

			System.out.println("image:" +filePart);
			if (filePart != null) {
				// debug messages
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
			}
			System.out.println(inputStream);
			int count=bankDao.chequeDeposit(accountNo,cheqNo,ammoutTransfer,inputStream);
			/*model.addAttribute("userApproveList",listUser);*/
			if(count>0)
			{
				succMsg="Cheque Successfully Deposited";
				model.addAttribute("SUCMSG",succMsg);
			}
			else{
				errorMsg="Error while Updating";
				model.addAttribute("ERORMSG",errorMsg);
			}
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return "cheque";

	}
	
	@RequestMapping(value="loanApprove")
		public String loadApprove(Model model,HttpServletRequest req,HttpServletResponse resp,HttpSession session)
	{
		String succMsg="";
		String errorMsg="";
	int accno=(int) session.getAttribute("acc_no");
	System.out.println("accono......."+accno);
	
	int tenure=Integer.parseInt(req.getParameter("tenure"));
	float amount_transfer=Float.parseFloat(req.getParameter("amount_transfer"));	

	int count=bankDao.userLoanRequest(accno,tenure,amount_transfer);
	/*model.addAttribute("transactionDetails", transactionDetails);*/
	/*view="userPage";*/
       if(count==-1){
		errorMsg="Open Loan already exists on same account number";
		model.addAttribute("ERRORMSG",errorMsg);
	}
	else if(count>0)
	{
		 succMsg="Successfully Applied For Loan";
		model.addAttribute("SUCCMSG",succMsg);
	}
	else{
		 errorMsg="Error while Applying For Loan";
		model.addAttribute("ERRORMSG",errorMsg);
	}
	return "loan";
	}
	
	@RequestMapping(value="transaction_history")
	public String trnasactionHistory(Model model,HttpServletRequest req,HttpServletResponse resp,HttpSession session)
	{
		BankDaoImpl daoImpl=new BankDaoImpl();
		System.out.println("acc in tras :"+ session.getAttribute("acc_no").toString());
		//daoImpl.transactionHistory(Integer.parseInt(session.getAttribute("acc_no").toString()));
		//return "register";
		List<TransactionDetails>transactionDetails=new ArrayList<TransactionDetails>();
		try{

			transactionDetails=daoImpl.transactionHistory(Integer.parseInt(session.getAttribute("acc_no").toString()));

			model.addAttribute("transactionDetails",transactionDetails);

			}
			catch(Exception e)
			{
				e.getStackTrace();
			}
			return "transaction_history";

	}

	@RequestMapping(value="amTransferSubmit")
	public String FundTransfer(Model model,HttpServletRequest req,HttpServletResponse resp,HttpSession session)
	{
		System.out.println("inside amtransfer");
		BankDaoImpl banImpl=new BankDaoImpl();
		int account_number=Integer.parseInt(req.getParameter("account_number"));
		System.out.println("inside amtransfer"+account_number);
		String account_holder=req.getParameter("account_holder");
		float amount_transfer=Float.parseFloat(req.getParameter("amount_transfer"));	
		int acc_no=Integer.parseInt(session.getAttribute("acc_no").toString());
		System.out.println("inside amtransfer"+acc_no);		
		String status=banImpl.amountTransfer(account_number, account_holder, amount_transfer,acc_no);
		if (status.contains("Success"))
		model.addAttribute("statusSuccess",status);
		else
			model.addAttribute("statusError",status);
		return "amTransfer";

	}
	
	@RequestMapping(value="chequeApprovalPage")
    public String checkApproveUsers(Model model)
    {
           List<BankUser> cheqApprovList=new ArrayList<BankUser>();
           System.out.println("Entered cheque approval");

           try{

                  cheqApprovList=bankDao.chequApproveList();

           model.addAttribute("cheqApprovList",cheqApprovList);
           System.out.println("Exiting cheque approval");
          
           }
           catch(Exception e)
           {
                  e.getStackTrace();
           }
           return "chequeApprovalPage";

    }
	
	@RequestMapping(value="loanApprovalPage")
    public String loanApproveUsers(Model model)
    {
           List<BankUser> loanApprovList=new ArrayList<BankUser>();
           System.out.println("Entered loan approval");

           try{

        	   loanApprovList=bankDao.loanApproveList();

           model.addAttribute("loanApprovList",loanApprovList);
           System.out.println("Exiting loan approval");

           }
           catch(Exception e)
           {
                  e.getStackTrace();
           }
           return "loanApprovalPage";

    }


	@RequestMapping(value="AproveUser",method=RequestMethod.GET)
    public String AproveUser(Model model,HttpServletRequest req,HttpServletResponse resp)
    {
		System.out.println("inside AproveUser");
		List<BankUser> listUser=new ArrayList<BankUser>();
		String uname=null;
		BankDaoImpl banImpl=new BankDaoImpl();
		
		//System.out.println(req.getParameterValues("uname"));
		
	String[]users =req.getParameterValues("uname");
	for(int i=0;i<users.length;i++){
		uname=users[i];
		banImpl.approveUser(uname);
	}
		
		
		System.out.println(req.getParameter("uname")+"||"+req.getParameter("pass"));
		//banImpl.approveUser(id,id);
		
		listUser=bankDao.populateUserApprove();
		model.addAttribute("userApproveList",listUser);
		
		return "adminLandingPage";
    }
	
	@RequestMapping(value="RejectUser",method=RequestMethod.GET)
    public String rejectUser(Model model,HttpServletRequest req,HttpServletResponse resp)
    {
		System.out.println("inside AproveUser");
		List<BankUser> listUser=new ArrayList<BankUser>();
		String uname=null;
		BankDaoImpl banImpl=new BankDaoImpl();
		
		//System.out.println(req.getParameterValues("uname"));
		
	String[]users =req.getParameterValues("uname");
	for(int i=0;i<users.length;i++){
		uname=users[i];
		banImpl.rejectUser(uname);
	}
		

		listUser=bankDao.populateUserApprove();
		model.addAttribute("userApproveList",listUser);
		
		return "adminLandingPage";
    }
	
	
	@RequestMapping(value="ApproveCheque",method=RequestMethod.GET)
    public String AproveCheque(Model model,HttpServletRequest req,HttpServletResponse resp,HttpSession session)
    {
		System.out.println("inside ApproveCheque");
		 List<BankUser> cheqApprovList=new ArrayList<BankUser>();
		String cnum=null;
		BankDaoImpl banImpl=new BankDaoImpl();
		
		//System.out.println(req.getParameterValues("uname"));
		
	String[]users =req.getParameterValues("cnum");
	for(int i=0;i<users.length;i++){
		cnum=users[i];
		banImpl.approveCheque(Integer.parseInt(cnum),session);
	}
		
	 cheqApprovList=bankDao.chequApproveList();

     model.addAttribute("cheqApprovList",cheqApprovList);
		
		return "chequeApprovalPage";
    }

	@RequestMapping(value="RejectCheque",method=RequestMethod.GET)
    public String RejectCheque(Model model,HttpServletRequest req,HttpServletResponse resp,HttpSession session)
    {
		System.out.println("inside RejectCheque");
		 List<BankUser> cheqApprovList=new ArrayList<BankUser>();
		String cnum=null;
		BankDaoImpl banImpl=new BankDaoImpl();
		
		//System.out.println(req.getParameterValues("uname"));
		
	String[]users =req.getParameterValues("cnum");
	for(int i=0;i<users.length;i++){
		cnum=users[i];
		banImpl.rejectCheque(Integer.parseInt(cnum));
	}
		
	 cheqApprovList=bankDao.chequApproveList();

     model.addAttribute("cheqApprovList",cheqApprovList);
		
		return "chequeApprovalPage";
    }
	
	@RequestMapping(value="ApproveLoan",method=RequestMethod.GET)
    public String ApproveLoan(Model model,HttpServletRequest req,HttpServletResponse resp,HttpSession session)
    {
		System.out.println("inside ApproveCheque");
		 List<BankUser> loanApprovList=new ArrayList<BankUser>();
		String accno=null;
		BankDaoImpl banImpl=new BankDaoImpl();
		
		//System.out.println(req.getParameterValues("uname"));
		
	String[]users =req.getParameterValues("accno");
	for(int i=0;i<users.length;i++){
		accno=users[i];
		banImpl.approveLoan(Integer.parseInt(accno));
	}
	 loanApprovList=bankDao.loanApproveList();

     model.addAttribute("loanApprovList",loanApprovList);
     System.out.println("Exiting loan approval");
		
		return "loanApprovalPage";
    }
	
	@RequestMapping(value="RejectLoan",method=RequestMethod.GET)
    public String RejectLoan(Model model,HttpServletRequest req,HttpServletResponse resp,HttpSession session)
    {
		System.out.println("inside RejectLoan");
		 List<BankUser> loanApprovList=new ArrayList<BankUser>();
		String accno=null;
		BankDaoImpl banImpl=new BankDaoImpl();
		
		//System.out.println(req.getParameterValues("uname"));
		
	String[]users =req.getParameterValues("accno");
	for(int i=0;i<users.length;i++){
		accno=users[i];
		banImpl.rejectLoan(Integer.parseInt(accno));
	}
	 loanApprovList=bankDao.loanApproveList();

     model.addAttribute("loanApprovList",loanApprovList);
     System.out.println("Exiting loan approval");
		
		return "loanApprovalPage";
    }
	
	@RequestMapping(value="home")
	public String home(Model model,HttpServletRequest req,HttpServletResponse resp,HttpSession session)
	{
		List<BankUser> listUser=new ArrayList<BankUser>();
		List<TransactionDetails> transactionDetails=new ArrayList<TransactionDetails>();
		
		System.out.println("inside home");
		
		BankDaoImpl banImpl=new BankDaoImpl();
		
		String role=null;
		if(session.getAttribute("role")!=null)
		role=session.getAttribute("role").toString();
				
		String view="";
		if(role==null)
			view="logout";	
		else if(role.equalsIgnoreCase("admin"))
		{			
			listUser=bankDao.populateUserApprove();
			model.addAttribute("userApproveList",listUser);
			view="adminLandingPage";			
		}
		else if(role.equalsIgnoreCase("user"))
		{
			
			int accno=(int) session.getAttribute("acc_no");
			System.out.println("accono......."+accno);
			transactionDetails=bankDao.userDashboard(accno);
			model.addAttribute("transactionDetails", transactionDetails);
			view="userPage";
		}
		return view;
		
		//return "userPage";
		//return "dummytable";

	}
	


}
@RequestMapping(value="userDeletePage")
    public String deleteUserList(Model model)
    {
           List<BankUser> userDeleteList=new ArrayList<BankUser>();
           System.out.println("Entered Users List");

           try{

        	   userDeleteList=bankDao.userDeleteList();

           model.addAttribute("userDeleteList",userDeleteList);
           System.out.println("Users List");

           }
           catch(Exception e)
           {
                  e.getStackTrace();
           }
           return "userDeletePage";

    }
	
	

    @RequestMapping(value="userDeletePage")
    public String deleteUserList(Model model)
    {
           List<BankUser> userDeleteList=new ArrayList<BankUser>();
           System.out.println("Entered Users List");

           try{

        	   userDeleteList=bankDao.userDeleteList();

           model.addAttribute("userDeleteList",userDeleteList);
           System.out.println("Users List");

           }
           catch(Exception e)
           {
                  e.getStackTrace();
           }
           return "userDeletePage";

    }
	
	@RequestMapping(value="DeleteUser",method=RequestMethod.GET)
    public String DeleteUser(Model model,HttpServletRequest req,HttpServletResponse resp,HttpSession session)
    {
           List<BankUser> userDeleteList=new ArrayList<BankUser>();
           System.out.println("Entered Delete user");
           String accno=null;
   		BankDaoImpl banImpl=new BankDaoImpl();

           try{

        	   
        	   String[]users =req.getParameterValues("accno");
        		for(int i=0;i<users.length;i++){
        			accno=users[i];
        			banImpl.deleteUser(Integer.parseInt(accno));
        		}
        		
        	   userDeleteList=bankDao.userDeleteList();

           model.addAttribute("userDeleteList",userDeleteList);
           System.out.println("Exiting delete user");

           }
           catch(Exception e)
           {
                  e.getStackTrace();
           }
           return "userDeletePage";

    }
	
<<<<<<< HEAD
=======


>>>>>>> refs/remotes/origin/master
}


