package mayton.game.tracers;

import mayton.math.Matrix;
import mayton.game.IMap;
import mayton.game.PathTracerException;


import static java.lang.Math.*;

// ������ ���������� ����������� ���������-������� ����������� ���� �� ����� � �������������
// ����� ���� ����������� ��� ������ ��� ��������� ������ ��������

public class MyPathTracer implements mayton.game.IPathTracer
{
	protected int X;
	protected int Y;
	protected int i;
	protected int x,y;	
	protected int[] xm;
	protected int[] ym;
	protected Matrix map;


	public String getVersion()
	{
		return "1.0";
	}

    /*
    public void initializePathTracer(IMap Map) throws ua.dn.mayton.game.PathTracerException
	{
		// �������� ������ ��� ������������� ��������
		// ���������� ���������, �������� ��������, ���� ����������
	}*/

	public int sgn(int v)
	{
		if (v<0) return -1;
		if (v>0) return 1;
		return 0;
	}

    public void initializePathTracer(Matrix map) throws PathTracerException {
        try{
			X=map.getX();
			Y=map.getY();
			this.map=map;
		}
		catch(Exception Ex)
		{
			throw new PathTracerException(Ex.toString());
		}
    }

    public boolean processPathTracing(int x1,int y1,int x2,int y2) throws mayton.game.PathTracerException
    {
        // ������������ ���� �� ����� (x1,y1) � ����� (x2,y2)

        int xmin=min(x1,x2);
        int xmax=max(x1,x2);
        int ymin=min(y1,y2);
        int ymax=max(y1,y2);

        boolean res=true;

        int i;

        int count;

        if ((xmax-xmin)>(ymax-ymin))
        {
            count=Math.abs(x2-x1);
            xm=new int[count];
            ym=new int[count];
            int dx=sgn(x2-x1);
            i=0;
            for(int x=x1;x<x2;x+=dx)
            {
                y=((y2-y1)*(x-x1))/(x2-x1)+y1;
                if (map.get(x,y)!=0) {res=false;break;}
                xm[i]=x;
                ym[i]=y;
                i++;
            }
        }
        else
        {
            count=Math.abs(y2-y1);
            xm=new int[count];
            ym=new int[count];
            int dy=sgn(y2-y1);
            i=0;
            for(int y=y1;y<y2;y+=dy)
            {
                x=((x2-x1)*(y-y1))/(y2-y1)+x1;
                if (map.get(x,y)!=0) {res=false;break;}
                xm[i]=x;
                ym[i]=y;
                i++;
            }
        }

        // ���������:
                //    ������ ���� ���� ������
        //    ���� - ������������, ���� ���� �� ���������� � ��������, ���� ���������
        //    ��������� ������� � ������������ ������������������ ������ � ������ ��������
        //    (������� �������� ��������, ������ ������� ���� ��������� ��������� � �.�)
        return res;
    }


	public void reset()
	{
		// ����� ���� � ��������� ���������
		i=-1;
	}

	public boolean moveNext()
	{
		// ������������ �� ��� ������
		i++;

		
		// ��������� ������ ���� ���������� �����
		return false;
	}

	public int getX()
	{
		// ������� ������� X ���������� ����
		return xm[i];
	}

	public int getY()
	{
		// ������� ������� Y ���������� ����		
		return ym[i];
	}

	public void disposePathTracer()
	{
		// �������� ������ ��� ������������ �������� �������, ���� ����������
	}

	public String getDeveleperInfo()
	{
		return "Mark Kovalev";
	}

	public String getAlgorytmInfo()
	{
		return "Generic & Simple Algorythm";
	}

}