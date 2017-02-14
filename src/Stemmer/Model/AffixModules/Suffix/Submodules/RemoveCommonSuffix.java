package Stemmer.Model.AffixModules.Suffix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.AffixModules.AffixList;
import Stemmer.Model.Stem;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class RemoveCommonSuffix extends AbstractMorphoChange
{
	String[] commonSuffixes = AffixList.getCommonSuffixes();
	@Override
	public Stem reduceStem(Stem stem)
	{
		String word = stem.getStem();
		int suffixLength;
		for( String suffix: commonSuffixes )
		{
			suffixLength = suffix.length();
			rightStem  	 = word.substring( word.length() - suffixLength );
			if( suffix.equalsIgnoreCase(rightStem) )
			{
				foundAffix 	= suffix;
				leftStem  	= word.substring(0, word.length()-suffixLength);
				/* Update or Set stem properties */
				stem.setStem(leftStem);
				stem.setFeature( stem.getFeature() + "" + applyFeature( suffix ) );
				return stem;
			}
		}
		return stem;
	}


	@Override
	public String applyFeature(String foundAffix)
	{
		foundAffixFeatured = "+" + foundAffix;
		return foundAffixFeatured;
	}
}
