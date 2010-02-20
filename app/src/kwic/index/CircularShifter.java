package kwic.index;

import java.util.ArrayList;

/**
 * Performs circular shifting of the input tokens.
 */
public class CircularShifter extends Shifter {

   public CircularShifter() {
      this._list = new ArrayList<IndexedString>();
   }

   public void generatePermutations(IndexedString input_) {
      this.tokenize(input_.toString());
      this._sizeof = this._tokens.size();
      this._iter_position = 0;
   }

   @Override
   IndexedString next() {
      if (this._iter_position >= this._sizeof) {
         return null;
      }
      this._tokens.add(this._tokens.remove(0));
      //FIXME more parent/derivative input cruft, needs cleaning up here
      IndexedString is = new IndexedString(this._iter_position == 0 ? null : this._list.get(this._iter_position - 1), join());
      this._list.add(is);
      this._iter_position++;
      return is;
   }
   private int _sizeof;
   private int _iter_position;
   //FIXME remove this cruft, bad idea to track last generated IndexString
   private ArrayList<IndexedString> _list;
}
