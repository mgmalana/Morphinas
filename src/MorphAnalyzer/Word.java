package MorphAnalyzer;

import java.util.ArrayList;


public class Word {

	private MAResult maresult;
	private String originalWord;
	private String rootWord;
	private int affixCount = 0;
	//	private Affix affixes;
	private ArrayList<Affix> affixes; 
	private ArrayList<Affix> prefixes;
	private ArrayList<Affix> suffixes;
	private ArrayList<Affix> infixes;
	

	/**
	 * Creates a new Word object with complete parameters
	 * @param maresult
	 * @param originalWord
	 * @param rootWord
	 * @param affixCount
	 * @param affixes
	 */
	public Word(MAResult maresult, String originalWord, String rootWord, int affixCount, ArrayList<Affix> affixes) 
	{
		super();
		this.maresult 		= maresult;
		this.originalWord 	= originalWord;
		this.rootWord 		= rootWord;
		this.affixCount 	= affixCount;
		this.affixes 		= affixes;
	}
	
	/**
	 * Creates a new Word object when other information has not been generated yet.
	 * @param originalWord
	 */
	public Word(String originalWord)
	{
		this.originalWord 	= originalWord;
	}
	
	public boolean finalContentsReady()
	{
		int contentCount = 0;
		
		//if root and input are already the same, the rootword might be blank and there's no need 
		if( originalWord.equals(rootWord) == true) {
			return true;
		}
		
		if( this.maresult == null ) {
			println("[WORD] maresult is null");
			return false;
		}
		if( this.originalWord == null ) {
			println("[WORD] originalWord is null");
			return false;
		}
		if( this.rootWord == null ) {
			println("[WORD] rootWord is null");
			return false;
		}
		if( this.affixCount == 0 ) {
			println("[WORD] affixCount is null");
			return false;
		}
		if( this.affixes.isEmpty() == true) {
			println("[WORD] affixes is null");
			return false;
		}
		
		return true;
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


	public ArrayList<Affix> getAffixes() {
		return affixes;
	}


	public void setAffixes(ArrayList<Affix> affixes) {
		this.affixes = affixes;
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
	
	public void printWordContent()
	{
		int size = affixes.size();
		println("Printing contents of the Word object/class.");
		if( finalContentsReady() == true) 
		{
			println("Original: " + this.originalWord + "| Root: " + this.rootWord);
			println("Number of Affixes in the original word: " + this.affixCount);
			
			for(int i = 0; i < size; i++)
			{
				Affix affix = affixes.get(i);
				println("Affix: " + affix.getAffix() + "| Type: " + affix.getAffixType());
			}
		}
	}
	
	public void printFormattedResult()
	{
		
	}
	
	private void println(String input)
	{
		System.out.println("" + input);
	}
	
}
