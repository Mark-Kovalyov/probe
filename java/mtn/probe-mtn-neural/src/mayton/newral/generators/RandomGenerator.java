package ua.dn.mayton.newral.generators;

import java.util.*;
import java.text.*;
import mayton.math.*;

public class RandomGenerator implements Enumeration
{
	protected double v1;
	protected double size;

	Random RandomGen=new Random();

	public RandomGenerator()
	{
		this.v1=0.0;
		size=1.0;
	}


	public RandomGenerator(double v1,double v2)
	{
		this.v1=Math.min(v1,v2);
		size=Math.max(v1,v2)-v1;		
	}

	public boolean hasMoreElements()
	{
		return true;
	}

	public Object nextElement()
	{
		return new Double(v1+size*RandomGen.nextDouble());
	}
}
