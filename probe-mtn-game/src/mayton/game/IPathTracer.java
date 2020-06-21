package mayton.game;

public interface IPathTracer
{
	//public void initializePathTracer(mayton.math.Matrix Map) throws ua.dn.mayton.game.PathTracerException;
	//public boolean processPathTracing(int x1,int y1,int x2,int y2) throws ua.dn.mayton.game.PathTracerException;
	public void reset();
	public boolean moveNext();
	public int getX();
	public int getY();
	public void disposePathTracer();
	public String getDeveleperInfo();
	public String getAlgorytmInfo();
	public String getVersion();
}