package mayton.newral;

import java.lang.*;

public class ActivationFuncLogisticSygmoid implements IActivationFunc
{
	protected double ymax=1.0;

	protected double ymin=0.0;

	protected double ysize=ymax-ymin;

	public ActivationFuncLogisticSygmoid(double y1,double y2)
	{
		setBound(y1,y2);
	}


	public void	setBound(double y1,double y2)
	{
		ymax=Math.max(y1,y2);
		ymin=Math.min(y1,y2);
		ysize=ymax-ymin;
	}

	public double	getValue(double x)
	{
		return ysize/(1.0+Math.exp(-x))+ymin;
	}	
}