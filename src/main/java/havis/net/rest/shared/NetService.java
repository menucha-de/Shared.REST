package havis.net.rest.shared;

import havis.net.rest.shared.data.Uri;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("net")
public class NetService extends Resource {
	@PUT
	@Path("uri/complex")
	@PermitAll
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	public Uri parseUri(String uri) throws URISyntaxException {
		URI javaURI = new URI(uri);
		Uri gwtUri = new Uri(javaURI.getScheme(), javaURI.getSchemeSpecificPart(), javaURI.getAuthority(), javaURI.getUserInfo(), javaURI.getHost(),
				javaURI.getPort(), javaURI.getPath(), javaURI.getQuery(), javaURI.getRawQuery(), javaURI.getFragment());
		return gwtUri;
	}

	@PUT
	@Path("uri/plain")
	@PermitAll
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	public String printUri(Uri uri) throws URISyntaxException {
		return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment()).toString();
	}

	@GET
	@Path("hostname")
	@PermitAll
	@Produces({ MediaType.TEXT_PLAIN })
	public String getHostname() {
		String hostname = System.getenv("HOSTNAME");
		if (hostname != null) {
			int index = hostname.indexOf('-');
			return index > 0 ? hostname.substring(0, index) : hostname;
		}
		return "";
	}
}