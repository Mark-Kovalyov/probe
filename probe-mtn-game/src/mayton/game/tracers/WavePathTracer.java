package mayton.game.tracers;

import mayton.game.IMap;
import mayton.game.PathTracerException;


import java.awt.*;
import java.awt.geom.Point2D;
import mayton.math.Matrix;


/**
 *       ���� �p���p �������p�p��� ����� �p��������� ���� � ����p����.
 *       ��� _��_ �������������� p��������� ��������� ����p����, �
 *       �p����������� ��� ������ ��� �������p���� ��� �p�������.
 */

public class WavePathTracer implements mayton.game.IPathTracer
{
	protected int X;
	protected int Y;
	protected int i;
	protected int x,y;	
	protected int[] xm;
	protected int[] ym;
	protected IMap Map;



	public void initializePathTracer(IMap Map) throws mayton.game.PathTracerException
	{
		// �������� ������ ��� ������������� ��������
		// ���������� ���������, �������� ��������, ���� ����������

		try{
			X=Map.getWidth();
			Y=Map.getHeight();
			this.Map=Map;
		}
		catch(Exception Ex)
		{
			throw new mayton.game.PathTracerException(Ex.toString());
		}

	}

	public int sgn(int v)
	{
		if (v<0) return -1;
		if (v>0) return 1;
		return 0;
	}

    char movecost[][]={
       {0,0,0,0,0,0,0,0,0,0},
       {0,1,6,6,6,6,6,1,1,0},
       {0,1,0,0,0,0,6,0,0,0},
       {0,1,0,1,1,1,1,1,1,0},
       {0,1,0,1,1,0,0,0,1,0}, // ��� � ���� ����p���
       {0,1,0,1,0,0,1,0,1,0}, // 0 - �����
       {0,1,0,1,0,1,1,0,1,0}, // ����� �p���� �����-
       {0,1,0,0,0,0,0,0,1,0}, //  ������� �p����������
       {0,1,8,1,1,1,1,1,1,0}, //  1- ������ �p����������
       {0,0,0,0,0,0,0,0,0,0}
    };


    char [][]fillmap = new char[10][10];

    
    Point.Float[] buf=new Point.Float[256];

    int bufp,bufe; // �������� � buf

    int sx,sy,tx,ty; // H�������� � �������� ���p������ ����

    /**
     * ����� ��p���� ���p����� ����� �� buf � ����p������� 1, ���� �p��� ������, �� ����p������� 0
     */
    int pop(int x,int y){
        //int pop(int *x,int *y){
        //if(bufp==bufe)return 0;
        //*x=buf[bufp].x;
        //*y=buf[bufp].y;
        //bufp++;   // �� ��, ��� � � bufe !!!  ��. ^
        return 1;
    }



    /**
     * ��� ������� �p���p��� �������� �� �p���������� ���� � ����� �����
     * ��p�����,
     * ��� �������� p����, � ���� ��, �� ���������� ����� � buf.
     * @param x
     * @param y
     * @param n
     */
    void push(int x,int y,int n)
    {
        //if(fillmap[y][x]<=n)return;  ���� ����� ���� �� ��p���-����� ���
        //fillmap[y][x]=n;    ���������� ����� ����� ����
        //buf[bufe].x=x;
        //buf[bufe].y=y;     ���������� �����
        //bufe++;    P����p buf-256 bufe - byte, ���������� ����,
        // ����� ���� ������ bufe=(bufe+1)%(p����p buf)
        //scr[y][x*2  ].chr=n/10+48;
        //��� �p���� p�������� � �������� ������� ������
        //scr[y][x*2+1].chr=(n%10)+48;
        //getch();
    };




    
    public void initializePathTracer(Matrix Map) throws PathTracerException {

    }

    public boolean processPathTracing(int x1, int y1, int x2, int y2) throws PathTracerException {
        return false;
    }

    public void reset() {
        		i=-1;
    }

    public boolean moveNext() {
        i++;		
		// ��������� ������ ���� ���������� �����
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getX() {
        return xm[i];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getY() {
        return ym[i]; 
    }

    public void disposePathTracer() {

    }

    public String getDeveleperInfo() {
        return null; 
    }

    public String getAlgorytmInfo() {
        return null;
    }

    public String getVersion()
	{
		return "1.0";
	}



	

}