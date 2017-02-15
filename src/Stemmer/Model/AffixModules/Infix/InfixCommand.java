package Stemmer.Model.AffixModules.Infix;

import Stemmer.Model.AffixModules.Infix.Submodules.RemoveCommonInfix;
import Stemmer.Model.Stem;

import static Utility.print.println;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class InfixCommand
{

	Stem stem;

	public InfixCommand()
	{}

	public Stem performStemmingModules(Stem stem)
	{
		RemoveCommonInfix rci = new RemoveCommonInfix();
		stem = rci.reduceStem(stem);
		return stem;
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
