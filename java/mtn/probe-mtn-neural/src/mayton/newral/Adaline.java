package mayton.newral;

public class Adaline extends Perceptron {

    protected double[] w;

    public int getSynapscount() {
        return synapscount;
    }

    public void setSynapscount(int synapscount) {
        this.synapscount = synapscount;
    }

    protected int synapscount;
    protected int axonescount;

    public Adaline(int synapscount)
    {
        assert(synapscount>2);
        w=new double[synapscount];
    }

    public boolean isAutorefresh() {
        return autorefresh;
    }

    public void setAutorefresh(boolean autorefresh) {
        this.autorefresh = autorefresh;
    }

    protected boolean autorefresh;

    public int getSynapsCount() {
        return 256;
    }

    public int getAxonesCount() {
        return 2;
    }

    public void refresh() {

    }

    public void reset() {

    }

    public void setSynaps(int i, double v) {

    }

    public double getAxon(int i) {
        assert((i>=0)&&(i<w.length));
        return w[i];
    }

    public int getAxonescount() {
        return axonescount;
    }

    public void setAxonescount(int axonescount) {
        this.axonescount = axonescount;
    }
}
