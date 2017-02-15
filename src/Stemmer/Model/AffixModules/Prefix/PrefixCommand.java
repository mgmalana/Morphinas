package Stemmer.Model.AffixModules.Prefix;

import Stemmer.Model.AffixModules.Prefix.Submodules.RemoveCommonPrefix;
import Stemmer.Model.Stem;

import static Utility.print.println;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class PrefixCommand {
	String word;

	public PrefixCommand() {
	}

	public PrefixCommand(Stem stem) {
		this.word = stem.getStem();
//		performStemmingModules(stem);
	}

	public Stem performStemmingModules(Stem stem)
	{
		RemoveCommonPrefix rcp = new RemoveCommonPrefix();
		stem = rcp.reduceStem( stem );
		return stem;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public static class test
	{
		public static void main(String[] args)
		{
			RemoveCommonPrefix cp = new RemoveCommonPrefix();
			String word = "pinahiram";
			Stem stem = new Stem(word);

			stem = cp.reduceStem(stem);
			println("word: " + word + " -> " + stem.getStem());
			println("Affix: " + cp.getFoundAffix());
			println("AffixFeatured: " + cp.getFoundAffixFeatured());
		}
	}
}
