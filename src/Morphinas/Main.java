package Morphinas;

import DataStructures.*;
import MorphAnalyzer.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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
		MorphPI mpi = new MorphPI("/Users/laurenztolentino/Eclipse/workspace/Morphinas/src/","testHPOST.words");
		mpi.pushFile();
		mpi.pullFeaturedResults();
		endTime = System.currentTimeMillis();

		printElapsedTime(startTime, endTime);
	}

	public void sampleSingleRun(String sWord) throws Exception
	{
		MorphLearnerRedup mpl = new MorphLearnerRedup();
		String input = Formatter.removeNonLetters(sWord);

		TestMaker tm = new TestMaker();
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


	public static void main(String[] args) throws Exception
	{
		Main m = new Main();
		m.sampleLongRun();
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
