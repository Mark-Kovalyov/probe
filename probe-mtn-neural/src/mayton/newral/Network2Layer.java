package mayton.newral;

import java.util.*;
import java.text.*;
import mayton.math.*;
import ua.dn.mayton.newral.generators.*;

/**
 * ����������� ��������� ����. 
 * 	- �������� �� ������ ��������� ��������������� ������
 *      - ����� ������� ��������� (���������,�������������, ������������� (������� ���������������)
 *      
 */

public class Network2Layer extends Perceptron
{
	protected int n; // ���������� �������� (������)

	protected int m; // ���������� ������� (�������)

	protected int L; // ���������� �������� �������� ����

	protected int N; // ����� ������� (���������)

	protected double[] synapses;

	protected double[] axones;

	protected double[] axonesL;

	protected MatrixGeneric w1;

	protected MatrixGeneric w2;

	protected IActivationFunc Activation1Layer;

	protected IActivationFunc Activation2Layer;

	protected boolean autoRefresh=false;

	public int getSynapsCount()
	{
		return n;
	}

	protected int getHiddenLayerAdalinesCount()
	{
		return (int)((m*N/(1.0+numeric.log2(N))/(n+m))+1.0);
	}

	public Network2Layer(int n,int m,int N)
	{				
		this.n=n;
		this.m=m;
		this.N=N;
		Activation1Layer=new ActivationFuncLogisticSygmoid(0.0,1.0);
		Activation1Layer=new ActivationFuncLogisticSygmoid(0.0,1.0);
		L=getHiddenLayerAdalinesCount();
		Enumeration Ai=new RandomGenerator(-0.2,0.2);
		w1=new MatrixGeneric(n,L,Ai);
		w2=new MatrixGeneric(L,m,Ai);
		axones=new double[m];		
		axonesL=new double[L];
		synapses=new double[n];
	}

	public Network2Layer(int n,int m,int N,IActivationFunc ActivationFunc1Layer,IActivationFunc ActivationFunc2Layer)
	{				
		this.n=n;
		this.m=m;
		this.N=N;		
		Activation1Layer=ActivationFunc1Layer;
		Activation2Layer=ActivationFunc2Layer;
		L=getHiddenLayerAdalinesCount();
		Enumeration Ai=new RandomGenerator(-0.2,0.2);
		w1=new MatrixGeneric(n,L,Ai);
		w2=new MatrixGeneric(L,m,Ai);
		axones=new double[m];
		axonesL=new double[L];
		synapses=new double[n];
	}

	public int getAxonesCount()
	{
		return m;
	}

	public void reset()
	{
		
	}


	public double getAxon(int i)
	{
		return axones[i];
	}

	public void setSynaps(int i,double v)
	{
		synapses[i]=v;
		if (autoRefresh) refresh();
	}

    public int getSynapscount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getAxonescount() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void refresh()
	{
		// ���������� �������� ������� �������� ���� � ���������� ���������

		double sum;

		for(int i=0;i<L;i++)
		{
			sum=0.0;
			try{
				for(int j=0;j<w1.getX();j++) sum+=synapses[i]*w1.get(j,i);
			}
			catch(Exception Ex){};
			axonesL[i]=Activation1Layer.getValue(sum);
		}

		// ���������� �������� �������� �������

		/*
		for(int i=0;i<L;i++)
		{
			sum=0.0;
			try{
				for(int j=0;j<w1.getX();j++) sum+=axonesL[i]*w2.get(j,i);
			}
			catch(Exception Ex){};
			axones[i]=Activation2Layer.getValue(sum);
		}*/
	}

	public void setAutoRefresh(boolean autoRefresh)
	{
		this.autoRefresh=autoRefresh;
	}

	public String toString()
	{
		StringBuffer Sb=new StringBuffer();
		Sb.append("n = "+n);
		Sb.append("\nm = "+m);
		Sb.append("\nN = "+N);
		Sb.append("\nL = "+L);
		Sb.append("\n\n");

		Sb.append(w1.toString());

		Sb.append("\n(L) = (\n");

		DecimalFormat Df=new DecimalFormat("-###0.00; ###0.00");
		
		for(int i=0;i<L;i++)
		{			
			Sb.append(Df.format(axonesL[i]));
			Sb.append("\n");
		}

		Sb.append(")\n\n");		

		Sb.append(w2.toString());

		Sb.append("\n(m) = (\n");
		
		for(int i=0;i<m;i++)
		{			
			Sb.append(Df.format(axonesL[i]));
			Sb.append("\n");
		}

		Sb.append(")\n\n");		
		
		return Sb.toString();
	}
}