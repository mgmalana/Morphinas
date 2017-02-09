package Stemmer.Model.AffixModules.Infix;

import Stemmer.Model.AffixModules.Infix.Submodules.RemoveCommonInfix;

import static Utility.print.println;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class InfixCommand
{
	public static class test
	{
		public static void main(String[] args)
		{
			RemoveCommonInfix ci = new RemoveCommonInfix();
			String word = "sinindi";

			word = ci.reduceStem(word);
			println("word: " + word);
			println("Affix: " + ci.getFoundAffix());
			println("AffixFeatured: " + ci.getFoundAffixFeatured());
		}
	}
}
