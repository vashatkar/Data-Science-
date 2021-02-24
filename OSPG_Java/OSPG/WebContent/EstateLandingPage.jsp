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
		<meta charset="utf-8" />s
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

<div class="datapane">
<div class="leftpane">

<h3>Welcome!</h3>
<br>
<h3>Filter properties : </h3>
<form action = "EstateLandingPage" method = "POST">
   <table border="1">
   <tr>
   <td> <label for="bedroomdropdown">Bedrooms</label> </td>
   <td>
		 <select name = "bedroomdropdown">
            <option value = "1">1</option>
            <option value = "2">2</option>
            <option value = "3" selected>3</option>
            <option value = "4">4</option>
            <option value = "5">5+</option>
         </select>
    </td>     
   </tr>
     
   <tr>
   <td> <label for="bathroomdropdown">Bathrooms</label> </td>
   <td>
		 <select name = "bathroomdropdown">
            <option value = "1" selected>1</option>
            <option value = "2">2</option>
            <option value = "3" selected>3</option>
            <option value = "4">4</option>
            <option value = "5">5+</option>
         </select>
   </td>
   </tr>
   
    <tr>
   <td> <label for="city">City</label> </td>
   <td>
		 <select name = "city">
            <option value = "Boston" selected>Boston</option>
            <option value = "Chicago">Chicago</option>
            <option value = "Los Angeles">Los Angeles</option>
            <option value = "New York">New York</option>
            <option value = "San Francisco">San Francisco</option>
            <option value = "Washington">Washington</option>
         </select>
   </td>
   </tr>
   
   <tr>
   <td colspan="2">
   <input type="hidden" id="hidden" name="hidden" value="${User.getUser_id()}" />
   <input type = "submit" name = "submit" value = "Submit" />
   </td>
   </tr>
   </table>
   </form>
   </div>
   
   <div class="rightpane">
   <c:if test="${estate != null}">
   <h1>Matching Estate</h1>
        <table border="1">
            <tr>
                <th>Click to view</th>
                <th>Property Id</th>
                <th>Bathrooms</th>
                <th>Bedrooms</th>
                <th>Area(sqft)</th>
                <th>Construction Year</th>
                <th>Location(Zipcode)</th>
                <th>City</th>
                <th>State</th>
                <th>Price</th>
                <th>Premium</th>
                <th>Review</th>
                <th>Visit site</th>
            </tr>
   
   <c:forEach items="${estate}" var="estate">
    <tr>
                    <td><a href="ViewEstate?estateId=<c:out value="${estate.getProperty_id()}"/>">view</a></td>
                    <td>${estate.getProperty_id()}</td>
                    <td>${estate.getBathroom()}</td>
                    <td>${estate.getBedroom()}</td>
                    <td>${estate.getFinal_sqft()}</td>
                    <td>${estate.getYear_built()}</td>
                    <td><a href="ViewZipcode?zip=<c:out value="${estate.getZipcode_fk().getZipcode()}"/>&city=<c:out value="${estate.getCity()}"/>&state=<c:out value="${estate.getState()}"/>">${estate.getZipcode_fk().getZipcode()}</a></td>
                    <td>${estate.getCity()}</td>
                    <td>${estate.getState()}</td>
                    <td>$${estate.getPrice()}</td>
                    <td>$${estate.getInsurancePremium()}</td>
                    <td><a href="CreateReview?Property_id=<c:out value="${estate.getProperty_id()}"/>&User_id=<c:out value="${user}"/>">Review</a></td>
                    <td><a href="CreateSiteVisit?Property_id=<c:out value="${estate.getProperty_id()}"/>&User_id=<c:out value="${user}"/>">Visit Site</a></td>
         
    </tr>
   </c:forEach> 
   </table>
   </c:if>
   </div>
</div>   

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


