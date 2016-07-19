package Morphinas;

import java.util.ArrayList;
import DataStructures.*;

import java.util.HashSet;
import java.util.Objects;

/**
 * Created by laurenztolentino on 06/27/2016.
 */
public class Comparator
{
	/* String from morphinas-result and compareTo */
	private String[] compareFrom;
	private String[] compareTo;
	/* word counts */
	private int correctWords = 0;
	private int totalWords;
	/* result */
	private float accuracy;

	/* constructor */
	public Comparator() {}


	public double lemmaComparator( ArrayList<Sentence> testeeSentences, ArrayList<Sentence> goldStandardSentences)
	{
		/* Value to be returned a.k.a. the result */
		double result = 0.0;
		double total, currentScore = 0.00;
		/* HashSet of unique words */
		HashSet<String> uniqueWords = new HashSet<>();
		/* temp variables */
		ArrayList<Word> goldWords, testWords;
		Sentence goldSentence, testSentence;
		String to, from;
		/* lengths and sizes */
		int sentencesSize = testeeSentences.size();
		int goldWordsLength, testWordsLength;
		/* iterate all sentences */
		for( int i = 0; i < sentencesSize; i++ )
		{
			/* place values */
			testSentence = testeeSentences.get(i);
			goldSentence = goldStandardSentences.get(i);
			goldWords 	 = goldSentence.getWords();
			testWords 	 = testSentence.getWords();
			/* update lengths */
			goldWordsLength = goldWords.size();
			testWordsLength  = testWords.size();
			/* make sure both sentences are aligned (same no. of words) */
			if( goldWordsLength != testWordsLength )
			{
				println("Sentence #" + i + " are not of the same length between gold standard and system output. G-"+ goldWordsLength + " T-" + testWordsLength);
				System.exit(0);
			}
			/* otherwise */
			for( int k = 0; k < goldWordsLength; k++ )
			{
				from = goldWords.get(k).getRootWord().toLowerCase();
				to 	 = testWords.get(k).getRootWord().toLowerCase();
				/* if the word in from is not present in the uniqueWords set */
				if( !uniqueWords.contains( from ) )
				{
					if( from.equals(to) )
					{
						/* add to hashlist of known words */
						uniqueWords.add(from);
						/* update the score */
						currentScore++;
					}
				}
			}
		}

		return result;
	}

	/**
	 * Starts comparing from a to b :)
	 * @param compareTo
	 * @param compareFrom
	 * @return
	 */
	public double compare( String[] compareTo, String[] compareFrom )
	{
		/* constructor thingy */
		this.compareFrom = compareFrom;
		this.compareTo   = compareTo;
		/* clean compareFrom */
		compareTo 	= cleanSpaces(compareTo);
		compareFrom = cleanSpaces(compareFrom);
		this.compareFrom = compareFrom;
		this.compareTo   = compareTo;
		/* sizes */
		int fromSize = compareFrom.length;
		int toSize 	 = compareTo.length;
		/* count dracula */
		double result = 0.0;
		int correct   = 0;
		int wrong 	  = 0;
		/* temp */
		String from;
		String to;
		/* if both params' sizes are not equal */
		if( toSize != fromSize )
		{
			println("Comparator.compare error: ArrayList sizes of both parameters are not the same.");
			println( fromSize + "(from) != " + toSize +"(to)");
			return -999.0;
		}
		/* else */
		for( int i = 0; i < toSize; i++ )
		{
			from = compareFrom[i];
			to 	 = compareTo[i];
			/* compare both strings */
			if( from.equals(to) )
			{
				correct++;
			}
			else
			{
				println("["+ i +"] r"+ from + " != " + to);
			}
		}
		println("Correct: " + correct);
		/* math stuff */
		wrong = fromSize - correct;
		result = (double)correct / (double)fromSize;

		println("Wrong count: " + wrong);
		println("Accuracy: " + result);
		/* return result */
		return result;
	}

	/**
	 * removes spaces from an array of string
	 * @param elements
	 * @return
	 */
	public String[] cleanSpaces(String[] elements)
	{
		ArrayList<String> temp = new ArrayList<>();
		String[] result;

		/* populate the arraylist */
		for(int i = 0; i < elements.length; i++)
		{
			if( elements[i].charAt(0) != '\n')
			{
				println(i + "F: " + elements[i] + " T: " + this.compareTo[i]);
				temp.add( elements[i] );
			} else {
				elements[i] = elements[i].substring(1);
				println(i + "F: " + elements[i] + " T: " + this.compareTo[i]);
				temp.add( elements[i] );
			}
		}

		/* set result[] size */
		result = new String[temp.size()];

		/* populate result[] */
		for(int i = 0; i < temp.size(); i++)
		{
			result[i] = temp.get(i);
		}

		println("clean count: " + result.length);
		return result;
	}

	public static void main(String[] args)
	{
		Comparator comp = new Comparator();
		/*comp.testLemmaComparator();*/
//		comp.testHashSetCode();
	}

	/*
	 * Utility Code
	 */
	public static void println(Object input)
	{
		System.out.println("" + input.toString());
	}
}
