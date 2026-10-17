package webservices;

import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UniteEnsRestApi {
    UniteEnseignementBusiness helper =new UniteEnseignementBusiness();
    //getListUE
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListUe(){
        return Response
                .status(200)
                .entity(helper.getListeUE())
                .build();
    }
   // @Path("/add")
    //@POST
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.TEXT_PLAIN)
    //public Response addUe(UniteEnseignementBusiness ue)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{code}")
    public Response getUEBycode(@PathParam("code") int code){
        return Response.status(200)
                .entity((helper.getUEByCode(code)))
                .build();
    }
}
