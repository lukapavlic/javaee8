package si.um.feri.javaee.knjiznica.rest;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ApiKeyFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String apiKey = requestContext.getHeaderString("API-Key");
		if (!"3cbd4dbd-3217-4389-94a0-b9ae0627ed56".equals(apiKey)) {
			Response r = Response.status(Response.Status.UNAUTHORIZED).build();
			requestContext.abortWith(r);
		}
	}

}