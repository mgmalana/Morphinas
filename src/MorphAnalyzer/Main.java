package MorphAnalyzer;

import DataStructures.*;

import java.text.Format;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main 
{
	MorphLearnerRedup mpl = new MorphLearnerRedup();
    WordsLoader training = new WordsLoader(WordsLoader.TRAINING);
	
    String inputSentence = "";
    String input = "";
    
	public Main() throws Exception
	{
		
	}

    /**
	 * noGUI launches fun stuff without that old-school JFrame
	 * @param input
     */
	public void noGUI(String input)
	{
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
        
//		root = mpl.analyzeMultipleModWithSemantic2(input).result;
//      println("From Sir Sol's MAResult:");
//      println("Result is:  " + root);
//      println("infix: " + maresult.infix);
//      println("suffix: " + maresult.suffix);
//      println("prefix: " + maresult.prefix);
//      println("Redup: " + maresult.redup);
//      println("\n \n");
//      println("word: " + word.getRootWord());

		fm = new Formatter(word);
		//fm.printWordContentDetailed();
        // fm.printBracketedResult();
		fm.printFormattedResult();
		println("");
//		AffixBreakdown ab = new AffixBreakdown();

		try {
			fm.printLongestOnly();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			println("whoopsies. Didn't find the longest ");
		}
        
	}

	public String startIt() throws Exception
	{
//		TestMaker tm = new TestMaker("/Users/laurenztolentino/Eclipse/workspace/Morphinas/src/","testHPOST.words");
		TestMaker tm = new TestMaker();
		String[] wordsList = tm.readFromFile();
		MAResult maresult;
		String result = "";
		Formatter fm;
		Word word;

		for( int i = 0; i < wordsList.length; i++ )
		{
//			println("wordList"+"[" + i + "]: " + wordsList[i]);
			String single = wordsList[i];
			single = single.toLowerCase();
			single = Formatter.removeNonLetters(single);

			mpl.analyzeMultipleMod(single);
			word = mpl.getWordObject();
			fm   = new Formatter(word);

			if( !fm.generateFeaturesResult().equalsIgnoreCase(""))
				result = result + fm.generateFeaturesResult() + "\n";
		}

		println("");
		println(result);



		return result;
	}
	
	public static void main(String[] args) throws Exception 
	{	
		Main m = new Main();
		m.startIt();
//		String input = "gitnang";
//		input = Formatter.removeNonLetters(input);
//		m.noGUI(input);
	}



	public static void println(String in)
	{
		System.out.println("" + in);	
	}
	public static void print(String in) {
		System.out.print("" + in);
	}
}
