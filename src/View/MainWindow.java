package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

/**
 * Created by Simon on 2018-12-23.
 *
 * Public class for creating a MainWindow.
 * Is part of the view of the RadioInfo program.
 */
public class MainWindow{

    private JFrame frame;
    private JTable programTable;
    private TopBarMenu topBarMenu;
    private JScrollPane scrollPane;
    private DefaultTableModel mtm;

    /**
     * Public constructor for the MainWindow.
     * @param title - String for the Title of the GUI.
     * @param channelNames - String[] for the channels names, used in the
     *                     dropdown menu's.
     * @param channelIds - int[] for the channels identification number, used
     *                   in the dropdown menu's.
     * @param selectingChannelInMenu - ActionListener for the dropdown menu.
     * @param columnNames - String[] for the tables header for the columns.
     * @param tableData - Object[][] for the tables data.
     */
    public MainWindow(String title, String[] channelNames, int[] channelIds,
                      ActionListener selectingChannelInMenu,
                      String[] columnNames, Object[][] tableData){
        frame = init(title, channelNames, channelIds, selectingChannelInMenu,
                columnNames, tableData);
    }

    private JFrame init(String title, String[] channelNames, int[] channelIds,
                        ActionListener selectingChannelInMenu,
                        String[] columnNames, Object[][] tableData) {
        frame = new JFrame(title);
        topBarMenu = new TopBarMenu(channelNames, channelIds,
                selectingChannelInMenu);
        frame.setJMenuBar(topBarMenu);

        programTable = new JTable();

        mtm = new DefaultTableModel(0,0);
        programTable.setModel(mtm);
        mtm.setColumnIdentifiers(columnNames);
        mtm.addRow(new Object[]{ "Loading", "Loading", "Loading" });
        updateTable(tableData);

        scrollPane = new JScrollPane(programTable);
        frame.add(scrollPane);

        frame.setSize(900,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        return frame;
    }

    /**
     * Public method to set the actionlistener for the UpdateButton of the
     * TopBarMenu.
     * @param actionListener - ActionListener for the UpdateButton.
     */
    public void updateButtonListener(ActionListener actionListener){
        TopBarMenu.updateButtonListener(actionListener);
    }

    /**
     * Public method to set the actionlistener for the ProgramInfoButton of the
     * TopBarMenu.
     * @param actionListener - ActionListener for the ProgramInfoButton.
     */
    public void programInfoButtonListener(ActionListener actionListener){
        TopBarMenu.programInfoButtonListener(actionListener);
    }

    /**
     * Public method to update the tables data.
     * @param tableData - Object[][] of the tables data.
     *                  Should be of form {(String)Episode name,
     *                  (LocalDateTime)Start time, (LocalDateTime)End time}
     *                  as columns.
     */
    public void updateTable(Object[][] tableData){
        mtm.setRowCount(0);
        for (int i = 0; i < tableData.length; i++) {
            mtm.addRow(new Object[]{tableData[i][0],tableData[i][1],tableData[i][2]});
        }
    }

    /**
     * Public method to get the selected rows information from the table.
     * @return - Object[] of the selected table row.
     * @throws ArrayIndexOutOfBoundsException
     */
    public Object[] getSelectedTableRow() throws ArrayIndexOutOfBoundsException{
        int row = programTable.getSelectedRow();
        return new Object[]{programTable.getValueAt(row,0),
                programTable.getValueAt(row,1),
                programTable.getValueAt(row,2)};
    }

    /**
     * Public method to update the title of the window.
     * Should be called when changing channel.
     * @param newTitle - String of the new Title.
     */
    public void setTitle(String newTitle){
        frame.setTitle(newTitle);
        frame.validate();
        frame.repaint();
    }
}
