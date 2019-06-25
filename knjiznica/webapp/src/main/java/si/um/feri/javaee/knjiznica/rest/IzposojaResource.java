package si.um.feri.javaee.knjiznica.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import si.um.feri.javaee.knjiznica.ejb.Izposoje;
import si.um.feri.javaee.knjiznica.vao.Izposoja;
import si.um.feri.javaee.knjiznica.vao.json.IzposojaJson;

/**
 * Demonstracija REST spletne storitve
 */
@Path("/izposoja")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IzposojaResource {

	@EJB
	Izposoje izposojeEjb;
	
	@GET
	@Path("/rezervacije/{clan}")
	public List<IzposojaJson> getVseRezervacije(@PathParam("clan") String kodaClana) {
		return izposojeEjb.getRezervacijeJson(kodaClana);
	}
	
	@GET
	@Path("/izposoje/{clan}")
	public Response getVseIzposoje(@PathParam("clan") String kodaClana) {
		List<IzposojaJson> ret=izposojeEjb.getIzposojeJson(kodaClana);
		return Response.ok(ret).build();
	}
	
	@POST
	@Path("/izposoje/{clan}")
	public Response zabeleziIzposojo(IzposojaJson i) {
		izposojeEjb.izposodi(i.getKodaKnjige(),i.getKodaClana());
		return Response.ok().build();
	}
	
	@PUT
	@Path("/izposoje/{clan}")
	public Response vrniKnjigo(Izposoja i,@PathParam("clan") String kodaClana) {
		return null;
	}
	
}
