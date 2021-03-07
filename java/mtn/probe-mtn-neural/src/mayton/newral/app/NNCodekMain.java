package mayton.newral.app;

import java.io.FileInputStream;
import java.io.IOException;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.imageio.ImageIO;


public class NNCodekMain {



    Logger logger=Logger.getLogger("ua.dn.mayton.newral.app.NNCodekMain");

    public BufferedImage sourceImage;
    public BufferedImage destImage;
    public BufferedImage errorImage;

    NNCodekControlPanel nncp;


    public void updateYUVLayers()
    {
        
    }


    public void onLoadSourceImage()
    {
        logger.info("> onLoadSourceImage()");
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(nncp);
        if (result == JFileChooser.CANCEL_OPTION) return;
         
        sourceImage=null;
        try{
            sourceImage=ImageIO.read(new FileInputStream(chooser.getSelectedFile()));
        }
        catch(IOException e)
        {
            logger.error(e);
        }
        
        logger.info("> OK");
    }

    public NNCodekMain()
    {
        logger.info("> NNCodekMain()");
        /*
        BitmapImportFilter bif=BitmapImportFilter.createInstance();
        source=null;
        try{
            source=bif.from(new FileInputStream(""));
        }
        catch(Exception ex){}
        */



        try{
            sourceImage=ImageIO.read(new FileInputStream("C:\\001-1949.bmp"));
        }
        catch(IOException e)
        {
            logger.error(e);
        }


        logger.info("> source image thread started");
        
        logger.info("> new NNCodekControlPanel(this);");
        nncp=new NNCodekControlPanel(this);
        nncp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
        nncp.addWindowListener(new WindowAdapter(  ) {
            public void windowClosing(WindowEvent we) { System.exit(0); }
        });
        */
        logger.info("> setVisible");
        nncp.setVisible(true);
        logger.info("> constructor complete!");
    }

    public static void main(String[] args)
    {
        BasicConfigurator.configure();
        new NNCodekMain();
    }

    public void finalize()
    {
        logger.info("> finalize OK!");
    }

}
