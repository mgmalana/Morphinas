package Stemmer.Model.AffixModules.Prefix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.AffixModules.AffixList;
import Stemmer.Model.Stem;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class RemoveCommonPrefix extends AbstractMorphoChange
{
	/* AbstractMorphoChange Properties: foundAffix */
	String[] commonPrefixes = AffixList.getCommonPrefixes();

	@Override
	public Stem reduceStem(Stem stem)
	{
		String word = stem.getStem();
		int prefixLength;
		for( String prefix: commonPrefixes )
		{
			prefixLength = prefix.length();
			leftStem 	 = word.substring(0, prefixLength);
			if( prefix.equalsIgnoreCase(leftStem) )
			{
				this.foundAffix = leftStem;
				rightStem 		= word.substring(prefixLength);
				/* Update or Set Stem properties */
				stem.setStem(rightStem);
				stem.setFeature( stem.getFeature() + "" + applyFeature( prefix ));
				return stem;
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
