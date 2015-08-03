
<% 
String mesg = request.getParameter("encResp"); %>
<jsp:forward page="appsdaily">
	<jsp:param name="action" value="confirmOrder" />
	<jsp:param name="encResp" value="<%= mesg%>" />
</jsp:forward>

