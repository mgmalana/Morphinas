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

	public String[] readFromFile (String fileName) throws Exception
	{
		this.fileDirectory 	= "";
		this.fileName 	  	= fileName;
		return readFromFile();
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
				System.out.print("\r " + lineNumber + " out of (lagpas sa sampung daliriri)");
				if( content.toString().matches("^.*[.].*$")) {
					if( content.toString().length() > 1 )
					{

					}
				}
				finalContent = finalContent + content + "\n";
			}
			println("\n Done reading from file.");
			/* Splits content by spaces. */
			words = finalContent.split("\n");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* return result */
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

	public static void main( String[] args ) throws Exception
	{
		IOHandler ioh = new IOHandler("/Users/laurenztolentino/Developer/Morphinas/morphinas/","minitext.txt");
		String[] test = new String[0];
		try {
			test = ioh.readFromFile();
		} catch (Exception e) {
			println("Error reading file. File is probably missing. <sad face emoticon>");
		}

		for ( String temp: test )
		{
			println(temp + "");
		}


	}

	public static void print( Object input )
	{
		System.out.print("" + input.toString() );
	}
	public static void println(Object input)
	{
		System.out.println("" + input.toString());
	}
}
