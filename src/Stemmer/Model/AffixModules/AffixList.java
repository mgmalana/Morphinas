package Stemmer.Model.AffixModules;

import java.util.HashMap;

/**
 * Created by laurenztolentino on 02/09/2017.
 */
public class AffixList
{
	final static char vowels[] = {
			'a', 'e', 'i', 'o', 'u'
	};

	final static String commonPrefixes[] = {
			"na", "nag", "ma", "mag", "i", "i-", "ika-", "isa-", "ipa",
			"ipag", "ipang", "pa", "pag", "um", "in", "ka", "ni", "pinaka",
			"pina", "pinag"
	};

	final static String commonInfixes[] = {
		"um", "in"
	};

	final static String commonSuffixes[] = {
			"an", "in", "han", "hin", "ng"
	};

	final static String longPrefixes[] = {
			"napag", "mapag", "nakipag", "nakikipag", "makipag", "makikipag",
			"nakiki", "makiki", "naka", "nakaka",
	};

	/**
	 * List down all the affixes with their equivalent breakdown
	 * @return
	 * HashMap with prefix and it's equivalent format
	 */
	public HashMap initiateEquivalentBreakdown()
	{
		HashMap hm = new HashMap();
		hm.put("pinag", 	 "~pinag");
		hm.put("pinagpa",	 "~pinag~pa");
		hm.put("ipang", 	 "~ipang");
		hm.put("ipinag",	 "~i~pinag");
		hm.put("ipinagpa", 	 "~i~pinag~pa");
		hm.put("nakiki", 	 "~na$ki$ki");
		hm.put("makiki", 	 "~ma$ki$ki");
		hm.put("nakikipag",  "~na$ki$ki~pag");
		hm.put("napag", 	 "~na~pag");
		hm.put("mapag", 	 "~ma~pag");
		hm.put("nakipag", 	 "~na~ki~pag");
		hm.put("makipag", 	 "~ma~ki~pag");
		hm.put("makikipag",  "~ma~ki~pag");
		hm.put("naka", 		 "~na~ka");
		hm.put("nagpa", 	 "~nag~pa");
		hm.put("nakaka", 	 "~na$ka$ka");
		hm.put("maka", 	 	 "~ma~ka");
		hm.put("makaka", 	 "~ma$ka$ka");
		hm.put("nagka", 	 "~nag~ka");
		hm.put("nagkaka", 	 "~nag$ka$ka");
//		hm.put("magka", 	 "~mag~ka");
//		hm.put("magkaka", 	 "~mag$ka$ka");
		hm.put("napaki", 	 "~na~pa~ki");
		hm.put("napakiki", 	 "~na~pa$ki$ki");
		hm.put("mapaki", 	 "~ma~pa~ki");
		hm.put("mapakiki", 	 "~ma~pa$ki$ki");
		hm.put("paki", 		 "~pa~ki");
		hm.put("pagka", 	 "~pag~ka");
		hm.put("pakiki", 	 "~pa$ki$ki");
		hm.put("pakikipag",  "~pa$ki$ki~pag");
		hm.put("pagki", 	 "~pag~ki");
		hm.put("pagkiki", 	 "~pag$ki$ki");
		hm.put("pagkikipag", "~pag$ki$ki~pag");
		/* recently added jan 2017 */
		hm.put("ika", 		 "~i~ka");
		hm.put("ikapag",	 "~i~ka~pag");
		hm.put("ikapagpa", 	 "~i~ka~pag~pa");
		hm.put("ikina", 	 "~i~ka@in");
		hm.put("ikapang", 	 "~i~ka~pang");
		hm.put("ipa", 		 "~i~pa");
		hm.put("ipaki", 	 "~i~paki");
		hm.put("ipag", 		 "~i~pag");
		hm.put("ipagka", 	 "~i~pag~ka");
		hm.put("ipagpa", 	 "~i~pag~pa");
		hm.put("ipapang", 	 "~i$pa$pang");
		hm.put("makapag", 	 "~maka~pag");
		hm.put("magkanda", 	 "~mag~kanda");
		hm.put("magkang", 	 "~mag~ka~ng");
		hm.put("magkasing",  "~mag~ka~sing");
		hm.put("maging",  	 "~maging");
		hm.put("nakapag-", 	 "~nakapag");
		hm.put("nakapaka", 	 "~napa~ka");
		return hm;
	}

	public static String[] getCommonPrefixes() {
		return commonPrefixes;
	}

	public static String[] getLongPrefixes() {
		return longPrefixes;
	}

	public static String[] getCommonInfixes() {
		return commonInfixes;
	}

	public static String[] getCommonSuffixes() {
		return commonSuffixes;
	}

	public static char[] getVowels() {
		return vowels;
	}
}
