package View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Simon on 2018-12-24.
 *
 * Public class for creating a TopBarMenu.
 * Used to create and fill the JMenuBar of the GUI.
 */
public class TopBarMenu extends JMenuBar {

    private static JButton programInfo;
    private static JButton update;

    /**
     * Public constructor for the TopBarMenu.
     * @param channelNames - String[] of the channel names.
     *                     Used for filling the dropdown menu's with the
     *                     channels names.
     * @param channelIds - int[] of the channels id's.
     *                   Used for putting the clients properties of the
     *                   menu items.
     * @param selectingChannelInMenu - ActionListener for the menu items.
     */
    public TopBarMenu(String[] channelNames, int[] channelIds, ActionListener selectingChannelInMenu){

        programInfo = new JButton("Program Info");
        programInfo.setMnemonic(KeyEvent.VK_N);

        update = new JButton("Update Now");
        update.setMnemonic(KeyEvent.VK_N);

        this.add(programInfo);
        this.add(update);

        updateMenues(channelNames,channelIds,selectingChannelInMenu);
    }

    private void updateMenues(String[] channelNames, int[] channelIds, ActionListener selectingChannelInMenu){
        JMenu channelMenu1 = new JMenu("Channel Menu (1)");
        channelMenu1.setMnemonic(KeyEvent.VK_N);

        JMenu channelMenu2 = new JMenu("Channel Menu (2)");
        channelMenu2.setMnemonic(KeyEvent.VK_N);

        this.add(channelMenu1);
        this.add(channelMenu2);

        for (int i = 0; i < channelNames.length; i++) {
            JMenuItem tempMenuItem = new JMenuItem(channelNames[i]);
            tempMenuItem.putClientProperty("channelid",channelIds[i]);
            tempMenuItem.addActionListener(selectingChannelInMenu);
            if (i%2 == 0){
                this.getMenu(this.getComponentIndex(channelMenu1)).add(tempMenuItem);
            } else{
                this.getMenu(this.getComponentIndex(channelMenu2)).add(tempMenuItem);
            }
        }
        this.revalidate();
    }

    /**
     * Public method for setting the ProgramInfoButton's actionlistener.
     * @param actionListener - ActionListener for the ProgramInfoButton.
     */
    public static void programInfoButtonListener(ActionListener actionListener){
        programInfo.addActionListener(actionListener);
    }

    /**
     * Public method for setting the UpdateButton's actionlistener.
     * @param actionListener - ActionListener for the UpdateButton.
     */
    public static void updateButtonListener(ActionListener actionListener) {
        update.addActionListener(actionListener);
    }
}
