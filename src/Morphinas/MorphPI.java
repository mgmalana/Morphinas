package Morphinas;

import DataStructures.MAResult;
import DataStructures.Sentence;
import DataStructures.Word;
import MorphAnalyzer.*;
import MorphAnalyzer.MorphLearnerRedup;

import java.util.ArrayList;

/**
 * Created by laurenztolentino on 05/30/2016.
 */
public class MorphPI
{
	MorphLearnerRedup mpl = new MorphLearnerRedup();
	WordsLoader training = new WordsLoader(WordsLoader.TRAINING);
	IOHandler gPush;
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
		IOHandler ioh = new IOHandler(address, fileName);
	}

	/**
	 * Starts file reading given an address and filename
	 * @param address
	 * Ex: /Users/.../something/
	 * (Please do not forget the '/' at the end
	 * @param fileName
	 * Ex: file.txt
	 * (Please include filename)
	 */
	public void pushFile(String address, String fileName)
	{
		this.address  = address;
		this.fileName = fileName;

		pushFile();
	}

	public void pushFile()
	{
		if( this.fileName != null & this.address != null )
		{
			println("Reading from " + fileName);
			IOHandler ioh 	= new IOHandler(this.address, this.fileName);
			this.gPush 		= ioh;
		} else {
			println("File name and/or address is null.");
		}

	}

	public void pushLine(String input) throws Exception
	{

	}

	public void pushWord(String sWord) throws Exception
	{
		MorphLearnerRedup mpl = new MorphLearnerRedup();
		String input = Formatter.removeNonLetters(sWord);


		Formatter fm;
		WordPair wp;
		input = input.toLowerCase();
//		println("Finding root of: " + input);
		// String ng result only
		String root = "";
		root = mpl.analyzeMultipleMod(input).result;
		// Result using MAResult
		MAResult maresult = mpl.analyzeMultipleMod(input); // Not working properly #why
		Word word = mpl.getWordObject();


		fm = new Formatter(word);
		fm.printWordContentDetailed();
		// fm.printBracketedResult();
		fm.printFormattedResult();
		fm.printFeaturesResult();
		println("");
//		AffixBreakdown ab = new AffixBreakdown();

		try {
			fm.printLongestOnly();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			println("whoopsies. Didn't find the longest ");
		}
	}

	public String generateRootResults(String[] wordsList) throws Exception
	{
		String result = "";

		Formatter fm;
		Word word;
//		Open up the database
		DBLexiconSQL t 		= new DBLexiconSQL();

		for( int i = 0; i < wordsList.length; i++ )
		{
			String single = wordsList[i];
			single = single.toLowerCase();
			println("analyzing: " + single);
			mpl.analyzeMultipleMod(single);
			word = mpl.getWordObject();
			fm = new Formatter(word);
			result = result + fm.generateRootResult() + "\n";
		}

		IOHandler ioh = new IOHandler();
		ioh.printToTxtFileRoot("manoLemma", result);

		return result;
	}

	/**
	 * Breaks down wordsList into sentences. <br>
	 * Ex: ["hello", "this", "is", "a", "sentence", "."], [ "this", "is", "another", "one". "."]
	 * WordsList
	 * @param wordsList
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Sentence> createSentences(String[] wordsList) throws Exception
	{
		ArrayList<Sentence> sentences = new ArrayList<>();
		ArrayList<Word> tempWords 	  = new ArrayList<>();
		Sentence sentence;
		Word word;

		for( int i = 0; i < wordsList.length; i++ )
		{
			word = new Word(wordsList[i]);
			tempWords.add(word);
			/* Will create a sentence once a '.' is found. */
			if( wordsList[i].equalsIgnoreCase(".") )
			{
				sentence = new Sentence(tempWords);
				sentence.setOrigCount( sentence.getWordCount() );
				sentences.add(sentence);
				/* Clears the tempWords object. */
				tempWords = new ArrayList<>();
			}
		}
		return sentences;
	}

	/**
	 * OYEA
	 * @param sentences
	 * @return
	 * @throws Exception
	 */
	public String featuredResultString(ArrayList<Sentence> sentences) throws Exception
	{
		/* Result to be returned */
		String result = "";
		/* Basic data structures objects */
		Sentence sentence;
		Formatter fm;
		Word word;
		/* Call the database (also opens a connection) */
		DBLexiconSQL db = new DBLexiconSQL();
		/* Temp variables */
		ArrayList<Word> words;
		String single;
		/* Iterate all existing sentences */
		for( int s = 0; s < sentences.size(); s++ )
		{
			sentence  = sentences.get(s);
			words     = sentence.getWords();
			/* Iterate all words in a sentence */
			for( int w = 0; w < words.size(); w++ )
			{
				single = words.get(w).getOriginalWord();
				/* For the first word in the sentence */
				if( w == 0 )
				{
					result = result + ":FS";
				}
				result = result + single + " ";
				/*mpl.analyzeMultipleMod( sentence.getWords().get(w).getOriginalWord() );
				word  	= mpl.getWordObject();
				fm 		= new Formatter( word );
				fm.generateFeaturesResult();
				fm.printFeaturesResult();*/
			}
		}
		println(result);
		return result;
	}

	public String generateFeaturedResult(String[] wordsList) throws Exception
	{
		String result = "";

		boolean skip 		= false;
		Formatter fm;
		Word word;
//		Open up the database
		DBLexiconSQL t 		= new DBLexiconSQL();

		for( int i = 0; i < wordsList.length; i++ )
		{
//			println("wordList"+"[" + i + "]: " + wordsList[i]);
			String single = wordsList[i];
			single = single.toLowerCase();

//			Checks if the word is either the first word in the entire input or first word of the sentence.
			if( i == 0)
			{
				result  = result + ":FS";
				skip	= true;
			}
			if( i == wordsList.length - 1) {
				skip = true;
			}
//			If the word's first letter is capital
			if( !single.equals(wordsList[i]) && !skip)
			{
				result 	= result + ":F*" + single + " ";
				skip 	= true;
			}
//			Start
			if( !skip )
			{
				if( single.equalsIgnoreCase(".") && !skip)
				{
					result = result + "#" + single + "\n" + ":FS";
					skip   = true;
				}
				else if( t.lookup(single) && !skip )
				{
					result 	= result + "#" + single + " ";
					skip 	= true;
				}
				else {
					single = Formatter.removeNonLetters(single);
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
						result = result + "#" + single + " ";
					}
				}
			}
			skip = false;
		}

		println("");
