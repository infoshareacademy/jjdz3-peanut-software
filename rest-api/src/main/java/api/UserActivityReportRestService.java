package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import domain.UserActivity;
import domain.UserActivityStorageService;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Mariusz Szymanski
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserActivityReportRestService {

    private static final Logger LOGGER = getLogger(UserActivityReportRestService.class);

    @Inject
    private UserActivityStorageService activityStorageService;

    @GET
    @Path("/usersActivity/all")
    public Response getUsersActivity() {

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        List<UserActivity> userActivities = activityStorageService.getAllUsersActivity();

        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userActivities);
            LOGGER.debug("UsersActivity Report JSON Response Status 200");
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (JsonProcessingException e) {
            LOGGER.warn("Json Processing Exception: " + e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
