package View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Simon on 2018-12-24.
 */
public class TopBarMenu extends JMenuBar {

    JMenu channelMenu;
    static JButton programInfo;
    static JButton update;

    public TopBarMenu(String[] channelNames, int[] channelIds, ActionListener selectingChannelInMenu){
        channelMenu = new JMenu("Channel Menu....");
        channelMenu.setMnemonic(KeyEvent.VK_N);

        programInfo = new JButton("Program Info");
        programInfo.setMnemonic(KeyEvent.VK_N);

        update = new JButton("Update Now");
        update.setMnemonic(KeyEvent.VK_N);

        this.add(channelMenu);
        this.add(programInfo);
        this.add(update);

        updateMenu(channelNames,channelIds,selectingChannelInMenu);
    }

    public void updateMenu(String[] channelNames, int[] channelIds, ActionListener selectingChannelInMenu){
        this.remove(this.getMenu(0));
        this.add(new JMenu("Channel Menu"),0);
        for (int i = 0; i < channelNames.length; i++) {
            JMenuItem tempMenuItem = new JMenuItem(channelNames[i]);
            tempMenuItem.putClientProperty("channelid",channelIds[i]);
            tempMenuItem.addActionListener(selectingChannelInMenu);
            this.getMenu(0).add(tempMenuItem);
        }
        this.revalidate();
    }

    // TODO: 2019-01-07 Lägg till så att det kommer upp en popup när man trycker på programinfo.
    public static void programInfoButtonListener(ActionListener actionListener){
        programInfo.addActionListener(actionListener);
    }

    public static void updateButtonListener(ActionListener actionListener) {
        update.addActionListener(actionListener);
    }
}
