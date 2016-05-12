package MorphAnalyzer;

import DataStructures.*;
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
		input = input.toLowerCase();
		WordPair wp;
		println("Finding root of: " + input);	
        
		// String ng result only
		String root = ""; 
        root = mpl.analyzeMultipleMod(input).result;
        
        // Result using MAResult
        MAResult maresult = mpl.analyzeMultipleMod(input); // Not working properly #why
        Word word = mpl.getWordObject();
        
        //root = mpl.analyzeMultipleModWithSemantic2(input).result;
        println("From Sir Sol's MAResult:");
        println("Result is:  " + root); 
        println("infix: " + maresult.infix);
        println("suffix: " + maresult.suffix);
        println("prefix: " + maresult.prefix);
        println("Redup: " + maresult.redup);
        println("\n \n");
        println("word: " + word.getRootWord());
        word.printWordContentDetailed();
        word.printBracketedResult();
        try {
			word.printLongestOnly();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			println("whoopsies. Didn't find the longest ");
		}
        
	}
	
	public static void main(String[] args) throws Exception 
	{	
		Main m = new Main();
		String input = "pinagpaliban";
		input = m.removeNonLetters(input);
		m.noGUI(input);
	}

	public String removeNonLetters(String input)
	{
		char[] nonLetters 	= { '-', ' ' };
		String result 		= "";
		boolean willAdd 	= false;
		for( int i = 0; i < input.length(); i++ )
		{

			for( int j = 0; j < nonLetters.length; j++)
			{
				if( input.charAt(i) != nonLetters[j]) {
					willAdd = true;
				} else {
					willAdd = false;
					break;
				}
			}

			if( willAdd ) {
				result = result + input.charAt(i);
			}
		}

		return result;
	}

	public static void println(String in)
	{
		System.out.println("" + in);	
	}
}
