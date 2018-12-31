package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Simon on 2018-12-23.
 */
public class MainWindow implements ActionListener{

    private JFrame frame;
    private JTable programTable;
    private TopBarMenu topBarMenu;
    private JScrollPane scrollPane;

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

        programTable = new JTable(tableData, columnNames);
        scrollPane = new JScrollPane(programTable);

        Container container = new Container();
        container.setLayout(new BorderLayout());
        container.add(programTable.getTableHeader(), BorderLayout.PAGE_START);
        container.add(scrollPane, BorderLayout.CENTER);

        frame.add(container);

        frame.setSize(900,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
