package mayton.ignite;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;

public class ShtuchkaMainFrame extends JFrame implements ActionListener, ItemListener {

    static Logger logger = LoggerFactory.getLogger(ShtuchkaMainFrame.class);

    private JList  assetList;
    private JTable metadata;
    private JTable timeSeries;
    private ShtuchkaApplication shtuchkaApplication;

    public ShtuchkaMainFrame(@NotNull ShtuchkaApplication shtuchkaApplication) throws HeadlessException {
        super("Shtuchka 1.0");
        createMenus(this);
        this.shtuchkaApplication = shtuchkaApplication;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        logger.info(":: actionPerformed = " + actionEvent.toString());
        switch (actionEvent.getActionCommand()) {
            case "Dev" :

                break;
            case "Local" :
                break;


            default:
                // nothing
        }
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        logger.info(":: itemStateChanged = " + itemEvent.toString());
    }

    private void createMenus(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu igniteMenu = new JMenu("Ignite");
        menuBar.add(igniteMenu);


        JMenu connectMenu = new JMenu("Connect");
        igniteMenu.add(connectMenu);

        ///////////////////////////////////////////////////////////////////////////////////
        JMenuItem local = new JMenuItem("Local", KeyEvent.VK_L);
        connectMenu.add(local);
        local.addActionListener(this);

        JMenuItem dev = new JMenuItem("Dev", KeyEvent.VK_D);
        connectMenu.add(dev);
        dev.addActionListener(this);



        /////////////////////////////////////////////////////////////////////////////

        igniteMenu.addSeparator();

        igniteMenu.add(new JMenuItem("Export", KeyEvent.VK_E));

        igniteMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");

        igniteMenu.add(exitItem);

        exitItem.addActionListener((listener) -> { System.exit(0);});

        frame.setJMenuBar(menuBar);


    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new ShtuchkaMainFrame(new ShtuchkaApplication());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        //frame.setSize(1920, 1080);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }


    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
