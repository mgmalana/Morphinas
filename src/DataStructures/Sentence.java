package DataStructures;

import java.util.ArrayList;

/**
 * Created by laurenztolentino on 06/16/2016.
 */
public class Sentence
{
	int wordCount;
	public ArrayList<Word> words;

	public Sentence()
	{}

	public Sentence(ArrayList<Word> words)
	{
		this.words = words;
	}

	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}

	public int getWordCount()
	{
		wordCount = words.size();
		return words.size();
	}
}
