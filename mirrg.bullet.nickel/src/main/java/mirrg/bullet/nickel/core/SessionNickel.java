package mirrg.bullet.nickel.core;

import java.io.File;

import mirrg.bullet.nickel.contents.CardStages;
import mirrg.bullet.nickel.stage.ICardStage;

public class SessionNickel
{

	public DataNickel data;

	public File fileSave;

	public SessionNickel(DataNickel data, File fileSave)
	{
		this.data = data;
		this.fileSave = fileSave;
	}

	public void onClear(ICardStage cardStage)
	{
		ICardStage s;

		s = CardStages.get(cardStage.getR() - 1, cardStage.getC());
		if (s != null) data.markAvailable(s);

		s = CardStages.get(cardStage.getR() + 1, cardStage.getC());
		if (s != null) data.markAvailable(s);

		s = CardStages.get(cardStage.getR(), cardStage.getC() - 1);
		if (s != null) data.markAvailable(s);

		s = CardStages.get(cardStage.getR(), cardStage.getC() + 1);
		if (s != null) data.markAvailable(s);
	}

}
