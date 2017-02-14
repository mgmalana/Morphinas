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

	}

	public void testCommands()
	{
		Branch p, i, s;
		String word = "pinahintayan";
		Stem stem = new Stem(word);
		p = new Branch('p', pc.performStemmingModules(stem));
		i = new Branch('i', stem);
		s = new Branch('i', stem);


	}

	/**
	 * Branch
	 */
	public class Branch
	{
		/* directions */
		final static char _s = 's', _i = 'i', _p = 'p';
		String directionHistory = "";
		char direction;
		Stem stem;
		int treeDepth;
		boolean isTop = false, isRoot = false;

		/**
		 * A branch in the tree.
		 * @param direction
		 * Must contain either 'p', 'i', 's'
		 * @param stem
		 * Must be passed every time.
		 */
		public Branch(char direction, Stem stem)
		{
			this.direction 			= direction;
			this.stem 				= stem;
			this.directionHistory 	= directionHistory + direction + "-";
		}

		public Branch(char direction, Stem stem, String directionHistory)
		{
			this.direction 			= direction;
			this.stem 				= stem;
			this.directionHistory 	= directionHistory + direction + "-";
		}

		public void generateChildrenForThisBranch(Stem currentStem)
		{
			generatePrefixBranch( currentStem );
			generateInfixBranch( currentStem );
			generateSuffixBranch( currentStem );
		}

		public Branch generatePrefixBranch(Stem parentStem)
		{
			Branch prefixBranch;
			PrefixCommand pc = new PrefixCommand();
			parentStem 		 = pc.performStemmingModules( parentStem );
			prefixBranch 	 = new Branch(_p, parentStem, this.directionHistory );
			return prefixBranch;
		}

		public Branch generateInfixBranch(Stem parentStem)
		{
			Branch infixBranch;
			InfixCommand ic = new InfixCommand();
			parentStem		= ic.performStemmingModules( parentStem );
			infixBranch 	= new Branch( _i, parentStem, this.directionHistory );
			return infixBranch;
		}

		public Branch generateSuffixBranch(Stem parentStem)
		{
			Branch suffixBranch;
			SuffixCommand sc = new SuffixCommand();
			parentStem  	 = sc.performStemmingModules( parentStem );
			suffixBranch 	 = new Branch( _s, parentStem, this.directionHistory );
			return suffixBranch;
		}

		/*
		 * Getters and Setters
		 */
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

	public static class Test
	{
		public static void main(String[] args)
		{

		}
	}
}
