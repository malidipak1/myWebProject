<%@page import="com.appsdaily.payment.util.PaymentProperties"%>
<%@page import="java.net.URLEncoder"%>
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
	 String workingKey = PaymentProperties.getEncKeyCcevenue();
	 Enumeration enumeration=request.getAttributeNames();
	 String ccaRequest="", pname="", pvalue="";
	 while(enumeration.hasMoreElements()) {
	      pname = ""+enumeration.nextElement();
	      if(!excludeParams.containsKey(pname)){
		      pvalue = (String)request.getParameter(pname);
		      ccaRequest = ccaRequest + pname + "=" + URLEncoder.encode(pvalue,"UTF-8") + "&";
	      }
	 }
	 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
	 String encRequest = aesUtil.encrypt(ccaRequest);
	%>

	<form id="nonseamless" method="get" name="redirect"
		action="<%=request.getParameter("cancel_url")%>" />
	<input type="hidden" id="encRequest" name="encResp"
		value="<%= encRequest %>">
	<script language='javascript'>document.redirect.submit();</script>
	</form>

</body>
</html>