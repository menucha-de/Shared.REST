package havis.net.rest.shared.async;

import havis.net.rest.shared.data.Uri;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.TextCallback;

@Path("../rest/net")
public interface NetServiceAsync extends RestService {

	@OPTIONS
	@Path("uri/complex")
	void getUriParseOptions(MethodCallback<Void> callback);

	@OPTIONS
	@Path("uri/plain")
	void getUriPrintOptions(MethodCallback<Void> callback);

	@PUT
	@Path("uri/complex")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.APPLICATION_JSON })
	void parseUri(String uri, MethodCallback<Uri> callback);

	@PUT
	@Path("uri/plain")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	void printUri(Uri uri, TextCallback callback);

	@GET
	@Path("hostname")
	@Produces({ MediaType.TEXT_PLAIN })
	void getHostname(TextCallback callback);

}
