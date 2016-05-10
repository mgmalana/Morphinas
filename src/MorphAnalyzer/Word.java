package MorphAnalyzer;

import java.util.ArrayList;


public class Word {

	private MAResult maresult;
	private String originalWord;
	private String rootWord;
	private int affixCount = 0;
	//	private Affix affixes;	
	private ArrayList<Affix> prefixes;
	private ArrayList<Affix> suffixes;
	private ArrayList<Affix> infixes;
	
	private String bracketedResult = "";
	private String dashedResult    = "";
	

	/**
	 * Creates a new Word object with complete parameters
	 * @param maresult
	 * @param originalWord
	 * @param rootWord
	 * @param affixCount
	 * @param prefixes
	 * @param infixes
	 * @param suffixes
	 */
	public Word(MAResult maresult, String originalWord, String rootWord, int affixCount, ArrayList<Affix> prefixes, ArrayList<Affix> infixes, ArrayList<Affix> suffixes) 
	{
		super();
		this.maresult 		= maresult;
		this.originalWord 	= originalWord;
		this.rootWord 		= rootWord;
		this.affixCount 	= affixCount;		
		this.prefixes 		= prefixes;
		this.infixes 		= infixes;
		this.suffixes 		= suffixes;
	}
	
	/**
	 * Creates a new Word object when other information has not been generated yet.
	 * @param originalWord
	 */
	public Word(String originalWord)
	{
		this.originalWord 	= originalWord;		
	}
	
	/**
	 * Tells a lost soul that this Word has all of the contents a word needs.
	 * @param willContinue
	 * will still return true
	 * @return
	 * True if all of the Word's content exist and false if not.
	 */
	public boolean finalContentsReady(boolean willContinue)
	{
		int contentCount = 0;
		boolean decision = false;
		
		//if root and input are already the same, the rootword might be blank and there's no need 
		if( originalWord.equals(rootWord) == true) {
			decision = true;
		}		
		else 
		{
			if( this.maresult == null ) {
				println("[WORD] maresult is null");
				decision = false;
			}
			if( this.originalWord == null ) {
				println("[WORD] originalWord is null");
				decision = false;
			}
			if( this.rootWord == null ) {
				println("[WORD] rootWord is null");
				decision = false;
			}
			if( this.affixCount == 0 ) {
				println("[WORD] affixCount is null");
				decision = false;
			}
			if( this.prefixes.isEmpty() == true) {
				println("[WORD] prefixes is null");
				decision = false;
			}
		}
		// 
		if( willContinue == true ) {
			return true;
		}
		
		return decision;
	}

	/**
	 * Gets the MAResult object coming from the MorphLearnerInfix
	 * @return
	 */
	public MAResult getMaresult()
	{
		return maresult;
	}

	/**
	 * Sets the MAResult object from MorphLearnerInfix
	 * @param maresult
	 */
	public void setMaresult(MAResult maresult) 
	{
		this.maresult = maresult;
	}

	/**
	 * Returns the original inflicted word
	 * @return
	 */
	public String getOriginalWord() 
	{
		return originalWord;
	}

	/**
	 * Set the original inflicted word
	 * @param originalWord
	 */
	public void setOriginalWord(String originalWord) 
	{
		this.originalWord = originalWord;
	}

	
	public String getRootWord() {
		return rootWord;
	}


	public void setRootWord(String rootWord) {
		this.rootWord = rootWord;
	}


	public int getAffixCount() {
		return affixCount;
	}


	public void setAffixCount(int affixCount) {
		this.affixCount = affixCount;
	}
 
	public ArrayList<Affix> getPrefixes() {
		return prefixes;
	}

	public void setPrefixes(ArrayList<Affix> prefixes) {
		this.prefixes = prefixes;
	}

	public ArrayList<Affix> getSuffixes() {
		return suffixes;
	}

	public void setSuffixes(ArrayList<Affix> suffixes) {
		this.suffixes = suffixes;
	}

	public ArrayList<Affix> getInfixes() {
		return infixes;
	}

	public void setInfixes(ArrayList<Affix> infixes) {
		this.infixes = infixes;
	}
	
	public void printWordContentDetailed()
	{
	
		// Initiate removing duplicate affixes
		removeDuplicateAffixes();
		
		println("Printing contents of the Word object/class.");
		if( finalContentsReady(true) == true) 
		{
			println("Original: " + this.originalWord + "| Root: " + this.rootWord);
			println("Number of Affixes in the original word: " + this.affixCount);
			
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
	
	public void printFormattedResult()
	{
		
	}
	
	private void println(String input)
	{
		System.out.println("" + input);
	}
	
	private void print(String input)
	{
		System.out.print("" + input);
	}

	/**
	 * Gets a formatted affix String
	 * @return
	 * It looks like this -> [pinagpa[pag[pa[liban]
	 */
	public String getBracketedResult() 
	{
		return bracketedResult;
	}

	public String getDashedResult() 
	{
		return dashedResult;
	}
	
}
