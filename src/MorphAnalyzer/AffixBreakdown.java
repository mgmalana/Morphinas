package MorphAnalyzer;

import java.util.HashMap;
import java.util.*;

/**
 * Created by laurenztolentino on 05/16/16.
 */
public class AffixBreakdown
{
	private HashMap hm;
	private String lookup;

	public AffixBreakdown()
	{
		hm = initiateEquivalentBreakdown();
	}

	public AffixBreakdown( String lookup )
	{
		this.lookup = lookup;
	}

	public String convertPrefix(String affix)
	{

		String result = "";

		try
		{
			result = this.hm.get(affix).toString();
		}
		catch (Exception e)
		{
			result = "$" + affix;
		}


//		System.out.println("convertPrefix: " + result);

		return result;
	}

	/**
	 * List down all the affixes with their equivalent breakdown
	 * @return
	 * HashMap with prefix and it's equivalent format
	 */
	public HashMap initiateEquivalentBreakdown()
	{
		HashMap hm = new HashMap();
		hm.put("pinag", 	 "~pi~nag");
		hm.put("pinagpa",	 "~pi~nag~pa");
		hm.put("ipang", 	 "~ipang");
		hm.put("ipinag",	 "~i~pi~nag");
		hm.put("ipinagpa", 	 "~i~pi~nag~pa");
		hm.put("nakiki", 	 "~na$ki$ki");
		hm.put("makiki", 	 "~ma$ki$ki");
		hm.put("nakikipag",  "~na$ki$ki~pag");
		hm.put("napag", 	 "~na~pag");
		hm.put("mapag", 	 "~ma~pag");
		hm.put("nakipag", 	 "~na~ki~pag");
		hm.put("makipag", 	 "~ma~ki~pag");
		hm.put("makikipag",  "~ma~ki~pag");
		hm.put("naka", 		 "~na~ka");
		hm.put("nakaka", 	 "~na$ka$ka");
		hm.put("maka", 	 	 "~ma~ka");
		hm.put("makaka", 	 "~ma$ka$ka");
		hm.put("nagka", 	 "~nag~ka");
		hm.put("nagkaka", 	 "~nag$ka$ka");
		hm.put("magka", 	 "~mag~ka");
		hm.put("magkaka", 	 "~mag$ka$ka");
		hm.put("napaki", 	 "~na~pa~ki");
		hm.put("napakiki", 	 "~na~pa$ki$ki");
		hm.put("mapaki", 	 "~ma~pa~ki");
		hm.put("mapakiki", 	 "~ma~pa$ki$ki");
		hm.put("paki", 		 "~pa~ki");
		hm.put("pakiki", 	 "~pa$ki$ki");
		hm.put("pakikipag",  "~pa$ki$ki~pag");
		hm.put("pagki", 	 "~pag~ki");
		hm.put("pagkiki", 	 "~pag$ki$ki");
		hm.put("pagkikipag", "~pag$ki$ki~pag");

		return hm;
	}

	private static void println(String input)
	{
		System.out.println("" + input);
	}

}
