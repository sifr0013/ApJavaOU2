package Model;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Public class for a RadioXMLReader. Is used for reading XML-documents from
 * sr.se's API. Returns either a ChannelModel or a ScheduledEpisodeModel
 * depending on which method is called.
 */
public class RadioXMLReader {

    /**
     * Method to convert data from 'Sveriges Radio' via URL to a ChannelModel
     * object.
     * @param xmlURLString - The URL-string
     * @return An ArrayList of all the channels on the URL.
     * @throws IllegalAccessException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public ArrayList<ChannelModel> getChannelModels(String xmlURLString) throws
            IllegalAccessException, ParserConfigurationException, IOException,
            SAXException {
        if (xmlURLString.contains("channels") && !xmlURLString.
                contains("?page=")){
            ArrayList<ChannelModel> cms = new ArrayList<>();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL(xmlURLString).openStream());

            doc.getDocumentElement().normalize();

            Node totalPages = doc.getElementsByTagName("totalpages").item(0);
            int nrOfTotalPages = Integer.parseInt(totalPages.getTextContent());

            //For every page with channels
            for (int i = 0; i < nrOfTotalPages; i++) {
                dbf = DocumentBuilderFactory.newInstance();
                db = dbf.newDocumentBuilder();
                doc = db.parse(new URL(xmlURLString+"?page="+(i+1)).
                        openStream());


                NodeList channelNodes = doc.getElementsByTagName("channel");

                //For every channel
                for (int j = 0; j < channelNodes.getLength(); j++) {
                    Node tempNode = channelNodes.item(j);
                    NamedNodeMap attributes = tempNode.getAttributes();

                    //Make new channelmodel with correct ID and channel name
                    ChannelModel cm = new ChannelModel(Integer.parseInt(
                            attributes.item(0).getNodeValue()),
                            attributes.item(1).getNodeValue());

                    if (tempNode.hasChildNodes()){
                        NodeList channelChildNodes = tempNode.getChildNodes();

                        //For every channel child
                        for (int l = 0; l < channelChildNodes.getLength(); l++){
                            Node childTempNode = channelChildNodes.item(l);

                            switch (childTempNode.getNodeName()){
                                case "image": cm.setImageURL(new URL(
                                        childTempNode.getTextContent())); break;
                                case "imagetemplate": cm.setImageTemplateURL(
                                        new URL(childTempNode.
                                                getTextContent())); break;
                                case "color": cm.setColor(Color.decode("#"+
                                        childTempNode.getTextContent())); break;
                                case "tagline": cm.setTagline(childTempNode.
                                        getTextContent()); break;
                                case "siteurl": cm.setSiteURL(new URL(
                                        childTempNode.getTextContent())); break;
                                case "liveaudio":
                                    NamedNodeMap liveAttributes = childTempNode.
                                            getAttributes();
                                    NodeList liveAttributeChildren =
                                            childTempNode.getChildNodes();
                                    int laID = Integer.parseInt(liveAttributes.
                                            item(0).getNodeValue());
                                    URL url = new URL(liveAttributeChildren.
                                            item(1).getTextContent());
                                    String statkey = liveAttributeChildren.
                                            item(2).getTextContent();
                                    cm.setLiveAudioModel(new LiveAudioModel(
                                            laID,url,statkey));
                                    break;
                                case "scheduleurl": cm.setScheduleURL(new URL(
                                        childTempNode.getTextContent())); break;
                                case "channeltype": cm.setChannelType(
                                        childTempNode.getTextContent()); break;
                                case "xmltvid": cm.setXmltvid(
                                        childTempNode.getTextContent()); break;
                            }
                        }
                    }
                    cms.add(cm);
                }
            }

            return cms;
        } else {
            throw new IllegalAccessException("URL is not of Channel type.");
        }
    }

    /**
     * Public method to parse XML from an URL to a ScheduledEpisodeModel.
     * Gets the scheduled episodes airing at the time specified as well as the
     * episodes 12 hours before and 12 hours after the specified time.
     * It does this by a recursive manner.
     * @param xmlURLString
     * @param channelId
     * @param ldt - The specified time to get the episodes.
     * @param isFirst - True = if its the original call.
     *                  False = if its the recursive call.
     * @return
     * @throws IllegalAccessException
     */
    public ArrayList<ScheduledEpisodeModel> getScheduledEpisodeModels(
            String xmlURLString, int channelId, LocalDateTime ldt,
            boolean isFirst) throws IllegalAccessException,
            ParserConfigurationException, IOException, SAXException {
        if (xmlURLString.contains("scheduledepisodes")) {
            ArrayList<ScheduledEpisodeModel> sems = new ArrayList<>();

            int hourRightNow = ldt.getHour();

            String newUrlString;
            if (!xmlURLString.contains("?channelid=")){
                newUrlString = xmlURLString+"?channelid="+channelId+"&date="+
                        ldt.toLocalDate();
            } else {
                newUrlString = xmlURLString+channelId+"&date="+
                        ldt.toLocalDate();
            }

            //Opens the first URL-stream to get the total number of pages
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL(newUrlString).openStream());

            doc.getDocumentElement().normalize();

            int totalPages = Integer.parseInt(doc.getElementsByTagName(
                    "totalpages").item(0).getTextContent());

            //For every page with scheduled episodes
            for (int i = 0; i < totalPages; i++) {
                dbf = DocumentBuilderFactory.newInstance();
                db = dbf.newDocumentBuilder();
                doc = db.parse(new URL(newUrlString+"&page="+(i+1)).
                        openStream());

                NodeList scheduledEpisodesNL = doc.getElementsByTagName(
                        "scheduledepisode");

                //For every scheduled episodes
                for (int j = 0; j < scheduledEpisodesNL.getLength(); j++) {
                    Node tempNode = scheduledEpisodesNL.item(j);
                    ScheduledEpisodeModel sem = new ScheduledEpisodeModel();
                    if (tempNode.hasChildNodes()){
                        NodeList tempNodeChildren = tempNode.getChildNodes();

                        //For every element in scheduled episodes
                        for (int k = 0; k < tempNodeChildren.getLength(); k++) {
                            Node tempChildNode = tempNodeChildren.item(k);

                            switch (tempChildNode.getNodeName()){
                                case "episodeid": sem.setEpisodeID(Integer.
                                        parseInt(tempChildNode.
                                                getTextContent())); break;
                                case "title": sem.setTitle(tempChildNode.
                                        getTextContent()); break;
                                case "description": sem.setDescription(
                                        tempChildNode.getTextContent()); break;
                                case "starttimeutc": sem.setStartTimeUTC(
                                        LocalDateTime.parse(tempChildNode.
                                                getTextContent().
                                                replace("Z",""))); break;
                                case "endtimeutc": sem.setEndTimeUTC(
                                        LocalDateTime.parse(tempChildNode.
                                                getTextContent().
                                                replace("Z",""))); break;
                                case "program":
                                    NamedNodeMap programAttributes =
                                            tempChildNode.getAttributes();
                                    sem.setProgramID(Integer.parseInt(
                                            programAttributes.item(0).
                                                    getNodeValue()));
                                    try{
                                        sem.setProgramName(programAttributes.
                                                item(1).getNodeValue());
                                    } catch (NullPointerException e){ sem.
                                            setProgramName("");
                                    }
                                    break;
                                case "channel":
                                    NamedNodeMap channelAttributes =
                                            tempChildNode.getAttributes();
                                    sem.setChannelID(Integer.parseInt(
                                            channelAttributes.item(0).
                                                    getNodeValue()));
                                    sem.setChannelName(channelAttributes.
                                            item(1).getNodeValue());
                                    break;
                                case "imageurl": sem.setImageURL(new URL(
                                        tempChildNode.getTextContent()));
                                    break;
                                case "imageurltemplate": sem.
                                        setImageTemplateURL(new URL(
                                                tempChildNode.
                                                        getTextContent()));
                                    break;
                            }
                        }
                    }
                    sems.add(sem);
                }
            }

            //If its the original call, do recursive action. Else skip.
            if (isFirst){
                ArrayList<ScheduledEpisodeModel> newSemList;
                if (shouldWeGetYesterday(hourRightNow)){

                    newSemList = getScheduledEpisodeModels(xmlURLString,
                            channelId,ldt.minusHours(12),false);
                } else {

                    newSemList = getScheduledEpisodeModels(xmlURLString,
                            channelId,ldt.plusHours(12),false);
                }
                //Following is to add recursive list to original aswell as
                // handling duplicates
                ArrayList<ScheduledEpisodeModel> bothListsWithDuplicates =
                        new ArrayList<>();
                bothListsWithDuplicates.addAll(sems);
                bothListsWithDuplicates.addAll(newSemList);
                Set<ScheduledEpisodeModel> removeDuplicatesSet =
                        new LinkedHashSet<>(bothListsWithDuplicates);
                sems = new ArrayList<>();
                sems.addAll(removeDuplicatesSet);
            }
            return sems;
        } else {
            throw new IllegalAccessException(
                    "URL is not of ScheduledEpisode type.");
        }
    }

    /**
     * Private method to check if we should get yesterdays programs or not.
     * If false, we will get tomorrows programs instead.
     * @param hour - the current hour.
     * @return true - if we should get yesterdays programs.
     *          false - if we should get tomorrows programs.
     */
    private boolean shouldWeGetYesterday(int hour){
        boolean b;
        if (hour<=12){
            b = true;
        } else {
            b = false;
        }
        return b;
    }
}
