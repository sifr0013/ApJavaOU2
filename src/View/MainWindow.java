package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Simon on 2018-12-23.
 */
public class MainWindow{

    private JFrame frame;
    private JTable programTable;
    private TopBarMenu topBarMenu;
    private JScrollPane scrollPane;
    private DefaultTableModel mtm;

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

    public void updateButtonListener(ActionListener actionListener){
        TopBarMenu.updateButtonListener(actionListener);
    }

    public void programInfoButtonListener(ActionListener actionListener){
        TopBarMenu.programInfoButtonListener(actionListener);
    }


    public void updateTable(Object[][] tableData){
        mtm.setRowCount(0);
        for (int i = 0; i < tableData.length; i++) {
            mtm.addRow(new Object[]{tableData[i][0],tableData[i][1],tableData[i][2]});
        }
    }

    public Object[] getSelectedTableRow() throws ArrayIndexOutOfBoundsException{
        int row = programTable.getSelectedRow();
        return new Object[]{programTable.getValueAt(row,0),
                programTable.getValueAt(row,1),
                programTable.getValueAt(row,2)};
    }

    public void setTitle(String newTitle){
        frame.setTitle(newTitle);
        frame.validate();
        frame.repaint();
    }
}
