<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/userPage.css" />
<link rel="stylesheet" type="text/css" href="./css/cheque.css" />
<title>Upload Cheque</title>
<link rel="stylesheet" href="validatrix/validatrix.css">
<script src="validatrix/validatrix.js"></script>
<script>
$(function(){
    $("#submit-form").click(function(){
      if( validatrix( $("#cheque-form") ) ){
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
<!-- <div class='sidebar'> -->
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
 
 
  
    

    
  <div class="form">
    <span style="color: green;">${SUCMSG}</span>
 <span style="color: red;">${ERORMSG}</span><br>
   <span style="color: #33b5e5; font-family: cursive; font-size:xx-large;">Cheque Deposit</span>
   <form id="cheque-form" action="chequeDeposit.do" enctype="multipart/form-data" method="post">
   <div>
       <input type="text" placeholder="Account Number" name="accountNo" class="required"/>
       </div><div>
        <input type="text" placeholder="Cheque Number" name="cheqNo" class="required"/>
        </div><div>
      <input type="text" placeholder="Amount to be transferred" name="ammoutTransfer" class="required">
      </div>
      <input type="file" name="photo">
      <button type="submit" id="submit-form">Submit</button>
    </form>
    
  </div>


</body>
</html>