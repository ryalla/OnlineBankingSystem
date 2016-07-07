<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- <link rel="stylesheet" type="text/css" href="./css/userPage.css" /> -->
<link rel="stylesheet" type="text/css" href="./css/cheque.css" />
 <link rel="stylesheet" type="text/css" href="./css/jquery.dataTables.css" />
 <script type="text/javascript" src="./js/jQuery.dataTable.js"></script> 
<script type="text/javascript" src="./js/user_page.js"></script>
<title>Upload Cheque</title>
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
 
 
<div id="transaction_details_new" class="form" style="width:100%;height:auto;overflow: scroll;max-height: 400px "> 
 <span style="color: #33b5e5; font-family: cursive; font-size:xx-large;">Transaction History</span> 
<table  style="width:100%;" >
 <thead>
	  <tr>
	    <th>Account Number</th>
	    <th>Transaction Date</th>
	     <th>Transaction Amount</th>
	     <th>Transaction Action</th>
	  </tr>
  </thead>
  <tbody>
  	<c:forEach items="${transactionDetails}" var="transactionDetails">
	  <tr>
	   <td class="align_center"><c:out value="${transactionDetails.account_no}"></c:out>
	    <td class="align_center"><c:out value="${transactionDetails.transaction_date}"></c:out>
	     <td class="align_center"><c:out value="${transactionDetails.transaction_amount}"></c:out>
	      <td class="align_center"><c:out value="${transactionDetails.transaction_action}"></c:out>
	     
	  </tr>
	  </c:forEach>
  </tbody>
</table>
</div >

</body>
</html>