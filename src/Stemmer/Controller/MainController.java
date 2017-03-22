package Stemmer.Controller;

import Stemmer.Model.AffixModules.AffixCommand;
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
		if( !processed )
		{
			createRootSet();
		}
		return this.lemma;
	}

	public String getFeatures()
	{
		if( !processed )
		{
			createRootSet();
		}
		return this.features;
	}


	/**
	 * Test only
	 */
	public static class Test
	{
		public static void main(String[] args)
		{
			MainController mc = new MainController("silangan");
			println( "MC Lemma: " + mc.getLemma() );
			println( "MC Features: " + mc.getFeatures() );
		}
	}
}
