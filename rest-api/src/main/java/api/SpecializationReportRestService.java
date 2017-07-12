package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.PreferredSpecializations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * @author Mariusz Szymanski
 */
@Path("/specializations")
@Produces(MediaType.APPLICATION_JSON)
public class SpecializationReportRestService {

    private Logger LOG = LoggerFactory.getLogger(SpecializationReportRestService.class);

    @Inject
    private PreferredSpecializations preferredSpecializations;

    @GET
    @Path("/all")
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

    @POST
    @Path("/add")
    public Response setPreferredSpecializations(
            @QueryParam("specialization") String specialization,
            @QueryParam("value") Integer value) {

        LOG.info("add/update specialization: " + specialization + " value: " + value);

        Map<String, Integer> statistics = preferredSpecializations.getSpecializationsStatistics();
        statistics.put(specialization, value);
        preferredSpecializations.setSpecializationsStatistics(statistics);

        return getPreferredSpecializations();
    }
}
