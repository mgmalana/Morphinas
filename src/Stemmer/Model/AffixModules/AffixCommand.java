package Stemmer.Model.AffixModules;

import Stemmer.Model.AffixModules.Infix.InfixCommand;
import Stemmer.Model.AffixModules.Prefix.PrefixCommand;
import Stemmer.Model.AffixModules.Suffix.SuffixCommand;
import Stemmer.Model.Stem;
import static Utility.print.*;

/**
 * Created by laurenz on 09/02/2017.
 */
public class AffixCommand
{
	/* Tree properties */
	int treeDepth;
	boolean mustStop 	= false;
	boolean donePrefix 	= false;
	boolean doneInfix 	= false;
	boolean doneSuffix	= false;
	/* Commands */
	PrefixCommand pc 	= new PrefixCommand();
	InfixCommand ic 	= new InfixCommand();
	SuffixCommand sc 	= new SuffixCommand();

	public AffixCommand()
	{
		Stem stem = new Stem("pinahintay");
	}


	/**
	 * Branch
	 */
	public class Branch
	{
		String directionHistory = "";
		char direction;
		Stem stem;
		int treeDepth;
		/**
		 * A branch in the tree.
		 * @param direction
		 * Must contain either 'p', 'i', 's'
		 * @param stem
		 * Must be passed every time.
		 */
		public Branch(char direction, Stem stem)
		{
			this.direction 	= direction;
			this.stem 		= stem;
			directionHistory = directionHistory + direction;
		}

		public String getDirectionHistory() {
			return directionHistory;
		}

		public void setDirectionHistory(String directionHistory) {
			this.directionHistory = directionHistory;
		}

		public void printBranchStem()
		{
			println( "B-Stem: " + this.stem.getStem() );
		}
	}
}
