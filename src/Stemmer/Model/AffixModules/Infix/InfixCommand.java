package Stemmer.Model.AffixModules.Infix;

import Stemmer.Model.AffixModules.AbstractAffixCommand;
import Stemmer.Model.AffixModules.Infix.Submodules.RemoveCommonInfix;
import Stemmer.Model.Stem;

import static Utility.print.println;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class InfixCommand extends AbstractAffixCommand
{
	Stem newStem;

	public InfixCommand(){}

	public Stem performStemmingModules(Stem stem)
	{
		/* Initialize new Stem objects for immutability */
		Stem oldStem = stem;
		Stem newStem = stem;
		/* Initialize submodules for stemming */
		RemoveCommonInfix rci = new RemoveCommonInfix();
		/* Perform stemming */
		newStem = rci.reduceStem( newStem );
		/* Check for Changes and update boolean changed */
		checkForChanges(oldStem, newStem);
		return newStem;
	}

	public static class test
	{
		public static void main(String[] args)
		{
			RemoveCommonInfix ci = new RemoveCommonInfix();
			String word = "sinindi";
			Stem stem = new Stem(word);
			stem = ci.reduceStem(stem);
//			word = ci.reduceStem(word);
			println("word: " + stem.getStemString());
			println("Affix: " + ci.getFoundAffix());
			println("AffixFeatured: " + ci.getFoundAffixFeatured());
		}
	}
}
