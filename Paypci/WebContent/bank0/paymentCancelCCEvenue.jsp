<%@page import="com.appsdaily.payment.util.PaymentProperties"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="com.ccavenue.security.AesCryptUtil"%>
<%
		String workingKey = PaymentProperties.getEncKeyCcevenue();    // Put in the Working Key provided by CCAVENUES	
		String encResp= request.getParameter("encResp");
		AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		String decResp = aesUtil.decrypt(encResp);
		/* String decResp = "order_status=Failure&order_id=579&txn_err_msg=Aborted"; */
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
					hs.put(pname, pvalue);
				}
			}
		}
		String errorMesg = null;
	    if(hs.get("order_status") != null && ((String)hs.get("order_status")).equalsIgnoreCase("Failure")){ 
    		errorMesg = (String)hs.get("txn_err_msg");
    	}else if(hs.get("order_status") != null && ((String)hs.get("order_status")).equalsIgnoreCase("Aborted")){
    		errorMesg = (String)hs.get("order_status");
    	} 
	   String url = "";
	    		
	 %>

<%-- <script>
		location.href = "<%= url %>";
	</script>	 --%>
