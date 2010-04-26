package kwic.backend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.regex.PatternSyntaxException;

/**
 * Responsible for reading the input from the File.
 */
public class InputReader extends LineNumberReader {

   public InputReader(Reader in) {
      super(in);
   }

   public InputReader(Reader in, int size) {
      super(in, size);
   }

   /**
    * Constructor for reading from a text directly.
    * @param Text to break up by newlines.
    * @throws FileNotFoundException
    */
   public InputReader(String text_) throws FileNotFoundException {
      this(new BufferedReader(new StringReader(text_)));
   }

   /**
    * Iterator for the InputReader, returns line by line until it reads null.
    * Validation is not performed on the input, as it may be the version of input
    * that does not have a URL on the end.
    * @return IndexedString containing the next line of the file
    * @throws java.util.regex.PatternSyntaxException
    */
   public IndexedString next() throws java.util.regex.PatternSyntaxException {
      IndexedString ret = null;
      String line = "";
      try {
         line = this.readLine();
         if(line != null) {
            ret = new IndexedString(line);
         }
      } catch (IOException ex) {
         ex.printStackTrace();
      }
      return ret;
   }
}
