/*
 * DBLexicon.java
 *
 * Created on January 9, 2006, 1:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package MorphAnalyzer;

/**
 *
 * @author Solomon See
 */
import java.sql.*;
import java.util.*;
import DataStructures.*;
import java.io.Serializable;

public class DBLexicon implements Serializable {
	
    Connection con;
    ResultSet rs;   
    PreparedStatement psFindRoot = null;
    Trie t = new Trie(new DefaultTrieImpl());
    /** Creates a new instance of DBLexicon */
    public DBLexicon() throws Exception {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con = DriverManager.getConnection("jdbc:odbc:Words","sa","masterkey");
        psFindRoot = con.prepareStatement("select * from rootWords");
        rs = psFindRoot.executeQuery();
//        System.out.println("Loading Lexicon...");
        while(rs.next())
            t.store(rs.getString(1));
        con.close();
        con = null;
        psFindRoot = null;
        rs = null;
//        System.out.println("Completed Loading Lexicon");
    }    
    public boolean lookup(String word) throws Exception {
        return t.lookup(word);
    }
}
