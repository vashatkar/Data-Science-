<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
!DOCTYPE HTML>
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


	<h3>Create Rental</h3>
	<form action="CreateRental" method="post">
		<table border="1">
		<tr>
		    <td><label for="bathrooms">Number of bathrooms</label></td>
			<td><input id="bathrooms" name="bathrooms" value=""></td>
		</tr>
		<tr>
		    <td><label for="bedrooms">Number of bedrooms</label></td>
			<td><input id="bedrooms" name="bedrooms" value=""></td>
		</tr>
		<tr>
		    <td><label for="beds">Number of beds</label></td>
			<td><input id="beds" name="beds" value=""></td>
		</tr>
		<tr>
		    <td><label for="host_response_rate">Host response rate</label></td>
			<td><input id="host_response_rate" name="host_response_rate" value=""></td>
		</tr>
		<tr>
		    <td><label for="latitude">latitude</label></td>
			<td><input id="latitude" name="latitude" value=""></td>
		</tr>
		<tr>
		    <td><label for="longitude">longitude</label></td>
			<td><input id="longitude" name="longitude" value=""></td>
		</tr>
		<tr>
		    <td><label for="number_of_reviews">Number of reviews</label></td>
			<td><input id="number_of_reviews" name="number_of_reviews" value=""></td>
		</tr>
		<tr>
		    <td><label for="review_scores_rating">Review Rating</label></td>
			<td><input id="review_scores_rating" name="review_scores_rating" value=""></td>
		</tr>
		<tr>
		    <td colspan="2"><h4> Please enter 1 for Yes, 0 for No for the following : </h4></td>
		</tr>
		<tr>
		    <td><label for="hours24_check_in">24 hours check in?</label></td>
			<td><input id="hours24_check_in" name="hours24_check_in" value=""></td>
		</tr>
		<tr>
		    <td><label for="air_conditioning">Air conditioning?</label></td>
			<td><input id="air_conditioning" name="air_conditioning" value=""></td>
		</tr>
		<tr>
		    <td><label for="bath_towel">Bath towel?</label></td>
			<td><input id="bath_towel" name="bath_towel" value=""></td>
		</tr>
		<tr>
		    <td><label for="bed_linens">Bed linens?</label></td>
			<td><input id="bed_linens" name="bed_linens" value=""></td>
		</tr>
		<tr>
		    <td><label for="body_soap">Body soap?</label></td>
			<td><input id="body_soap" name="body_soap" value=""></td>
		</tr>
		<tr>
		    <td><label for="wireless_intercom">Wireless intercom?</label></td>
			<td><input id="wireless_intercom" name="wireless_intercom" value=""></td>
		</tr>
		<tr>
		    <td><label for="cable_TV">Cable TV?</label></td>
			<td><input id="cable_TV" name="cable_TV" value=""></td>
		</tr>
		<tr>
		    <td><label for="cleaning_before_checkout">Cleaning before checkout?</label></td>
			<td><input id="cleaning_before_checkout" name="cleaning_before_checkout" value=""></td>
		</tr>
		<tr>
		    <td><label for="coffee_maker">Coffee maker?</label></td>
			<td><input id="coffee_maker" name="coffee_maker" value=""></td>
		</tr>
		<tr>
		    <td><label for="cooking_basics">Cooking basics?</label></td>
			<td><input id="cooking_basics" name="cooking_basics" value=""></td>
		</tr>
		<tr>
		    <td><label for="dishes">Dishes?</label></td>
			<td><input id="dishes" name="dishes" value=""></td>
		</tr>
		<tr>
		    <td><label for="etherne_connection">Ethernet connection?</label></td>
			<td><input id="etherne_connection" name="etherne_connection" value=""></td>
		</tr>
		<tr>
		    <td><label for="free_parking_on_premises">Free parking on premises?</label></td>
			<td><input id="free_parking_on_premises" name="free_parking_on_premises" value=""></td>
		</tr>
		<tr>
		    <td><label for="free_parking_on_street">Free parking on street?</label></td>
			<td><input id="free_parking_on_street" name="free_parking_on_street" value=""></td>
		</tr>
		<tr>
		    <td><label for="garden_or_backyard">Garden or backyard?</label></td>
			<td><input id="garden_or_backyard" name="garden_or_backyard" value=""></td>
		</tr>
		<tr>
		    <td><label for="hot_water">Hot water?</label></td>
			<td><input id="hot_water" name="hot_water" value=""></td>
		</tr>
		<tr>
		    <td><label for="indoor_fireplace">Indoor fireplace?</label></td>
			<td><input id="indoor_fireplace" name="indoor_fireplace" value=""></td>
		</tr>
		<tr>
		    <td><label for="luggage_dropoff_allowed">Luggage dropoff allowed?</label></td>
			<td><input id="luggage_dropoff_allowed" name="luggage_dropoff_allowed" value=""></td>
		</tr>
		<tr>
		    <td><label for="paid_parking_off_premises">Paid parking off premises?</label></td>
			<td><input id="paid_parking_off_premises" name="paid_parking_off_premises" value=""></td>
		</tr>
		<tr>
		    <td><label for="patio_or_balcony">Patio or balcony present?</label></td>
			<td><input id="patio_or_balcony" name="patio_or_balcony" value=""></td>
		</tr>
		<tr>
		    <td><label for="pets_allowed">Pets allowed?</label></td>
			<td><input id="pets_allowed" name="pets_allowed" value=""></td>
		</tr>
		<tr>
		    <td><label for="self_Check_In">self Check In?</label></td>
			<td><input id="self_Check_In" name="self_Check_In" value=""></td>
		</tr>
		<tr>
		    <td><label for="wireless_Internet">Wireless Internet?</label></td>
			<td><input id="wireless_Internet" name="wireless_Internet" value=""></td>
		</tr>
		<tr>
		    <td><label for="bathtub">Bathtub?</label></td>
			<td><input id="bathtub" name="bathtub" value=""></td>
		</tr>
		<tr>
		    <td><label for="breakfast">breakfast?</label></td>
			<td><input id="breakfast" name="breakfast" value=""></td>
		</tr>
		<tr>
		    <td><label for="dryer">Dryer?</label></td>
			<td><input id="dryer" name="dryer" value=""></td>
		</tr>
		<tr>
		    <td><label for="elevator">Elevator?</label></td>
			<td><input id="elevator" name="elevator" value=""></td>
		</tr>
		<tr>
		    <td><label for="gym">Gym?</label></td>
			<td><input id="gym" name="gym" value=""></td>
		</tr>
		<tr>
		    <td><label for="heating">Heating?</label></td>
			<td><input id="heating" name="heating" value=""></td>
		</tr>
		<tr>
		    <td><label for="internet">Internet?</label></td>
			<td><input id="internet" name="internet" value=""></td>
		</tr>
		<tr>
		    <td><label for="iron">Iron?</label></td>
			<td><input id="iron" name="iron" value=""></td>
		</tr>
		<tr>
		    <td><label for="kitchen">Kitchen?</label></td>
			<td><input id="kitchen" name="kitchen" value=""></td>
		</tr>
		<tr>
		    <td><label for="microwave">Microwave?</label></td>
			<td><input id="microwave" name="microwave" value=""></td>
		</tr>
		<tr>
		    <td><label for="pool">Pool?</label></td>
			<td><input id="pool" name="pool" value=""></td>
		</tr>
		<tr>
		    <td><label for="refrigerator">Refrigerator?</label></td>
			<td><input id="refrigerator" name="refrigerator" value=""></td>
		</tr>
		<tr>
		    <td><label for="shampoo">Shampoo?</label></td>
			<td><input id="shampoo" name="shampoo" value=""></td>
		</tr>
		<tr>
		    <td><label for="stove">Stove?</label></td>
			<td><input id="stove" name="stove" value=""></td>
		</tr>
		<tr>
		    <td><label for="tV">TV?</label></td>
			<td><input id="tV" name="tV" value=""></td>
		</tr>
		<tr>
		    <td><label for="washer">Washer?</label></td>
			<td><input id="washer" name="washer" value=""></td>
		</tr>
		<tr><td colspan="2"></td></tr>
		<tr>
		    <td><label for="property_type">Property type?</label></td>
			<td><input id="property_type" name="property_type" value=""></td>
		</tr>
		<tr>
		    <td><label for="room_type">Room type?</label></td>
			<td><input id="room_type" name="room_type" value=""></td>
		</tr>
		<tr>
		    <td><label for="bed_type">Bed type?</label></td>
			<td><input id="bed_type" name="bed_type" value=""></td>
		</tr>
		<tr>
		    <td><label for="cancellation_policy">Cancellation policy?</label></td>
			<td><input id="cancellation_policy" name="cancellation_policy" value=""></td>
		</tr>
		<tr>
		    <td><label for="city">city</label></td>
			<td><input id="city" name="city" value=""></td>
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