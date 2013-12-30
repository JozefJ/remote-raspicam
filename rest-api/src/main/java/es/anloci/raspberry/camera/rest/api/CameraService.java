package es.anloci.raspberry.camera.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * 
 * @author Angel Lopez-Cima
 *
 */
public interface CameraService {
    @Path("/stop")
    @POST
    void stopVideo();
    
    @Path("/record")
    @POST
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    StreamURL recordVideo(CameraOptions options) throws WebApplicationException;
}
