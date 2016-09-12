package resources;

import com.google.inject.Inject;
import dao.AccessTokenDao;
import dao.UserDao;
import domain.AccessToken;
import domain.Person;
import io.dropwizard.hibernate.UnitOfWork;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/oauth2/token")
public class OAuth2Resource {
    private AccessTokenDao accessTokenDAO;
    private UserDao userDAO;

    @Inject
    public OAuth2Resource(AccessTokenDao accessTokenDAO, UserDao userDAO) {
        this.accessTokenDAO = accessTokenDAO;
        this.userDAO = userDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public String postForToken( Person person
          //  @FormParam("grant_type") String grantType,
          //  @FormParam("username") String username,
          //  @FormParam("password") String password
          //  @FormParam("client_id") String clientId
    ) {
//        // Check if the grant type is allowed
//        if (!allowedGrantTypes.contains(grantType)) {
//            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
//        }

        // Try to find a person with the supplied credentials.
        Optional<Person> user = userDAO.findUserByUsernameAndPassword(person.getEmail(), person.getPassword());
        if (user == null || !user.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        // Person was found, generate a token and return it.
        AccessToken accessToken = accessTokenDAO.generateNewAccessToken(user.get(), new DateTime());
        return accessToken.getAccessTokenId().toString();
    }
}
