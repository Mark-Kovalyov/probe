package mayton.newral;

public abstract class Perceptron
{
	public abstract int	getSynapscount();
	public abstract int	getAxonescount();	
	public abstract void	refresh();
	public abstract void	reset();
	public abstract void	setSynaps(int i,double v);
	public abstract double	getAxon(int i);
}