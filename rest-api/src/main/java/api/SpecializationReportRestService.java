package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.PreferredSpecializations;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * @author Mariusz Szymanski
 */
@Path("/specializations")
@Produces(MediaType.APPLICATION_JSON)
public class SpecializationReportRestService {

    @Inject
    private PreferredSpecializations preferredSpecializations;

    @GET
    public Response getPreferredSpecializations() {
        Map<String, Integer> statistics = preferredSpecializations.getSpecializationsStatistics();
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(statistics);
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(json).build();
    }
}
