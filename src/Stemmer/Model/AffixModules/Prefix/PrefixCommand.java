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
	String word;

	public PrefixCommand(Stem stem)
	{
		super(stem);
	}

	public Stem performStemmingModules()
	{
		RemoveCommonPrefix rcp = new RemoveCommonPrefix();
		newStem = rcp.reduceStem( stem );
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
			String word = "pinahintay";
			Stem stem  	= new Stem(word);
			PrefixCommand pc = new PrefixCommand( stem );
			println( "Changes: " + pc.isChanged() );
			println( "StemString: " + pc.stem.getStemString() );
		}
	}
}
