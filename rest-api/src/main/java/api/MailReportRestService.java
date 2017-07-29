package api;

import org.slf4j.Logger;
import quartz.CronSchedulerTrigger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author bartek
 */
@Path("/mail")
@Produces(MediaType.APPLICATION_JSON)
public class MailReportRestService {

    @Inject
    CronSchedulerTrigger trigger;

    private static final Logger LOGGER = getLogger(MailReportRestService.class);

    @GET
    @Path("/start")
    public Response startMailer() {

        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/stop")
    public Response stopMailer() {


        return Response.status(Response.Status.OK).build();
    }
}
