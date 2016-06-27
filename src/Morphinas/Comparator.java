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
		String from = ":FS~ka+han #sa #mga ~ma #na #bansa #, #simula +ng #20 #siglo #, ~ma+ng #mga +ng #may :F*down *syndrome #ang ~na #sa #mga #institusyon #o +ng #hindi ~ka #sa *lipunan #. \n" +
				":FS*magkaroon #ang #lugar #ng +ng ~ma #na #“ #rock *scene #” #sa +ng #1960’s #at #1970’s ~pa #ng :F*grand :F*ballroom #kasama #ng #mga +ng :F*alice :F*cooper #, :F*ted :F*nugent #, :F*mitch :F*ryder #, :F*rare :F*earth #, :F*brownsville :F*station #, :F*glenn :F*frey #, #at :F*bob :F*seger #. \n" +
				":FS+an #si :F*grant #ng +ng +ng +ng #si :F*virginia :F*cherrill +ng *ika26 #ng :F*marso #, *1955 #dahil #sa #mga #paratang #na *binuntal #siya #ni :F*grant #. \n" +
				":FS#nasa :F*refuge #ang #mga #pulo #, *coastal *wetlands #, *marshes #, #at #lupa #sa #tabi #ng #tubig #ng :F*detroit :F*river #at :F*western :F*lake :F*erie #. \n" +
				":FS~in #ni :F*copeland #ang :F*onggo #sa ~n+ng :F*protoctista #, ~g +ng ~ma #ang #problema #ngunit ~ki #ang ~ka+ng #espesyal #na *katayuan #. \n" +
				":FS#habang ~pa #, #hindi ~na #ni :F*dion #na ~ma #sa ~ka+an #. \n" +
				":FS#“ :F*sinubukan *kong +in #ang ~n+ng #panunungkulan #, :F*ginoong :F*pangulo #” #. \n" +
				":FS#gayunman #, #ang *topograpiya #at #kundisyon #ng #panahon #ang ~nag #ng #4 #% #lamang #sa #mga ~i #ng +in #. \n" +
				":FS#ang +ng #paraan #ng ~pa #ng ~pag+in #ay ~pa *magkaroon #ng #food ~s #at #ang ~s #ay #ang ~n #sa ~pag #ng ~po #na ~nag #sa ~pag #ng #labor #na *binuo #ng +ng #paraan #ng #produksyon #. \n" +
				":FS#sa +ng #normal #na ~pag #, ~pi+an #ng #mga #opisyal #ng #lungsod #na ~mag #ng #mga +han #at ~ma~pag+an #ng #mga #sundalo #, #dahil #sa #sitwasyon #na #ito ~na+an #ang #mga #tao #na ~mag #ng +han #at ~pag+in #para #sa ~n+ng #sarili #. ";
		String to	= ":FS~ka+han #sa #mga #ma$u #na #bansa #, #simula +g *20 #siglo #, ~ma+ng #mga +ng #may :F*down *syndrome #ang ~na #sa #mga #institusyon #o +ng #hindi ~ka$tanggap #sa #lipunan #. \n" +
				":FS~mag~ka #ang #lugar #ng +ng ~ma #na #\" *rock *scene #\" #sa +ng *1960's #at *1970's ~pa #ng :F*grand :F*ballroom ~ka #ng #mga +ng :F*alice :F*cooper #, :F*ted :F*nugent #, :F*mitch :F*ryder #, :F*rare :F*earth #, :F*brownsville :F*station #, :F*glenn :F*frey #, #at :F*bob :F*seger #. \n" +
				":FS@in+an #si :F*grant #ng +ng +ng +ng #si :F*virginia :F*cherrill +g *ika-26 #ng :F*marso #, *1955 #dahil #sa #mga +ng #na @in #siya #ni :F*grant #. \n" +
				":FS#nasa :F*refuge #ang #mga #pulo #, *coastal *wetlands #, *marshes #, #at #lupa #sa #tabi #ng #tubig #ng :F*detroit :F*river #at :F*western :F*lake :F*erie #. \n" +
				":FS~in@i #ni :F*copeland #ang :F*onggo #sa +ng :F*protoctista #, +ng +ng ~ma #ang #problema #ngunit $ki+nin #ang +ng #espesyal #na #katayuan #. \n" +
				"#habang ~nag$pa #, #hindi +na #ni :F*dion #na ~maka #sa ~ka+an #. \n" +
				":FS#“ :F@in+an +ng +in #ang +ng # #, :F*ginoong :F*pangulo #\" #. \n" +
				":FS#gayunman #, #ang #topograpiya #at #kundisyon #ng #panahon #ang ~nag$ta #ng #4 #% #lamang #sa #mga ~in$a #ng +in #. \n" +
				":FS#ang +ng #paraan #ng ~pag #ng ~pag #ay ~pina+ng ~mag~ka #ng *food *surplus #at #ang *surplus #ay #ang +ng #sa ~pag #ng #populasyon #na ~nag$re #sa ~pag #ng #labor #na #binuo #ng +ng #paraan #ng #produksyon #. \n" +
				":FS#sa +ng #normal #na ~pag #, ~ma$pi #ng #mga #opisyal #ng #lungsod #na ~mag #ng #mga +han #at ~ma~pag$ka+an #ng #mga #sundalo #, #dahil #sa #sitwasyon #na #ito ~na+an #ang #mga #tao #na ~mag #ng +han #at ~pag #para #sa +ng #sarili #. ";
		String hpost = ":FS~ka #sa #mga ~ma$u #na *bansa #, *simula #noon#g *20 *siglo #, ~ma #mga #tao#ng #may :F*down *syndrome #ang ~na #mga ~i+n #o *kolonyang #hindi ~ka$tanggap- #sa *lipunan #. \n" +
				":FS~ma #ang *lugar #ng #sa#ng~i ~ma #na #“ *rock *scene #” #sa *huling *1960’s #at *1970’s ~pa~i #ng :F*grand :F*ballroom ~ka *ng_mga *artistang :F*alice :F*cooper #, :F*ted :F*nugent #, :F*mitch :F*ryder #, :F*rare :F*earth #, :F*brownsville :F*station #, :F*glenn :F*frey #, #at :F*bob :F*seger #. \n" +
				":FS@in #si :F*grant #ng #una#ng ~ni *asawang #si :F*virginia :F*cherrill #noon#g - #ng :F*marso #, *1955 *dahil_sa #mga ~pa #na @in #siya #ni :F*grant #. \n" +
				"#nasa:FS :F*refuge *ang_mga *pulo #, *coastal *wetlands #, ~ma #, #at *lupa ~sa #ng *tubig #ng :F*detroit :F*river #at :F*western :F*lake :F*erie #. \n" +
				":FS~i #ni :F*copeland #ang :F*onggo #sa #nya#ng~ka :F*protoctista #, ~ga *bahagyang ~ma~i #ang *problema #ngunit ~ki~ki$la #ang #lang~ka~ni *espesyal #na ~ka #. \n" +
				"#habang:FS ~na #, #hindi ~na #ni :F*dion #na ~ma~ka #sa ~ka #. \n" +
				"#“ :F@in #ko#ng *sirain #ang ~i ~pa+n$nu #, :F+g@in #ng#ulo:F~pa #” #. \n" +
				"#yun#man:FS~ga #, #ang *topograpiya #at *kundisyon #ng #na#ho~pa+n #ang ~na #ng *4 #% #lamang #sa #mga ~i #ng *lupain #. \n" +
				"#ang:FS #bago#ng ~pa #ng ~pa #ng ~pa #ay ~pa~pa@in ~ma #ng *food *surplus #at #ang *surplus #ay #ang ~pa~ni #sa ~pa #ng *populasyon #na ~na #sa ~pa #ng *labor #na #buo@in #ng #bago#ng ~pa #ng *produksyon #. \n" +
				"#sa:FS #sa#ng~i *normal #na ~pa #, ~ma$pi *ng_mga *opisyal #ng *lungsod #na ~ma *ng_mga *tirahan #at ~ma~pa *ng_mga *sundalo #, *dahil_sa *sitwasyon #na #ito ~na *ang_mga #tao #na ~ma #ng *tirahan #at ~pa ~pa #nya#ng~ka ~sa #. ";
		/* variables to be passed as params*/
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
