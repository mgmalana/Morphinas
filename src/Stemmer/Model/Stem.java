package Stemmer.Model;

/**
 * Created by laurenztolentino on 02/08/2017.
 */
public class Stem
{
	String stem;
	String affix;
	String feature;
	String rootWord;
	String pathTaken;

	public Stem(String stem)
	{
		this.stem = stem;
	}

	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}

	public String getAffix() {
		return affix;
	}

	public void setAffix(String affix) {
		this.affix = affix;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getRootWord() {
		return rootWord;
	}

	public void setRootWord(String rootWord) {
		this.rootWord = rootWord;
	}

	public String getPathTaken() {
		return pathTaken;
	}

	public void setPathTaken(String pathTaken) {
		this.pathTaken = pathTaken;
	}
}
