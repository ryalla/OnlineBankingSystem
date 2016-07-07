<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Amount Transfer</title>
<link rel="stylesheet" type="text/css" href="./css/userPage.css" />
 <link rel="stylesheet" type="text/css" href="./css/cheque.css" />
 <link rel="stylesheet" type="text/css" href="./css/amtTransfer.css" /> 
<link rel="stylesheet" href="validatrix/validatrix.css">
<script src="validatrix/validatrix.js"></script>
<script>
$(function(){
    $("#submit-form").click(function(){
      if( validatrix( $("#amTransfer") ) ){
        //alert("Submit Form");
          return true;
      }else{
        console.log("Some fields are required");
      }
      return false; //Prevent form submition
    });
  });
</script>
</head>
<body>
<div class='sidebar' style=" position: relative;
  width: 220px;
  background: #444;" >
       
   <ul class='nav'>
  
      <li>
        <a href="view_balance.do">View Balance</a>
      </li>
      <li>
          <a href="loan.do">Loan</a>
      </li>
      <li>
        <a href="cheque.do">Cheque Deposit</a>
      </li>
      <li>
        <a href="transaction_history.do">Transaction History</a>
      </li>
       <li>
        <a href="amTransfer.do">Fund Transfer</a>
      </li>
      <li>
        <a href="logout.do">Logout</a>
      </li>
    </ul>
  </div>
 
 
  

		
	
    
  <div class="form2">
  <span   style="color:red;">${statusError}</span>
   <span  style="color:green;">${statusSuccess}</span><br>
   <span style="color: #33b5e5; font-family: cursive; font-size:x-large;">Fund Transfer</span>
    <form id="amTransfer" action="amTransferSubmit.do">
    		<!-- <h1>Transfer money anywhere anytime</h1> -->
   <div>
      <input name="account_number" id="account_number" type="text" class="required" placeholder="Account Number"/>
      </div>
      <!-- <input name="account_holder" id="account_holder" type="text" placeholder="Account Holder Name"/> -->
      <div>
      <input name="amount_transfer" id="amount_transfer" type="text" class="required" placeholder="Amount to be transferred"/>
      </div>
        <button type="submit" id="submit-form">Submit</button>
    </form>
    
  </div>


</body>
</html>