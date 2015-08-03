<%@page import="com.appsdaily.payment.util.PaymentProperties"%>
<%@page import="java.net.URLEncoder"%>
<%
/*
   This is the sample Checkout Page JSP script. It can be directly used for integration with CCAvenue if your application is developed in JSP. You need to simply change the variables to match your variables as well as insert routines (if any) for handling a successful or unsuccessful transaction.
*/
%>
<%@ page import="java.io.*,java.util.*,com.ccavenue.security.*"%>
<html>
<head>
<title>Sub-merchant checkout page</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	<%
	HashMap<String, String> excludeParams = new HashMap<String, String>();
	excludeParams.put("javax.servlet.forward.query_string", "");
	excludeParams.put("javax.servlet.forward.context_path", "");
	excludeParams.put("javax.servlet.forward.servlet_path", "");
	excludeParams.put("javax.servlet.forward.request_uri", "");
	 String accessCode= PaymentProperties.getAccessCodeCcevenue();
	 String workingKey = PaymentProperties.getEncKeyCcevenue();
	 Enumeration enumeration=request.getAttributeNames();
	 String ccaRequest="", pname="", pvalue="";
	 while(enumeration.hasMoreElements()) {
	      pname = ""+enumeration.nextElement();
	      if(!excludeParams.containsKey(pname)){
		      pvalue = (String)request.getAttribute(pname);
		      ccaRequest = ccaRequest + pname + "=" + URLEncoder.encode(pvalue,"UTF-8") + "&";
	      }
	 }
	 System.out.print(ccaRequest);
	 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
	 String encRequest = aesUtil.encrypt(ccaRequest);
	%>

	<form id="nonseamless" method="post" name="redirect"
		action="http://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction" />
	<input type="hidden" id="encRequest" name="encRequest"
		value="<%= encRequest %>">
	<input type="hidden" name="access_code" id="access_code"
		value="<%= accessCode %>">
	<script language='javascript'>document.redirect.submit();</script>
	</form>

</body>
</html>