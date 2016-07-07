<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flat Login Form 3.0</title>
<link rel="stylesheet" href="./css/reset.css">
<link rel='stylesheet prefetch'
	href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="validatrix/validatrix.css">
<script src="validatrix/validatrix.js"></script>
<script>
$(function(){
    $("#submit-form").click(function(){
      if( validatrix( $("#loginForm") ) ){
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

	<div class="pen-title">
		<span style="color: #33b5e5; font-family: cursive; font-size:xx-large;">Login</span>
	</div>
	<!-- Form Module-->
	<div class="module form-module">
		<div class="toggle">
			<i class="fa fa-times fa-pencil"></i>
			<div class="tooltip">Click Me</div>
		</div>
		<div class="form">
			<span style="color: #ef6f00; font-family: cursive; font-size: large;">Login to your account</span>
			<span style="padding-top: 15px;"></span>
			<form id="loginForm" action="user_Login.do">
			<div>
				<input type="text" placeholder="Username" class="required" name="userName"/> 
				</div>
				<div>
				<input type="password" placeholder="Password" class="required" name="password"/>
				</div>
				<button type="submit" id="submit-form">Login</button>
			</form>
		</div>
		<div>
			<button onclick="location.href='register.do';">Register</button>
		</div>
		<!--  <div class="cta" style="background-color: #33B5E5;" ></div> -->
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="./js/index.js"></script>
</body>
</html>
