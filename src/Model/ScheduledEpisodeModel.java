package Model;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Simon on 2018-12-24.
 */
public class ScheduledEpisodeModel {
    private int episodeID;
    private String title;
    private String description;
    private LocalDateTime startTimeUTC;
    private LocalDateTime endTimeUTC;
    private int programID;
    private String programName;

    private int channelID;
    private String channelName;
    private URL imageURL;
    private URL imageTemplateURL;

    public ScheduledEpisodeModel(){}

    public int getEpisodeID() {
        return episodeID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTimeUTC() {
        return startTimeUTC;
    }

    public LocalDateTime getEndTimeUTC() {
        return endTimeUTC;
    }

    public int getProgramID() {
        return programID;
    }

    public String getProgramName() {
        return programName;
    }

    public int getChannelID() {
        return channelID;
    }

    public String getChannelName() {
        return channelName;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public URL getImageTemplateURL() {
        return imageTemplateURL;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndTimeUTC(LocalDateTime endTimeUTC) {
        this.endTimeUTC = endTimeUTC;
    }

    public void setEpisodeID(int episodeID) {
        this.episodeID = episodeID;
    }

    public void setImageTemplateURL(URL imageTemplateURL) {
        this.imageTemplateURL = imageTemplateURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setStartTimeUTC(LocalDateTime startTimeUTC) {
        this.startTimeUTC = startTimeUTC;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void printOut(){
        System.out.println("Episode id: "+episodeID);
        System.out.println("Start time: "+startTimeUTC);
        System.out.println("End time: "+endTimeUTC);
        System.out.println("-----------------------------");
    }
}
