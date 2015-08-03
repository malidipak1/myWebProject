<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.appsdaily.payment.util.PaymentProperties"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ccavenue.security.AesCryptUtil"%>
<%@page import="java.util.Enumeration"%>
<%
	 String merchantId = (String)request.getAttribute("merchant_id");   
	String accessCode = PaymentProperties.getAccessCodeCcevenue();	// Put in the Access Code provided by CCAVENUES
	String workingKey = PaymentProperties.getEncKeyCcevenue();    // Put in the Working Key provided by CCAVENUES								 
	HashMap<String, String> excludeParams = new HashMap<String, String>();
	excludeParams.put("gateway", "");
	excludeParams.put("subscriberId", "");
	excludeParams.put("orderType", "");
	excludeParams.put("appIds", "");
	excludeParams.put("javax.servlet.forward.query_string", "");
	excludeParams.put("javax.servlet.forward.context_path", "");
	excludeParams.put("javax.servlet.forward.servlet_path", "");
	excludeParams.put("javax.servlet.forward.request_uri", "");
	 Enumeration enumeration=request.getAttributeNames();
	 String ccaRequest="", pname="", pvalue="";
	 while(enumeration.hasMoreElements()) {
	      pname = ""+enumeration.nextElement();
	      pvalue = (String)request.getAttribute(pname);
	      if(!excludeParams.containsKey(pname)){
	      	ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
	      }
	 }
	 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
	 String encRequest = aesUtil.encrypt(ccaRequest);
	%>
<%
	System.out.print("https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction&merchant_id="+merchantId+"&encRequest="+encRequest+"&access_code="+accessCode);
	response.sendRedirect("https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction&merchant_id="+merchantId+"&encRequest="+encRequest+"&access_code="+accessCode+"");
	%>