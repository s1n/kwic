package kwic.frontend;

import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import kwic.backend.CircularShifter;
import kwic.backend.IndexList;
import kwic.backend.IndexedString;

/**
 *
 */
public class SearchService {

   public SearchService() {
      _index = new IndexList(new CircularShifter());
      try {
         _indexEJB = (IndexedStringFacadeRemote)new InitialContext().lookup("web/IndexedString/remote");
         List<IndexedString> stuff = _indexEJB.findAll();
         if(stuff.size() > 0) {
            for(IndexedString is : stuff) {
               System.err.println("Restoring: " + is.toString());
               _index.add(is);
            }
         } else {
            //IndexedString parent = null;
            //IndexedString is = new IndexedString(parent, "dark side http://foo.com");
            //_index.add(is);
            //_indexEJB.create(is);
            //is = new IndexedString(parent, "yeah yeah yeahs http://yeahs.com");
            //_index.add(is);
            //_indexEJB.create(is);
            //is = new IndexedString(parent, "this project is time consuming http://timewasters.com");
            //_index.add(is);
            //_indexEJB.create(is);
            //is = new IndexedString(parent, "omg wtf bbq http://wtf.com");
            //_index.add(is);
            //_indexEJB.create(is);
         }
      } catch(Exception e) {
         //drop it like it's hot
      }
      for(IndexedString is : _index) {
         System.err.println("index data: " + is.toString());
      }
   }

   public String search(String for_) {
      StringBuilder sb = new StringBuilder();
      sb.append("<ul>");
      java.util.TreeSet<kwic.backend.IndexedString> results = _index.search(for_);
      for(IndexedString is : results) {
         sb.append("<li><a href=\"" + is.getURL() + "\">" + is.toString() + "</a><br/>");
      }
      sb.append("</ul>");
      sb.append("Index: <br/><br/>");
      for(IndexedString is : _index) {
         sb.append("<li><a href=\"" + is.getURL() + "\">" + is.toString() + "</a><br/>");
      }
      sb.append("</ul>");
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
