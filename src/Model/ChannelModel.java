package Model;

import java.awt.*;
import java.net.URL;

/**
 * Created by Simon on 2018-12-24.
 */
public class ChannelModel {
    private URL imageURL;
    private URL imageTemplateURL;
    private Color color;
    private String tagline;
    private URL siteURL;
    private LiveAudioModel liveAudioModel;
    private URL scheduleURL;
    private String channelType;
    private String xmltvid;

    private int id;
    private String channelName;

    public ChannelModel(int id, String channelName){
        this.id = id;
        this.channelName = channelName;
    }

    public void setLiveAudioModel(LiveAudioModel lam) {
        this.liveAudioModel = lam;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setImageTemplateURL(URL imageTemplateURL) {
        this.imageTemplateURL = imageTemplateURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

    public void setScheduleURL(URL scheduleURL) {
        this.scheduleURL = scheduleURL;
    }

    public void setSiteURL(URL siteURL) {
        this.siteURL = siteURL;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setXmltvid(String xmltvid) {
        this.xmltvid = xmltvid;
    }

    public void printOut(){
        System.out.println("ID: "+id);
        System.out.println("Channelname: "+channelName);
        System.out.println("imageurl: "+imageURL.toString());
        System.out.println(liveAudioModel.toString());
        System.out.println("--------------------------------");
    }
}
