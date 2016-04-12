package mirrg.bullet.nickel.weapon;

public class KeyBase<T> implements IKey<T>
{

	private String string;
	private T valueDefault;

	public KeyBase(String string, T valueDefault)
	{
		this.string = string;
		this.valueDefault = valueDefault;
	}

	@Override
	public String toString()
	{
		return string;
	}

	@Override
	public T cast(Object value)
	{
		return (T) value;
	}

	@Override
	public T getValueDefault()
	{
		return valueDefault;
	}

}
