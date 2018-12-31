package Controller;

import Model.ChannelModel;
import Model.RadioXMLReader;
import Model.ScheduledEpisodeModel;
import View.MainWindow;
import com.sun.xml.internal.bind.v2.TODO;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Simon on 2018-12-24.
 */
public class RadioInfoController implements Runnable{

    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();
    private static final String CHANNELS_URL = "http://api.sr.se/api/v2/channels/";
    private static final String EPISODES_URL = "http://api.sr.se/api/v2/scheduledepisodes";
    private ArrayList<ChannelModel> channels;
    private String[] channelNames;
    private int[] channelIds;
    private final String[] TABLE_HEADERS = {"Program","Start time","End time"};
    private Object[][] tableData;
    private ChannelModel currentChannel;

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

        ActionListener channelButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        try {
            MainWindow mw = new MainWindow(currentChannel.getChannelName(),
                    channelNames,channelIds,channelButtonListener,TABLE_HEADERS,
                    getTableData(currentChannel.getId(),LocalDateTime.now()));
        } catch (ParserConfigurationException | IOException |
                IllegalAccessException | SAXException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        Thread updateDataThread = new Thread(() -> {
            while (running) {
                synchronized (pauseLock) {
                    if (!running) {
                        break;
                    }
                    if (paused) {
                        try{
                            pauseLock.wait(); //ISTÄLLET FÖR ATT PAUSA, GÖR EN UPPDATERING.
                        } catch (InterruptedException e){
                            break;
                        }
                        if (!running) {
                            break;
                        }
                    }
                }


                try {
                    updateData(); //Uppdatera all data i tabellen. Detta ska ske:
                                    // Varje timme, när någon trycker på uppdatera,
                                    // eller när man byter kanal.
                } catch (Exception e){}//Lägg till lämplig exception
                // TODO: 2018-12-28 GUI .redraw() eller något liknande

                try {
                    Thread.sleep(1000); // GÖR SÅ MAN TAR TID
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateDataThread.start();
    }

    private Object[][] getTableData(int channelId, LocalDateTime ldt)
            throws ParserConfigurationException, IOException, SAXException,
            IllegalAccessException {

        RadioXMLReader rxmlr = new RadioXMLReader();
        ArrayList<ScheduledEpisodeModel> episodes = rxmlr.
                getScheduledEpisodeModels(EPISODES_URL,channelId,ldt,true);
        Object[][] tableData = new Object[episodes.size()][3];

        for (int i = 0; i < episodes.size(); i++) {
            tableData[i][0] = episodes.get(i).getProgramName();
            tableData[i][1] = episodes.get(i).getStartTimeUTC();
            tableData[i][2] = episodes.get(i).getEndTimeUTC();
        }

        return tableData;
    }

    private void updateChannelInfo(){
        channelNames = new String[channels.size()];
        channelIds = new int[channels.size()];

        for (int i = 0; i < channels.size(); i++) {
            ChannelModel tempcm = channels.get(i);
            channelNames[i] = tempcm.getChannelName();
            channelIds[i] = tempcm.getId();
        }
    }

    private void updateData(){

    }

}
