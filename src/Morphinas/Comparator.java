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

	public String unalignedFinder( ArrayList<Sentence> testeeSentences, ArrayList<Sentence> goldSentences)
	{
		/* result */
		String result = "";
		/* temp variables */
		ArrayList<Word> goldWords, testWords;
		Sentence goldSentence, testSentence;
		String to, from;
		/* lengths and sizes */
		int sentencesSize, goldWordsLength, testWordsLength;
		/*
		* Check if sentence sizes of both gold standard and test are equal.
		* Program will exit if they are not of the same sizes.
		* */
		if( testeeSentences.size() == goldSentences.size() )
		{
			sentencesSize = testeeSentences.size();
		} else {
			sentencesSize = -99;
		}
		for( int i = 0; i < sentencesSize; i++ )
		{
			/* update temp values */
			testSentence = testeeSentences.get(i);
			goldSentence = goldSentences.get(i);
			goldWords 	 = goldSentence.getWords();
			testWords 	 = testSentence.getWords();
			/* update lengths */
			goldWordsLength = goldWords.size();
			testWordsLength  = testWords.size();
			/* make sure both sentences are aligned (same no. of words) */
			if( goldWordsLength != testWordsLength )
			{
//				println("Sentence #" + i + " are not of the same length between gold /n standard and system output. G-length: "+ goldWordsLength + "vs T-length: " + testWordsLength);
//				println("Your Annotation: ");
//				goldSentence.printString();
//				println("");
//				println("My Morphinas: ");
				testSentence.printString();
				result = result + " LINE-" + (i+388) + "\n GOLD: \n" + goldSentence.stringSentence() + "\n MORPHINAS: \n " + testSentence.stringSentence() + "\n";
			}
		}

		return result;
	}

	public double multiComparator( ArrayList<Sentence> testeeSentences, ArrayList<Sentence> goldStandardSentences, String compareType)
	{
		/* Value to be returned a.k.a. the result */
		double result;
		/* Values for computing resulting accuracy */
		double currentScore = 0.00;
		double total 		= 0.00;
		/* HashSet of unique words */
		HashSet<String> uniqueWords = new HashSet<>();
		/* temp variables */
		ArrayList<Word> goldWords, testWords;
		Sentence goldSentence, testSentence;
		String to, from;
		/* lengths and sizes */
		int sentencesSize, goldWordsLength, testWordsLength;
		/*
		* Check if sentence sizes of both gold standard and test are equal.
		* Program will exit if they are not of the same sizes.
		* */
		if( testeeSentences.size() == goldStandardSentences.size() )
		{
			sentencesSize = testeeSentences.size();
		} else {
			sentencesSize = -99;
		}
		/* iterate all sentences */
		for( int i = 0; i < sentencesSize; i++ )
		{
			/* update temp values */
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
				println("sentence gold: ");
				goldSentence.printString();
				println("");
				println("sentence testee: ");
				testSentence.printString();
				System.exit(0);
			} else {
//				total = total + goldWordsLength;
			}
			/* if we are comparing root words/lemma*/
			if( compareType.equalsIgnoreCase("lemma") || compareType.equalsIgnoreCase("root") || compareType.equalsIgnoreCase("rootword"))
			{
				/* otherwise */
				for( int k = 0; k < goldWordsLength; k++ )
				{
					from = testWords.get(k).getOriginalWord().toLowerCase();
					to 	 = goldWords.get(k).getOriginalWord().toLowerCase();
					if( k == 0 )
					{
						uniqueWords.add( from );
						currentScore++;
						total++;
					}
					if( !uniqueWords.contains( from ) )
					{
						uniqueWords.add( from );
						if( from.equalsIgnoreCase( to ) )
						{
							currentScore++;
						}
						total++;
					}

				} /* /iterates all words */
			}
			/* if we are comparing features*/
			else if ( compareType.equalsIgnoreCase("features") || compareType.equalsIgnoreCase("feature") )
			{
				for( int k = 0; k < goldWordsLength; k++ )
				{
					from = testWords.get(k).getOriginalWord().toLowerCase();
					to 	 = goldWords.get(k).getOriginalWord().toLowerCase();
					if( from.equalsIgnoreCase(to) )
					{
						currentScore++;
					}
					total++;
				}
			}
			/* if user did not specify anything #dickmove */
			else
			{
				println("Please input comparison type: 'lemma' or 'features'");
			}

		} /* /iterates all sentences */

		/* prints all found unique words :> */
//		for( String testWord: uniqueWords)
//		{
//			println(testWord);
//		}

		result = currentScore / ( total);
		return result;
	}



	public ArrayList<String> findNoneUniqueWords(ArrayList<Sentence> testSentences, ArrayList<Sentence> goldSentences)
	{
		/* Result */
		ArrayList<String> result = new ArrayList<>();



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

	public ArrayList<Word> convertToWordsList(String[] sWords)
	{
		ArrayList<Word> words = new ArrayList<>();

		for( String word: sWords )
		{
			Word wWord = new Word(word);
			words.add(wWord);
		}

		return words;
	}

	/*
	 * Utility Code
	 */
	public static void println(Object input)
	{
		System.out.println("" + input.toString());
	}
}


