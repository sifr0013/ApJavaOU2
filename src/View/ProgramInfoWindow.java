package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Simon on 2019-01-08.
 *
 * Public class for ProgramInfoWindow.
 * Is used to create a popup window of the selected episode's information.
 */
public class ProgramInfoWindow extends JOptionPane{
    /**
     * Public constructor for the ProgramInfoWindow.
     * @param programName - String of the selected programs name.
     * @param description - String of the selected programs description.
     * @param imageURL - URL of the selected programs Image.
     */
    public ProgramInfoWindow(String programName, String description, URL imageURL){
        Image image;
        try {
            image = ImageIO.read(imageURL);
            ImageIcon icon = new ImageIcon(image);
            this.showMessageDialog(this, "<html><body><p style='width: 400px;'>"+
                            description+
                            "</p></body></html>",programName,
                    JOptionPane.INFORMATION_MESSAGE,icon);
        } catch (IOException | IllegalArgumentException e) {
            this.showMessageDialog(this, "<html><body><p style='width: 400px;'>"+
                            description+
                            "</p></body></html>",programName,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
