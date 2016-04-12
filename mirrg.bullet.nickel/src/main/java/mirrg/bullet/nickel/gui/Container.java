package mirrg.bullet.nickel.gui;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Container implements IComponent
{

	private ArrayList<IComponent> components = new ArrayList<>();

	public Container add(IComponent component)
	{
		components.add(component);
		return this;
	}

	public void clear()
	{
		components.clear();
	}

	@Override
	public void move()
	{
		for (IComponent component : components) {
			component.move();
		}
	}

	@Override
	public void paint(Graphics2D graphics)
	{
		for (IComponent component : components) {
			component.paint(graphics);
		}
	}

}
