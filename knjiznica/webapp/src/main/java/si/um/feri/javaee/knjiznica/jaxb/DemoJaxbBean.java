package si.um.feri.javaee.knjiznica.jaxb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@Named("demoJAXB")
@SessionScoped
public class DemoJaxbBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(DemoJaxbBean.class.getSimpleName());

	/**
	 * Unmarshaling
	 * 
	 * @return String, ki predstavlja vsebino datoteke XML
	 * @throws JAXBException
	 * @throws IOException
	 */
	public void deserializiraj() throws JAXBException, IOException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Oseba.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		// Unmarshal from InputStream
		log.info("Unmarshaling");
		// dostop do datoteke XML, ki se nahaja v isti mapi kjer je tudi ta bean
		InputStream inStream = DemoJaxbBean.class.getResourceAsStream("oseba.xml");
		Oseba oseba = (Oseba) jaxbUnmarshaller.unmarshal(inStream);

		inStream.close();

		log.info(oseba.toString());
	}

	/**
	 * Marshaling
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 */
	public void serializiraj() throws JAXBException, IOException {
		// Ustvarimo objek za serializacijo
		Oseba o = new Oseba();
		o.setIme("Viktor");
		o.setPriimek("Taneski");
		o.setStarost(31);
		o.setNaslov("Kardeljeva cesta 57");
		o.setClan(false);
		List<String> knjige = new ArrayList<String>();
		knjige.add("Iliada/Odiseja");
		knjige.add("Božanska komedija");
		knjige.add("1984");
		o.setKnjige(knjige);

		JAXBContext jaxbContext = JAXBContext.newInstance(Oseba.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

		log.info("Marshaling");
		// Marshal to file

		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF");

		File output = new File(path + "/classes/si/um/feri/javaee/knjiznica/jaxb/osebaExport.xml");
		OutputStream os = new FileOutputStream(output);
		jaxbMarshaller.marshal(o, os);

		os.close();
	}
}
