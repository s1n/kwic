package kwic.index;

/**
 * Performs circular shifting of the input tokens.
 */
public class CircularShifter extends Shifter {

   public CircularShifter() {
   }

   public void generatePermutations(IndexedString input_) {
      this.tokenize(input_.toString());
      this._iter_position = 0;
   }

   @Override
   public void clear() {
      this._origin = null;
      this._iter_position = 0;
   }

   @Override
   IndexedString next() {
      if (this._iter_position >= this._tokens.size()) {
         return null;
      } else if(this._iter_position != 0) {
         this._tokens.add(this._tokens.remove(0));
      }
      IndexedString is = new IndexedString(this._iter_position == 0 ? null : this._origin, join());
      if(this._iter_position == 0) {
         this._origin = is;
      }
      this._iter_position++;
      return is;
   }
   private int _iter_position = 0;
   private IndexedString _origin = null;
}
