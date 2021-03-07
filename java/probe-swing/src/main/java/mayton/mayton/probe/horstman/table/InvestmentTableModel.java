package mayton.mayton.probe.horstman.table;

import javax.swing.table.AbstractTableModel;

public class InvestmentTableModel extends AbstractTableModel {

    private int years;
    private int minRate;
    private int maxRate;

    private static double INITIAL_BALANCE = 10000.0;

    public InvestmentTableModel(int years, int minRate, int maxRate) {
        this.years = years;
        this.minRate = minRate;
        this.maxRate = maxRate;
    }

    @Override
    public int getRowCount() {
        return years;
    }

    @Override
    public int getColumnCount() {
        return maxRate - minRate + 1;
    }

    @Override
    public Object getValueAt(int r, int c) {
        double rate = (c + minRate) / 100.0;
        int nperiods = r;
        double futureBalance = INITIAL_BALANCE * Math.pow(1.0 + rate, nperiods);
        return String.format("%.2f", futureBalance);
    }

    @Override
    public String getColumnName(int c) {
        return (c + minRate) + "%";
    }
}
