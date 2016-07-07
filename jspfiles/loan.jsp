<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loan</title>
<link rel="stylesheet" type="text/css" href="./css/userPage.css" />
<link rel="stylesheet" type="text/css" href="./css/loan.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<!-- Validatrix -->
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
	<div class='sidebar'>

		<ul class='nav'>

			<li><a href="view_balance.do">View Balance</a></li>
			<li><a href="loan.do">Loan</a></li>
			<li><a href="cheque.do">Cheque Deposit</a></li>
			<li><a href="transaction_history.do">Transaction History</a></li>
			<li><a href="amTransfer.do">Fund Transfer</a></li>
			<li><a href="logout.do">Logout</a></li>
		</ul>
	</div>





	<div class="form1">
		<span style="color: green;">${SUCCMSG}</span> <span
			style="color: red;">${ERRORMSG}</span><br> <span
			style="color: #33b5e5; font-family: cursive; font-size: xx-large;">Apply
			for Instant Loan</span>
		<form id="cheque-form" action="loanApprove.do">
			<div>
			<input type="text" class="required" placeholder="Enter Required amount"	name="amount_transfer" />
				</div> 
				<div>
				<input type="text" class="required"	placeholder="Tenure in years" name="tenure" />
				</div>
			<button type="submit" id="submit-form">Submit</button>
		</form>

	</div>


</body>
</html>