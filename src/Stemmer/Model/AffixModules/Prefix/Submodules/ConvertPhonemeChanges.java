package Stemmer.Model.AffixModules.Prefix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.Stem;
import static Utility.print.*;
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
		String leftPart, rightPart, combinedPart;
		/* r -> d */
		char origChar, newChar;

		for(int k = 0; k < phonemeChangePrefixes.length; k++ )
		{
			if( word.contains( phonemeChangePrefixes[k]) )
			{
				leftPart = word.substring( 0, phonemeChangePrefixes[k].length() );
				if( phonemeChangePrefixes[k].equalsIgnoreCase(leftPart) )
				{
					leftPart 		= "d";
					rightPart 		= word.substring( phonemeChangePrefixes[k].length() );
					combinedPart 	= leftPart + rightPart;
					stem.setStemString( combinedPart );
					stem.setPrefixFeatures( stem.getPrefixFeatures() + applyFeature(phonemeChangePrefixes[k]) );
				}
			}
		}

		return stem;
	}

	@Override
	public String applyFeature(String foundAffix)
	{
		return "~" + foundAffix;
	}

	public static class Test
	{
		ConvertPhonemeChanges cpc = new ConvertPhonemeChanges();

		public static void main(String[] args)
		{
			ConvertPhonemeChanges convertPhonemeChanges = new ConvertPhonemeChanges();
			String word = "marumi";
			Stem stem 	= new Stem( word );
			stem = convertPhonemeChanges.reduceStem(stem);
			println( "Reduced Stem: " + stem.getStemString() );
		}
	}
}
