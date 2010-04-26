package kwic.backend;

import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Collects all the results by maintaining order to the results. This object
 * can also be iterated over to inspect the indexed results.
 */
public class IndexList extends java.util.TreeSet<kwic.backend.IndexedString> {

   /**
    * Constructor, takes an implementation of Shifter interface
    * such as a CircularShifter, then this IndexList will build a TreeSet
    * of all Circular Shifts returned by iterating the shifter
    * @param cs_
    */
   public IndexList(Shifter cs_) {
      this._shift = cs_;
   }

   @Deprecated
   public boolean containsIndex(IndexedString o) {
      for(IndexedString is : this) {
         if(is.getIndex().equalsIgnoreCase(o.getIndex())) {
            return true;
         }
      }
      return false;
   }

   @Deprecated
   public boolean containsInput(IndexedString o) {
      for(IndexedString is : this) {
         if(is.toString().compareTo(o.toString()) != 0) {
            return true;
         }
      }
      return false;
   }

   public IndexedString findFirstIndexMatch(String i) {
      for(IndexedString is : this) {
         if(is.getIndex().equalsIgnoreCase(i)) {
            return is;
         }
      }
      return null;
   }

   @Deprecated
   public TreeSet<IndexedString> findAnyIndexMatches(String i) {
      TreeSet<IndexedString> li = new TreeSet<IndexedString>();
      for(IndexedString is : this) {
         if(is.getIndex().startsWith(i)) {
            li.add(is);
         }
      }
      return li;
   }

   @Deprecated
   public IndexedString findFirstInputMatch(String i) {
      for(IndexedString is : this) {
         if(is.toString().equals(i)) {
            return is;
         }
      }
      return null;
   }

   public java.util.TreeSet<kwic.backend.IndexedString> search(String i) {
      TreeSet<IndexedString> possible = findAnyInputMatches(i);
      TreeSet<IndexedString> matches = new TreeSet<IndexedString>();
      for(IndexedString is : possible) {
         if(is.originIndex() == null) {
            //it's an original input string
            System.err.println("Origin input string: " + is.toString());
            matches.add(is);
         } else {
            //else it's a shifted input, so let's get the original input
            System.err.println("Shifted input string: " + is.toString());
            matches.add(findFirstIndexMatch(is.originIndex()));
         }
      }
      return matches;
   }

   public TreeSet<IndexedString> findAnyInputMatches(String i) {
      TreeSet<IndexedString> li = new TreeSet<IndexedString>();
      for(IndexedString is : this) {
         if(is.originIndex() == null) {
            continue;
         }
         boolean containsalltokens = true;
         StringTokenizer st = new StringTokenizer(i);
         while(st.hasMoreTokens()) {
            String next = st.nextToken();
            containsalltokens &= is.toString().contains(next);
         }
         if(containsalltokens) {
            li.add(is);
         }
      }
      return li;
   }

   @Override
   public boolean add(IndexedString e_) {
      this._shift.clear();
      //e_.print();
      this._shift.tokenize(e_);
      this._shift.generatePermutations(e_);

      IndexedString is;
      while((is = this._shift.next()) != null) {
         //System.err.println("Shift: " + is.toString());
         super.add(is);
      }
      return true;
   }

   @Override
   public boolean remove(Object o) {
      return super.remove((IndexedString) o);
   }
   private Shifter _shift = null;
}
