package kwic.backend;

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

   public boolean containsIndex(IndexedString o) {
      for(IndexedString is : this) {
         if(is.getIndex().equalsIgnoreCase(o.getIndex())) {
            return true;
         }
      }
      return false;
   }

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

   public TreeSet<IndexedString> findAnyIndexMatches(String i) {
      TreeSet<IndexedString> li = new TreeSet<IndexedString>();
      for(IndexedString is : this) {
         if(is.getIndex().startsWith(i)) {
            li.add(is);
         }
      }
      return li;
   }

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
            matches.add(is);
         } else {
            //else it's a shifted input, so let's get the original input
            matches.add(findFirstIndexMatch(is.originIndex()));
         }
      }
      return matches;
   }

   public TreeSet<IndexedString> findAnyInputMatches(String i) {
      TreeSet<IndexedString> li = new TreeSet<IndexedString>();
      for(IndexedString is : this) {

         //calculate the max substring length
         int sublen = i.length();
         if(is.toString().length() < sublen) {
            sublen = is.toString().length();
         }

         //case insensitive substring match
         if(is.toString().substring(0, sublen).equalsIgnoreCase(i)) {
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
         super.add(is);
      }
      return true;
   }

   @Override
   public boolean remove(Object o) {
      return super.remove((IndexedString)o);
   }
   private Shifter _shift = null;
}
