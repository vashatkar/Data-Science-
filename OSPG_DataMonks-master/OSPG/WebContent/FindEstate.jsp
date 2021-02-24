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

	<form action="FindEstate" method="post">
		<h1>Search for a Property by Estate Id</h1>
		<p>
			<label for="estateId">Property Id</label>
			<input id="estateId" name="estateId" value="${fn:escapeXml(param.estateId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="CreateEstate"><a href="CreateEstate">Create Estate</a></div>
	<br/>
	<h1>Matching Estate</h1>
        <table border="1">
            <tr>
                <th>Property Id</th>
                <th>Bathrooms</th>
                <th>Bedrooms</th>
                <th>Area(sqft)</th>
                <th>Location(latitude)</th>
                <th>Location(longitude)</th>
                <th>Construction Year</th>
                <th>Location(Zipcode)</th>
                <th>Delete Estate</th>
                <th>Update Estate</th>
            </tr>
            
                <tr>
                    <td>${estate.getProperty_id()}</td>
                    <td>${estate.getBathroom()}</td>
                    <td>${estate.getBedroom()}</td>
                    <td>${estate.getFinal_sqft()}</td>
                    <td>${estate.getLatitude()}</td>
                    <td>${estate.getLongitude()}</td>
                    <td>${estate.getYear_built()}</td>
                    <td>${estate.getZipcode_fk().getZipcode()}</td>
                    <td><a href="DeleteEstate?estateId=<c:out value="${estate.getProperty_id()}"/>">Delete</a></td>
                    <td><a href="UpdateEstate?estateId=<c:out value="${estate.getProperty_id()}"/>">Update</a></td>
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