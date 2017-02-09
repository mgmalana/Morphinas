package Stemmer.Model.AffixModules.Suffix;

import Stemmer.Model.AffixModules.Suffix.Submodules.RemoveCommonSuffix;

import static Utility.print.println;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class SuffixCommand
{
	public static class test
	{
		public static void main(String[] args)
		{
			RemoveCommonSuffix cs = new RemoveCommonSuffix();
			String word = "ihawin";

			word = cs.reduceStem(word);
			println("word: " + word);
			println("Affix: " + cs.getFoundAffix());
			println("AffixFeatured: " + cs.getFoundAffixFeatured());
		}
	}
}
