<html>

<link rel="stylesheet" type="text/css" href="./css/styles.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
	<script src="js/slides.min.jquery.js"></script>
	
	<script>
		$(function(){
			$('#slides').slides({
				preload: true,
				preloadImage: 'images/loading.gif',
				play: 5000,
				pause: 500,
				hoverPause: true
			});
		});
	</script>
 <body>
 <div align="right"style="font-size: 18px; padding-bottom: 30px;">
<%---<span style="background-color: #ef6f00;"><a href="login.do">Login</a><a href="register.do">/Signup</a></span>---%>
<span style="background-color: black;"><a href="login.do">Login</a></span>   <span style="background-color: red"> <a href="register.do">Signup</a></span>


</div>

    <div id="slides">
 <div class="slides_container">
  	<%--<img src="./images/banner3.png" width="960" height="300" alt="baner">  
   
  <img src="./images/banner2.png" width="960" height="300" alt="baner">
   <img src="./images/banner1.jpg" width="960" height="300" alt="baner">---%>
<img src="./images/pic1.jpg" width="960" height="300" alt="baner">  
   <img src="./images/pic2.jpg" width="960" height="300" alt="baner">  
   <img src="./images/pic3.png" width="960" height="300" alt="baner">
   <img src="./images/pic4.png" width="960" height="300" alt="baner">
   <img src="./images/pic6.jpg" width="960" height="300" alt="baner">
  <img src="./images/pic5.jpg" width="960" height="300" alt="baner"> 


    </div>
</div>

<div  align="right"style="font-size: 15px; padding-top: 300px;">
  
   <p>Elan Banking System is one of the leading banks in US. Frequently comes with exciting offers, customer centric...</p>
   <%--<a href=""><span class="button">Read More</span></a>---%>


</div>
   
   
 </body>

   
 </html>