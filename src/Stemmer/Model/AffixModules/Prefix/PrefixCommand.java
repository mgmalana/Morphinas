package Stemmer.Model.AffixModules.Prefix;

import Stemmer.Model.AffixModules.AbstractAffixCommand;
import Stemmer.Model.AffixModules.Prefix.Submodules.RemoveCommonPrefix;
import Stemmer.Model.Stem;

import static Utility.print.println;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class PrefixCommand extends AbstractAffixCommand
{
	Stem newStem;

	public PrefixCommand(){}

	public Stem performStemmingModules(Stem stem)
	{
		/* Initialize new Stem objects for immutability */
		Stem oldStem = stem.cloneThis();
		Stem newStem = stem.cloneThis();
		/* Initialize submodules for stemming */
		RemoveCommonPrefix rcp = new RemoveCommonPrefix();
		/* Perform stemming */
		newStem = rcp.reduceStem( newStem );
		/* Check for Changes and update boolean changed */
		checkForChanges(oldStem, newStem);
		return newStem;
	}

	/*
	  * ********************************************************************
	  *              Getters and Setters only beyond this point
	  * ********************************************************************
	 */

	public Stem getNewStem() {
		return newStem;
	}

	/*
	  * ********************************************************************
	  *                    Testing Only Beyond this Point
	  * ********************************************************************
	 */

	public static class test
	{
		public static void main(String[] args)
		{
			String word = "pinahintay";
			final Stem stem  	= new Stem(word);
			PrefixCommand pc = new PrefixCommand();
			Stem newStem = pc.performStemmingModules(stem);
			println(stem.getStemString() + " -> " + newStem.getStemString());
			println( "Changes: " + pc.isChanged() );

		}
	}
}
