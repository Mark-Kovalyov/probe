package mayton.newral.app;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

public class StatPanel extends JPanel
{
    public StatPanel(NNCodekMain NNCodekMainObj)
    {
        JTable jtb=new JTable();
        TableColumnModel tcm=new DefaultTableColumnModel();
        add(jtb);
    }
}
