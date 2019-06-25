package si.um.feri.javaee.knjiznica.vao.json;

import java.util.Calendar;
import javax.json.bind.annotation.JsonbDateFormat;
import si.um.feri.javaee.knjiznica.vao.Izposoja;

/**
 * VAO, ki demonstrira preslikovanje v JSON
 */
public class IzposojaJson {

	private String kodaKnjige;

	private String kodaClana;
	
	@JsonbDateFormat(value = "yyyy-MM-dd")
	private Calendar datumRezervacije;
	
	@JsonbDateFormat(value = "yyyy-MM-dd")
	private Calendar datumIzposoje;
	
	@JsonbDateFormat(value = "yyyy-MM-dd")
	private Calendar datumVrnitve;
	
	private String komentar;
	
	public IzposojaJson() {
	}
	
	public IzposojaJson(Izposoja i) {
		kodaClana=i.getClan().getKoda();
		kodaKnjige=i.getKnjiga().getKodaKnjige();
		datumRezervacije=i.getDatumRezervacije();
		datumIzposoje=i.getDatumIzposoje();
		datumVrnitve=i.getDatumVrnitve();
		komentar=i.getKomentar();
	}
	
	public String getKodaKnjige() {
		return kodaKnjige;
	}
	public void setKodaKnjige(String kodaKnjige) {
		this.kodaKnjige = kodaKnjige;
	}
	public String getKodaClana() {
		return kodaClana;
	}
	public void setKodaClana(String kodaClana) {
		this.kodaClana = kodaClana;
	}
	public Calendar getDatumRezervacije() {
		return datumRezervacije;
	}
	public void setDatumRezervacije(Calendar datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}
	public Calendar getDatumIzposoje() {
		return datumIzposoje;
	}
	public void setDatumIzposoje(Calendar datumIzposoje) {
		this.datumIzposoje = datumIzposoje;
	}
	public Calendar getDatumVrnitve() {
		return datumVrnitve;
	}
	public void setDatumVrnitve(Calendar datumVrnitve) {
		this.datumVrnitve = datumVrnitve;
	}
	public String getKomentar() {
		return komentar;
	}
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	
}
