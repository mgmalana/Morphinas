package Stemmer.Model.AffixModules.Prefix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.AffixModules.AffixList;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class RemoveCommonPrefix extends AbstractMorphoChange
{
	/* AbstractMorphoChange Properties: foundAffix */
	String[] commonPrefixes = AffixList.getCommonPrefixes();

	@Override
	public String reduceStem(String stem)
	{
		int prefixLength;
		for( String prefix: commonPrefixes )
		{
			prefixLength = prefix.length();
			leftStem 	 = stem.substring(0, prefixLength);
			if( prefix.equalsIgnoreCase(leftStem) )
			{
				this.foundAffix = leftStem;
				rightStem 		= stem.substring(prefixLength);
				applyFeature( prefix );
				return rightStem;
			}
		}
		return stem;
	}

	@Override
	public String applyFeature(String foundAffix)
	{
		foundAffixFeatured = "~" + foundAffix;
		return foundAffixFeatured;
	}
}
