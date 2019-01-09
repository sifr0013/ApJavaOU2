package Model;

import java.awt.*;
import java.net.URL;

/**
 * Created by Simon on 2018-12-24.
 *
 * Public class ChannelModel is used to store information gotten from the
 * RadioXMLReader.
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

    /**
     * Constructor for a ChannelModel.
     * @param id - The identification number for the ChannelModel.
     * @param channelName - The name of the ChannelModel.
     */
    public ChannelModel(int id, String channelName){
        this.id = id;
        this.channelName = channelName;
    }

    /**
     * Getter for the ImageUrl.
     * @return - URL for the image.
     */
    public URL getImageURL() {
        return imageURL;
    }

    /**
     * Getter for the ImageTemplateUrl.
     * @return - URL for the image template.
     */
    public URL getImageTemplateURL() {
        return imageTemplateURL;
    }

    /**
     * Getter for the Color.
     * @return - Color of the ChannelModel.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for the Tagline.
     * @return - String of the Tagline.
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * Getter for the SiteURL.
     * @return - URL for the site.
     */
    public URL getSiteURL() {
        return siteURL;
    }

    /**
     * Getter for the LiveAudioModel.
     * @return - LiveAudioModel for the ChannelModel.
     */
    public LiveAudioModel getLiveAudioModel() {
        return liveAudioModel;
    }

    /**
     * Getter for the ScheduleURL.
     * @return - URL for the schedule.
     */
    public URL getScheduleURL() {
        return scheduleURL;
    }

    /**
     * Getter for the ChannelType.
     * @return - String of the channel type.
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * Getter for the XMLtVid.
     * @return - String of the xmltvid.
     */
    public String getXmltvid() {
        return xmltvid;
    }

    /**
     * Getter for the ID of the ChannelModel.
     * @return - ID as int.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the ChannelName.
     * @return - String of the channel name.
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Setter for the LiveAudioModel.
     * @param lam - LiveAudioModel.
     */
    public void setLiveAudioModel(LiveAudioModel lam) {
        this.liveAudioModel = lam;
    }

    /**
     * Setter for the ChannelType.
     * @param channelType - String.
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    /**
     * Setter for the Color.
     * @param color - Color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Setter for the ImageTemplateURL.
     * @param imageTemplateURL - URL.
     */
    public void setImageTemplateURL(URL imageTemplateURL) {
        this.imageTemplateURL = imageTemplateURL;
    }

    /**
     * Setter for the ImageURL.
     * @param imageURL - URL.
     */
    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Setter for the ScheduleURL.
     * @param scheduleURL - URL.
     */
    public void setScheduleURL(URL scheduleURL) {
        this.scheduleURL = scheduleURL;
    }

    /**
     * Setter for the SiteURL.
     * @param siteURL - URL.
     */
    public void setSiteURL(URL siteURL) {
        this.siteURL = siteURL;
    }

    /**
     * Setter for the Tagline.
     * @param tagline - String.
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    /**
     * Setter for the XMLtVid.
     * @param xmltvid - String.
     */
    public void setXmltvid(String xmltvid) {
        this.xmltvid = xmltvid;
    }
}
