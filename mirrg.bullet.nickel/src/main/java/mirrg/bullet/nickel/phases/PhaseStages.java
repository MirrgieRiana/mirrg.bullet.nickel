package mirrg.bullet.nickel.phases;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;

import mirrg.bullet.nickel.contents.CardStages;
import mirrg.bullet.nickel.core.GameNickel;
import mirrg.bullet.nickel.core.SessionNickel;
import mirrg.bullet.nickel.gui.Button;
import mirrg.bullet.nickel.gui.Label;
import mirrg.bullet.nickel.gui.PanelButtons;
import mirrg.bullet.nickel.phase.IPhase;
import mirrg.bullet.nickel.stage.ICardStage;

public class PhaseStages extends PhaseHomeBase
{

	public PhaseStages(GameNickel game, SessionNickel session)
	{
		super(game, session);
	}

	@Override
	protected synchronized void reform()
	{
		super.reform();

		PanelButtons panelButtons = new PanelButtons(11, 11, 40, 40,
			new Point2D.Double(game.sizeGame.width / 2, game.sizeGame.height / 2));
		components.add(panelButtons);

		Label label = new Label(new Point2D.Double(
			game.sizeGame.width / 2,
			game.sizeGame.height / 2 - panelButtons.getArea().height / 2 - 30),
			"", new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		components.add(label);

		for (ICardStage cardStage : CardStages.values()) {
			Button button = panelButtons.putButton(game, cardStage.getR(), cardStage.getC());
			if (session.data.isAvailable(cardStage)) {
				button
					.setOnMouseUp(() -> {
						IPhase phase = new PhaseBattle(game, session, cardStage);
						phase.init();
						game.setPhase(phase);
					})
					.setOnMouseIn(() -> {
						label.setText(cardStage.getLabel());
					})
					.setColor(cardStage.getItem().getColor());
			} else {
				button
					.setEnable(false)
					.setColor(new Color(0, 0, 0, 0));
			}
		}

	}

}
