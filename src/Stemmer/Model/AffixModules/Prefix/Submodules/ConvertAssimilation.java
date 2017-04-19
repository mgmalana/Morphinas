package Stemmer.Model.AffixModules.Prefix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.AffixModules.AffixList;
import Stemmer.Model.Stem;

/**
 * Created by laurenz on 09/03/2017.
 */
public class ConvertAssimilation extends AbstractMorphoChange
{
	/* Copy the list of possible prefixes under assimilation */
	String[] assimilationPrefixes = AffixList.getPrefixAssimiliation();
	/* List of possible character replacements during stemming */
	char[] possibleReplacements = AffixList.getPossibleAssimilationCharReplacements();

	@Override
	public Stem reduceStem(Stem stem)
	{
		/* Original word */
		String word = stem.getStemString();
		/* Word Parts as always */
		String leftPart, rightPart, combinedPart;

		/* I should really find a way to combine all of this */
		for( int k = 0; k < assimilationPrefixes.length; k++ )
		{
			if( word.substring(0, word.length()/2).contains(assimilationPrefixes[k]) )
			{

			}
		}

		/* Unreachable return? Oo. Hotel? Trivago. */
		return stem;
	}

	@Override
	public String applyFeature(String foundAffix) {
		return null;
	}
}
