package havis.net.rest.shared.data;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.TextCallback;

public interface ServiceAsync extends RestService {
	@GET
	@Path("")
	void isServiceAvailable(TextCallback callback);
}
