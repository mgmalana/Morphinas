package Morphinas.Testing.Experiments;


/**
 * Created by laurenztolentino on 09/21/2016.
 */
public class Reduplication_Exp
{

	public Reduplication_Exp(){}

	public void runMe(String word)
	{
		String left, right;
		int leftLength, rightLength;
		int wordLength = word.length();

		leftLength = 1;
		rightLength = wordLength - leftLength;

		for( int i = 0; i < wordLength; i++ )
		{

		}
	}


	public static void main(String[] args)
	{
		/* Word to be tested */
		String word = "haluhalong";

		Reduplication_Exp re = new Reduplication_Exp();

		re.runMe(word);
	}

}
