package es.anloci.raspberry.camera.rest.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>Camera options.</p>
 * <p>Missing parameters:</p>
 * <ul>
 *   <li> -cfx, --colfx     : Set colour effect (U:V)</li>
 *   <li> -ev, --ev         : Set EV compensation</li>
 *   <li> -cfx, --colfx     : Set colour effect (U:V)</li>
 *   <li> -hf, --hflip      : Set horizontal flip</li>
 *   <li> -vf, --vflip      : Set vertical flip</li>
 *   <li> -roi, --roi       : Set region of interest (x,y,w,d as normalised coordinates [0.0-1.0])</li>
 *   <li> -ss, --shutter    : Set shutter speed in microseconds</li>
 * </ul>
 * @author Angel Lopez-Cima
 * 
 */
@XmlRootElement(name = "options")
@XmlAccessorType(XmlAccessType.FIELD)
public class CameraOptions {
    public enum Profile {
        BASELINE("baseline"), MAIN("main"), HIGH("high");

        private String profile;

        private Profile(String profile) {
            this.profile = profile;
        }

        public String toString() {
            return this.profile;
        }
    }

    public enum Exposure {
        AUTO("auto") ,NIGHT("night"), NIGHT_PREVIEW("nightpreview"), BACKLIGHT("backlight"),
        SPOTLIGHT("spotlight"), SPORTS("sports"), SNOW("snow"), BEACH("beach"), VERY_LONG("verylong"),
        FIXED_FPS("fixedfps"), ANTISHAKE("antishake"), FIREWORKS("fireworks");

        private String exposure;

        private Exposure(String exposure) {
            this.exposure = exposure;
        }

        public String toString() {
            return this.exposure;
        }
    }

    public enum AWB {
        OFF("off"), AUTO("auto"), SUN("sun"), CLOUD("cloud"), SHADE("shade"), TUNGSTEN("tungsten"),
        FLUORESCENT("fluorescent"), INCANDESCENT("incandescent"), FLASH("flash"), HORIZON("horizon");

        private String awb;

        private AWB(String awb) {
            this.awb = awb;
        }

        public String toString() {
            return this.awb;
        }
    }

    public enum ImageEffect {
        NONE("none"), NEGATIVE("negative"), SOLARISE("solarise"), SKETCH("sketch"), DENOISE("denoise"),
        EMBOSS("emboss"), OILPAINT("oilpaint"), HATCH("hatch"), GPEN("gpen"), PASTEL("pastel"),
        WATERCOLOUR("watercolour"), FILM("film"), BLUR("blur"), SATURATION("saturation"),
        COLOUR_SWAP("colourswap"), WASHED_OUT("washedout"), POSTERISE("posterise"), COLOUR_POINT("colourpoint"),
        COLOUR_BALANCE("colourbalance"), CARTOON("cartoon");

        private String effect;

        private ImageEffect(String effect) {
            this.effect = effect;
        }

        public String toString() {
            return this.effect;
        }
    }
    
    public enum Metering {
        AVARAGE("average"), SPOT("spot"), BACKLIT("backlit"), MATRIX("matrix");

        private String metering;

        private Metering(String metering) {
            this.metering = metering;
        }

        public String toString() {
            return this.metering;
        }
    }

    /*
     * -w, --width : Set image width <size>. Default 1920
     */
    @XmlElement
    private int width = 1920;

    /**
     * -h, --height : Set image height <size>. Default 1080
     */
    @XmlElement
    private int height = 1080;
 
    /**
     * <p>Bitrate in MBits/secs</p>
     * <p>-b, --bitrate : Set bitrate. Use bits per second (e.g. 10MBits/s would be -b 10000000)</p>
     */
    @XmlElement
    private Double mbitrate = null;
 
    /**
     * <p>Time in seconds</p>
     * <p>-t, --timeout : Time (in ms) to capture for. If not specified, set to 5s. Zero to disable</p>
     */
    @XmlElement(defaultValue = "5")
    @Min(0)
    private int timeout = 5;

    /**
     * Output video file
     */
    @XmlElement
    private String file = null;

    /**
     * Stream port
     */
    @XmlElement
    private Integer port = 8554;

    /**
     * -fps, --framerate : Specify the frames per second to record
     */
    @XmlElement
    private Integer framerate = null;
 
    /**
     * -g, --intra : Specify the intra refresh period (key frame rate/GoP size)
     */
    @XmlElement
    private Integer intra = null;
 
    /**
     * -pf, --profile : Specify H264 profile to use for encoding
     */
    @XmlElement
    private Profile profile = null;
  
    /**
     * -qp, --qp : Quantisation parameter. Use approximately 10-40. Default 0 (off)
     */
    @XmlElement(defaultValue = "0")
    @Min(0)
    private int quantisation = 0;
  
    /**
     * -ih, --inline : Insert inline headers (SPS, PPS) to stream
     */
    @XmlElement
    private String inline = null;
 
    /**
     * -sh, --sharpness : Set image sharpness (-100 to 100)
     */
    @XmlElement
    @Min(-100)
    @Max(100)
    private Integer sharpness = null;

    /**
     * -co, --contrast : Set image contrast (-100 to 100)
     */
    private Integer contrast = null;

    /**
     * -br, --brightness : Set image brightness (0 to 100)
     */
    @XmlElement
    @Min(-100)
    @Max(100)
    private Integer brightness = null;

    /**
     * -sa, --saturation : Set image saturation (-100 to 100)
     */
    @XmlElement
    @Min(-100)
    @Max(100)
    private Integer saturation = null;
    
