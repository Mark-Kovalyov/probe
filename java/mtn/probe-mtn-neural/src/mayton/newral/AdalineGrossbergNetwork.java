package mayton.newral;

import mayton.math.Matrix;
import mayton.math.MatrixGeneric;

import java.io.Serializable;

import org.apache.log4j.Logger;


public class AdalineGrossbergNetwork implements Serializable {

    protected MatrixGeneric M;
    protected double res;

    static Logger logger=Logger.getLogger("ua.dn.mayton.newral.AdalineGrossbergNetwork");

    public AdalineGrossbergNetwork(int nclasses,int nhistogram)
    {
        M=new MatrixGeneric();
    }

    public void setFactor(int nclass,int nhistogram,double value)
    {
        M.set(nclass,nhistogram,value);
    }

    public void processLearn(double[] v,int nclass)
    {
        
    }

    public int getNclass()
    {

        return 0;
    }

}
