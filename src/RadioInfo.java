import View.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

/**
 * Created by Simon on 2018-12-23.
 */
public class RadioInfo {
    public static void main(String[] args) {

        testGUI();
    }




    public static void testGUI(){
        String title = "Test title";
        String[] channelNames = {"Channel 1","Channel 2","Channel 3"};
        int[] channelIds = {1,2,3};

        String[] columnNames = {"Program","Start time","End time"};

        Object[][] tableData = {
                {"Program 1", LocalDateTime.now(),
                        LocalDateTime.now().plusHours(12)},
                {"Program 2",LocalDateTime.now(),
                        LocalDateTime.now().plusHours(12)},
                {"Program 3",LocalDateTime.now(),
                        LocalDateTime.now().plusHours(12)}};
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedChannelId = (Integer)((JMenuItem)e.getSource()).getClientProperty("channelid");
                System.out.println("Got the channel ID: "+selectedChannelId);
            }
        };
        MainWindow mw = new MainWindow(title,channelNames,channelIds,actionListener,columnNames,tableData);

        javax.swing.SwingUtilities.invokeLater(() -> {

        });
    }
}
