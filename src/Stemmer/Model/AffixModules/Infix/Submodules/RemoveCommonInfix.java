package Stemmer.Model.AffixModules.Infix.Submodules;

import Stemmer.Model.AffixModules.AbstractMorphoChange;
import Stemmer.Model.AffixModules.AffixList;
import Stemmer.Model.Stem;

import static Utility.print.*;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class RemoveCommonInfix extends AbstractMorphoChange
{
	String[] commonInfixes = AffixList.getCommonInfixes();
	char[] vowels			= AffixList.getVowels();
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
				/* if it matches */
				if ( infix.equalsIgnoreCase(inString) )
				{
					if( ruleNotCCAfterStemming(word, i, infixLength) )
					{
						/* return original */
						return stem;
					}
					else
					{
						if( word.charAt(i) != vowels[0])
						{
							leftStem 	= word.substring(0, i);
						}
						rightStem 	= word.substring( i+infixLength);
						foundAffix 	= infix;
						/* Set or Update stem properties */
						stem.setFeature( applyFeature(infix) );
						stem.setStemString(leftStem.concat(rightStem));
					}
					return stem;
				}
			}
		}
		return stem;
	}

	public boolean ruleNotCCAfterStemming(String word, int prevCharIndex, int nextCharIndex)
	{
		int consonantCount = 0;
		prevCharIndex--;
		for( int i = 0; i < vowels.length; i++ )
		{
//			println(word.charAt(prevCharIndex)+"-"+word.charAt(nextCharIndex));
			if( word.charAt(prevCharIndex) != vowels[i] || word.charAt(nextCharIndex) != vowels[i] )
			{
				consonantCount++;
			}
		}

		if( consonantCount >= 2)
		{
				return true;
		}
		return false;
	}

	@Override
	public String applyFeature(String foundAffix)
	{
		foundAffixFeatured = "@" + foundAffix;
		return foundAffixFeatured;
	}
}
