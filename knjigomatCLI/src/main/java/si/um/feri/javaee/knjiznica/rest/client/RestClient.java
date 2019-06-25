package si.um.feri.javaee.knjiznica.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {
	
	private static String URL_IZPOSOJE = "http://localhost:8080/webapp/rest/izposoja/izposoje/";
	private static String URL_REZERVACIJE = "http://localhost:8080/webapp/rest/izposoja/rezervacije/";
	private static String CLAN_KODA = "mkrp163";
	private static String IZPOSOJA_JSON = "{\"kodaClana\":\"mkrp163\",\"kodaKnjige\":\"ICAN1\"}";
	
	public static void get(String url) {
		System.out.println(url);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        int status = response.getStatus();
        response.close();  

        System.out.println(status);
        System.out.println(value);
	}

	public static void post(String url, String json) {
		System.out.println(url);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        
        Response response = target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON));
        String value = response.readEntity(String.class);
        int status = response.getStatus();
        response.close();  

        System.out.println(status);
        System.out.println(value);
	}

	
	public static void main(String[] args) {

		//post(URL_IZPOSOJE+CLAN_KODA, IZPOSOJA_JSON);

		System.out.println("Rezervacije:");
		get(URL_REZERVACIJE+CLAN_KODA);

		System.out.println("--------------------------------------------");
		System.out.println("Izposoje:");
		get(URL_IZPOSOJE+CLAN_KODA);
		
	}
	
}
