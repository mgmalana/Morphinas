package MorphAnalyzer;

import java.util.ArrayList;

/**
 * Created by laurenztolentino on 05/16/16.
 */
public class Formatter
{

	Word word;

	ArrayList<Affix> prefixes;// = word.getPrefixes();
	ArrayList<Affix> suffixes;// = word.getSuffixes();
	ArrayList<Affix> infixes;//  = word.getInfixes();
	String rootWord;// 		  = word.getRootWord();

	String bracketedResult;

	public Formatter(Word word)
	{
		this.word 		= word;
		this.prefixes	= word.getPrefixes();
		this.suffixes	= word.getSuffixes();
		this.infixes 	= word.getInfixes();
		this.rootWord 	= word.getRootWord();
	}


	public String generateDashedResult()
	{
		String result = "";

		for( int i = 0; i < this.prefixes.size(); i++ )
		{
			result = result + "" + this.prefixes.get(i).getAffixBrackets();
			if( this.prefixes.get(i).getAffixBrackets() == "" ) {
				println("it's null bruh");
			}
		}

		result = result + "[" + rootWord + "]";

		for( int i = 0; i < this.suffixes.size(); i++ )
		{
			result = result + "" + this.suffixes.get(i).getAffixBrackets();
		}

		this.bracketedResult = result;
		return result;
	}

	/**
	 * Required by Gramatika
	 * ~
	 * @return
	 */
	public String generateFormattedResult()
	{
		ArrayList<Affix> gPrefix = reverseAffixOrder(this.prefixes);
		ArrayList<Affix> gInfix  = reverseAffixOrder(this.infixes);
		ArrayList<Affix> gSuffix = reverseAffixOrder(this.suffixes);
		String result	 		 = "";

		// cycle through all the prefixes first
		for( int i = 0; i < this.prefixes.size(); i++)
		{
			result = result + "~" + gPrefix.get(i).getAffix();
		}

		// insert the rootword
		result = result + "#" + this.rootWord;

		// cycle through all the suffixes as the last
		for( int i = 0; i < gSuffix.size(); i++)
		{
			result = result + "$" + gSuffix.get(i).getAffix();
		}
		return result;
	}

	/**
	 * Reverses the order of a selected ArrayList of Affix.
	 * Created because scanning of affixes start from the end of the word.
	 * Used in generated formatted result :)
	 * @return
	 * An ArrayList<Affix> with the same content but in a reversed order.
	 */
	public static ArrayList<Affix> reverseAffixOrder(ArrayList<Affix> reverseMe)
	{
		ArrayList<Affix> result = new ArrayList<Affix>();

		for( int i = reverseMe.size() - 1; i >= 0; i-- )
		{
			Affix tempAffix = reverseMe.get(i);
			result.add(tempAffix);
		}

		return result;
	}

	public String generateBracketedResult()
	{
		String result = "";
		// Creates "[prefix"
		for( int i = 0; i < this.prefixes.size(); i++ )
		{
			result = result + "" + this.prefixes.get(i).getAffixBrackets();
			// Adds [ before the root to signify the opening bracket of the suffix]
			if(i == (this.suffixes.size() - 1) )
			{
				for( int k = 0; k < this.suffixes.size(); k++ )
				{
					result = result + "[";
				}
			}
		}
		// creates [<rootword>]
		result = result + "[ROOT:" + this.rootWord + "]";
		// Adds ] after the root to signify the closing bracket of the prefix
		for( int k = 0; k < prefixes.size(); k++ )
		{
			result = result + "]";
		}
		// Creates "suffix]"
		for( int i = 0; i < this.suffixes.size(); i++ )
		{
			result = result + "" + this.suffixes.get(i).getAffixBrackets();
		}

		// Creates [infix|infix|..]
		for( int i = 0; i < this.infixes.size(); i++ )
		{
			if ( i == 0 )
			{
				result = result + "[<INF>";
			}
			// Creates "(infix)(infix)(..)"
			result = result + this.infixes.get(i).getAffixBrackets();
			// Adds the final ]
			if (i == (this.infixes.size() - 1) )
			{
				result = result + "]";
			}
		}

		this.bracketedResult = result;
		return result;
	}

