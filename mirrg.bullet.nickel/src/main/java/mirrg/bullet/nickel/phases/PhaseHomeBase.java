package mirrg.bullet.nickel.phases;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.function.Supplier;

import mirrg.bullet.nickel.contents.Recipes;
import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.core.SessionNickel;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.Counter;
import mirrg.bullet.nickel.gui.Label;
import mirrg.bullet.nickel.phase.IPhase;

public class PhaseHomeBase extends PhaseGUIBase
{

	protected SessionNickel session;

	public PhaseHomeBase(GameNickel game, SessionNickel session)
	{
		super(game);
		this.session = session;
	}

	@Override
	public void init()
	{
		super.init();

		save();
	}

	private void save()
	{
		try {
			GameNickel.createXStream().toXML(session.data, new FileOutputStream(session.fileSave));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void reform()
	{
		super.reform();
		putButtonsMenu();
	}

	private void putButtonsMenu()
	{
		Counter counter = new Counter();

		putButton(() -> new PhaseTitle(game), "タイトル", counter, Color.decode("#000000"));
		putButton(() -> new PhaseStages(game, session), "戦闘", counter, Color.decode("#990000"));
		putButton(() -> new PhaseInventory(game, session), "インベントリ", counter, Color.decode("#2AB700"));
		putButton(() -> new PhaseCraft(game, session, Recipes.recipesShop), "商店", counter, Color.decode("#BAA700"));
		putButton(() -> new PhaseCraft(game, session, Recipes.recipesMake), "武器生産", counter, Color.decode("#0045B5"));
		putButton(() -> new PhaseCraft(game, session, Recipes.recipesUpgrade), "武器強化", counter, Color.decode("#0045B5"));
		putButton(() -> new PhaseCraft(game, session, Recipes.recipesCombine), "素材加工", counter, Color.decode("#0045B5"));
		putButton(() -> new PhaseCraft(game, session, Recipes.recipesRefine), "素材精錬", counter, Color.decode("#0045B5"));
		putButton(() -> new PhaseCraft(game, session, Recipes.recipesDebug), "デバッグ入手", counter, Color.decode("#AC00B2"));
		putButton(() -> new PhaseDebug(game, session), "デバッグ機能", counter, Color.decode("#AC00B2"));

	}

	private void putButton(Supplier<IPhase> supplierPhase, String label, Counter counter, Color color)
	{
		Rectangle2D.Double rectangle;
		rectangle = new Rectangle2D.Double(5, 5 + (40 + 5) * counter.value, 150, 40);
		components.add(new Button(game, rectangle)
			.setOnMouseUp(() -> {

				save();

				IPhase phase = supplierPhase.get();
				phase.init();
				game.setPhase(phase);
			})
			.setColor(color));
		components.add(new Label(new Point2D.Double(rectangle.getCenterX(), rectangle.getCenterY()),
			label, new Font(Font.SANS_SERIF, Font.PLAIN, 24)));
		counter.add(1);
	}

}
