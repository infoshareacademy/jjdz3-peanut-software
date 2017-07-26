package api;

import domain.Survey;
import domain.SurveyStorageService;
import org.slf4j.Logger;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Mariusz Szymanski
 */

@Path("/survey")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class SurveysReportRestService {

    private static final Logger LOGGER = getLogger(SurveysReportRestService.class);

    @EJB
    private SurveyStorageService surveyStorage;

    @GET
    public Response getAllSurveys() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        List<Survey> surveys = surveyStorage.getAllSurveys();
        for (Survey survey : surveys) {
            jsonArrayBuilder.add(survey.toJsonObject());
        }
        return Response.status(Response.Status.OK).entity(jsonArrayBuilder.build()).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Survey survey = surveyStorage.getSurvey(id);
        if (survey == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK)
                .entity(survey.toJsonObject())
                .build();
    }
}
