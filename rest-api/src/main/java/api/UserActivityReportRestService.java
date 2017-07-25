package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import domain.UserActivity;
import domain.UserActivityStorageService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Mariusz Szymanski
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UserActivityReportRestService {

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
            return Response.status(Response.Status.OK).entity(json).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
