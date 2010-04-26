package kwic.backend;

import java.util.Iterator;

/**
 * Class to perform circular shifting of input tokens.
 */
public class CircularShifter extends Shifter {

   public CircularShifter() {
   }

   public void generatePermutations(IndexedString input_) {
      if(this._tokens == null || this._tokens.size() == 0) {
         this.tokenize(input_.getInput());
         this._iter_position = 0;
         this._iter_max = this._tokens.size();
      }
   }

   @Override
   public void clear() {
      this._origin = null;
      this._iter_position = 0;
      this._iter_max = 0;
   }

   /**
    * Method to iterate through the shifter, each iteration returns
    * the next shifted version of the string, until it has returned to the first.
    *
    * @return IndexedString which is a shift of input_
    */
   @Override
   public IndexedString next() {
      do {
         if(this._iter_position >= this._iter_max) {
            return null;
         } else if(this._iter_position == 0 && !startsWithNoise()) {
            break;
         } else if(this._iter_position >= 0) {
            //need fewer shift results if we encounter noise
            if(startsWithNoise()) {
               this._iter_max--;
            }
            this._tokens.add(this._tokens.remove(0));
         }
      } while(startsWithNoise());

      IndexedString is = new IndexedString(this._iter_position == 0 ? null : this._origin, join());
      is.setURL(this._url.toString());
      if(this._iter_position == 0) {
         this._origin = is;
      }
      this._iter_position++;
      return is;
   }
   private Iterator<String> _betteriter;
   private int _iter_position = 0;
   private int _iter_max = 0;
   private IndexedString _origin = null;
}
