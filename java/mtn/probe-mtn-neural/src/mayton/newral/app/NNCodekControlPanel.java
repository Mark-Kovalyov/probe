package mayton.newral.app;

import java.awt.Event;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class NNCodekControlPanel extends JFrame
{
    public NNCodekMain NNCodekMainObj;

    NNActionListener NNActionListenerObj;

    ArrayList <JFrame> ChildFrames = new ArrayList<JFrame>();

    public NNCodekControlPanel(NNCodekMain NNCodekMainObj)
    {
        super("Neural image encoder/decoder 1.0.0001");
        this.NNCodekMainObj=NNCodekMainObj;
        NNActionListenerObj=new NNActionListener(this);

        setSize(800, 600);
        setLocation(10, 10);

        // create the Utensils menu
    JMenu utensils = new JMenu("File");

        utensils.setMnemonic(KeyEvent.VK_U);


        JMenuItem jma=new JMenuItem("Open image..");

        utensils.add(jma);

        jma.addActionListener(NNActionListenerObj);

        utensils.add(new JMenuItem("Save result image.."));
        utensils.addSeparator();
        utensils.add(new JMenuItem("Save neural preset.."));


    JMenu hybrid = new JMenu("Save reports");

        hybrid.add(new JMenuItem("Differencial"));
        hybrid.add(new JMenuItem("Statistics"));
        hybrid.add(new JMenuItem("All"));
        utensils.add(hybrid);
        utensils.addSeparator();

    // do some fancy stuff with the Quit item
    JMenuItem quitItem = new JMenuItem("Quit");
    quitItem.setMnemonic(KeyEvent.VK_Q);
    quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
    quitItem.addActionListener(new ActionListener(  ) {
      public void actionPerformed(ActionEvent e) { System.exit(0); }
    });
    utensils.add(quitItem);

    // create the Spices menu
    JMenu spices = new JMenu("Statistics");
        spices.setMnemonic(KeyEvent.VK_S);
        spices.add(new JCheckBoxMenuItem("Differencial image"));
        spices.add(new JCheckBoxMenuItem("Log window"));
        spices.add(new JCheckBoxMenuItem("Codek stats", true));
        spices.add(new JCheckBoxMenuItem("Version"));

    // create the Cheese menu
    JMenu cheese = new JMenu("Cheese");
        cheese.setMnemonic(KeyEvent.VK_C);
        ButtonGroup group = new ButtonGroup(  );
        JRadioButtonMenuItem rbmi;

    rbmi = new JRadioButtonMenuItem("M1", true);
        group.add(rbmi);
        cheese.add(rbmi);

    rbmi = new JRadioButtonMenuItem("m2");
        group.add(rbmi);
        cheese.add(rbmi);

    rbmi = new JRadioButtonMenuItem("m3");
        group.add(rbmi);
        cheese.add(rbmi);

    JMenu about = new JMenu("Help");
        about.add(new JMenuItem("About"));

    // create a menu bar and use it in this JFrame
    JMenuBar menuBar = new JMenuBar(  );
        menuBar.add(utensils);
        menuBar.add(spices);
        menuBar.add(cheese);
        menuBar.add(about);
        setJMenuBar(menuBar);


     JTabbedPane jtp = new JTabbedPane();

        jtp.addTab("Source",new SourceImagePanel(NNCodekMainObj));
        jtp.addTab("Destination",new DestImagePanel(NNCodekMainObj));
        jtp.addTab("Both",new Both(NNCodekMainObj));
        jtp.addTab("YUV-Layers",new YUVLayers(NNCodekMainObj));
        jtp.addTab("Error",new ErrorImagePanel(NNCodekMainObj));
        jtp.addTab("Stat",new StatPanel(NNCodekMainObj));

     getContentPane().add(jtp);

        /*
        JLabel jl=new JLabel();
        jl.setText("Algorithm");
        add(jl);
        JComboBox jcb=new JComboBox();
        jcb.addItem("--- [none] ---");
        jcb.addItem("Simple Adaline");
        jcb.addItem("Kohonen Network");
        jcb.addItem("Neokognitron");
        add(jcb);
        */
    }
}

