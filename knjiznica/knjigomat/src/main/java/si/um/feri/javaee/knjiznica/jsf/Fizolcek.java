package si.um.feri.javaee.knjiznica.jsf;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("fizolcek")
@SessionScoped
public class Fizolcek implements Serializable {

	private static final long serialVersionUID = 8921711242052520843L;

	private String pozdrav="Jaz sem fižolček.";

	public String getPozdrav() {
		return pozdrav;
	}

	public void setPozdrav(String pozdrav) {
		this.pozdrav = pozdrav;
	}
	
}
