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


	<h3>Create Estate</h3>
	<form action="CreateEstate" method="post">
		<table border="1">
		<tr>
		   <td> <label for="bathroom">Number of bathrooms</label></td>
			<td><input id="bathroom" name="bathroom" value=""></td>
		</tr>
		<tr>
		   <td><label for="bedroom">Number of bedrooms</label></td>
			<td><input id="bedroom" name="bedroom" value=""></td>
		</tr>
		<tr>
		    <td><label for="final_sqft">Area in square feet</label></td>
			<td><input id="final_sqft" name="final_sqft" value=""></td>
		</tr>
		<tr>
		    <td><label for="latitude">Location(latitude)</label></td>
			<td><input id="latitude" name="latitude" value=""></td>
		</tr>
		<tr>
		    <td><label for="longitude">Location(longitude)</label></td>
			<td><input id="longitude" name="longitude" value=""></td>
		</tr>
		<tr>
		   <td><label for="year_built">Construction year</label></td>
			<td><input id="year_built" name="year_built" value=""></td>
		</tr>
		<tr>
		    <td><label for="Zipcode_fk">Location(zipcode)</label></td>
			<td><input id="Zipcode_fk" name="Zipcode_fk" value=""></td>
		</tr>
		<tr>
		    <td><label for="price">Price</label></td>
			<td><input id="price" name="price" value=""></td>
		</tr>
		<tr>
		    <td><label for="premium">Premium</label></td>
			<td><input id="premium" name="premium" value=""></td>
		</tr>
		
		<tr>
			<td colspan="2"><input type="submit"></td>
		</tr>
	</table>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>

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
		
		
		
		