package mayton.newral;

public class ActivationFuncLinear implements IActivationFunc
{
	protected double ymax=1.0;

	protected double ymin=0.0;

	public void	setBound(double y1,double y2)
	{
		ymax=Math.max(y1,y2);
		ymin=Math.min(y1,y2);
	}
	public double	getValue(double x)
	{
		if (x>ymax) return ymax;
		if (x<ymin) return ymin;
		return x;
	}
}