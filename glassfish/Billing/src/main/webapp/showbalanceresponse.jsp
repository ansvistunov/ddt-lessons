<%@ page import="com.asw.ws.ex1.endpoint.generated.BillingService,com.asw.ws.ex1.endpoint.generated.Billing, com.asw.ws.ex1.endpoint.generated.Card" %>
<%  
    Card resp = null;
    try {
      Billing billing = new BillingService().getBillingPort();
      resp = billing.getCard(request.getParameter("cardnumber"));

    } catch (Exception ex) {
        resp = new Card();
    }
%>
<h2><font color="black"><%=resp.getPerson()+"\t"+resp.getBalance()%></font></h2>














