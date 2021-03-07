package mayton.geo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class App  {


    private static void createAndShowGUI() {

        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //JLabel emptyLabel = new JLabel("Label1");
        //emptyLabel.setPreferredSize(new Dimension(175, 100));
        //frame.getContentPane().add(emptyLabel, BorderLayout.CENTER );

        frame.add(new MapViewer());

        frame.pack();
        frame.setVisible(true);
        frame.setSize(320,200);
        frame.setLocation(10,10);
        
    }


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


}
