package kwic.index;

/**
 * Class to perform circular shifting of input tokens.
 */
public class CircularShifter extends Shifter {

   public CircularShifter() {
   }

   public void generatePermutations(IndexedString input_) {
      if(this._tokens == null || this._tokens.size() == 0) {
         this.tokenize(input_.toString());
         this._iter_position = 0;
      }
   }

   @Override
   public void clear() {
      this._origin = null;
      this._iter_position = 0;
   }

   /**
    * Method to iterate through the shifter, each iteration returns
    * the next shifted version of the string, until it has returned to the first.
    *
    * @return IndexedString which is a shift of input_
    */
   @Override
   IndexedString next() {
      if (this._iter_position >= this._tokens.size()) {
         return null;
      } else if(this._iter_position != 0) {
         this._tokens.add(this._tokens.remove(0));
      }
      IndexedString is = new IndexedString(this._iter_position == 0 ? null : this._origin, join());
      is.setURL(this._url.toString());
      if(this._iter_position == 0) {
         this._origin = is;
      }
      this._iter_position++;
      return is;
   }
   private int _iter_position = 0;
   private IndexedString _origin = null;
}
