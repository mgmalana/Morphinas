package Stemmer.Model.AffixModules.Prefix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.Stem;

/**
 * Created by laurenz on 02/03/2017.
 */
public class ConvertPhonemeChanges extends AbstractMorphoChange
{
	String[] phonemeChangePrefixes = { "mar" };

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

		for(int k = 0; k < phonemeChangePrefixes.length; k++ )
		{
			if( word.contains( phonemeChangePrefixes[k]) )
			{
				leftPart = word.substring( 0, phonemeChangePrefixes[k].length() );
				if( phonemeChangePrefixes[k].equalsIgnoreCase( leftPart) )
				{
					// do something
				}
			}

		}

		return null;
	}

	@Override
	public String applyFeature(String foundAffix)
	{

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
