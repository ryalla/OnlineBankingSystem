<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<link rel="stylesheet" type="text/css" href="./css/jquery.dataTables.css" />
 <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css" /> 
<script src="./js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/styles.css" />
<link rel="stylesheet" type="text/css" href="./css/local.css" />
<script type="text/javascript" src="./js/menu_jquery.js"></script>
<script type="text/javascript" src="./js/jQuery.dataTable.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="./images/favicon.ico" />
<title>ELAN BANKING</title>
</head>
<body>
	<div id="wrapper">
		<div id="head">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-3">
					<div id="primaryLogo">
						<a href="#"> AT&T </a>
					</div>
					<div class="lg-text"><span style="font-size: x-large;">ELAN BANKING</span></div>
				</div>
				<div class="col-md-5"></div>
				<div class="col-md-2"></div>
			</div>
		</div>
		<div id="content-wrapper">

			<div id="bodycontainer">

				<!-- <div class="row">
					<div class="col-md-12 hdbar"></div>
				</div> -->
					<div id="content">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<tiles:insertAttribute name="menu" />
					</div>
					<div class="col-md-2"></div>
				</div>
		
	
				<div class="row padtop">
					<div class="col-md-2"></div>

					<div class="col-md-8">
						<tiles:insertAttribute name="body" />
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
			</div>
			<div id="footer">
			<!-- <div class="row footerbg"> 
				 <div class="col-md-2"></div>
				<div class="col-md-8"> -->
					<tiles:insertAttribute name="footer" />
				</div>
				<!-- <div class="col-md-2"></div> -->
			</div>
			</div>
		</div>

	</div>

</body>
</html>
