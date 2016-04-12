package mirrg.bullet.nickel.entity;

public interface ILiving extends IEntity
{

	public double getX();

	public double getY();

	public double getRadius();

	public int getToughness();

	public default double getDistance(double x, double y)
	{
		double x2 = x - getX();
		double y2 = y - getY();
		return Math.sqrt(x2 * x2 + y2 * y2);
	}

}