//		println(result);

		IOHandler ioh = new IOHandler();
		ioh.printToTxtFile(result);
		return result;
	}

	public String pullRootResultsFromFile() throws Exception
	{
		String result = "";

		if( address.equalsIgnoreCase("") || fileName.equalsIgnoreCase("") )
		{
			println("Will not pull until address and fileName is not blank.");
			System.exit(0);
		}

//		Read from the file sent to IOHandler through gPush
		String[] wordsList 	= gPush.readFromFile();

		result = generateRootResults(wordsList);

		return result;
	}


	public String pullFeaturedResultsFromFile() throws Exception
	{
		String result = "";

		if( address.equalsIgnoreCase("") || fileName.equalsIgnoreCase("") )
		{
			println("Will not pull until address and fileName is not blank.");
			 System.exit(0);
		}

//		Read from the file sent to IOHandler through gPush
		String[] wordsList 	= gPush.readFromFile();

		result = generateFeaturedResult(wordsList);

		return result;
	}

//	public String startIt() throws Exception
//	{

//		String[] wordsList = tm.readFromFile();
//		boolean skip = false;
//		String result = "";
//		MAResult maresult;
//		Formatter fm;
//		Word word;
//
//		DBLexiconSQL t = new DBLexiconSQL();
//
//		for( int i = 0; i < wordsList.length; i++ )
//		{
////			println("wordList"+"[" + i + "]: " + wordsList[i]);
//			String single = wordsList[i];
//			single = single.toLowerCase();
//			single = Formatter.removeNonLetters(single);
//
//			if( single.equalsIgnoreCase(".") )
//			{
//				result = result + "#" + single + "\n";
//				skip   = true;
//			}
//
//			else if( t.lookup(single) && !skip )
//			{
//				result 	= result + "#" + single + " ";
//				skip 	= true;
//			} else {
//				skip = false;
//
//				// because all tagalog words are already root when <= 3
//				if( single.length() > 3 && !skip)
//				{
//					mpl.globalPrefix = "";
//					mpl.globalSuffix = "";
//					mpl.analyzeMultipleMod(single);
//					word = mpl.getWordObject();
//					fm   = new Formatter(word);
//
//					if( !fm.generateFeaturesResult().equalsIgnoreCase(""))
//					{
//						result = result + fm.generateFeaturesResult() + " ";
//					} else {
//						result = result + "*" + single + " ";
//					}
//
//				} else {
//					result = result + "*" + single + " ";
//				}
//			}
//
//
//
//
//		}
//
//		println("");
//		println(result);
//
//
//
//		return result;
//	}



	public static void main(String[] args) throws Exception
	{

		ArrayList<Sentence> sentences;
		String testMe = "Ito ay isang halimbawa ng isang pangungusap . Ito ay pangalawang pangugusap . Leche , gumagana ba talaga ito ?";
		MorphPI mpi = new MorphPI();

		String[] splitMe = testMe.split(" ");
		sentences = mpi.createSentences(splitMe);
		mpi.featuredResultString(sentences);


	}

	public static void println(String input)
	{
		System.out.println("" + input);
	}

}
