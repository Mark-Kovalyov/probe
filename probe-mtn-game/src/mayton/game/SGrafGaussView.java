package mayton.game;

import java.awt.*;


public class SGrafGaussView
{
	protected SGraf SGraf1;

	protected int   x1;
	protected int   x2;
	protected int   y1;
	protected int   y2;

	protected int X;
	protected int Y;


	public SGrafGaussView(int x1,int y1,int x2,int y2,SGraf SGraf1)
	{
		this.SGraf1=SGraf1;
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		X=x2-x1;
		Y=y2-y1;
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.green);
		g.fillRect(x1,y1,x2-x1,y2-y1);
	}
}