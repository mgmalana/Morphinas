package Morphinas;

import DataStructures.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by laurenztolentino on 05/31/2016.
 */
public class Main {

	// for printing running time
	long startTime, endTime;

	public Main() throws Exception
	{
		this.startTime  = System.currentTimeMillis();
	}

	public void sampleLongRun() throws Exception
	{
		MorphPI mpi = new MorphPI("/Users/laurenztolentino/Developer/Morphinas/morphinas/","testHPOST.words");
		mpi.readFromFile();
		mpi.pullFeaturedResultsFromFile();
		endTime = System.currentTimeMillis();

		printElapsedTime(startTime, endTime);
	}

	/*
	* PRIMARY
	* */
	public void sampleLongRunSentences() throws Exception
	{
		ArrayList<Sentence> sentences;
		MorphPI mpi = new MorphPI("/Users/laurenztolentino/Developer/Morphinas/morphinas/","testHPOST.words");
		mpi.readFromFile();
		sentences   = mpi.createSentences( mpi.pullContent() );
		mpi.featuredResultString( sentences );

//		/* for computing the elapsed time */
		endTime = System.currentTimeMillis();
		/* Print the elapsed time */
		printElapsedTime(startTime, endTime);
	}

	public void sampleLongRunSentencesLemma() throws Exception
	{
		ArrayList<Sentence> sentences;

		MorphPI mpi = new MorphPI("/Users/laurenztolentino/Developer/Morphinas/morphinas/","testHPOST2.words");
		mpi.readFromFile();
		sentences   = mpi.createSentences(mpi.pullContent());
		mpi.lemmaResultStrig(sentences);

		endTime = System.currentTimeMillis();
		/* Print the elapsed time */
		printElapsedTime(startTime, endTime);
	}

	public void manoLongRun() throws Exception
	{
		/*
		MorphPI mpi = new MorphPI();
		* */
		MorphPI mpi = new MorphPI("/Users/laurenztolentino/Developer/Morphinas/morphinas/","Lemma.txt");
		mpi.readFromFile();
		mpi.pullRootResultsFromFile();
		println("reached here.");
		endTime = System.currentTimeMillis();

		printElapsedTime(startTime, endTime);

	}

	public void sampleSingleRun(String sWord) throws Exception
	{
		MorphPI mpi = new MorphPI();
		mpi.pushWord(sWord);
	}

	public static void main(String[] args) throws Exception
	{
		Main m = new Main();
//		m.sampleLongRun();
//		m.sampleSingleRun(".s");
//		m.manoLongRun();
//		m.sampleLongRunSentencesLemma();
		m.sampleLongRunSentences();
	}

	public void printElapsedTime(long startTime, long endTime)
	{
		NumberFormat formatter = new DecimalFormat("#0.00000");
		println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
	}

	public void println(String input)
	{
		System.out.println("" + input);
	}

}
