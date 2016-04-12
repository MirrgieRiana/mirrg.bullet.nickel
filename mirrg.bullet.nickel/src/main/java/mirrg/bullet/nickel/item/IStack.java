package mirrg.bullet.nickel.item;

import java.awt.Color;
import java.util.ArrayList;

public interface IStack
{

	public String getName();

	public String getNameInButtle();

	public int getAmount();

	public void getMessages(ArrayList<String> messages);

	public String getOreName();

	public Color getColor();

	public IStack copy(int amount);

}
