package MorphAnalyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by laurenztolentino on 05/19/2016.
 */
public class TestMaker
{
	String fileDirectory = "/Users/laurenztolentino/Eclipse/workspace/Morphinas/src/";
	String fileName		 = "words.txt";


	public TestMaker()
	{}

	/**
	 * When you have a custom fileDirectory and fileName
	 * @param fileDirectory
	 * @param fileName
	 */
	public TestMaker(String fileDirectory, String fileName)
	{
		this.fileDirectory 	= fileDirectory;
		this.fileName 		= fileName;
	}

	public String[] readFromFile () throws Exception
	{
		ArrayList<String> input;
		String finalContent = "";
		String content = "";
		String[] words = { "" };

		BufferedReader br;

		try
		{
			br = new BufferedReader( new FileReader(fileDirectory + fileName) );
			println("started ....");
			while ((content = br.readLine()) != null)
			{
				finalContent = finalContent + content;
			}

			words = finalContent.split(" ");




		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



		return words;
	}

	public static void main(String[] args)
	{

		String[] testData;

		TestMaker tm = new TestMaker();

		try
		{
			testData = tm.readFromFile();

			for(int i = 0; i < testData.length; i++)
			{
				println("" + testData[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void println(String input)
	{
		System.out.println("" + input);
	}

}
