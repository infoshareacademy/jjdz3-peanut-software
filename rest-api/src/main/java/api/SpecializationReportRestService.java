package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.PreferredSpecializations;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Mariusz Szymanski
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class SpecializationReportRestService {

    private static final Logger LOGGER = getLogger(SpecializationReportRestService.class);

    @Inject
    private PreferredSpecializations preferredSpecializations;

    @GET
    @Path("/specialization")
    public Response getPreferredSpecializations() {
        Map<String, Integer> statistics = preferredSpecializations.getSpecializationsStatistics();
        return this.generateResponse(statistics);
    }

    @GET
    @Path("/specialization/{name}")
    public Response getPreferredSpecializationQuantity(
            @PathParam("name") String specialization) {
        Map<String, Integer> statistics = preferredSpecializations
                .getSpecializationsStatistics().entrySet().stream()
                .filter(map -> specialization.equals(map.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return this.generateResponse(statistics);
    }

    private Response generateResponse(Map<String, Integer> statistics) {
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(statistics);
        } catch (JsonProcessingException e) {
            LOGGER.debug("Json Processing Exception: " + e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).entity(json).build();
    }
}
