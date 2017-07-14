package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.PreferredSpecializations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Mariusz Szymanski
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class SpecializationReportRestService {

    private Logger LOG = LoggerFactory.getLogger(SpecializationReportRestService.class);

    @Inject
    private PreferredSpecializations preferredSpecializations;

    @GET
    @Path("/specializations/all")
    public Response getPreferredSpecializations() {
        Map<String, Integer> statistics = preferredSpecializations.getSpecializationsStatistics();
        return this.generateResponse(statistics);
    }

    @GET
    @Path("/specialization/{specialization}")
    public Response getPreferredSpecializationQuantity(
            @PathParam("specialization") String specialization) {
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
            LOG.debug("Json Processing Exception");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(json).build();
    }
}
