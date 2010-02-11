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
        this._digest = createIndex(input_);
        this._index = getIndex();
    }

    public java.security.MessageDigest createIndex(String input_) {
        try {
            //compute the index from a SHA-1
            this._digest = MessageDigest.getInstance("SHA-1");
            this._digest.reset();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ShiftedInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        this._digest.update(this._input.getBytes());
        return _digest;
    }

    public String getIndex() {
        //recompute the index
        if(this._index == null || this._index.length() <= 0) {
            this._index = (new BigInteger(1, this._digest.digest())).toString(16);
        }
        return this._index;
    }

    @Override public String toString() {
        return this._input;
    }
    private java.lang.String _input;
    private java.lang.String _index;
    private java.security.MessageDigest _digest;
}
 