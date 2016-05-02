package MorphAnalyzer;

public class Affix {

	private String affix;
	private String affixType;
	
	/**
	 * Creates a new Affix object/class which contains (in string) affix (-um, -an) and the affix type (suffix, prefix) 
	 * @param affix
	 * The affix of the word: -um, -an, etc.
	 * @param affixType
	 * The affix type of the word: infix, prefix, suffix
	 */
	public Affix(String affix, String affixType)
	{
		this.affix 		= affix;
		this.affixType 	= affixType;
	}
	
	/** 
	 * 
	 * @return
	 * The affix of the word in String
	 */
	public String getAffix()
	{
		return this.affix;
	}
	
	/**
	 * 
	 * @return
	 * Affix type of the word in String
	 */
	public String getAffixType()
	{
		return this.affixType;
	}
	
	/**
	 * 
	 * @param inputAffix
	 * Sets affix in String
	 */
	public void setAffix(String inputAffix)
	{
		this.affix = inputAffix;
	}
	
	/**
	 * Sets affix type in String
	 * @param inputAffixType
	 */
	public void setAffixType(String inputAffixType)
	{
		this.affixType = affixType;
	}
	
}
