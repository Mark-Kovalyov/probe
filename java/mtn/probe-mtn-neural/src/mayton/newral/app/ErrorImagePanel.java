package mayton.newral.app;

import javax.swing.*;


public class ErrorImagePanel extends JPanel {


    class UpperImage extends JPanel
    {
        public UpperImage()
        {

        }
    }

    class LowerPanel extends JPanel
    {
        public LowerPanel()
        {
            JButton jb=new JButton("Refresh");
            add(jb);
        }
    }

    private JSplitPane splitPane;



    public ErrorImagePanel(NNCodekMain NNCodekMainObj)
    {
        JPanel ui=new UpperImage();
        JPanel lp=new LowerPanel();
        splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,ui,lp);
        splitPane.setResizeWeight(0.5);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        add(splitPane);
    }
}

