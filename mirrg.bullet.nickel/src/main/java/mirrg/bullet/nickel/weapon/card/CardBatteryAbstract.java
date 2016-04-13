package mirrg.bullet.nickel.weapon.card;

import java.awt.Color;
import java.util.Hashtable;
import java.util.function.Function;

public abstract class CardBatteryAbstract implements ICardBattery
{

	private Hashtable<Key<?>, Object> fields = new Hashtable<>();

	public CardBatteryAbstract(CardBatteryAbstract... parents)
	{
		for (CardBatteryAbstract parent : parents) {
			fields.putAll(parent.fields);
		}
	}

	public CardBatteryAbstract set(Object key, Void value)
	{
		fields.remove(key);
		return this;
	}

	private <T> CardBatteryAbstract setImpl(Key<T> key, T value)
	{
		fields.put(key, value);
		return this;
	}

	private <T> T getImpl(Key<T> key)
	{
		T value = key.cast(fields.get(key));
		if (value == null) return key.getValueDefault();
		return value;
	}

	public <T> CardBatteryAbstract map(Key<T> key, Function<T, T> value)
	{
		return setImpl(key, value.apply(getImpl(key)));
	}

	///////

	public CardBatteryAbstract set(KeyInteger key, int value)
	{
		return setImpl(key, value);
	}

	public int get(KeyInteger key)
	{
		return getImpl(key);
	}

	public CardBatteryAbstract set(KeyDouble key, double value)
	{
		return setImpl(key, value);
	}

	public double get(KeyDouble key)
	{
		return getImpl(key);
	}

	public CardBatteryAbstract set(KeyBoolean key, boolean value)
	{
		return setImpl(key, value);
	}

	public boolean get(KeyBoolean key)
	{
		return getImpl(key);
	}

	public CardBatteryAbstract set(KeyColor key, Color value)
	{
		return setImpl(key, value);
	}

	public Color get(KeyColor key)
	{
		return getImpl(key);
	}

}
