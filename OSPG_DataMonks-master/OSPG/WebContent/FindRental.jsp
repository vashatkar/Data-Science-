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

	<h1>Search for a rental property by Rental Id</h1>
	<form action="FindRental" method="post">		
		<p>
			<label for="rental_Id">Rental Property Id</label>
			<input id="rental_Id" name="rental_Id" value="${fn:escapeXml(param.rental_Id)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="CreateRental"><a href="CreateRental">Create Rental Estate</a></div>
	<br/>
	<h1>Matching Rental</h1>
        <table border="1">
            <tr>
                <th>Rental Id</th>
                <th>bathrooms</th>
                <th>bedrooms</th>
                <th>beds</th>
                <th>host response rate</th>
                <th>latitude</th>
                <th>longitude</th>
                <th>number of reviews?Yes(1)/No(0)</th>
                <th>review scores rating?Yes(1)/No(0)</th>
                <th>hours24 check in?Yes(1)/No(0)</th>
                <th>air conditioning?Yes(1)/No(0)</th>
                <th>bath towel?Yes(1)/No(0)</th>
                <th>bed linens?Yes(1)/No(0)</th>
                <th>body soap?Yes(1)/No(0)</th>
                <th>wireless intercom?Yes(1)/No(0)</th>
                <th>Cable TV?Yes(1)/No(0)</th>
                <th>Cleaning before checkout?Yes(1)/No(0)</th>
                <th>Coffee maker?Yes(1)/No(0)</th>
                <th>Cooking basics?Yes(1)/No(0)</th>
                <th>Dishes?Yes(1)/No(0)</th>
                <th>Ethernet connection?Yes(1)/No(0)</th>
                <th>Free parking on premises?Yes(1)/No(0)</th>
                <th>Free parking on street?Yes(1)/No(0)</th>
                <th>Garden or backyard?Yes(1)/No(0)</th>
                <th>Hot water?Yes(1)/No(0)</th>
                <th>Indoor fireplace?Yes(1)/No(0)</th>
                <th>Luggage drop off allowed?Yes(1)/No(0)</th>
                <th>Paid parking off premises?Yes(1)/No(0)</th>
                <th>Patio or balcony?Yes(1)/No(0)</th>
                <th>Pets allowed?Yes(1)/No(0)</th>
                <th>Self Check In?Yes(1)/No(0)</th>
                <th>Wireless Internet?Yes(1)/No(0)</th>
                <th>Bathtub?Yes(1)/No(0)</th>
                <th>Breakfast?Yes(1)/No(0)</th>
                <th>Dryer?Yes(1)/No(0)</th>
                <th>Elevator?Yes(1)/No(0)</th>
                <th>Gym ?Yes(1)/No(0)</th>
                <th>Heating?Yes(1)/No(0)</th>
                <th>Internet?Yes(1)/No(0)</th>
                <th>Iron?Yes(1)/No(0)</th>
                <th>Kitchen?Yes(1)/No(0)</th>
                <th>Microwave?Yes(1)/No(0)</th>
                <th>Pool?Yes(1)/No(0)</th>
                <th>Refrigerator?Yes(1)/No(0)</th>
                <th>Shampoo?Yes(1)/No(0)</th>
                <th>Stove?Yes(1)/No(0)</th>
                <th>TV?Yes(1)/No(0)</th>
                <th>Washer?Yes(1)/No(0)</th>
                <th>Property type</th>
                <th>Room type</th>
                <th>Bed type</th>
                <th>Cancellation policy</th>
                <th>City</th>
                <th>Location(zipcode)</th>
                <th>Delete Rental</th>
                <th>Update Rental</th>
            </tr>
            
                <tr>
                    <td>${rental.getRental_id()}</td>
                    <td>${rental.getBathrooms()}</td>
					<td>${rental.getBedrooms()}</td>
					<td>${rental.getBeds()}</td>
					<td>${rental.getHost_response_rate()}</td>
					<td>${rental.getLatitude()}</td>
					<td>${rental.getLongitude()}</td>
					<td>${rental.getNumber_of_reviews()}</td>
					<td>${rental.getReview_scores_rating()}</td>
					<td>${rental.getHours24_check_in()}</td>
					<td>${rental.getAir_conditioning()}</td>
					<td>${rental.getBath_towel()}</td>
					<td>${rental.getBed_linens()}</td>
					<td>${rental.getBody_soap()}</td>
					<td>${rental.getWireless_intercom()}</td>
					<td>${rental.getCable_TV()}</td>
					<td>${rental.getCleaning_before_checkout()}</td>
					<td>${rental.getCoffee_maker()}</td>
					<td>${rental.getCooking_basics()}</td>
					<td>${rental.getDishes()}</td>
					<td>${rental.getEtherne_connection()}</td>
					<td>${rental.getFree_parking_on_premises()}</td>
					<td>${rental.getFree_parking_on_street()}</td>
					<td>${rental.getGarden_or_backyard()}</td>
					<td>${rental.getHot_water()}</td>
					<td>${rental.getIndoor_fireplace()}</td>
					<td>${rental.getLuggage_dropoff_allowed()}</td>
					<td>${rental.getPaid_parking_off_premises()}</td>
					<td>${rental.getPatio_or_balcony()}</td>
					<td>${rental.getPets_allowed()}</td>
					<td>${rental.getSelf_Check_In()}</td>
					<td>${rental.getWireless_Internet()}</td>
					<td>${rental.getBathtub()}</td>
					<td>${rental.getBreakfast()}</td>
			<td>${rental.getDryer()}</td>
			<td>${rental.getElevator()}</td>
			<td>${rental.getGym()}</td>
			<td>${rental.getHeating()}</td>
			<td>${rental.getInternet()}</td>
			<td>${rental.getIron()}</td>
			<td>${rental.getKitchen()}</td>
			<td>${rental.getMicrowave()}</td>
			<td>${rental.getPool()}</td>
			<td>${rental.getRefrigerator()}</td>
			<td>${rental.getShampoo()}</td>
			<td>${rental.getStove()}</td>
			<td>${rental.getTV ()}</td>
			<td>${rental.getWasher()}</td>
			<td>${rental.getProperty_type()}</td>
			<td>${rental.getRoom_type()}</td>
			<td>${rental.getBed_type()}</td>
			<td>${rental.getCancellation_policy()}</td>
			<td>${rental.getCity()}</td>
			<td>${rental.getZipcode_fk().getZipcode()}</td>
                    <td><a href="DeleteRental?rental_Id=<c:out value="${rental.getRental_id()}"/>">Delete</a></td>
                    <td><a href="UpdateRental?rental_Id=<c:out value="${rental.getRental_id()}"/>">Update</a></td>
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
