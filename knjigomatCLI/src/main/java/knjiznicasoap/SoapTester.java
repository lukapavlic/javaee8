package knjiznicasoap;

import knjiznicasoap.client.Knjigomat;
import knjiznicasoap.client.KnjigomatiSoap;
import knjiznicasoap.client.KnjigomatiSoapService;

public class SoapTester {

	public static void main(String[] args) {
		KnjigomatiSoap soap=new KnjigomatiSoapService().getKnjigomatiSoapPort();
		
		for (Knjigomat k : soap.vrniVse())
			System.out.println(k.getNaziv());
		
	}
	
}
