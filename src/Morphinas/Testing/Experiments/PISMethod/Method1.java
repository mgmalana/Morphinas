package Morphinas.Testing.Experiments.PISMethod;


import java.util.ArrayList;

/**
 * Created by laurenz on 27/01/2017.
 */
public class Method1
{

	private final static String _LEFT = "left";
	private final static String _RIGHT = "right";

	private String[] storedPrefixes = { "pinag", "ka" };
	private String[] storedInfixes 	= { "in", "um" };
	private String[] storedSuffixes = { "in", "an" };
	private String[] storedRoots 	= { "kain", "ka"};

	private ArrayList<String> foundRoots = new ArrayList<>();

	private boolean mustStop		= false;
	private boolean donePrefix		= false;
	private boolean doneSuffix		= false;
	private boolean doneInfix 		= false;

	private int repeats 			= 0;

	public Method1()
	{
		println("Running Method1...");
	}

	public static void main(String[] args)
	{
		String test = "pinagkainan";
		Method1 m1 = new Method1();

		m1.test1(test);


	}

	public void test0(String test)
	{
		performStemming(test);

		println("Printing found rootwords: ");
		for(String found: foundRoots)
		{
			println(found);
		}
	}

	public void test1(String test)
	{
		Method1 m1 	= new Method1();
		Stem stem	= new Stem(test);

		println("Starting Left");
		m1.performStemming2(stem, _LEFT);

//		println("Starting Right");
//		m1.performStemming2(stem, _RIGHT);
		println("Found roots: " + foundRoots.size());
		for(String found: foundRoots )
		{
			println(found);
		}
	}

	public void testWordObject()
	{
		Stem stem = new Stem("pinagkainan");
		println( "testingWordObject: " + stem.getStem() );
	}

	public String performStemming(String stem)
	{
		println("current stem: " + stem);

		if( isRoot(stem) )
		{
			foundRoots.add(stem);
			return stem;
		}
		else
		{
			if( donePrefix == false )
			{
				donePrefix = true;
				performStemming( rmvPrefix(stem));
			}
			else if( doneSuffix == false )
			{
				doneSuffix = true;
				performStemming( rmvSuffix(stem));
			}
			else {
				donePrefix = false;
				doneSuffix = false;

				if( repeats < 5 ) {
					repeats++;
					performStemming( stem );
				}
				println("Did not succeed");
			}

		}

		return stem;
	}

	public String performStemming2(Stem stem, String direction)
	{
		println("current stem: " + stem.getStem() );

		if( isRoot(stem.getStem()) )
		{
			this.foundRoots.add( stem.getStem() );
			println("Updating foundRoots with " + stem.getStem() );
			return stem.getStem();
		}
		else
		{
			if( direction.equalsIgnoreCase(_LEFT) )
			{
				if( stem.isChkPrefix() == false )
				{
					stem.setChkPrefix(true);
					stem.setStem( rmvPrefix( stem.getStem() ) );
					println("1: " + stem.getStem());
					performStemming2( stem, _LEFT );
				}
				else if( stem.isChkSuffix() == false )
				{
					stem.setChkSuffix(true);
					stem.setStem( rmvSuffix(stem.getStem()));
					println("2: " + stem.getStem());
					performStemming2( stem, _LEFT );
				}
			}
			else if( direction.equals(_RIGHT) )
			{
				if( stem.isChkSuffix() == false )
				{
					stem.setChkSuffix(true);
					stem.setStem( rmvSuffix(stem.getStem()));
					performStemming2( stem, _RIGHT );
				}
				else if( stem.isChkPrefix() == false )
				{
					stem.setChkPrefix(true);
					stem.setStem( rmvPrefix(stem.getStem()));
					performStemming2( stem, _RIGHT );
				}
			}
			else {
				stem.setChkPrefix(false);
				stem.setChkSuffix(false);

				if( repeats < 5 ) {
					println("repeats: " + repeats);
					repeats++;
					performStemming2( stem, direction );
				}
				println("Did not succeed");
			}

		}

		return "NONE FOUND";
	}

	public String rmvPrefix(String stem)
	{
		String result = stem;
		String pfxCompare;
		int pfxLen;

		println("stem: " + stem);
		for ( String prefix: storedPrefixes)
		{
			pfxLen = prefix.length();
			println("pfxLen: " + pfxLen);
			pfxCompare = stem.substring(0, pfxLen);

			if( pfxCompare.equalsIgnoreCase(prefix) )
			{
				println("removed " + prefix);
				return stem.substring(pfxLen);
			}
		}

		return result;
	}

	public String rmvSuffix(String stem)
	{
		String result = stem;
		String sfxCompare;
		int sfxLen;

		for( String suffix: storedSuffixes)
		{

			sfxLen = suffix.length();
			sfxCompare = stem.substring( stem.length() - sfxLen );
			if( sfxCompare.equalsIgnoreCase(suffix) )
			{
				println("removed " + suffix);
				return stem.substring(0, stem.length()-sfxLen);
			}
		}

		return result;
	}

	public String rmvInfix(String stem)
	{
		String result = stem;

		return result;
	}

	public boolean isRoot(String stem)
	{
		for(String roots: storedRoots )
		{
			if( roots.equalsIgnoreCase(stem) )
			{
				println("found match");
				return true;
			}

		}
		return false;
	}

	public static void println(Object text)
	{
		System.out.println(text.toString());
	}

	public class Stem
	{
		String stem;
		ArrayList<String> prefixes;
		ArrayList<String> infixes;
		ArrayList<String> suffixes;
		boolean chkPrefix = false;
		boolean chkSuffix = false;

		public Stem(String stem)
		{
			this.stem = stem;
		}

		/* generated getters and setters */

		public String getStem() {
			return stem;
		}

		public void setStem(String stem) {
			this.stem = stem;
		}

		public ArrayList<String> getPrefixes() {
			return prefixes;
		}

		public void setPrefixes(ArrayList<String> prefixes) {
			this.prefixes = prefixes;
		}

		public ArrayList<String> getInfixes() {
			return infixes;
		}

		public void setInfixes(ArrayList<String> infixes) {
			this.infixes = infixes;
		}

		public ArrayList<String> getSuffixes() {
			return suffixes;
		}

		public void setSuffixes(ArrayList<String> suffixes) {
			this.suffixes = suffixes;
		}

		public boolean isChkPrefix() {
			return chkPrefix;
		}

		public void setChkPrefix(boolean chkPrefix) {
			this.chkPrefix = chkPrefix;
		}

		public boolean isChkSuffix() {
			return chkSuffix;
		}

		public void setChkSuffix(boolean chkSuffix) {
			this.chkSuffix = chkSuffix;
		}

	}
}
