package si.um.feri.javaee.knjiznica.vao;

import javax.persistence.Entity;

@Entity
public class OcenaKnjigeSKomentarjem extends OcenaKnjigeEnostavna {

	private static final long serialVersionUID = 3000031329570803680L;

	private String komentar;

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	@Override
	public String toString() {
		return "Ocena id: " + getId() +  ", ocena: " + getOcena() +  ", komentar: " + getKomentar();
	}

}
