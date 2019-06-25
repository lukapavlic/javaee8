package si.um.feri.javaee.knjiznica.ejb;

import java.util.List;
import si.um.feri.javaee.knjiznica.vao.Knjiga;

public interface Knjige {

	void shrani(Knjiga k);
	
	Knjiga najdi(int id);
	
	Knjiga najdi(String koda);
	
	List<Knjiga> poisci(String iskalniNiz);

	List<Knjiga> vrniVse(String zanr);
	
}
