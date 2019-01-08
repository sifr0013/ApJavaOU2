package Controller;

import Model.ChannelModel;
import Model.RadioXMLReader;
import Model.ScheduledEpisodeModel;
import View.MainWindow;
import View.ProgramInfoWindow;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * Created by Simon on 2018-12-24.
 */
public class RadioInfoController{

    private static final String CHANNELS_URL = "http://api.sr.se/api/v2/channels/";
    private static final String EPISODES_URL = "http://api.sr.se/api/v2/scheduledepisodes";
    private ArrayList<ChannelModel> channels;
    private ArrayList<ScheduledEpisodeModel> episodes;
    private String[] channelNames;
    private int[] channelIds;
    private final String[] TABLE_HEADERS = {"Program","Start time","End time"};
    private ChannelModel currentChannel;
    private MainWindow mw;

    public RadioInfoController(){
        try {
            RadioXMLReader rxmlr = new RadioXMLReader();
            channels = rxmlr.
                    getChannelModels(CHANNELS_URL);
        } catch (IllegalAccessException | ParserConfigurationException |
                SAXException | IOException e) {
            e.printStackTrace();
        }

        updateChannelInfo();

        currentChannel = channels.get(0);

        runGUI();
    }

    private void runGUI(){

        ActionListener channelButtonListener = menuItemActionListener();

        try {
            mw = new MainWindow(currentChannel.getChannelName(),
                    channelNames,channelIds,channelButtonListener,TABLE_HEADERS,
                    getTableData(currentChannel.getId(),LocalDateTime.now()));


            javax.swing.SwingUtilities.invokeLater(() ->{

                mw.updateButtonListener(e -> {
                    try {
                        mw.updateTable(getTableData(currentChannel.getId(),LocalDateTime.now()));
                    } catch (ParserConfigurationException | IOException |
                            IllegalAccessException | SAXException e1) {
                        e1.printStackTrace();
                    }
                });

                mw.programInfoButtonListener(e -> {
                    try{
                        Object[] selectedProgram = mw.getSelectedTableRow();
                        LocalDateTime startTime =
                                (LocalDateTime) selectedProgram[1];
                        LocalDateTime endTime =
                                (LocalDateTime) selectedProgram[2];

                        episodes.forEach((episode)->{
                            if (episode.getProgramName()==selectedProgram[0]&&
                                    episode.getEndTimeUTC()==endTime&&
                                    episode.getStartTimeUTC()==startTime){
                                ProgramInfoWindow popup = new ProgramInfoWindow(
                                        episode.getProgramName(),
                                        episode.getDescription(),
                                        episode.getImageURL());
                            }
                        });
                    } catch (ArrayIndexOutOfBoundsException ignored){}
                });

            });

            startBackgroundUpdate();



        } catch (ParserConfigurationException | IOException |
                IllegalAccessException | SAXException e) {
            e.printStackTrace();
        }

    }

    /**
     * Private method to do hourly updates of the tabledata. Runs in the background.
     * Thread safe.
     */
    private void startBackgroundUpdate() {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture sf = ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    mw.updateTable(getTableData(currentChannel.getId(),LocalDateTime.now()));
                } catch (ParserConfigurationException | IOException | IllegalAccessException | SAXException e) {
                    e.printStackTrace();
                }
            }
        },1,1,TimeUnit.HOURS);
    }

    private Object[][] getTableData(int channelId, LocalDateTime ldt)
            throws ParserConfigurationException, IOException, SAXException,
            IllegalAccessException {

        RadioXMLReader rxmlr = new RadioXMLReader();
        episodes = rxmlr.
                getScheduledEpisodeModels(EPISODES_URL,channelId,ldt,true);
        Object[][] tableData = new Object[episodes.size()][3];

        for (int i = 0; i < episodes.size(); i++) {
            ScheduledEpisodeModel temp = episodes.get(i);
            LocalDateTime now = LocalDateTime.now();
            if (temp.getEndTimeUTC().isBefore(now)){ //Programmet har slutat
                tableData[i][0] = temp.getProgramName()+" (SLUTAT)";
            } else if ((temp.getEndTimeUTC().isEqual(now) ||
                    temp.getEndTimeUTC().isAfter(now)) &&
                    (temp.getStartTimeUTC().isEqual(now) ||
                            temp.getStartTimeUTC().isBefore(now))) { //Programmet körs
                tableData[i][0] = temp.getProgramName()+" (SPELAR NU)";
            } else { //Programmet har inte körts
                tableData[i][0] = temp.getProgramName();
            }

            tableData[i][1] = temp.getStartTimeUTC();
            tableData[i][2] = temp.getEndTimeUTC();
        }

        return tableData;
    }

    /**
     * Used to update the menu items. Called one time.
     */
    private void updateChannelInfo(){
        channelNames = new String[channels.size()];
        channelIds = new int[channels.size()];

        for (int i = 0; i < channels.size(); i++) {
            ChannelModel tempcm = channels.get(i);
            channelNames[i] = tempcm.getChannelName();
            channelIds[i] = tempcm.getId();
        }
    }

    private ActionListener menuItemActionListener(){
        ActionListener actionListener = e -> {
            int selectedChannelId = (Integer)((JMenuItem)e.getSource()).getClientProperty("channelid");
            updateCurrentChannel(selectedChannelId);
            try {
                mw.setTitle(currentChannel.getChannelName());
                mw.updateTable(getTableData(currentChannel.getId(),LocalDateTime.now()));
            } catch (ParserConfigurationException | IOException |
                    IllegalAccessException | SAXException e1) {
                e1.printStackTrace();
            }
        };
        return actionListener;
    }

    /**
     * Private method to update the current channel of the program to the selected ID.
     * @param channelId - The selected ID.
     */
    private void updateCurrentChannel(int channelId){
        channels.forEach(channelModel -> {
            if (channelModel.getId() == channelId){
                currentChannel = channelModel;
            }
        });
    }

}
