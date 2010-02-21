package kwic.index;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Collator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Shifted input. Keeps track of it's own index and the originating input.
 * @todo Remove the stored _input to compress.
 */
public class IndexedString implements Comparable<IndexedString> {

   public IndexedString() {
      this._index = null;
      this._input = null;
      this._digest = null;
   }

   public IndexedString(IndexedString pre_) {
      this._origin_digest = pre_._digest;
      this._origin_index = pre_._index;
   }

   @Override
   public boolean equals(Object obj) {
      return this._input.equals(obj.toString());
   }

   public IndexedString(IndexedString pre_, String input_) {
      if (pre_ != null) {
         this._origin_digest = pre_._digest;
         this._origin_index = pre_._index;
      }
      this._input = input_;
      this._digest = createIndex(input_);
      this._index = getIndex();
   }

   public IndexedString(String input_) {
      this._input = input_;
      this._digest = createIndex(input_);
      this._index = getIndex();
      this._origin_digest = this._digest;
      this._origin_index = this._index;
   }

   public java.security.MessageDigest createIndex(String input_) {
      try {
         //compute the index from a SHA-1
         this._digest = MessageDigest.getInstance("SHA-1");
         this._digest.reset();
      } catch (NoSuchAlgorithmException ex) {
         Logger.getLogger(IndexedString.class.getName()).log(Level.SEVERE, null, ex);
      }
      this._digest.update(this._input.getBytes());
      return _digest;
   }

   public String getIndex() {
      //recompute the index
      if (this._index == null || this._index.length() <= 0) {
         this._index = (new BigInteger(1, this._digest.digest())).toString(16);
      }
      return this._index;
   }

   @Override
   public String toString() {
      return this._input;
   }

   public String originIndex() {
      return this._origin_index;
   }

   public int compareTo(IndexedString is_) {
      //return this._input.toUpperCase().compareTo(is_.toString().toUpperCase());
      Collator coll = Collator.getInstance();
      coll.setStrength(Collator.TERTIARY);
      return coll.compare(this._input, is_.toString());
   }

   public boolean valid() throws java.util.regex.PatternSyntaxException {
      return this._input.matches("^[A-Za-z ]+$");
      //return true;
   }

   private java.lang.String _input;
   private java.lang.String _index;
   private java.lang.String _origin_index;
   private java.security.MessageDigest _digest;
   private java.security.MessageDigest _origin_digest;
}
 