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
		MorphPI mpi = new MorphPI();

		mpi.pushWord(sWord);
	}


	public static void main(String[] args) throws Exception
	{
		Main m = new Main();
		m.sampleLongRun();
//		m.sampleSingleRun("harangan");
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
