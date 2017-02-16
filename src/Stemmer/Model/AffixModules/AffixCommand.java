package Stemmer.Model.AffixModules;

import Stemmer.Model.AffixModules.Infix.InfixCommand;
import Stemmer.Model.AffixModules.Prefix.PrefixCommand;
import Stemmer.Model.AffixModules.Suffix.SuffixCommand;
import Stemmer.Model.DBHandler;
import Stemmer.Model.Stem;

import java.util.ArrayList;

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
	PrefixCommand pc;
	InfixCommand ic;
	SuffixCommand sc;

	public AffixCommand()
	{}

	public void generatePISTree2(String word)
	{
		/* saving the trees */
		ArrayList<ArrayList<Branch>> tY = new ArrayList<>();
		ArrayList<Branch> tX 			= new ArrayList<>();
		ArrayList<Branch> newParents 	= new ArrayList<>();
		/* Children of the Root */
		Branch root, parent, prefixBranch, infixBranch, suffixBranch, tempParent;
		int expectedTreeWidth = 1;
		/* Initialize first stem */
		Stem rootStem 	= new Stem( word );
		root 			= new Branch( rootStem );

		parent = root;
		tX.add( parent );
		tY.add( tX );

		for ( int y = 0; y < tY.size(); y++ )
		{
			println(tX.get(0).getStem().getStemString() + " - " + tY.size());
			tX = new ArrayList<>();
			ArrayList<Branch> tempX = tY.get( y );
			println("tempX.size: " + tempX.size() );
			for( int x = 0; x < tempX.size(); x++ )
			{
				tempX.get(x).generateBranchChildren();
				tX.add( tempX.get(x).getPrefixBranch() );
				tX.add( tempX.get(x).getInfixBranch() );
				tX.add( tempX.get(x).getSuffixBranch() );
			}
			tY.add( tX );

			if( tY.size() > 2 )
			{
				break;
			}
		}

		printTreeContent(tY);
	}

	public void generatePISTree(String word)
	{
		/* saving the trees */
		ArrayList<ArrayList<Branch>> tY = new ArrayList<>();
		ArrayList<Branch> tX 			= new ArrayList<>();
		ArrayList<Branch> newParents 	= new ArrayList<>();
		/* Children of the Root */
		Branch root, parent, prefixBranch, infixBranch, suffixBranch, tempParent;
		int expectedTreeWidth = 1;
		/* Stopping properties */
		/* Stem */
		Stem stem, temp;
		/* Begin */
		stem = new Stem(word);
		root = new Branch(stem);
		parent = new Branch(stem);
		/* initialize first depth of arraylist */
		tX.add(root);
		tY.add(tX);
		/* tree expander */
		while( parent.stopper < 3 )
		{
			expectedTreeWidth 	= expectedTreeWidth * 3;

			tX 					= new ArrayList<>();

			parent.generateBranchChildren();
			prefixBranch = parent.getPrefixBranch();
			infixBranch  = parent.getInfixBranch();
			suffixBranch = parent.getSuffixBranch();

			tX.add(prefixBranch);
			tX.add(infixBranch);
			tX.add(suffixBranch);
			tY.add(tX);

			boolean donePref = false, doneInf = false, doneSuff = false;
			for( int i = 0; i < expectedTreeWidth; i++ )
			{
				newParents = tX;
				tX = new ArrayList<>();
//				println( "NewParent: " + newParents.get(i).getStem().getStemString() );
				tempParent = newParents.get(i);
				if ( parent.getStem().getStemString().length() > 4 )
				{
					parent.generateBranchChildren();
					prefixBranch = tempParent.getPrefixBranch();
					infixBranch  = tempParent.getInfixBranch();
					suffixBranch = tempParent.getSuffixBranch();

					tX.add(prefixBranch);
					tX.add(infixBranch);
					tX.add(suffixBranch);

					if( !donePref && !doneInf && !doneSuff )
					{
						donePref = true;
					}
					else if ( donePref && !doneInf && !doneSuff )
					{
						doneInf = true;
					}
					else if ( donePref && doneInf && !doneSuff )
					{
						doneSuff = true;
					}
					tY.add(tX);
				}
				else {
					parent.stopper++;
				}
			}
		}

	}

	public void printTreeContent(ArrayList<ArrayList<Branch>> tY)
	{
		/* try to print contents of tree */
		for( int y = 0; y < tY.size(); y++ )
		{
			ArrayList<Branch> tempTree = tY.get(y);
			for( int x = 0; x < tempTree.size(); x++ )
			{
				print( tempTree.size() +"-");
				print( tempTree.get(x).getStem().getStemString() +" ");
				if( (x+1) % 3 == 0 )
				{
					print("|| ");
				}
			}
			println("");
		}
	}

	public void testTree()
	{
		/* mmhmm */
		ArrayList<ArrayList<Branch>> treeY = new ArrayList<>();
		ArrayList<Branch> treeX = new ArrayList<>();
		/* user given */
		String word = "pinahintayan";
		Stem stem = new Stem(word);
		/* treeStruct contents */
		Branch p, i, s, root;
		/* root of tree */
		root = new Branch(stem);
		treeX.add( root );
		treeY.add( treeX );
		/* first row of children */
		treeX = null;
		treeX = new ArrayList<>();
		root.generateBranchChildren();
		p = root.getPrefixBranch();
		i = root.getInfixBranch();
		s = root.getSuffixBranch();
		treeX.add(p);
		treeX.add(i);
		treeX.add(s);
		treeY.add(treeX);
		/* try to print contents of tree */
		for( int y = 0; y < treeY.size(); y++ )
		{
			ArrayList<Branch> tempTree = treeY.get(y);
			for( int x = 0; x < tempTree.size(); x++ )
			{
//				print( tempTree.size() +"-");
				print( tempTree.get(x).getStem().getStemString() +"[");
//				println( tempTree.get(x).getStem().getAffix());
				print( tempTree.get(x).getDirectionHistory() + "]");
			}
			println("");
		}
	}

	public void testCommands()
	{
		/* mmhmm */
		ArrayList<ArrayList<Branch>> treeY = new ArrayList<>();
		ArrayList<Branch> treeX = new ArrayList<>();
		/* user given */
		String word = "pinahintayan";
		/* treeStruct contents */
		Branch p, i, s, root;

		Stem stem = new Stem(word);

		root = new Branch(stem);
		root.generateBranchChildren();
		p = root.getPrefixBranch();
		i = root.getInfixBranch();
		s = root.getSuffixBranch();

		/* Prefix */
		println(p.getStem().getStemString());
		/* Infix */
		println(i.getStem().getStemString());
		println(i.getDirectionHistory());
		/* Suffix */
		println(s.getStem().getStemString());
	}

	public static class Test
	{
		public static void main(String[] args)
		{
			AffixCommand ac = new AffixCommand();
			ac.generatePISTree2("pinahintayan");
//			ac.generatePISTree("pinahintayan");
		}
	}

	/**
	 * Branch
	 */
	public final class Branch
	{
		/*
		  * ********************************************************************
		  *                               Variables
		  * ********************************************************************
		 */
		/* Important properties */
		private Stem stem;
		/* Children Branches */
		private Branch prefixBranch, infixBranch, suffixBranch;
		/* directions */
		private final static char _s = 's', _i = 'i', _p = 'p', _c = 'R';
		private String directionHistory = "";
		private char direction;
		/* Tree properties */
		private boolean isTop = false, isRootWord = false, isTreeRoot = false, isTreeLeaf = false;
		private boolean isPrefixRoot = false, isInfixRoot = false, isSuffixRoot = false;
		private int treeDepth, nullCount = 0;
		/* Stoppers */
		int stopper = 0;

		/*
		  * ********************************************************************
		  *                             Constructors
		  * ********************************************************************
		 */

		/**
		 * Use this for root of the tree only (unstemmed)
		 * @param untouchedStem The original input word by the user
		 */
		public Branch(Stem untouchedStem) {
			this.stem = untouchedStem;
			this.direction = _c;
			this.directionHistory = directionHistory + _c + "-";
		}

		/**
		 * AVOID USING THIS ONE
		 * @param direction Must contain either 'p', 'i', 's'
		 * @param stem      Must be passed every time.
		 */
		public Branch(char direction, Stem stem) {
			this.direction = direction;
			this.stem = stem;
			this.directionHistory = directionHistory + direction + "-";
		}

		/**
		 * Use only when creating a non-root branch
		 * @param direction
		 * Direction of this branch (prefix, infix, suffix)
		 * @param stem
		 * latest modified stem
		 * @param directionHistory
		 * The latest modified path that this branch has gone through to get here
		 */
		public Branch(char direction, Stem stem, String directionHistory) {
			this.direction = direction;
			this.stem = stem;
			this.directionHistory = directionHistory + direction + "-";
		}

		public void generateBranchChildren() {
			generateBranchChildren(this.stem);
		}

		public void generateBranchChildren(Stem currentStem)
		{
			/* Stem p = currentStem is mutable. Any changes to p will reflect on currentStem also (pass-by-reference) */
			Stem p = new Stem(currentStem.getStemString());
			Stem i = new Stem(currentStem.getStemString());
			Stem s = new Stem(currentStem.getStemString());

			generatePrefixBranch(p);
			generateInfixBranch(i);
			generateSuffixBranch(s);
		}

		/*
		  * ********************************************************************
		  *                      Branch Children Generators
		  * ********************************************************************
		 */

//			parentStem = pc.performStemmingModules();
//			println("pb: " + parentStem.getStemString());
		public Branch generatePrefixBranch(Stem parentStem)
		{
			PrefixCommand pc = new PrefixCommand(parentStem);
			/* check if stem is already root word */

//			if( pc.checkDB() )
//			{
//				isPrefixRoot = true;
//				parentStem.setRootWord( parentStem.getStemString() );
//				parentStem.setPathTaken( directionHistory );
//				stopper++;
//			}
			/* check if stem is too small for more stemming */
			checkIfStemLengthAtSmallest( parentStem );
			/* set this branch's p branch */
			setPrefixBranch( new Branch(_p, parentStem, this.directionHistory) );
			return prefixBranch;
		}

		public Branch generateInfixBranch(Stem parentStem)
		{
			InfixCommand ic = new InfixCommand(parentStem);
			/* check if stem is already root word */
//			if( ic.checkDB() )
//			{
//				isInfixRoot = true;
//				parentStem.setRootWord( parentStem.getStemString() );
//				parentStem.setPathTaken( directionHistory );
//				stopper++;
//			}
			/* check if stem is too small for more stemming */
			checkIfStemLengthAtSmallest( parentStem );
			/* set this branch's i branch */
			setInfixBranch( new Branch(_i, parentStem, this.directionHistory) );
			return infixBranch;
		}

		public Branch generateSuffixBranch(Stem parentStem)
		{
			SuffixCommand sc = new SuffixCommand(parentStem);
			/* check if stem is already root word */
//			if( sc.checkDB() )
//			{
//				isSuffixRoot = true;
//				parentStem.setRootWord( parentStem.getStemString() );
//				parentStem.setPathTaken( directionHistory );
//				stopper++;
//			}
			/* check if stem is too small for more stemming */
			checkIfStemLengthAtSmallest( parentStem );
			/* set this branch's s branch */
			setSuffixBranch( new Branch(_s, parentStem, this.directionHistory) );
			return suffixBranch;
		}

		/**
		 * Gets children in a Branch[] format.
		 * index 0 for prefix, 1 for infix, and 2 for suffix.
		 * @return
		 * children of the selected branch
		 */
		public Branch[] getChildren()
		{
			Branch[] children = new Branch[3];
			children[0] = getPrefixBranch();
			children[1] = getInfixBranch();
			children[2] = getSuffixBranch();
			return children;
		}

		/*
		  * ********************************************************************
		  *                             Other Utility
		  * ********************************************************************
		 */

		/**
		 * Checks DB if current stem is a root word
		 * @param checkStem
		 * stem to be checked if root word
		 * @return
		 * true if root word otherwise false
		 */
		public boolean checkIfRoot(Stem checkStem)
		{
			DBHandler db = new DBHandler();
			String word  = checkStem.getStemString();
			return db.lookup(word);
		}

		/**
		 * Check if the length of the current stem is suitable for further stemming.
		 * @param checkStem
		 * stem to be checked
		 * @return
		 * true if stem is <= 4 and false otherwise.
		 * @Note Stemming should end when this is true.
		 */
		public boolean checkIfStemLengthAtSmallest(Stem checkStem)
		{
			String word = checkStem.getStemString();
			if ( word.length() <= 4 )
			{
				isTreeLeaf = true;
				stopper++;
				return true;
			}
			return false;
		}

		/*
		  * ********************************************************************
		  *                         Getters and Setters
		  * ********************************************************************
		 */
		public String getDirectionHistory() {
			return directionHistory;
		}

		public void setDirectionHistory(String directionHistory) {
			this.directionHistory = directionHistory;
		}

		public void printBranchStem()
		{
			println( "B-Stem: " + this.stem.getStemString() );
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
