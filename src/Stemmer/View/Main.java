package Stemmer.View;

import Stemmer.Controller.MainController;
import Stemmer.Model.Sentence;

import java.util.ArrayList;

import static Utility.print.*;
/**
 * Created by laurenztolentino on 02/08/2017.
 */
public class Main
{
	IOHandler ioHandler;

	long startTime, endTime;
	final String addressPrefix = "/Users/laurenztolentino/Developer/morphinas/Morphinas/ReadFiles/";

	/* testHPOST Variations to load */
	final String testHPOSTuncleaned = "testHPOST-uncleaned.words";
	final String testHPOST 			= "testHPOST.words";
	final String morphRead 			= "morphRead.pinas";
	final String minitext			= "minitext.txt";
	final String readThisFile		= "correct_words.txt";

	public Main() {}

	public void startTesting() throws Exception
	{
		ArrayList<Sentence> sentences;
		ioHandler = new IOHandler( addressPrefix, testHPOST );
		String[] content = ioHandler.readFromFile();
		sentences = ioHandler.createSentences( content );
		println("Sentence Size: " + sentences.size());
		for( Sentence sentence : sentences )
		{
			for( String word : sentence.getWords() )
			{
				print( word + " " );
			}
			println("");
		}
	}

	public void performStemming() throws Exception
	{
		/* Input variables */
		ArrayList<Sentence> sentences;
		ioHandler = new IOHandler( addressPrefix, testHPOST );
		String[] content = ioHandler.readFromFile();
		sentences = ioHandler.createSentences( content );
		/* Output variables */
		ArrayList<Sentence> stemmedSentences = new ArrayList<>();
		ArrayList<String> stemmedWords 		 = new ArrayList<>();
		Sentence stemmedSentence;
		/* Stemming clasees */
		MainController mc;
		for ( Sentence sentence : sentences )
		{
			ArrayList<String> words = sentence.getWords();

			for ( String word : words )
			{
				mc = new MainController(word);
				stemmedWords.add( mc.getFeatures() );
			}
			stemmedSentence = new Sentence();
			stemmedSentence.setWords( stemmedWords );
			stemmedSentences.add( stemmedSentence );
		}

		printSentencesContent( stemmedSentences );

	}

	public void printSentencesContent( ArrayList<Sentence> sentences)
	{
		for( Sentence sentence : sentences )
		{
			for( String word : sentence.getWords() )
			{
				print( word + " " );
			}
			println("");
		}
	}

	public static class Test
	{
		public static void main(String[] args) throws Exception
		{
			Main m = new Main();
			m.performStemming();
		}
	}

}
