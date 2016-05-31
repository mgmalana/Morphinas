package Morphinas;

import MorphAnalyzer.*;
import MorphAnalyzer.MorphLearnerRedup;

/**
 * Created by laurenztolentino on 05/30/2016.
 */
public class MorphPI
{
	MorphLearnerRedup mpl = new MorphLearnerRedup();
	WordsLoader training = new WordsLoader(WordsLoader.TRAINING);
	FilePush gPush;
//	Enter filename here
	String address;
	String fileName;


	String inputSentence = "";
	String input = "";

	public MorphPI() throws Exception
	{
		println("Please don't forget to set file address (complete address and folder name) and file name");
		println("Address: /Users/morphinasdev/Eclipse/workspace/Morphinas/src/");
		println("fileName: testHPOST.words");
	}

	public MorphPI(String address, String fileName) throws Exception
	{
		this.address  = address;
		this.fileName = fileName;
		FilePush filePush = new FilePush(address, fileName);
	}

	public void pushFile(String address, String fileName)
	{
		this.address  = address;
		this.fileName = fileName;

		pushFile();
	}

	public void pushFile()
	{
		FilePush filePush = new FilePush(this.address, this.fileName);
		this.gPush = filePush;
	}

	public String pullFeaturedResults() throws Exception
	{
		String result = "";

		if( address.equalsIgnoreCase("") || fileName.equalsIgnoreCase("") )
		{
			println("Will not pull until address and fileName is not blank.");
			 System.exit(0);
		}

//		Read from the file sent to FilePush through gPush
		String[] wordsLIst 	= gPush.readFromFile();
		boolean skip 		= false;
		Formatter fm;
		Word word;
//		Open up the database
		DBLexiconSQL t 		= new DBLexiconSQL();


		
		return result;
	}

	public String startIt() throws Exception
	{
		TestMaker tm = new TestMaker("/Users/laurenztolentino/Eclipse/workspace/Morphinas/src/","testHPOST.words");
		String[] wordsList = tm.readFromFile();
		boolean skip = false;
		String result = "";
		MAResult maresult;
		Formatter fm;
		Word word;

		DBLexiconSQL t = new DBLexiconSQL();

		for( int i = 0; i < wordsList.length; i++ )
		{
//			println("wordList"+"[" + i + "]: " + wordsList[i]);
			String single = wordsList[i];
			single = single.toLowerCase();
			single = Formatter.removeNonLetters(single);

			if( single.equalsIgnoreCase(".") )
			{
				result = result + "#" + single + "\n";
				skip   = true;
			}

			else if( t.lookup(single) && !skip )
			{
				result 	= result + "#" + single + " ";
				skip 	= true;
			} else {
				skip = false;

				// because all tagalog words are already root when <= 3
				if( single.length() > 3 && !skip)
				{
					mpl.globalPrefix = "";
					mpl.globalSuffix = "";
					mpl.analyzeMultipleMod(single);
					word = mpl.getWordObject();
					fm   = new Formatter(word);

					if( !fm.generateFeaturesResult().equalsIgnoreCase(""))
					{
						result = result + fm.generateFeaturesResult() + " ";
					} else {
						result = result + "*" + single + " ";
					}

				} else {
					result = result + "*" + single + " ";
				}
			}




		}

		println("");
		println(result);



		return result;
	}

	public void println(String input)
	{
		System.out.println("" + input);
	}

}
