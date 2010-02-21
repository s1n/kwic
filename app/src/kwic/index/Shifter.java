package kwic.index;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Shifter interface to classes that can reorganize input data for indexing.
 */
public abstract class Shifter {
   abstract void clear();

   abstract IndexedString next();

   abstract void generatePermutations(IndexedString input_);

   public void tokenize(String input_) {
      this._tokens = new ArrayList<String>(Arrays.asList(input_.split("\\s+")));
   }

   public String join() {
      StringBuilder sb = new StringBuilder("");
      for (String s : this._tokens) {
         sb.append(s).append(" ");
      }
      return sb.toString().trim();
   }
   protected ArrayList<String> _tokens;
}
