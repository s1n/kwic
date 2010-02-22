package kwic.index;

/**
 * Collects all the results by maintaining order to the results. This object
 * can also be iterated over to inspect the indexed results.
 */
public class IndexList extends java.util.TreeSet<kwic.index.IndexedString> {

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

   public IndexedString findIndex(String i) {
      for(IndexedString is : this) {
         if(is.getIndex().equalsIgnoreCase(i)) {
            return is;
         }
      }
      return null;
   }

   public IndexedString findInput(String i) {
      for(IndexedString is : this) {
         if(is.toString().equals(i)) {
            return is;
         }
      }
      return null;
   }

   @Override
   public boolean add(IndexedString e_) {
      this._shift.clear();
      this._shift.generatePermutations(e_);

      IndexedString is;
      while ((is = this._shift.next()) != null) {
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
