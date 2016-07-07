<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/cheque.css" />
<!-- <link rel="stylesheet" href="./css/jquery.dataTables.css"> -->
<!--  <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css" />  -->
<!-- <link rel="stylesheet" type="text/css" href="./css/jquery.dataTables.css" /> -->
 <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script> 
<script type="text/javascript" src="js/jQuery.dataTable.js"></script> 
<script src="./js/jquery-1.11.1.min.js" type="text/javascript"></script>

<script>

function enableText(i,row){

	
	 if(i==1) document.myform.action="DeleteUser.do?accno="+accno;
	
	// if(i==2)  document.myform.action="RejectLoan.do?accno="+accno;
	//  document.myform.action="AproveUser.do?uname="+uname+"&pass="+pass;

	  document.myform.submit();
	}
	



</script>
<title></title>
</head>
<body>


<div class='sidebar' style=" position: relative;
  width: 220px;
  background: #444;color: black" >
       
    <ul class='nav'>
  
      <li>
        <a href="registrationApproval.do">Registration Approval</a>
      </li>
      <li>
        <a href="chequeApprovalPage.do">Cheque Approval</a>
      </li>
      <li>
        <a href="loanApprovalPage.do">Loan Approval</a>
      </li>
      <li>
        <a href="userDeletePage.do">Users List</a>
      </li>      
      <li>
        <a href="logout.do">Logout</a>
      </li>
    </ul>
  </div>




<form name="myform" method="get">
<div id="admin_inf" class="form" style="width:100%;height:auto;overflow: scroll;max-height: 400px "> 

<table id="registration_approval" width="100%" >
					<thead>
						<tr>
							<th></th>
							<th>Account Number</th>
							<th>User Name</th>
						</tr>
					</thead>
					<tbody>
					
										 
			<c:forEach items="${userDeleteList}" var="userDeleteList" varStatus="rowCount">
				
					<tr>
					 <td style="display: none;"><input type="hidden" value="${userDeleteList.account_noAProv}"   id="uname_row${rowCount.count}"></td> 
  		   			   
    			     <td style="display: none;"><input type="hidden" value="${userDeleteList.uname}"  id="pass_row${rowCount.count}"></td>
    						     
    			     
    			     <td><input type="checkbox" name="accno" value="${userDeleteList.account_noAProv}"></td>
    			
    			 
    			     <td class="align_center" id="accno"><c:out value="${userDeleteList.account_noAProv}" ></c:out>
     			     
    			     <td class="align_center"><c:out value="${userDeleteList.uname}"></c:out>
    			       			    
    			</tr>
					</c:forEach>
					</tbody> 
			     
				</table>
				<input type="button" name="Delete" value="Delete"  onclick="enableText(1,'row${rowCount.count}')">
    							</div>
						   
</form>

	
</body>
</html>