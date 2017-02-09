package Stemmer.Model.AffixModules.Suffix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.AffixModules.AffixList;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class RemoveCommonSuffix extends AbstractMorphoChange
{
	String[] commonSuffixes = AffixList.getCommonSuffixes();
	@Override
	public String reduceStem(String stem)
	{
		int suffixLength;
		for( String suffix: commonSuffixes )
		{
			suffixLength = suffix.length();
			rightStem  	 = stem.substring( stem.length() - suffixLength );
			if( suffix.equalsIgnoreCase(rightStem) )
			{
				foundAffix 	= suffix;
				leftStem  	= stem.substring(0, stem.length()-suffixLength);
				applyFeature( suffix );
				return leftStem;
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
