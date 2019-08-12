package mayton.mayton.probe.horstman.table;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class InvestmentTableFrame extends JFrame {


    public InvestmentTableFrame() throws HeadlessException {
        setTitle("Investment table 1.0");
        setSize(800, 600);
        TableModel model = new InvestmentTableModel(30, 5, 10);
        JTable table = new JTable(model);
        add(new JScrollPane(table));
    }
}
