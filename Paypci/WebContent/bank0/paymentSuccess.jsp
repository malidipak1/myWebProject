<!DOCTYPE html>
<html lang="en">
<head>
<title>Welcome to AppsDaily - World's First Retail Distribution
	Network for Mobile Apps</title>

<%
String orderStatus = request.getParameter("orderStatus");
String orderType = request.getParameter("orderType");
String orderId = request.getParameter("orderId");
String subId = request.getParameter("subId");
String errMesg = request.getParameter("errMesg");
String appId = request.getParameter("appId");

%>
</head>
<body>
	<!--  Main Navigation -->
	<!--  Main Navigation Ends Here -->

	<!-- Wrap all page content here -->

	<!--  Content Wrap Starts here-->
	<div id="wrap">
		<br />
		<div class="well" style="min-heght: 350px;">

			<h3 class="text-center">Success Page</h3>


			<div class="row text-center">
				<%if(orderStatus != null && !(orderStatus).equalsIgnoreCase("Failure")){ %>
				<%if(orderType != null && (orderType).equalsIgnoreCase("new")){ %>
				<p class="mt5">Register to access your account.</p>
				<p class="mt5">
					<span class="error_message"></span>
				</p>
				</br>
				<p class="mt5">
					<a class="btn btn-default"
						href="edu?type=register&orderID=<%=(orderId)%>">Click to
						Register</a>
				</p>
				<%}%>
				<%}else{ %>
				PaymentFailed
				<%} %>

				<%if(orderStatus != null && !(orderStatus).equalsIgnoreCase("Failure")){ %>
				<script type="text/javascript">
								createJsonObjectOrderSuccess("<%=orderId%>");
								sendOrderSuccessToServer("<%=orderId%>");
								</script>
				<%}else{%>
				<script type="text/javascript">
								createJsonObjectOrderFailed("<%=orderId%>","<%=errMesg%>");
							</script>
				<%} %>

			</div>


		</div>



		<!--  Footer Navigation Starts Here -->
		<!--  Footer Navigation Ends Here -->

	</div>
	<!--  Content Wrap Ends Here -->

	<!--Site Script references -->


	<!--Site Script References -->

	<!--  Footer Scripts -->
	<script>
	!function ($) {
			  $(function(){
			    // carousel demo
			    $('#myCarousel').carousel()
			  })
	}(window.jQuery)
</script>
	<!--  Footer Scripts Ends Here -->


</body>
</html>