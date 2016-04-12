package mirrg.bullet.nickel.weapon;

public interface IKey<T>
{

	public T cast(Object value);

	public T getValueDefault();

}
