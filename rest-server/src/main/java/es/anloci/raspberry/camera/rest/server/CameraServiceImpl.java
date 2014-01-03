package es.anloci.raspberry.camera.rest.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.cxf.helpers.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;

import es.anloci.raspberry.camera.rest.api.CameraOptions;
import es.anloci.raspberry.camera.rest.api.CameraService;
import es.anloci.raspberry.camera.rest.api.StreamURL;

public class CameraServiceImpl implements CameraService {
    private static Process process = null;
    private final String host;
    
    public CameraServiceImpl() throws UnknownHostException {
        this.host = InetAddress.getLocalHost().getHostAddress();
        init();
    }

    private void init() {
        URL url = this.getClass().getClassLoader().getResource("init.json");
        if (url != null) {
            try {
                JAXBContext jc = JAXBContext.newInstance(CameraOptions.class);

                JSONObject obj = new JSONObject(IOUtils.toString(url.openStream()));
                Configuration config = new Configuration();
                MappedNamespaceConvention con = new MappedNamespaceConvention(config);
                XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);

                Unmarshaller unmarshaller = jc.createUnmarshaller();
                CameraOptions options = (CameraOptions) unmarshaller.unmarshal(xmlStreamReader);
                recordVideo(options);
            } catch (JSONException e) {
            } catch (IOException e) {
            } catch (XMLStreamException e) {
            } catch (JAXBException e) {
            }
        }
    }
    
    public synchronized void stopVideo() {
        if (CameraServiceImpl.process != null) {
            try {
                CameraServiceImpl.process.exitValue();
            } catch(IllegalThreadStateException e) {
                CameraServiceImpl.process.destroy();
            } finally {
                CameraServiceImpl.process = null;
            }
        }
    }

    public synchronized StreamURL recordVideo(CameraOptions options) throws WebApplicationException {        
        if (CameraServiceImpl.process != null) {
            WebApplicationException ex = new WebApplicationException(500);
            throw ex;
        }
        String raspividCmd = createRaspividCommand(options);
        String vlcCmd = createVLCCommand(options);
        try {
            String cmd = raspividCmd + ((vlcCmd == null) ? "" : " | " +  vlcCmd);
            System.out.println(cmd);
            CameraServiceImpl.process = Runtime.getRuntime().exec(cmd);
            return (vlcCmd == null) ? null : new StreamURL("http://" + this.host +":" + options.getPort() + "/");
        } catch (IOException e) {
            WebApplicationException ex = new WebApplicationException(500);
            ex.addSuppressed(e);
            throw ex;
        }
    }

    private String createVLCCommand(CameraOptions options) {
        String file = options.getFile();
        Integer port = options.getPort();
        if (port == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("cvlc");
        builder.append(' ').append("-vvv");
        builder.append(' ').append("stream:///dev/stdin");
        builder.append(' ').append("--sout '");
        if (port != null) {
            builder.append("#rtp{sdp=rtsp://:" + port + "/}");
        }
        if (file != null) {
            builder.append("#std{access=file,mux=ps,dst=" + file + "}");
        }
        builder.append('\'');
        builder.append(' ').append(":demux=h264");
        return builder.toString();
    }
    
    private String createRaspividCommand(CameraOptions options) {
        StringBuilder builder = new StringBuilder();
        builder.append("raspivid").append(' ');
        builder.append(options.toString());
        return builder.toString();
    }
}
