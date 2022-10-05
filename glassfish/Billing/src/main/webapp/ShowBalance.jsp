<html>
<head><title>BillingService</title></head>
<h2>Enter card number</h2>
<form method="get">
<input type="text" name="cardnumber" size="25">
<p></p>
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</form>

<%
    String cardnumber = request.getParameter("cardnumber");
    if ( cardnumber != null && cardnumber.length() > 0 ) {
%>
    <%@include file="showbalanceresponse.jsp" %>
<%
    }
%>
</body>
</html>
