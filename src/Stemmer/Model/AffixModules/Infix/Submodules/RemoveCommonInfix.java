package Stemmer.Model.AffixModules.Infix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.AffixModules.AffixList;
import Stemmer.Model.Stem;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class RemoveCommonInfix extends AbstractMorphoChange
{
	String[] commonInfixes = AffixList.getCommonInfixes();

	@Override
	public Stem reduceStem(Stem stem)
	{
		String word = stem.getStemString();
		String inString;
		int infixLength;
		int stemLength = word.length();
		for( String infix: commonInfixes )
		{
			infixLength = infix.length();
			for( int i = 1; i < (stemLength/2) + 1; i++ )
			{
				inString = word.substring(i, i+infixLength);
				if ( infix.equalsIgnoreCase(inString) )
				{
					leftStem 	= word.substring(0, i);
					rightStem 	= word.substring( i+infixLength);
					foundAffix 	= infix;
					/* Set or Update stem properties */
					stem.setFeature( applyFeature(infix) );
					stem.setStemString(leftStem.concat(rightStem));
					return stem;
				}
			}
		}
		return stem;
	}

	@Override
	public String applyFeature(String foundAffix)
	{
		foundAffixFeatured = "@" + foundAffix;
		return foundAffixFeatured;
	}
}
