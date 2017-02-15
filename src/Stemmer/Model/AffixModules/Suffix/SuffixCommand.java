package Stemmer.Model.AffixModules.Suffix;

import Stemmer.Model.AffixModules.Suffix.Submodules.RemoveCommonSuffix;
import Stemmer.Model.Stem;

import static Utility.print.println;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class SuffixCommand
{
	Stem stem;

	public Stem performStemmingModules(Stem stem)
	{
		RemoveCommonSuffix rcs = new RemoveCommonSuffix();
		stem = rcs.reduceStem(stem);
		return stem;
	}
	public static class test
	{
		public static void main(String[] args)
		{
			RemoveCommonSuffix cs = new RemoveCommonSuffix();
			String word = "ihawin";
			Stem stem = new Stem(word);

			stem = cs.reduceStem(stem);
			println("word: " + word + " -> " + stem.getStemString());
			println("Affix: " + cs.getFoundAffix());
			println("AffixFeatured: " + cs.getFoundAffixFeatured());
		}
	}
}
