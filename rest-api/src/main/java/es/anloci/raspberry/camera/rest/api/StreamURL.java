package es.anloci.raspberry.camera.rest.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The response of execution the operation start. The operation response with the public URL of the streaming.
 * @author Angel Lopez-Cima
 *
 */
@XmlRootElement(name = "options")
@XmlAccessorType(XmlAccessType.FIELD)
public class StreamURL {
    @XmlElement
    private String url = null;

    public StreamURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
