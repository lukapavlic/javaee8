package si.um.feri.javaee.knjiznica.ejb;

import java.util.List;

import javax.ejb.Local;

import si.um.feri.javaee.knjiznica.vao.Izposoja;
import si.um.feri.javaee.knjiznica.vao.json.IzposojaJson;

@Local
public interface Izposoje {

	Izposoja rezerviraj(String kodaKnjige, String kodaClana);

	Izposoja izposodi(String kodaKnjige, String kodaClana);
	
	void vrni(String kodaKnjige, String kodaClana);

	Izposoja najdiRezervacijo(String kodaKnjige, String kodaClana);
	
	Izposoja najdiIzposojo(String kodaKnjige, String kodaClana);
	
	List<Izposoja> getRezervacije();
	
	List<Izposoja> getIzposoje();

	List<IzposojaJson> getRezervacijeJson(String kodaClana);
	
	List<IzposojaJson> getIzposojeJson(String kodaClana);
	
	List<Izposoja> getRezervacije(String kodaClana);
	
	List<Izposoja> getIzposoje(String kodaClana);
	
}
