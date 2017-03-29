package Stemmer.Controller;

import Stemmer.Model.AffixModules.AffixCommand;
import Stemmer.Model.DBHandler;
import Stemmer.Model.RootSet;

import static Utility.print.*;

/**
 * Created by laurenz on 21/03/2017.
 */
public class MainController
{
	AffixCommand affixCommand = new AffixCommand();
	String inflectedWord;
	String lemma;
	String features;
	boolean processed = false;

	/**
	 * This constructor will perform stemming
	 * @param inflectedWord
	 * inflected word to be stemmed
	 */
	public MainController(String inflectedWord)
	{
		this.inflectedWord = inflectedWord;
		createRootSet();
	}

	public void setInflectedWord(String inflectedWord) {
		this.inflectedWord = inflectedWord;
	}

	public RootSet createRootSet()
	{
		RootSet rs;
		processed 		= true;
		rs 				= affixCommand.generatePISTree3( this.getInflectedWord() );
		this.lemma	 	= rs.getLemma();
		this.features 	= rs.getFeatures();
		return rs;
	}

	public String getInflectedWord()
	{
		return inflectedWord;
	}

	public String getLemma()
	{
//		if( !processed )
//		{
//
//			createRootSet();
//		}
		return this.lemma;
	}

	public String getFeatures()
	{
		String changedWord = specialResultsFeatures( this.inflectedWord );
//		if( changedWord.equalsIgnoreCase( inflectedWord ) )
//		{
//			if( !processed )
//			{
//				createRootSet();
//			}
//		}
//		else if ( this.features.length() < 1)
//		{
//			createRootSet();
//		}
//		else
//		{
//			return changedWord;
//		}
//		createRootSet();
		return this.features;
	}


	/**
	 * Checks if word is already a root word or a punctuation mark
	 * @param specialWord
	 * @return
	 */
	public String specialResultsFeatures( String specialWord )
	{
		/* Return this */
		String result = "";
		/* Database lookup */
		DBHandler dbHandler = new DBHandler();
		if ( specialWord.length() < 4 )
		{
			return "#" + result;
		}
		else if ( dbHandler.lookup( specialWord) )
		{
			return "#" + result;
		}
		return result;
	}


	/**
	 * Test only
	 */
	public static class Test
	{
		public static void main(String[] args)
		{
			MainController mc = new MainController("karamihan");
			RootSet rs = mc.createRootSet();
			println("RS Lemma: " + rs.getLemma());
			println("RS Features: " + rs.getFeatures());
			println( "MC Lemma: " + mc.getLemma() );
			println( "MC Features: " + mc.getFeatures() );
		}
	}
}
