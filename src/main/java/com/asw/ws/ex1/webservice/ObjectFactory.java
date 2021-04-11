
package com.asw.ws.ex1.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.asw.ws.ex1.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddMoney_QNAME = new QName("http://endpoint.ex1.ws.asw.com/", "addMoney");
    private final static QName _AddMoneyResponse_QNAME = new QName("http://endpoint.ex1.ws.asw.com/", "addMoneyResponse");
    private final static QName _AddNewCard_QNAME = new QName("http://endpoint.ex1.ws.asw.com/", "addNewCard");
    private final static QName _AddNewCardResponse_QNAME = new QName("http://endpoint.ex1.ws.asw.com/", "addNewCardResponse");
    private final static QName _GetCard_QNAME = new QName("http://endpoint.ex1.ws.asw.com/", "getCard");
    private final static QName _GetCardResponse_QNAME = new QName("http://endpoint.ex1.ws.asw.com/", "getCardResponse");
    private final static QName _ProcessOperation_QNAME = new QName("http://endpoint.ex1.ws.asw.com/", "processOperation");
    private final static QName _ProcessOperationResponse_QNAME = new QName("http://endpoint.ex1.ws.asw.com/", "processOperationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.asw.ws.ex1.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddMoney }
     * 
     */
    public AddMoney createAddMoney() {
        return new AddMoney();
    }

    /**
     * Create an instance of {@link AddMoneyResponse }
     * 
     */
    public AddMoneyResponse createAddMoneyResponse() {
        return new AddMoneyResponse();
    }

    /**
     * Create an instance of {@link AddNewCard }
     * 
     */
    public AddNewCard createAddNewCard() {
        return new AddNewCard();
    }

    /**
     * Create an instance of {@link AddNewCardResponse }
     * 
     */
    public AddNewCardResponse createAddNewCardResponse() {
        return new AddNewCardResponse();
    }

    /**
     * Create an instance of {@link GetCard }
     * 
     */
    public GetCard createGetCard() {
        return new GetCard();
    }

    /**
     * Create an instance of {@link GetCardResponse }
     * 
     */
    public GetCardResponse createGetCardResponse() {
        return new GetCardResponse();
    }

    /**
     * Create an instance of {@link ProcessOperation }
     * 
     */
    public ProcessOperation createProcessOperation() {
        return new ProcessOperation();
    }

    /**
     * Create an instance of {@link ProcessOperationResponse }
     * 
     */
    public ProcessOperationResponse createProcessOperationResponse() {
        return new ProcessOperationResponse();
    }

    /**
     * Create an instance of {@link Card }
     * 
     */
    public Card createCard() {
        return new Card();
    }

    /**
     * Create an instance of {@link CardOperation }
     * 
     */
    public CardOperation createCardOperation() {
        return new CardOperation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMoney }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddMoney }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.ex1.ws.asw.com/", name = "addMoney")
    public JAXBElement<AddMoney> createAddMoney(AddMoney value) {
        return new JAXBElement<AddMoney>(_AddMoney_QNAME, AddMoney.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddMoneyResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddMoneyResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.ex1.ws.asw.com/", name = "addMoneyResponse")
    public JAXBElement<AddMoneyResponse> createAddMoneyResponse(AddMoneyResponse value) {
        return new JAXBElement<AddMoneyResponse>(_AddMoneyResponse_QNAME, AddMoneyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewCard }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddNewCard }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.ex1.ws.asw.com/", name = "addNewCard")
    public JAXBElement<AddNewCard> createAddNewCard(AddNewCard value) {
        return new JAXBElement<AddNewCard>(_AddNewCard_QNAME, AddNewCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewCardResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddNewCardResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.ex1.ws.asw.com/", name = "addNewCardResponse")
    public JAXBElement<AddNewCardResponse> createAddNewCardResponse(AddNewCardResponse value) {
        return new JAXBElement<AddNewCardResponse>(_AddNewCardResponse_QNAME, AddNewCardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCard }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCard }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.ex1.ws.asw.com/", name = "getCard")
    public JAXBElement<GetCard> createGetCard(GetCard value) {
        return new JAXBElement<GetCard>(_GetCard_QNAME, GetCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCardResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCardResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.ex1.ws.asw.com/", name = "getCardResponse")
    public JAXBElement<GetCardResponse> createGetCardResponse(GetCardResponse value) {
        return new JAXBElement<GetCardResponse>(_GetCardResponse_QNAME, GetCardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessOperation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProcessOperation }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.ex1.ws.asw.com/", name = "processOperation")
    public JAXBElement<ProcessOperation> createProcessOperation(ProcessOperation value) {
        return new JAXBElement<ProcessOperation>(_ProcessOperation_QNAME, ProcessOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessOperationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProcessOperationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://endpoint.ex1.ws.asw.com/", name = "processOperationResponse")
    public JAXBElement<ProcessOperationResponse> createProcessOperationResponse(ProcessOperationResponse value) {
        return new JAXBElement<ProcessOperationResponse>(_ProcessOperationResponse_QNAME, ProcessOperationResponse.class, null, value);
    }

}
