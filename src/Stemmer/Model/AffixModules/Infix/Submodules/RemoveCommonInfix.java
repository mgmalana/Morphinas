package Stemmer.Model.AffixModules.Infix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.AffixModules.AffixList;

import static Utility.print.println;
/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class RemoveCommonInfix extends AbstractMorphoChange
{
	String[] commonInfixes = AffixList.getCommonInfixes();

	@Override
	public String reduceStem(String stem)
	{
		String inString;
		int infixLength;
		int stemLength = stem.length();
		for( String infix: commonInfixes )
		{
			infixLength = infix.length();
			for( int i = 1; i < (stemLength/2) + 1; i++ )
			{
				inString = stem.substring(i, i+infixLength);
				if ( infix.equalsIgnoreCase(inString) )
				{
					leftStem 	= stem.substring(0, i);
					rightStem 	= stem.substring( i+infixLength);
					foundAffix 	= infix;
					applyFeature(infix);
					return leftStem.concat(rightStem);
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
