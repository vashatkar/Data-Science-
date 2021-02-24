<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<!--
	Hielo by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
	<head>
		<title>OSPG</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body class="subpage">

		<!-- Header -->
			<header id="header">
				<div class="logo"><a href="index.jsp">One Stop Property Guide </a></div>
				<a href="#menu">Menu</a>
			</header>

		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li><a href="index.jsp">Home</a></li>
					<li><a href="Options.jsp">Login</a></li>
					<li><a href="EstateLandingPage.jsp">Search Properties To Buy</a></li>
					<li><a href="RentingLandingPage.jsp">Search Properties To Rent</a></li>					
				</ul>
			</nav>

		<!-- One -->
			<section id="One" class="wrapper style3">
				<div class="inner">
					<header class="align-center">
						<p>Best guide to property search</p>
						<h2>Find Your Home</h2>
					</header>
				</div>
			</section>

		<!-- Main -->
			<div id="main" class="container">

	<h3>Zip Code Information</h3>
		<p>			
            <c:if test="${not empty car}">    
            <div><label for="numrentals "> Number of rental cars in the locality :  ${car.getTotal_cars()} </label> </div>
            </c:if> 
            <br>
             
            
    <c:if test="${not empty crime}">             
	<table border="1">
        <tr>
           <th>Different crime types in the area</th>                             
        </tr>	
                
		<c:forEach items="${crime}" var="crime">
	    <tr>                  
	        <td>${crime.getCrime_type()}</td>        
	    </tr>
	   </c:forEach>
   </table> 
   </c:if>
   <br> 
	   
	<c:if test="${not empty univ}">
	<table border="1">
            <tr>
                <th>Schools/Universities in the area</th>                             
            </tr>	
			<c:forEach items="${univ}" var="univ">
    		<tr>
                    
                    <td>${univ.getName()}</td>      
    		</tr>
   			</c:forEach>
      </table>
      </c:if>
      <br>
   
   
   <c:if test="${not empty bus}">
   <table border="1">
      <tr>
         <th>Types of businesses in the area</th>                             
      </tr>
   
	   <c:forEach items="${bus}" var="bus">   
	    <tr>	                    
	      <td>${bus.getBusiness_name()}</td>	                  	         
	    </tr>    
	   </c:forEach>
    </table>
    </c:if>
    <br>

   <c:if test="${not empty weather}">
   <table border="1">
            <tr>
                <th>Month</th>
                <th>Average Temperature(fahrenheit)</th>           
            </tr>
		   <c:forEach items="${weather}" var="weather">	   
		    <tr>	                    
		      <td>${weather.getMonth()}</td>
		      <td>${weather.getAvg_temp()}</td>	         
		    </tr>    
		   </c:forEach> 
   </table>
   </c:if>
   <br>
   
   <c:if test="${not empty trains}">
   <table border="1">
            <tr>
                <th>Nearby Stations for commute</th>                            
            </tr>
   
			<c:forEach items="${trains}" var="trains">		   
			    <tr>			                    
			      <td>${trains.getStaion_name()}</td>			         
			    </tr>    
		    </c:forEach>
   </table>
   </c:if>
   <br>
   

   	<c:if test="${not empty disaster}">
     <table border="1">
            <tr>
                <th>Disaster Information</th>                             
            </tr> 
		   <c:forEach items="${disaster}" var="disaster">	   
		    <tr>		                    
		     <td>${disaster.getDisaster_title()}</td>		         
		    </tr>    
		   </c:forEach>
   </table>
 	</c:if>
 	<br>
	
			</div>

		<!-- Footer -->
			<footer id="footer">
				<div class="container">
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
					</ul>
				</div>
				<div class="copyright">
					&copy; Untitled. All rights reserved.
				</div>
			</footer>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>

	</body>
</html>