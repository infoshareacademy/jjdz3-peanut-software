package api;

import domain.Survey;
import domain.SurveyStorageService;

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

/**
 * @author Mariusz Szymanski
 */

@Path("/surveys")
@Produces(MediaType.APPLICATION_JSON)
public class SurveysReportRestService {

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