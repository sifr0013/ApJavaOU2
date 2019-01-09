package Model;

import java.net.URL;
import java.time.LocalDateTime;

/**
 * Created by Simon on 2018-12-24.
 *
 * Public class ScheduledEpisodeModel.
 * Used for storing information.
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

    /**
     * Public constructor for creating a ScheduledEpisodeModel.
     */
    public ScheduledEpisodeModel(){}

    /**
     * Getter for the EpisodeID.
     * @return - ID as int.
     */
    public int getEpisodeID() {
        return episodeID;
    }

    /**
     * Getter for the Title.
     * @return - String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the Description.
     * @return - String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the StartTimeUTC.
     * @return - LocalDateTime.
     */
    public LocalDateTime getStartTimeUTC() {
        return startTimeUTC;
    }

    /**
     * Getter for the EndTimeUTC.
     * @return - LocalDateTime.
     */
    public LocalDateTime getEndTimeUTC() {
        return endTimeUTC;
    }

    /**
     * Getter for the ProgramID.
     * @return - int.
     */
    public int getProgramID() {
        return programID;
    }

    /**
     * Getter for the ProgramName.
     * @return - String.
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * Getter for the ChannelID.
     * @return - int.
     */
    public int getChannelID() {
        return channelID;
    }

    /**
     * Getter for the ChannelName.
     * @return - String.
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Getter for the ImageURL.
     * @return - URL.
     */
    public URL getImageURL() {
        return imageURL;
    }

    /**
     * Getter for the ImageTemplateURL.
     * @return - URL.
     */
    public URL getImageTemplateURL() {
        return imageTemplateURL;
    }

    /**
     * Setter for the ChannelID.
     * @param channelID - Identification number for the channel as int.
     */
    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    /**
     * Setter for the ChannelName.
     * @param channelName - The channels name as String.
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * Setter for the Description.
     * @param description - The episodes description as String.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter for the EndTimeUTC.
     * @param endTimeUTC - The time the episodes ends as LocalDateTime.
     */
    public void setEndTimeUTC(LocalDateTime endTimeUTC) {
        this.endTimeUTC = endTimeUTC;
    }

    /**
     * Setter for the EpisodeID.
     * @param episodeID - The identification number of the episode as int.
     */
    public void setEpisodeID(int episodeID) {
        this.episodeID = episodeID;
    }

    /**
     * Setter for the ImageTemplateURL.
     * @param imageTemplateURL - The URL of the episodes image template
     *                         (large picture) as URL.
     */
    public void setImageTemplateURL(URL imageTemplateURL) {
        this.imageTemplateURL = imageTemplateURL;
    }

    /**
     * Setter for the ImageURL.
     * @param imageURL - The URL of the episodes image (cropped image) as URL.
     */
    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Setter for the ProgramID.
     * @param programID - The programs identifaction number as int.
     */
    public void setProgramID(int programID) {
        this.programID = programID;
    }

    /**
     * Setter for the ProgramName.
     * @param programName - The programs name as String.
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * Setter for the StartTimeUTC.
     * @param startTimeUTC - The programs start time as LocalDateTime.
     */
    public void setStartTimeUTC(LocalDateTime startTimeUTC) {
        this.startTimeUTC = startTimeUTC;
    }

    /**
     * Setter for the Title.
     * @param title - The programs title as String.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
