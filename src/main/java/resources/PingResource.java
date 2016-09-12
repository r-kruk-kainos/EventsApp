package resources;

import domain.Person;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/ping")
public class PingResource {
	@GET
	@Path("/auth")
	public Response pongAuthenticated(@Auth Person person) {
		return Response.status(Response.Status.OK).build();
	}
}
