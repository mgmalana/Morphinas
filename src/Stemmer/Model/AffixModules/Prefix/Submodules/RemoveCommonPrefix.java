package Stemmer.Model.AffixModules.Prefix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.AffixModules.AffixList;
import Stemmer.Model.Stem;
import static Utility.print.*;
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
		String word = stem.getStemString();
		if( word.length() <= 4)
		{
			return stem;
		}
		int prefixLength;

		for( String prefix: commonPrefixes )
		{
			prefixLength = prefix.length();
			if ( word.length() > prefixLength )
			{
				leftStem 	 = word.substring(0, prefixLength);
				/* Will execute when a prefix is found */
				if( prefix.equalsIgnoreCase(leftStem) )
				{
					/* Must check if prefix found belongs to the prefixes with phoneme changing */
					if ( prefix.equalsIgnoreCase("ma") )
					{
						ConvertPhonemeChanges cpc = new ConvertPhonemeChanges();
						Stem cpcStem = cpc.reduceStem( stem.cloneThis() );
						if ( !cpcStem.getStemString().equalsIgnoreCase( stem.getStemString() ) )
						{
							// update the stem string
							stem.setStemString( cpcStem.getStemString() );
							// update the stem prefix features
							stem.setPrefixFeatures( stem.getPrefixFeatures() + cpcStem.getPrefixFeatures() );
						}
						else
						{
							this.foundAffix = leftStem;
							rightStem 		= word.substring(prefixLength);
							/* Update or Set Stem properties */
							stem.setStemString(rightStem);
//							stem.setFeature( stem.getFeature() + "" + applyFeature( prefix ));
							stem.setPrefixFeatures( stem.getPrefixFeatures() + applyFeature( prefix ));
							return stem;
						}

					}
					else
					{
						this.foundAffix = leftStem;
						rightStem 		= word.substring(prefixLength);
						/* Update or Set Stem properties */
						stem.setStemString(rightStem);
						stem.setFeature( stem.getFeature() + "" + applyFeature( prefix ));
						return stem;
					}

				}
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
