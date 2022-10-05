<html>
<head><title>BillingService</title></head>
<h2>Enter card number</h2>
<form method="get">
<input type="text" name="cardnumber" size="25">
<input type="text" name="person" size="25">
<p></p>
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</form>

<%
    String cardnumber = request.getParameter("cardnumber");
    String person = request.getParameter("person");
    if ( cardnumber != null && cardnumber.length() > 0 &&
	 person != null && person.length() > 0) {
%>
    <%@include file="addcardresponse.jsp" %>
<%
    }
%>
</body>
</html>
