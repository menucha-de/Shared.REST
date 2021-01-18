package havis.net.rest.shared;

import java.lang.reflect.Method;
import java.util.HashSet;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public abstract class Resource {

	@PermitAll
	@GET
	@Path("")
	@Produces({ "text/plain" })
	public boolean isServiceAvailable() {
		return true;
	}

	@PermitAll
	@OPTIONS
	@Path("{path:.*}")
	public Response getOptions(@PathParam("path") String path, @Context SecurityContext sc) {
		HashSet<String> options = new HashSet<String>();
		options.add("HEAD");
		options.add("OPTIONS");
		for (Method m : this.getClass().getMethods()) {
			if (m.isAnnotationPresent(Path.class)) {
				Path pathAnnotation = m.getAnnotation(Path.class);
				if (pathAnnotation.value().equals(path)) {
					if (isUserAllowed(sc, m)) {
						options.addAll(getOptionsForMethod(m));
					}
				}
			}
		}

		return Response.ok().allow(options).build();
	}
		
	private boolean isUserAllowed(SecurityContext sc, Method m) {
		if (m.isAnnotationPresent(RolesAllowed.class)) {
			RolesAllowed rolesAnnotation = m.getAnnotation(RolesAllowed.class);
			for (String role : rolesAnnotation.value()) {
				if (sc.isUserInRole(role)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	private HashSet<String> getOptionsForMethod(Method m) {
		HashSet<String> options = new HashSet<String>();
		if (m.isAnnotationPresent(GET.class))
			options.add("GET");
		if (m.isAnnotationPresent(POST.class))
			options.add("POST");
		if (m.isAnnotationPresent(PUT.class))
			options.add("PUT");
		if (m.isAnnotationPresent(DELETE.class))
			options.add("DELETE");
		return options;
	}

}
