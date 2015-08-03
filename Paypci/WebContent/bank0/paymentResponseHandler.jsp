<%@page import="com.appsdaily.payment.util.PaymentProperties"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.util.*,com.ccavenue.security.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Response Handler</title>
<style>
label {
	font-weight: normal;
}

.account-tabs {
	border-bottom: 0 !important;
}
</style>
</head>

<body>
	<%
		String workingKey = PaymentProperties.getEncKeyCcevenue();
		String encResp= request.getParameter("encResp");
		AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		String decResp = aesUtil.decrypt(encResp);
		StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
		Hashtable hs=new Hashtable();
		String pair=null, pname=null, pvalue=null;
		while (tokenizer.hasMoreTokens()) {
			pair = (String)tokenizer.nextToken();
			if(pair!=null) {
				StringTokenizer strTok=new StringTokenizer(pair, "=");
				pname=""; pvalue="";
				if(strTok.hasMoreTokens()) {
					pname=(String)strTok.nextToken();
					if(strTok.hasMoreTokens())
						pvalue=(String)strTok.nextToken();
					hs.put(pname, URLDecoder.decode(pvalue));
				}
			}
		}
	%>
	<!-- Wrap all page content here -->
	<div id="wrap">
		<!-- Begin page content -->
		<div class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a href="." class="navbar-brand"><img
						src="https://s3-ap-southeast-1.amazonaws.com/appsdwcom/assets/websiteimages-v2/logo.png"></a>
				</div>
			</div>
		</div>
		<div class="center-container">
			<!-- Partner descripiton-->

			<div class="col-sm-12 pad0">

				<div class="panel-default">

					<div class="panel-body">
						<div class="col-sm-12 well">
							<%if(((String)hs.get("order_status")).equalsIgnoreCase("Success")) {%>
							<h3>Thank you for your order</h3>
							<div class="col-sm-12 well">
								<h4>Invoice Details</h4>
								<%Date newDate = new Date(); %>
								<ul class="list-group">
									<li class="list-group-item"><strong>Order No :</strong> <%=hs.get("order_id")%></li>
									<li class="list-group-item"><strong>Delivery Date
											:</strong> Immediate</li>
									<li class="list-group-item"><strong>Order Date :</strong>
										<%=newDate%></li>
									<li class="list-group-item"><strong>Payment
											Status :</strong> Payment Successfully Received</li>

								</ul>
							</div>
							<%}else{ %>
							<script>
			window.location.href = "appsdailyPaymentPage.jsp?order_id=<%=hs.get("order_id")%>&amount=<%=hs.get("amount")%>&error_msg=<%=hs.get("order_status")%>";
		</script>
							<%} %>
							<%-- <%
				Enumeration enumeration = hs.keys();
				while(enumeration.hasMoreElements()) {
					pname=""+enumeration.nextElement();
					pvalue=""+ hs.get(pname);
			%>
				<tr>
					<td><%= pname %> </td>
					<td> <%= pvalue %> </td>
				</tr>
			<%
				}
		%> --%>
						</div>
						<!-- Tabs -->
						<div class="clearfix"></div>
						<div id="footer">
							<div class="col-sm-12">
								<p class="footer-blurb">
									<span style="color: #d71921;">APPSDAILY.</span> MAKE EVERY DAY
									BETTER
								</p>
								<ul style="position: relative; top: 5px" class="scl-icons">
									<li class="scl-facebook"><a target="_blank"
										href="https://www.facebook.com/appsdailyworld"
										data-lbl="facebook" title="AppsDaily on Facebook">Facebook</a></li>
									<li class="scl-linkedin"><a
										href="http://www.linkedin.com/company/appsdaily-solutions-pvt-ltd-"
										data-lbl="linkedIn" target="_blank"
										title="AppsDaily on LinkedIn">LinkedIn</a></li>
									<!-- <li class="scl-twitter"><a title="Follow AppsDaily on Twitter" data-lbl="twitter" href="#">Twitter</a></li> -->
									<li class="scl-googleplus"><a
										href="http://google.com/+appsdailymumbai"
										data-lbl="google plus" target="_blank"
										title="Follow AppsDaily on Google+">Google+</a></li>
									<li class="scl-youtube"><a
										href="https://www.youtube.com/channel/UCvEq7hRGW-mOgGeNjIdHzUQ/videos"
										data-lbl="youtube" target="_blank"
										title="Watch AppsDaily on YouTube">YouTube</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>