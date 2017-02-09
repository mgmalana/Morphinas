package Stemmer.Model.AffixModules;

import Stemmer.Model.AffixModules.Infix.InfixCommand;
import Stemmer.Model.AffixModules.Prefix.PrefixCommand;
import Stemmer.Model.AffixModules.Suffix.SuffixCommand;

/**
 * Created by laurenz on 09/02/2017.
 */
public class AffixCommand
{
	/* Commands */
	PrefixCommand pc;
	InfixCommand ic;
	SuffixCommand sc;
	/* Tree properties */
	int treeDepth;
	boolean mustStop 	= false;
	boolean donePrefix 	= false;
	boolean doneInfix 	= false;
	boolean doneSuffix	= false;

	public AffixCommand()
	{

	}


}
