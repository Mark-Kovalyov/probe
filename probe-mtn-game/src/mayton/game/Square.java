package mayton.game;

import static java.lang.Math.*;

import mayton.image.Raster;

// Class: Square 1.1 (JDK 1.5.x)
// Hotfix:
//  +
//  +

// �������� ��������� ����� ������� �������
//
//  1) ������� ����������� ���� � ����� �������� A(0,0,256,256);
//  2) ������� ������� � �� 4 ������ �������� A[0],A[1],A[2],A[3]
//  3) ��������� �������� �� ������ �� ��������� �� �����������
//  4) ���� ��� - �� �������� ������� - ��� �������� A[i].setValid();
//  5) ���� �� - ��������� ����� (1) ���������� �� ��� ���, ���� ��� ������� �� ����� �������
//
//  ��� ������������ ��������� ������� - ��������� ����� � ���� ��� �������� ���������
//


// ������������� x1 <= X < x2
//               y1 <= Y < y2

public class Square
{
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public int ID;
	public int color=0;
	public double deviation=0.0;

	//List <Square> Childs;

	public int getWidth()
	{
		return x2-x1;
	}

	public int getHeight()
	{
		return y2-y1;
	}

	Square(int ID,int x1,int y1,int x2,int y2)
	{
		this.ID=ID;
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;

	}


	Square(int ID,int x1,int y1,int x2,int y2,Raster r)
	{
		this.ID=ID;
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		color    =GetAverageColor(r);
		deviation=GetDeviation(r);
	}

	public double GetDeviation(Raster RasterObject)
	{
		int w=x2-x1;
		int h=y2-y1;

		if (max(w,h)<=1)
		{
		 	return 0.0;
		}

		double rdisperse=0;
		double gdisperse=0;
		double bdisperse=0;

		int mr=Raster.getRPixel(color);
		int mg=Raster.getGPixel(color);
		int mb=Raster.getBPixel(color);

		for(int x=x1;x<x2;x++)
		{
			for(int y=y1;y<y2;y++)
			{
				int px=RasterObject.getPixel(x,y);
				rdisperse+=(double)mayton.math.numeric.pow2(Raster.getRPixel(px)-mr);
				gdisperse+=(double)mayton.math.numeric.pow2(Raster.getGPixel(px)-mg);
				bdisperse+=(double)mayton.math.numeric.pow2(Raster.getBPixel(px)-mb);
			}
		}

		int count=(x2-x1)*(y2-y1);
		rdisperse/=count;
		gdisperse/=count;
		bdisperse/=count;

		return sqrt(rdisperse*Raster.RK+gdisperse*Raster.GK+bdisperse*Raster.BK);
	}


	public int GetAverageColor(Raster RasterObject)
	{
		long r=0;
		long g=0;
		long b=0;
		int w=x2-x1;
		int h=y2-y1;
		if (max(w,h)<=1)
		{
		 	return RasterObject.getPixel(x1,y1);
		}
		for(int x=x1;x<x2;x++)
		{
			for(int y=y1;y<y2;y++)
			{
				int px=RasterObject.getPixel(x,y);
				r+=Raster.getRPixel(px);
				g+=Raster.getGPixel(px);
				b+=Raster.getBPixel(px);
			}
		}
		int count=(x2-x1)*(y2-y1);
		r/=count;
		g/=count;
		b/=count;
		color=Raster.getPixel((int)r,(int)g,(int)b);
		return color;
	}
}
