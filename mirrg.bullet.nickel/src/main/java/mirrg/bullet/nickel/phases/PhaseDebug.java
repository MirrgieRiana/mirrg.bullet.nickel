package mirrg.bullet.nickel.phases;

import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import mirrg.bullet.nickel.contents.CardStages;
import mirrg.bullet.nickel.contents.Items;
import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.core.SessionNickel;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.Label;
import mirrg.bullet.nickel.item.StackItem;
import mirrg.bullet.nickel.stage.ICardStage;
import mirrg.struct.hydrogen.Tuple;

public class PhaseDebug extends PhaseListAbstract
{

	public static int counter;
	private ArrayList<Tuple<String, Runnable>> commands = new ArrayList<>();

	public PhaseDebug(GameNickel game, SessionNickel session)
	{
		super(game, session);

		commands.add(new Tuple<>("全ステージ開放", () -> {
			for (ICardStage cardStage : CardStages.values()) {
				session.data.markAvailable(cardStage);
			}
		}));
		commands.add(new Tuple<>("全素材入手", () -> {
			for (Items item : Items.values()) {
				session.data.getInventory().addStack(new StackItem(item, 100));
			}
		}));
		commands.add(new Tuple<>("キャッシュ破棄", () -> {
			counter++;
		}));
	}

	@Override
	protected int getItemCount()
	{
		return commands.size();
	}

	@Override
	protected void putRecord(GameNickel game, Rectangle2D.Double area, int row)
	{
		int index = row + lineCount * page;

		components.add(new Button(game, new Rectangle2D.Double(area.x + 5, area.y + 5 + (25 + 5) * row, 390, 25))
			.setOnMouseUp(commands.get(index).getY()));
		components.add(new Label(new Point2D.Double(area.x + 10, area.y + 5 + 12 + (25 + 5) * row),
			commands.get(index).getX(),
			new Font(Font.SANS_SERIF, Font.PLAIN, 24), Label.MIDDLE, Label.LEFT));
	}

}
