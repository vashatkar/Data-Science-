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

    
    

		<h3>Property Details</h3>
		<table border="1">
			<tr>  			  
				<td>Bathrooms </td> 
				<td>${estate.getBathroom()}</td>		
			</tr>
			<tr>  			  
				<td>Bedrooms </td> 
				<td>${estate.getBedroom()}</td>		
			</tr>
			<tr>  			  
				<td>Area </td> 
				<td>${estate.getFinal_sqft()} sq. ft.</td>		
			</tr>
			<tr>  			  
				<td>Year </td> 
				<td>${estate.getYear_built()}</td>		
			</tr>
			<tr>  			  
				<td>City </td> 
				<td>${estate.getCity()}</td>		
			</tr>
			<tr>  			  
				<td>State </td> 
				<td>${estate.getState()}</td>		
			</tr>
			<tr>  			  
				<td>Price </td> 
				<td>$ ${estate.getPrice()}</td>		
			</tr>
			<tr>  			  
				<td>Insurance Premium </td> 
				<td>$ ${estate.getInsurancePremium()}</td>		
			</tr>
			<tr>  			  
				<td colspan="2" align="center"><a href="CreateReview?Property_id=<c:out value="${estate.getProperty_id()}"/>">Write a Review</a> </td> 						
			</tr>
			<tr>  			  
				<td colspan="2" align="center"><a href="CreateSiteVisit?Property_id=<c:out value="${estate.getProperty_id()}"/>">Book a site visit</a> </td> 						
			</tr>
	  </table>          
                  
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