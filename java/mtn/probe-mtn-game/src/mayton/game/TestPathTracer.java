package mayton.game;

//import com.sun.media.jai.widget.DisplayJAI;

import org.apache.log4j.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.imageio.ImageIO;

import java.awt.event.*;
import java.awt.*;


import java.io.FileInputStream;

import javax.swing.UIManager;
import mayton.image.Raster;
import mayton.image.bmp.BitmapImportFilter;


public class TestPathTracer extends JFrame implements ChangeListener, ActionListener, ItemListener {

    

    private JSlider     slider;
    private JCheckBox   interactive;
    //private PlanarImage image;
    //private DisplayJAI  display;


    public Image backgroundImage;

    Raster r1;

    JFileChooser FileChooser;

    static Logger logger = Logger.getLogger("ua.dn.mayton.game.TestPathTracer");

    JMenuBar menuBar;

    public TestPathTracer()
    {
        logger.info("Constructor");

        try{
            backgroundImage= ImageIO.read(new FileInputStream("C:\\java\\Labirinth\\Forest1.bmp"));
        }catch(Exception ex){}
        
        menuBar=new JMenuBar();

        JMenu menu = new JMenu("File");

            menu.setMnemonic(KeyEvent.VK_A);
            menuBar.add(menu);

                JMenuItem menuItem = new JMenuItem("Open labirinth...", KeyEvent.VK_0);
                menuItem.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_1, ActionEvent.ALT_MASK));
                menuItem.addActionListener(this);
                menu.add(menuItem);

                JMenuItem menuItem2 = new JMenuItem("Save statistics...", KeyEvent.VK_2);
                menuItem2.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_3, ActionEvent.ALT_MASK));
                menuItem2.addActionListener(this);
                menu.add(menuItem2);

                JMenuItem menuItem3 = new JMenuItem("Save labirinth image...", KeyEvent.VK_0);
                menuItem3.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_3, ActionEvent.ALT_MASK));
                menuItem3.addActionListener(this);
                menu.add(menuItem3);

                JMenuItem menuItem7 = new JMenuItem("Exit");                
                menuItem7.addActionListener(this);
                menu.add(menuItem7);


            JMenu menu2 = new JMenu("Options");
            menuBar.add(menu2);

                JMenuItem menuItem5 = new JMenuItem("Plugins", KeyEvent.VK_4);
                menuItem5.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_5, ActionEvent.ALT_MASK));
                menuItem5.addActionListener(this);
                menu2.add(menuItem5);

                JMenuItem menuItem4 = new JMenuItem("Reset statistics", KeyEvent.VK_4);
                menuItem4.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_5, ActionEvent.ALT_MASK));
                menuItem4.addActionListener(this);
                menu2.add(menuItem4);



        this.setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("PathTracer Test container 1.0");

        pack(); // adjust the frame size using preferred dimensions.
 		setVisible(true); // show the frame.
        this.setLocation(20,20);
        this.setSize(640,480);
        

    }

    /*
    public void paint(Graphics g)
    {
        this.getContentPane().getGraphics().drawImage(backgroundImage,0,0,null);        
    } */

    public void paintComponent(Graphics g)
    {
        g.drawImage(backgroundImage,0,0,null);        
    }

    public static void main(String[] args) throws Exception
    {
        //org.junit.runner.JUnitCore.main(TestPathTracer.class.getName());
        BasicConfigurator.configure();
        DailyRollingFileAppender MainAppender = new DailyRollingFileAppender(
			new PatternLayout("%r [%t] %c %m %n"),
			"TestPathTracer.log",
			"'.'yyyy-MM-dd"
		);

        Logger DTDEntityResolverLogger  = logger.getLoggerRepository().getLogger("ua.dn.mayton.game.TestPathTracer");
        DTDEntityResolverLogger.addAppender(MainAppender);

        logger.info("main");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new TestPathTracer();
    }

    @Override
    public void finalize()
    {
        logger.info("finalize");
    }

    public void stateChanged(ChangeEvent e)
    {
        logger.info("stateChanged");
    }

    public void OpenLabirinth()
    {

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("BMP Images", "bmp");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {

            logger.info("Selected file : "+chooser.getSelectedFile().getPath());

            BitmapImportFilter bif=BitmapImportFilter.createInstance();
            r1=null;
            try{
                r1=bif.from(new FileInputStream(chooser.getSelectedFile().getPath()));
            }catch(Exception ex){}
            
        }
        
    }

    public void actionPerformed(ActionEvent e)
    {
        logger.info("command = "+e.getActionCommand()+" modifiers = "+e.getModifiers()+" id = "+e.getID());
        if (e.getActionCommand().equals("Open labirinth...")) OpenLabirinth();
    }

    public void itemStateChanged(ItemEvent e)
    {
        logger.info("itemStateChanged");
    }
}
