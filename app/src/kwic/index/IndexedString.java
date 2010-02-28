package kwic.index;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Shifted input. Keeps track of it's own index and the originating input.
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

   /**
    * Constructor, takes a string input_ and stores that as _input
    * builds a digest off input and sets its index
    * @param input_ a String to be encapsulated in the IndexedString
    */
   public IndexedString(String input_) {
      this._input = input_;
      this._digest = createIndex(input_);
      this._index = getIndex();
      this._origin_digest = this._digest;
      this._origin_index = this._index;
   }

   //use with care, digests are unset
   public IndexedString(String input_, String index_) {
      this._input = input_;
      this._index = index_;
      this._origin_index = this._index;
   }

   /**
    * Method to generate a SHA-1 digest for the input String
    * @param input_ the String to generate an index for
    * @return _digest result of input
    */
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

   /**
    * Method to get the Index of the IndexedString
    * @return index string, if null then calculates the index from the digest
    */
   public String getIndex() {
      //recompute the index
      if (this._index == null || this._index.length() <= 0) {
         this._index = (new BigInteger(1, this._digest.digest())).toString(16);
      }
      return this._index;
   }

   /**
    * Returns the original input string
    * @return input the string the IndexedString was constructed with
    */
   @Override
   public String toString() {
      return this._input;
   }

   /**
    * Returns the index of the parent IndexedString if there was one
    * @return _origin_index a string with the index of the parent
    */
   public String originIndex() {
      return this._origin_index;
   }

   /**
    * Compares two strings alphabetically using the Tertiary collator
    *
    * @param is_ IndexedString to be compared against
    * @return int the value 0 if the argument string is equal to this string;
    * a value less than 0 if this string is less than the
    * string argument; and a value greater than 0 if this string is greater.
    */
   public int compareTo(IndexedString is_) {
      try {
         //create the collator if it hasn't been done yet
         if(null == this._collator) {
            this._collator = new EnglishCollator();
         }
         return this._collator.compare(this._input, is_.toString());
      } catch (ParseException ex) {
         Logger.getLogger(IndexedString.class.getName()).log(Level.SEVERE, null, ex);
      }
      return -1;
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
   private EnglishCollator _collator = null;
}
 