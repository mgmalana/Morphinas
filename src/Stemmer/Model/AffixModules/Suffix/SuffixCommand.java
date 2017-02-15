package Stemmer.Model.AffixModules.Suffix;

import Stemmer.Model.AffixModules.AbstractAffixCommand;
import Stemmer.Model.AffixModules.Suffix.Submodules.RemoveCommonSuffix;
import Stemmer.Model.Stem;

import static Utility.print.println;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class SuffixCommand extends AbstractAffixCommand
{

	public SuffixCommand(Stem stem)
	{
		super(stem);
	}

	public Stem performStemmingModules()
	{
		RemoveCommonSuffix rcs = new RemoveCommonSuffix();
		newStem = rcs.reduceStem(stem);
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
