package kwic.index;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Shifted input. Keeps track of it's own index and the originating input.
 */
public class ShiftedInput {
   public ShiftedInput(String input_) {
       this._input = input_;
       this._index = createIndex(input_);
   }

   public java.security.MessageDigest createIndex(String input_) {
        try {
            this._index = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ShiftedInput.class.getName()).log(Level.SEVERE, null, ex);
        }
       this._index.digest(this._input.getBytes());
       return _index;
   }

   public String getIndex() {
       return new BigInteger(1, this._index.digest()).toString(16);
   }

   @Override public String toString() {
       return this._input;
   }

   private java.lang.String _input;
   private java.security.MessageDigest _index;
}
