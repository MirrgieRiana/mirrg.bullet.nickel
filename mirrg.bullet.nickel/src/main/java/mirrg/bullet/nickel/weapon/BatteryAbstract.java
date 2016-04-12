package mirrg.bullet.nickel.weapon;

import java.awt.Color;
import java.util.Hashtable;
import java.util.function.Function;

public abstract class BatteryAbstract implements IBattery
{

	private Hashtable<IKey<?>, Object> fields = new Hashtable<>();

	public BatteryAbstract(BatteryAbstract... parents)
	{
		for (BatteryAbstract parent : parents) {
			fields.putAll(parent.fields);
		}
	}

	public BatteryAbstract set(Object key, Void value)
	{
		fields.remove(key);
		return this;
	}

	private <T> BatteryAbstract setImpl(IKey<T> key, T value)
	{
		fields.put(key, value);
		return this;
	}

	private <T> T getImpl(IKey<T> key)
	{
		T value = key.cast(fields.get(key));
		if (value == null) return key.getValueDefault();
		return value;
	}

	public <T> BatteryAbstract map(IKey<T> key, Function<T, T> value)
	{
		return setImpl(key, value.apply(getImpl(key)));
	}

	///////

	public BatteryAbstract set(KeyInteger key, int value)
	{
		return setImpl(key, value);
	}

	public int get(KeyInteger key)
	{
		return getImpl(key);
	}

	public BatteryAbstract set(KeyDouble key, double value)
	{
		return setImpl(key, value);
	}

	public double get(KeyDouble key)
	{
		return getImpl(key);
	}

	public BatteryAbstract set(KeyBoolean key, boolean value)
	{
		return setImpl(key, value);
	}

	public boolean get(KeyBoolean key)
	{
		return getImpl(key);
	}

	public BatteryAbstract set(KeyColor key, Color value)
	{
		return setImpl(key, value);
	}

	public Color get(KeyColor key)
	{
		return getImpl(key);
	}

}
