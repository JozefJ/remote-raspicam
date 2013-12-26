package es.anloci.raspberry.camera.rest.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

/**
 * 
 * @author Angel Lopez-Cima
 *
 */
@Path("/camera")
public interface CameraService {
    @POST
    void stop();
    
    @POST
    StreamURL start(CameraOptions options) throws WebApplicationException;
}
