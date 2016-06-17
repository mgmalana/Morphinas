package Morphinas;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by laurenztolentino on 05/31/2016.
 */
public class IOHandler {
	String fileDirectory = "/Users/laurenztolentino/Eclipse/workspace/Morphinas/src/";
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

	public String[] readFromFile () throws Exception
	{
		ArrayList<String> input;
		String finalContent = "";
		String content = "";
		String[] words = { "" };
		int lineNumber = 0;
		BufferedReader br;

		try
		{
			br = new BufferedReader( new FileReader(fileDirectory + fileName) );
			println("Reading from file ....");

			while ((content = br.readLine()) != null)
			{
				lineNumber++;
				System.out.print(""+lineNumber);
				System.out.print("\r " + lineNumber + " out of (lagpas sa sampung daliri)");
				finalContent = finalContent + content + " ";
			}
			println("\n Done reading from file.");
			/* Splits content by spaces. */
			words = finalContent.split(" ");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



		return words;
	}

	public void printToTxtFileRoot(String fileName, String toPrint) throws Exception
	{
		String completeFileName = fileName + ".txt";
		PrintWriter writer = new PrintWriter(completeFileName, "UTF-8");
		// Write the result to file
		writer.println(toPrint);
		// Close the printer
		writer.close();
	}

	public void printToTxtFile(String toPrint) throws Exception
	{
		PrintWriter writer = new PrintWriter("morphinas-result.txt", "UTF-8");
		// Write the result to file
		writer.println(toPrint);
		// Close the printer
		writer.close();
	}

	public static void main(String[] args)
	{

		IOHandler fp = new IOHandler();
		String[] testData;



		try
		{
			testData = fp.readFromFile();

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
