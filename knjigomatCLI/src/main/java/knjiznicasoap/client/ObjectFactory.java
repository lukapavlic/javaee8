
package knjiznicasoap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the knjiznicasoap.client package. 
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

    private final static QName _VrniVse_QNAME = new QName("http://soap.knjiznica.javaee.feri.um.si/", "vrniVse");
    private final static QName _ShraniResponse_QNAME = new QName("http://soap.knjiznica.javaee.feri.um.si/", "shraniResponse");
    private final static QName _VrniVseResponse_QNAME = new QName("http://soap.knjiznica.javaee.feri.um.si/", "vrniVseResponse");
    private final static QName _Shrani_QNAME = new QName("http://soap.knjiznica.javaee.feri.um.si/", "shrani");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: knjiznicasoap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShraniResponse }
     * 
     */
    public ShraniResponse createShraniResponse() {
        return new ShraniResponse();
    }

    /**
     * Create an instance of {@link VrniVseResponse }
     * 
     */
    public VrniVseResponse createVrniVseResponse() {
        return new VrniVseResponse();
    }

    /**
     * Create an instance of {@link Shrani }
     * 
     */
    public Shrani createShrani() {
        return new Shrani();
    }

    /**
     * Create an instance of {@link VrniVse }
     * 
     */
    public VrniVse createVrniVse() {
        return new VrniVse();
    }

    /**
     * Create an instance of {@link Knjigomat }
     * 
     */
    public Knjigomat createKnjigomat() {
        return new Knjigomat();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VrniVse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.knjiznica.javaee.feri.um.si/", name = "vrniVse")
    public JAXBElement<VrniVse> createVrniVse(VrniVse value) {
        return new JAXBElement<VrniVse>(_VrniVse_QNAME, VrniVse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShraniResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.knjiznica.javaee.feri.um.si/", name = "shraniResponse")
    public JAXBElement<ShraniResponse> createShraniResponse(ShraniResponse value) {
        return new JAXBElement<ShraniResponse>(_ShraniResponse_QNAME, ShraniResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VrniVseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.knjiznica.javaee.feri.um.si/", name = "vrniVseResponse")
    public JAXBElement<VrniVseResponse> createVrniVseResponse(VrniVseResponse value) {
        return new JAXBElement<VrniVseResponse>(_VrniVseResponse_QNAME, VrniVseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Shrani }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.knjiznica.javaee.feri.um.si/", name = "shrani")
    public JAXBElement<Shrani> createShrani(Shrani value) {
        return new JAXBElement<Shrani>(_Shrani_QNAME, Shrani.class, null, value);
    }

}
