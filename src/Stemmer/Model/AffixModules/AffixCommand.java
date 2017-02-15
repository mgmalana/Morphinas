package Stemmer.Model.AffixModules;

import Stemmer.Model.AffixModules.Infix.InfixCommand;
import Stemmer.Model.AffixModules.Prefix.PrefixCommand;
import Stemmer.Model.AffixModules.Suffix.SuffixCommand;
import Stemmer.Model.Stem;
import static Utility.print.*;

/**
 * Created by laurenz on 09/02/2017.
 */
public class AffixCommand
{
	/* TreeTest properties */
	int treeDepth;
	boolean mustStop 	= false;
	boolean donePrefix 	= false;
	boolean doneInfix 	= false;
	boolean doneSuffix	= false;
	/* Commands */
	PrefixCommand pc 	= new PrefixCommand();
	InfixCommand ic 	= new InfixCommand();
	SuffixCommand sc 	= new SuffixCommand();

	public AffixCommand()
	{

	}

	public void testCommands()
	{
		Branch p, i, s, root;
		String word = "pinahintayan";
		Stem stem = new Stem(word);

		root = new Branch(stem);
		root.generateChildrenForThisBranch();
		println(root.getInfixBranch().getStem().getStem());
		println(root.getPrefixBranch().getStem().getStem());
		println(root.getSuffixBranch().getStem().getStem());
	}

	public static class Test
	{
		public static void main(String[] args)
		{
			AffixCommand ac = new AffixCommand();
			ac.testCommands();
		}
	}

	/**
	 * Branch
	 */
	public class Branch
	{
		/* Important properties */
		Stem stem;
		/* Children Branches */
		Branch prefixBranch, infixBranch, suffixBranch;
		/* directions */
		final static char _s = 's', _i = 'i', _p = 'p', _c = 'R';
		String directionHistory = "";
		char direction;
		/* Tree properties */
		boolean isTop = false, isRootWord = false, isTreeRoot = false, isTreeLeaf = false;
		int treeDepth;

		/**
		 * Use this for root of the tree only (unstemmed)
		 * @param untouchedStem
		 * The original input word by the user
		 */
		public Branch(Stem untouchedStem)
		{
			this.stem 				= untouchedStem;
			this.direction 			= _c;
			this.directionHistory 	= directionHistory + _c + "-";
		}

		/**
		 * A branch in the tree.
		 * @param direction
		 * Must contain either 'p', 'i', 's'
		 * @param stem
		 * Must be passed every time.
		 */
		public Branch(char direction, Stem stem)
		{
			this.direction 			= direction;
			this.stem 				= stem;
			this.directionHistory 	= directionHistory + direction + "-";
		}

		public Branch(char direction, Stem stem, String directionHistory)
		{
			this.direction 			= direction;
			this.stem 				= stem;
			this.directionHistory 	= directionHistory + direction + "-";
		}

		public void generateChildrenForThisBranch()
		{
			generateChildrenForThisBranch( this.stem );
		}

		public void generateChildrenForThisBranch(Stem currentStem)
		{
			generatePrefixBranch( currentStem );
			generateInfixBranch( currentStem );
			generateSuffixBranch( currentStem );
		}

		public Branch generatePrefixBranch(Stem parentStem)
		{
			PrefixCommand pc 	= new PrefixCommand();
			println("pb: " + pc.performStemmingModules( parentStem ).getStem());
			this.prefixBranch 	= new Branch(_p, pc.performStemmingModules( parentStem ), this.directionHistory );
			return prefixBranch;
		}

		public Branch generateInfixBranch(Stem parentStem)
		{
			InfixCommand ic 	= new InfixCommand();
			parentStem			= ic.performStemmingModules( parentStem );
			println("ib: " + parentStem.getStem());
			this.infixBranch 	= new Branch( _i, parentStem, this.directionHistory );
			return infixBranch;
		}

		public Branch generateSuffixBranch(Stem parentStem)
		{
			SuffixCommand sc  	 = new SuffixCommand();
			parentStem  	 	 = sc.performStemmingModules( parentStem );
			println("sb: " + parentStem.getStem());
			this.suffixBranch 	 = new Branch( _s, parentStem, this.directionHistory );
			return suffixBranch;
		}

		/*
		 * Getters and Setters
		 */
		public String getDirectionHistory() {
			return directionHistory;
		}

		public void setDirectionHistory(String directionHistory) {
			this.directionHistory = directionHistory;
		}

		public void printBranchStem()
		{
			println( "B-Stem: " + this.stem.getStem() );
		}

		public Branch getPrefixBranch() {
			return prefixBranch;
		}

		public void setPrefixBranch(Branch prefixBranch) {
			this.prefixBranch = prefixBranch;
		}

		public Branch getInfixBranch() {
			return infixBranch;
		}

		public void setInfixBranch(Branch infixBranch) {
			this.infixBranch = infixBranch;
		}

		public Branch getSuffixBranch() {
			return suffixBranch;
		}

		public void setSuffixBranch(Branch suffixBranch) {
			this.suffixBranch = suffixBranch;
		}

		public char getDirection() {
			return direction;
		}

		public void setDirection(char direction) {
			this.direction = direction;
		}

		public Stem getStem() {
			return stem;
		}

		public void setStem(Stem stem) {
			this.stem = stem;
		}

		public int getTreeDepth() {
			return treeDepth;
		}

		public void setTreeDepth(int treeDepth) {
			this.treeDepth = treeDepth;
		}

		public boolean isTop() {
			return isTop;
		}

		public void setTop(boolean top) {
			isTop = top;
		}

		public boolean isRoot() {
			return this.isRootWord;
		}

		public void setRoot(boolean root) {
			this.isRootWord = root;
		}

		public boolean isRootWord() {
			return isRootWord;
		}

		public void setRootWord(boolean rootWord) {
			isRootWord = rootWord;
		}

		public boolean isTreeRoot() {
			return isTreeRoot;
		}

		public void setTreeRoot(boolean treeRoot) {
			isTreeRoot = treeRoot;
		}

		public boolean isTreeLeaf() {
			return isTreeLeaf;
		}

		public void setTreeLeaf(boolean treeLeaf) {
			isTreeLeaf = treeLeaf;
		}
	}


}
