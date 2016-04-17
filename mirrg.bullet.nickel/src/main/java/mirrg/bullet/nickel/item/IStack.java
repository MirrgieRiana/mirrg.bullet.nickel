package mirrg.bullet.nickel.item;

import java.awt.Color;
import java.util.ArrayList;

public interface IStack
{

	public String getNameLocalized();

	public String getNameLocalizedInButtle();

	public String getNameOre();

	public int getAmount();

	public void getMessages(ArrayList<String> messages);

	public Color getColor();

	public IStack copy(int amount);

}
