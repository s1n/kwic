package kwic.index;

import java.net.MalformedURLException;
import java.net.URL;
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
      try {
         this._tokens = new ArrayList<String>(Arrays.asList(input_.split("\\s+")));
         this._url = new URL(this._tokens.remove(this._tokens.size() - 1));
      } catch (MalformedURLException ex) {
         ex.printStackTrace();
      }
   }

   public void tokenize(IndexedString input_) {
      this.tokenize(input_.toString());
      input_.setURL(this._url.toString());
   }

   public String join() {
      StringBuilder sb = new StringBuilder("");
      for (String s : this._tokens) {
         sb.append(s).append(" ");
      }
      return sb.toString().trim();
   }

   public boolean startsWithNoise() {
      if (null != this._tokens && 0 < this._tokens.size()) {
         return EnglishCollator.noise(this._tokens.get(0));
      }
      return false;
   }
   
   protected ArrayList<String> _tokens;
   protected URL _url = null;
}
