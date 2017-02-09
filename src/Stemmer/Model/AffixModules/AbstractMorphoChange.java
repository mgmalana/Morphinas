package Stemmer.Model.AffixModules;


/**
 * Created by laurenztolentino on 02/09/2017.
 */
public abstract class AbstractMorphoChange
{
	public String foundAffix, foundAffixFeatured = null, leftStem, rightStem;
	public abstract String reduceStem(String stem);
	public abstract String applyFeature(String foundAffix);

	public String getFoundAffixFeatured() {
		return foundAffixFeatured;
	}

	public String getFoundAffix() {
		return this.foundAffix;
	}

	public String getLeftStem() {
		return leftStem;
	}

	public String getRightStem() {
		return rightStem;
	}
}
