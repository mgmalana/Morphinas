package Stemmer.Model.AffixModules;

import Stemmer.Model.AffixModules.Infix.InfixCommand;
import Stemmer.Model.AffixModules.Prefix.PrefixCommand;
import Stemmer.Model.AffixModules.Suffix.SuffixCommand;
import Stemmer.Model.Branch;
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

			tX = null;
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

	public void generatePISTree3(String word)
	{
		/* Ecological Creation */
		ArrayList<ArrayList<Branch>> ty 	= new ArrayList<>();
		ArrayList<Branch> tx 				= new ArrayList<>();
		/* Branches */
		Branch rootBranch;
		/* Temp Vars */
		ArrayList<Branch> tempX;
		/* Create the root node */
		Stem rootStem 	= new Stem( word );
		rootBranch		= new Branch( rootStem );
		/* Add the root node in ArrayList */
		tx.add( rootBranch );
		ty.add( tx );
		/* Go out and populate */
		for( int y = 0; y < ty.size(); y++ )
		{
			tx 		= new ArrayList<>();
			tempX	= ty.get( y );
			for( int x = 0; x < tempX.size(); x++ )
			{
				tempX.get(x).generateBranchChildren();
				tx.add( tempX.get(x).getPrefixBranch() );
				tx.add( tempX.get(x).getInfixBranch() );
				tx.add( tempX.get(x).getSuffixBranch() );
			}
			ty.add(tx);

			if( ty.size() > 2)
			{
				break;
			}

		}
		printTreeContent(ty);
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
		while( parent.getStopper() < 3 )
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
				else
				{
					parent.setStopper( parent.getStopper() + 1 );
				}
			}
		}

	}

	public void printTreeContent( ArrayList<ArrayList<Branch>> tY )
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
		AffixCommand ac = new AffixCommand();

		public static void main(String[] args)
		{
			/* don't change this line */
			Test t = new Test();
			/* write below */
//			t.testCreateBranch();
			t.original();
		}

		public void original()
		{
//			ac.generatePISTree2("pinahintayan");
			ac.generatePISTree3("pinahintayan");
		}

		public void testCreateBranch()
		{

		}
	}


}
