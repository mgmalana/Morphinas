package Stemmer.View;

import Stemmer.Model.Sentence;

import java.util.ArrayList;

/**
 * Created by laurenz on 22/03/2017.
 */
public class IOHandler
{
	String fileDirectory = "/Users/laurenztolentino/Developer/morphinas/Morphinas/ReadFiles/";
	String fileName		 = "words.txt";

	public IOHandler()
	{}

	/**
	 * When you have a custom fileDirectory and fileName
	 * @param fileDirectory
	 * @param fileName
	 */
	public IOHandler(String fileDirectory, String fileName)
	{
		this.fileDirectory 	= fileDirectory;
		this.fileName 		= fileName;
	}

	public ArrayList<Sentence> createSentencesFromFile() throws Exception
	{
		ArrayList<Sentence> resultSentences = new ArrayList<>();

		return resultSentences;
	}
}
