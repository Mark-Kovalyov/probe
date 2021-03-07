package mayton.game;

import mayton.image.Raster;

import java.util.Hashtable;
import java.util.Enumeration;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;


public class SGraf// implements Serializable
{
	private int ID_VERTEX_GENERATOR=0;
	private int ID_WIRES_GENERATOR=0;
	Raster Area;
	double maxdeviation;
	int    minsquare=1;



	Hashtable<Integer,Square> Sqs=new Hashtable<Integer,Square>();

	SGraf(Raster Area,double maxdeviation)
	{
		this.Area=Area;
		Square s=new Square(ID_VERTEX_GENERATOR++,0,0,Area.X,Area.Y,Area);
		Sqs.put(new Integer(s.ID),s);
		System.out.println("deviation = "+s.deviation);
		this.maxdeviation=maxdeviation;
		run();

	}

	SGraf(Raster Area,double maxdeviation,int minsquare)
	{
		this.Area=Area;
		Square s=new Square(ID_VERTEX_GENERATOR++,0,0,Area.X,Area.Y,Area);
		Sqs.put(new Integer(s.ID),s);
		System.out.println("deviation = "+s.deviation);
		this.maxdeviation=maxdeviation;
		this.minsquare=minsquare;
		run();

	}


	public String toString()
	{
		StringBuffer Sb=new StringBuffer("DUMP of SGraf:\n");
		Sb.append("=====================================\n");
		Sb.append("Area(Raster)\n"+Area.toString());
		Sb.append("-------------------------------------\n");
		Sb.append("Sqs(Hashtable)\n"+Sqs.toString());
		Sb.append("=====================================\n");
		Sb.append("End of SGraf\n");
		return Sb.toString();
	}


	public void paint(Graphics g,int xoffset,int yoffset,boolean ShowSquares,boolean ShowSquaresFrame)
	{
		//g.setColor(Color.BLACK);

		//g.fillRect(0+xoffset,0,Area.X,Area.Y);

		Square element;

		Enumeration i=Sqs.elements();

		while(i.hasMoreElements())
		{
			Object ob=i.nextElement();
			element=(Square)ob;

			if (ShowSquares){
				g.setColor(new Color(element.color));
				g.fillRect(xoffset+element.x1,element.y1+yoffset,element.x2-element.x1,element.y2-element.y1);
			}
			if (ShowSquaresFrame)
			{
				g.setColor(Color.gray);
				g.drawRect(xoffset+element.x1,element.y1+yoffset,element.x2-element.x1,element.y2-element.y1);
			}
		}

	}



	public void Serialize(OutputStream out) throws IOException
	{

		/*
		DataOutputStream DOS=new DataOutputStream(out);

		Enumeration i;

		Square element;

		int w=Area.X;

		while(true)
		{
			i=Sqs.elements();

			DOS.writeShort(w);

			while(i.hasMoreElements())
			{
				Object ob=i.nextElement();
				element=(Square)ob;
				if ((element.x2-element.x1)==w)
				{
					DOS.writeByte(element.x1);
					DOS.writeByte(element.y1);
				}
			}
			if (w==1) break;
			w/=2;
		}

		DOS.flush();
		*/

		PrintWriter Pw=new PrintWriter(out);

		Enumeration en=Sqs.elements();

		while(en.hasMoreElements())
		{
			Square element=(Square)en.nextElement();
			Pw.print("(");
			Pw.print(Raster.getRGBTextSignatureHex(element.color));
			Pw.print(")");
		}

		Pw.close();

	}


	public void run()
	{
		// ���� ����������


		Enumeration<Square> i=Sqs.elements();

		while(i.hasMoreElements())
		{
			Square element    = i.nextElement();

			//element.color     = element.GetAverageColor(Area);

			//element.deviation = element.GetDeviation(Area);

			//element.detected  = false;

			int w=element.getWidth();

			if ((element.deviation>maxdeviation)&&(w>minsquare))
			{
				// ��������� 4 �������� ���������

				//if (w==2) break;

				int w2=w/2;
				int h=element.getHeight();
				int h2=h/2;
				int x1=element.x1;
				int y1=element.y1;

				Square Square0=new Square(ID_VERTEX_GENERATOR++,x1,	y1,	x1+w2,	y1+h2,Area);
				Square Square1=new Square(ID_VERTEX_GENERATOR++,x1+w2,  y1,	x1+w,   y1+h2,Area);
				Square Square2=new Square(ID_VERTEX_GENERATOR++,x1,	y1+h2,	x1+w2,	y1+h, Area);
				Square Square3=new Square(ID_VERTEX_GENERATOR++,x1+w2,  y1+h2,	x1+w,	y1+h, Area);

				Sqs.put(new Integer(Square0.ID),Square0);
				Sqs.put(new Integer(Square1.ID),Square1);
				Sqs.put(new Integer(Square2.ID),Square2);
				Sqs.put(new Integer(Square3.ID),Square3);

				// �������� ������� ��������

				int ID=element.ID;

				Sqs.remove(new Integer(ID));

				i=Sqs.elements();
			}
		}

	}
}