    /**
     * -ISO, --ISO       : Set capture ISO
     */
    @XmlElement
    private Boolean iso = null;

    /**
     * -vs, --vstab      : Turn on video stablisation
     */
    @XmlElement
    private Boolean stablisation = null;

    /**
     * -ex, --exposure   : Set exposure mode (see Notes)
     */
    @XmlElement
    private Exposure exposure = Exposure.AUTO;

    /**
     * -awb, --awb       : Set AWB mode (see Notes)
     */
    @XmlElement(defaultValue = "auto")
    private AWB awb = AWB.AUTO;
    
    /**
     * -ifx, --imxfx     : Set image effect (see Notes)
     */
    @XmlElement(defaultValue = "NONE")
    private ImageEffect imageEffect = ImageEffect.NONE;
    
    /**
     * -mm, --metering   : Set metering mode (see Notes)
     */
    @XmlElement(defaultValue = "AVARAGE")
    private Metering metering = Metering.AVARAGE;
    
    /**
     * -rot, --rotation  : Set image rotation (0-359)
     */
    @XmlElement
    @Min(0)
    @Max(359)
    private int rotation = 0;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Double getMbitrate() {
        return mbitrate;
    }

    public void setMbitrate(Double mbitrate) {
        this.mbitrate = mbitrate;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Integer getFramerate() {
        return framerate;
    }

    public void setFramerate(Integer framerate) {
        this.framerate = framerate;
    }

    public Integer getIntra() {
        return intra;
    }

    public void setIntra(Integer intra) {
        this.intra = intra;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getQuantisation() {
        return quantisation;
    }

    public void setQuantisation(int quantisation) {
        this.quantisation = quantisation;
    }

    public String getInline() {
        return inline;
    }

    public void setInline(String inline) {
        this.inline = inline;
    }

    public Integer getSharpness() {
        return sharpness;
    }

    public void setSharpness(Integer sharpness) {
        this.sharpness = sharpness;
    }

    public Integer getContrast() {
        return contrast;
    }

    public void setContrast(Integer contrast) {
        this.contrast = contrast;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public Integer getSaturation() {
        return saturation;
    }

    public void setSaturation(Integer saturation) {
        this.saturation = saturation;
    }

    public boolean isISO() {
        return iso != null && iso;
    }

    public void setISO(Boolean iso) {
        this.iso = iso;
    }

    public boolean isStablisation() {
        return stablisation != null && stablisation;
    }

    public void setStablisation(Boolean stablisation) {
        this.stablisation = stablisation;
    }

    public Exposure getExposure() {
        return exposure;
    }

    public void setExposure(Exposure exposure) {
        this.exposure = exposure;
    }

    public AWB getAWB() {
        return awb;
    }

    public void setAWB(AWB awb) {
        this.awb = awb;
    }

    public ImageEffect getImageEffect() {
        return imageEffect;
    }

    public void setImageEffect(ImageEffect imageEffect) {
        this.imageEffect = imageEffect;
    }

    public Metering getMetering() {
        return metering;
    }

    public void setMetering(Metering metering) {
        this.metering = metering;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
    
    public String[] toArguments() {
        List<String> builder = new ArrayList<String>();
        builder.add("--width");
        builder.add(Integer.toString(this.width));
        builder.add("--height");
        builder.add(Integer.toString(this.height));
        builder.add("--timeout");
        builder.add(Integer.toString(this.timeout * 1000));
        if (this.mbitrate != null) {
            builder.add("--bitrate");
            builder.add(Integer.toString((int) (this.mbitrate * 1000000.)));
        }
        if (this.framerate != null) {
            builder.add("--framerate");
            builder.add(this.framerate.toString());
        }
        if (this.intra != null) {
            builder.add("--intra");
            builder.add(this.intra.toString());
        }
        if (this.profile != null) {
            builder.add("--profile");
            builder.add(this.profile.toString());
        }
        builder.add("--quantisation");
        builder.add(Integer.toString(this.quantisation));
        if (this.inline != null) {
            builder.add("--inline");
            builder.add(this.inline);
        }
        
        if (this.sharpness != null) {
            builder.add("--sharpness");
            builder.add(this.sharpness.toString());
        }
        if (this.contrast != null) {
            builder.add("--contrast");
            builder.add(this.contrast.toString());
        }
        if (this.brightness != null) {
            builder.add("--brightness");
            builder.add(this.brightness.toString());
        }
        if (this.saturation != null) {
            builder.add("--saturation");
            builder.add(this.saturation.toString());
        }
        if (this.iso != null && this.iso) {
            builder.add("--ISO");
        }
        if (this.stablisation != null && this.stablisation) {
            builder.add("--vstab");
        }
        if (this.exposure != null) {
            builder.add("--exposure");
            builder.add(this.exposure.toString());
        }
        if (this.awb != null) {
            builder.add("--awb");
            builder.add(this.awb.toString());
        }
        if (this.imageEffect != null) {
            builder.add("--imxfx");
            builder.add(this.imageEffect.toString());
        }
        if (this.metering != null) {
            builder.add("--metering");
            builder.add(this.metering.toString());
        }
        builder.add("--rotation");
        builder.add(Integer.toString(this.rotation));
        return builder.toArray(new String[0]);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        String[] args = toArguments();
        if (args != null) {
            for (String arg : args) {
                if (builder.length() > 0) {
                    builder.append(' ');
                }
                builder.append(arg);
            }
        }
        return builder.toString();
    }
}
