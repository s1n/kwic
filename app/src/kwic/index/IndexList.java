package kwic.index;

import java.util.Collection;

/**
 * Collects all the results by maintaining order to the results. This object
 * can also be iterated over to inspect the indexed results.
 */
public class IndexList extends java.util.TreeSet<kwic.index.IndexedString> {

   public IndexList(Shifter cs_) {
      this._shift = cs_;
   }

   @Override
   public boolean add(IndexedString e_) {
      this._shift.generatePermutations(e_);

      IndexedString is;
      while ((is = this._shift.next()) != null) {
         //FIXME don't add is if it's already in this TreeSet
         super.add(is);
      }
      return true;
   }

   @Override
   public boolean remove(Object o) {
      return super.remove(o);
   }
   private Shifter _shift = null;
}
