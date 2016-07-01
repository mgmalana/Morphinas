package Morphinas;

import java.util.ArrayList;
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

	/**
	 * Starts comparing from a to b :)
	 * @param compareTo
	 * @param compareFrom
	 * @return
	 */
	public double compare(String[] compareTo, String[] compareFrom)
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
			} else {
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

	public static void println(Object input)
	{
		System.out.println("" + input.toString());
	}

	public static void main(String[] args)
	{
		/* Test input from morphinas-result.txt and compareWith.txt*/
		String from = ":FS~pa #ng *masalawahang ~di #sa :F*smolensk +ng :F*agosto #16 #18 #, *nakagawa #rin #siya #ng *depensibong #posisyon #sa :F*borodino #. \n" +
				":FS~i+ng #album #na #ito #ang ~pa #ni :F*dion #gayon #pa #man #, #at *nagpakita #si :F*dion #ng #ugali #na #husto #sa #gulang #sa #mga ~n+ng #\" :F*a :F*new :F*day :F*has :F*come #, #\" #\" #I'm :F*alive #, #\" #at #\" #Goodbye's #( :F*the :F*saddest :F*word #) #, #\" #ang ~ba+ng #naging #resulta #mula #sa ~n+ng +ng ~n *reponsibilidad #bilang +ng #ina #, #dahil #, #sa ~n+ng +ng #mga #salita #\" *becoming #a *mother *makes #you #a *grownup #. \n" +
				":FS~ipag #para #sa *subhetibong ~ka+an #ng ~ka+han #, ~i #niya #na #, #\" :F*ang ~pag #sa *panlasa #, #samakatuwid #, #ay #hindi +ng *kognitibong ~pag #, #at #kaya #hindi #ito ~l #, #ngunit #ay #estetika #, #na ~nga #ito #na #hindi ~a+ng #maging #iba #kundi *subhetibo #ang ~nag *pantukoy #. ";
		String to	= ":FS~pag #ng ~ma+g ~pag$di #sa :F*smolensk +g :F*agosto *16 #- *18 #, ~naka #rin #siya #ng +ng #posisyon #sa :F*borodino #. \n" +
				":FS+ng *album #na #ito #ang ~pinaka #ni :F*dion #gayon #pa #man #, #at ~nag~pa #si :F*dion #ng #ugali #na #husto #sa #gulang #sa #mga +ng #\" :F*A :F*new :F*day :F*has :F*come #, #\" #\" :F*i'm :F*alive #, #\" #at #\" :F*goodbye's #( :F*the :F*saddest :F*word #) #, #\" #ang ~pag$ba+ng #naging #resulta #mula #sa +ng +ng #mga #salita #\" *becoming #a *mother *makes *you #a *grown-up #\" #. \n" +
				":FS~i~pinag$ta #para #sa +ng ~ka+an #ng ~ka+han #, ~i@in #niya #na #, #\" :F*ang ~pag #sa ~pan #, #samakatuwid #, #ay #hindi +ng +ng ~pag #, #at #kaya #hindi #ito #lohikal #, #ngunit #ay *estetika #, #na ~na$nga+g #ito #na #hindi +ng #maging #iba #kundi +ng #ang ~na$gi ~pan #. ";

		/* variables to be passed as params */
		String[] compareFrom;
		String[] compareTo;

		/* convert String to String[]*/
//		from = from.trim();
		compareFrom = from.split(" ");
//		compareFrom = hpost.split(" "); // from hpost
		compareTo 	= to.split(" ");

		/* instantiate comparator */
		Comparator comp = new Comparator();

		/* start comparing */
		comp.compare(compareTo, compareFrom);
	}

}
