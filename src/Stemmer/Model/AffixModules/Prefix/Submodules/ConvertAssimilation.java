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
	String[] assimPrefixes = AffixList.getPrefixAssimiliation();
	/* List of possible character replacements during stemming */
	char[] possibleReplacements = { 'b', 'p' };

	@Override
	public Stem reduceStem(Stem stem)
	{
		/* Original word */
		String word = stem.getStemString();

		/* Unreachable return? Oo. Hotel? Trivago. */
		return stem;
	}

	@Override
	public String applyFeature(String foundAffix) {
		return null;
	}
}