	public void printFormattedResult()
	{
		println( this.generateFormattedResult() );
	}

	public void printBracketedResult()
	{
		println("");
		println( generateBracketedResult() );
	}

	public void printLongestOnly()
	{
		println("");
		println( "[" + this.longestCanonicalPrefix().getAffix() + "[" + this.rootWord + "]");
	}

	public void printWordContentDetailed()
	{

		// Initiate removing duplicate affixes
		removeDuplicateAffixes();

		println("Printing contents of the Word object/class.");
		if( word.finalContentsReady(true) == true)
		{
			println("Original: " + word.getRootWord() + "| Root: " + this.rootWord);
			println("Number of Affixes in the original word: " + word.getAffixCount());

			println("Printing Prefixes:");
			for(int i = 0; i < this.prefixes.size(); i++)
			{
				println( "PFX: " + this.prefixes.get(i).getAffix() );
			}

			println("Printing Infixes:");
			for(int i = 1; i < this.infixes.size(); i++)
			{
				println( "IFX: " + this.infixes.get(i).getAffix() );
			}

			println("Printing Suffixes:");
			for(int i = 0; i < this.suffixes.size(); i++)
			{
				println( "SFX: " + this.suffixes.get(i).getAffix() );
			}
		}
	}

	/**
	 * Keeps the code shorter.
	 * Simply removes all duplicates from all types of affixes.
	 */
	public void removeDuplicateAffixes()
	{
		this.prefixes = removeDuplicateAffix(prefixes);
		this.infixes  = removeDuplicateAffix(infixes);
		this.suffixes = removeDuplicateAffix(suffixes);
	}

	/**
	 * Removes duplicates on the selected affix by comparing affix.getAffix();
	 *
	 * @param affix
	 * Could be prefixes, suffixes, or infixes [vbt]
	 * @return
	 * An ArrayList<Affix> of affixes with duplicates removed
	 */
	public ArrayList<Affix> removeDuplicateAffix(ArrayList<Affix> affix)
	{
		ArrayList<Affix> tempAffix = affix;
		ArrayList<Affix> resultAffix = new ArrayList<Affix>();
		boolean skip = false;

		for( int i = 0; i < affix.size(); i++)
		{
			skip = false;
			for(int j = 0; j < affix.size(); j++)
			{
				if( skip == true ) {
					break;
				}
				if( tempAffix.get(i).getAffix().compareTo( affix.get(j).getAffix() ) != 0)
				{
					skip = true;
					resultAffix.add(tempAffix.get(i));
				}
			}
		}

		return resultAffix;
	}



	/**
	 * Finds the longest prefix on the Word's list of prefixes;
	 * Does not find the most
	 * @return
	 * The longest prefix by comparing length
	 */
	private Affix longestCanonicalPrefix()
	{
		Affix currLong 		= this.prefixes.get(0); // longest found affix
		int currLongLength  = currLong.getAffix().length();

		for( int i = 1; i < prefixes.size(); i++ )
		{
			Affix temp = this.prefixes.get(i);
			int prefixLength	= temp.getAffix().length();

			if( currLongLength < prefixLength )
			{
				currLong = temp;
			}
		}

		return currLong;
	}

	/**
	 * Looks for the longest existing canonical prefix and returns it's length for you.
	 * Because you know, we got it all for you.
	 * @return
	 * integer containing the longest canonical prefix length.
	 */
	private int longestCanonicalPrefixLength()
	{
		Affix currLong = this.longestCanonicalPrefix();
		int length     = currLong.getAffix().length();
		return length;
	}

	public static boolean checkIfRootViaDB(String word)
	{
		DBLexiconSQL lex 	= new DBLexiconSQL();
		boolean isRoot 		= false;

		try {
			if( lex.lookup(word) )
			{
				isRoot = true;
			}
		} catch (Exception e) {
			println("checkIfRootViaDB encountered a MySQL Problem huhuhuhuuh");
			e.printStackTrace();
		}

		return isRoot;
	}

	public static void println(String in)
	{
		System.out.println("" + in);
	}
}