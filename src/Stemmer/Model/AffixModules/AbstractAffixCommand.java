package Stemmer.Model.AffixModules;

import Stemmer.Model.Stem;
import static Utility.print.println;
/**
 * Created by laurenz on 09/02/2017.
 */
public abstract class AbstractAffixCommand {
	/* stem and newStem will have the same value. */
	protected Stem stem, oldStem, newStem;
	protected boolean changed = false;

	public AbstractAffixCommand(Stem stem)
	{
		this.stem = stem;
		oldStem = new Stem( stem.getStemString() );
		performStemmingModules();
		checkForChanges( oldStem, stem );
		performStemmingModules();
	}

	public abstract Stem performStemmingModules();

 	public boolean checkForChanges(Stem oldStem, Stem newStem)
	{
		if( oldStem.getStemString().equalsIgnoreCase( newStem.getStemString() ) ) {
			setChanged( false );
			return false;
		} else {
			setChanged( true );
			return true;
		}
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}


}
