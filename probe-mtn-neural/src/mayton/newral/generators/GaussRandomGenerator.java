package ua.dn.mayton.newral.generators;

import java.util.*;
import java.text.*;
import mayton.math.*;

public class GaussRandomGenerator implements Enumeration
{
	Random RandomGen=new Random();

	public GaussRandomGenerator()
	{
	}

	public boolean hasMoreElements()
	{
		return true;
	}

	public Object nextElement()
	{
		return new Double(RandomGen.nextGaussian());
	}
}
