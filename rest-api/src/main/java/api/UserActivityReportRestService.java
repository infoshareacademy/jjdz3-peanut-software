package api;

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
        List<UserActivity> userActivities = activityStorageService.getAllUsersActivity();
        return Response.status(Response.Status.OK).entity(userActivities).build();
    }
}
