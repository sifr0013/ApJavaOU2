package Tests;

import Model.ChannelModel;
import Model.RadioXMLReader;
import Model.ScheduledEpisodeModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Simon on 2018-12-24.
 *
 * Public class RadioXMLReaderTest.
 * Used for doing JUnit4 tests of the RadioXMLReader.
 */
public class RadioXMLReaderTest {
    private RadioXMLReader rxmlr;
    private final String CHANNEL_URL = "http://api.sr.se/api/v2/channels/";
    private final String SCHEDULED_EPISODE_URL =
            "http://api.sr.se/api/v2/scheduledepisodes";
    private final int CHANNEL_ID = 132;

    @Before
    public void startUp() throws IOException, SAXException,
            ParserConfigurationException {

        rxmlr = new RadioXMLReader();
    }

    @After
    public void tearDown(){
        rxmlr = null;
    }

    @Test
    public void shouldPrintOutChannelModelFromURL() throws
            ParserConfigurationException, IOException, SAXException,
            IllegalAccessException {
        ArrayList<ChannelModel> cmList = rxmlr.getChannelModels(CHANNEL_URL);
        assertNotNull(cmList);
        assertNotEquals(0,cmList.size());
    }

    @Test
    public void shouldPrint() throws IllegalAccessException, IOException,
            SAXException, ParserConfigurationException {
        ArrayList<ScheduledEpisodeModel> semList = rxmlr.
                getScheduledEpisodeModels(SCHEDULED_EPISODE_URL,CHANNEL_ID,
                        LocalDateTime.now(),true);
        assertNotEquals(0,semList.size());
        assertNotNull(semList);
    }
}
