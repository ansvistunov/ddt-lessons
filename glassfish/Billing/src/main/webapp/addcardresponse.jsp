<%@ page import="com.asw.ws.ex1.endpoint.generated.BillingService,com.asw.ws.ex1.endpoint.generated.Billing,com.asw.ws.ex1.endpoint.generated.Card" %>
<%  
    Card card = new Card();
    try {
      Billing billing = new BillingService().getBillingPort();
      String _cardnumber = request.getParameter("cardnumber");
      String _person = 	  request.getParameter("person");
      
      card.setPerson(_person);
      card.setCardNumber(_cardnumber);
		/*XMLGregorianCalendar dat = javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar();
		dat.setYear(2006);
		dat.setMonth(11);
		dat.setDay(5);
		*/
      
      java.util.Vector<Card> v = new java.util.Vector<Card>();
      v.add(card);
      billing.addNewCard(v);

    } catch (Exception ex) {
        
    }
%>
<h2><font color="black"><%=card.getPerson()+"\t"+card.getBalance()%></font></h2>














