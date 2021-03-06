package kwic.frontend;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import kwic.backend.CircularShifter;
import kwic.backend.IndexList;
import kwic.backend.IndexedString;
import kwic.backend.InputReader;

/**
 *
 */
public class SearchService {

   public SearchService() {
      _index = new IndexList(new CircularShifter());
      try {
         _indexEJB = (IndexedStringFacadeRemote) new InitialContext().lookup("web/IndexedString/remote");
         List<IndexedString> stuff = _indexEJB.findAll();
         if(stuff.size() > 0) {
            for(IndexedString is : stuff) {
               System.err.println("Restoring: " + is.toString());
               _index.add(is);
            }
         }
      } catch (Exception e) {
         //drop it like it's hot
      }
      for(IndexedString is : _index) {
         System.err.println("index data: " + is.toString());
      }
   }

   public String search(String for_) {
      StringBuilder sb = new StringBuilder();
      sb.append("<ul>");
      java.util.TreeSet<kwic.backend.IndexedString> res = new java.util.TreeSet<kwic.backend.IndexedString>();
      InputReader ir = new InputReader(new BufferedReader(new StringReader(for_)));
      IndexedString temp = ir.next();
      while(temp != null) {
         //System.err.println("searching for " + temp.toString());
         java.util.TreeSet<kwic.backend.IndexedString> results = _index.search(temp.toString());
         for(IndexedString is : results) {
            System.out.println("Search found: " + is.toString());
            res.add(is);
         }
         //System.err.println(" OR ");
         temp = ir.next();
      }
      for(IndexedString is : res) {
         sb.append("<li><a href=\"" + is.getURL() + "\">" + is.toString() + "</a><br/>");
      }
      sb.append("</ul>");
      //sb.append("Index: <br/><br/>");
      //for(IndexedString is : _index) {
      //   sb.append("<li><a href=\"" + is.getURL() + "\">" + is.toString() + "</a><br/>");
      //}
      //sb.append("</ul>");
      return sb.toString();
   }

   public String totalFound(String for_) {
      return String.valueOf(_index.search(for_).size());
   }

   public String generateShifts(IndexedString for_) {
      StringBuilder sb = new StringBuilder();
      sb.append("<ul>");
      IndexList il = new IndexList(new CircularShifter());
      il.add(for_);
      for(IndexedString is : il) {
         sb.append("<li>" + is.toString() + "</li>");
         for_.setURL(is.getURL());
      }

      //make sure this new entry goes to the database!
      _indexEJB.create(for_);
      return sb.toString();
   }

   public String getURL(IndexedString for_) {
      CircularShifter cs = new CircularShifter();
      cs.generatePermutations(for_);
      IndexedString temp = cs.next();
      return temp.getURL();
   }

   private IndexList _index;
   @EJB
   private static IndexedStringFacadeRemote _indexEJB;
}
