package Morphinas.Testing.Experiments.PISMethod;



/**
 * Created by laurenz on 27/01/2017.
 */
public class Method1
{

	String[] storedPrefixes = { "pinag", "ka" };
	String[] storedInfixes 	= { "in", "um" };
	String[] storedSuffixes = { "in", "an" };

	public Method1()
	{
		println("Running Method1...");
	}

	public static void main(String[] args)
	{
		String test = "pinagkainan";
		Method1 m1 = new Method1();

		println( m1.rmvPrefix(test) );
	}

	public String rmvPrefix(String stem)
	{
		String result = stem;
		String pfxCompare;
		int pfxLen;

		for ( String prefix: storedPrefixes)
		{
			pfxLen = prefix.length();
			pfxCompare = stem.substring(0, pfxLen);

			if( pfxCompare.equalsIgnoreCase(prefix) )
			{
				return stem.substring(pfxLen);
			}
		}

		return result;
	}

	public String rmvSuffix(String stem)
	{
		String result = stem;

		for( String suffix: storedSuffixes)
		{

		}

		return result;
	}

	public String rmvInfix(String stem)
	{
		String result = stem;

		return result;
	}

	public static void println(Object text)
	{
		System.out.println(text.toString());
	}
}
