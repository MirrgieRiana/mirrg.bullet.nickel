package mirrg.bullet.nickel.weapon.card;

public class Key<T>
{

	private String string;
	private T valueDefault;

	public Key(String string, T valueDefault)
	{
		this.string = string;
		this.valueDefault = valueDefault;
	}

	@Override
	public String toString()
	{
		return string;
	}

	public T cast(Object value)
	{
		return (T) value;
	}

	public T getValueDefault()
	{
		return valueDefault;
	}

}
