<%@page import="com.appsdaily.payment.scm.constants.Constants"%>
<%@page import=" com.onward.payment.helpers.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.onward.payment.beans.PayU"%>
<%
	PayU objPayU = (PayU) request.getAttribute("payuObj");
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Post to PayU</title>
</head>
<body>
	<form action="<%=Constants.PAYU_Url %>" method='post'>
		<input type="hidden" name="firstname"
			value="<%=objPayU.getFirstName() %>" /> <input type="hidden"
			name="lastname" value="" /> <input type="hidden" name="surl"
			value="<%=objPayU.getsUrl() %>" /> <input type="hidden" name="phone"
			value="<%=objPayU.getMobile() %>" /> <input type="hidden" name="key"
			value="<%=objPayU.getKey() %>" /> <input type="hidden" name="hash"
			value="<%=objPayU.getHash() %>" /> <input type="hidden" name="curl"
			value="" /> <input type="hidden" name="furl"
			value="<%=objPayU.getfUrl() %>" /> <input type="hidden" name="txnid"
			value="<%=objPayU.getOrderId() %>" /> <input type="hidden"
			name="productinfo" value="<%=objPayU.getProdInfo() %>" /> <input
			type="hidden" name="amount"
			value="<%=Helper.decimalFormatString(objPayU.getAmount()) %>" /> <input
			type="hidden" name="email" value="<%=objPayU.getEmail() %>" /> <input
			type="hidden" name="udf1" value="<%=objPayU.getUdf1() %>" /> <input
			type="hidden" name="udf2" value="<%=objPayU.getUdf2() %>" /> <input
			type="hidden" name="udf3" value="<%=objPayU.getUdf3() %>" /> <input
			type="hidden" name="udf4" value="<%=objPayU.getUdf4() %>" /> <input
			type="hidden" name="udf5" value="<%=objPayU.getUdf5() %>" />

		<!-- Net Banking Parameter-->
		<input type="hidden" name="pg" value="<%=objPayU.getPg() %>" /> <input
			type="hidden" name="bankcode" value="<%=objPayU.getBankCode() %>" />

		<!-- Credit / Debit Card Parameter -->
		<input type="hidden" name="ccnum"
			value="<%=objPayU.getCardNumber() %>" /> <input type="hidden"
			name="ccname" value="<%=objPayU.getCardName() %>" /> <input
			type="hidden" name="ccvv" value="<%=objPayU.getCvvNumber() %>" /> <input
			type="hidden" name="ccexpmon" value="<%=objPayU.getExpiryMonth() %>" />
		<input type="hidden" name="ccexpyr"
			value="<%=objPayU.getExpiryYear() %>" /> <input type="submit"
			value="submit">
	</form>
</body>
</html>