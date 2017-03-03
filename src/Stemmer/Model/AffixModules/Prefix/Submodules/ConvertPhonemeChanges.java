package Stemmer.Model.AffixModules.Prefix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.Stem;

/**
 * Created by laurenz on 02/03/2017.
 */
public class ConvertPhonemeChanges extends AbstractMorphoChange
{
	String[] phonemePrefixes = { "ma" };

	public ConvertPhonemeChanges()
	{

	}

	@Override
	public Stem reduceStem(Stem stem)
	{
		/* Original word */
		String word = stem.getStemString();
		/* Word Parts */
		String leftPart, rightPart;
		/* r -> d */
		char origChar, newChar;

		for( int k = 0; k < phonemePrefixes.length; k++ )
		{
			if( word.contains( phonemePrefixes[k]) )
			{
				leftPart = word.substring( 0, phonemePrefixes[k].length() );
				if( phonemePrefixes[k].equalsIgnoreCase( leftPart) )
				{
					// do something
				}
			}

		}

		return null;
	}

	@Override
	public String applyFeature(String foundAffix) {
		return null;
	}

	public static class Test
	{
		ConvertPhonemeChanges cpc = new ConvertPhonemeChanges();

		public static void main(String[] args)
		{

		}
	}
}
