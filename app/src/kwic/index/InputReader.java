package kwic.index;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    * Constructor for reading from a local file
    * @param file_ String location of the file
    * @throws FileNotFoundException
    */
   public InputReader(String file_) throws FileNotFoundException {
      this(new FileReader(new File(file_)));
   }

   /**
    * Iterator for the InputReader, returns line by line until it reads null
    * @return IndexedString containing the next line of the file
    * @throws java.util.regex.PatternSyntaxException
    */
   public IndexedString next() throws java.util.regex.PatternSyntaxException {
      IndexedString ret = null;
      String line = "";
      try {
         line = this.readLine();
         if (line != null) {
            ret = new IndexedString(line);
            if (!ret.valid()) {
               throw new PatternSyntaxException("Invalid format line: " + line, "alphanum", this.getLineNumber());
            }
         }
      } catch (IOException ex) {
         Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
         ex.printStackTrace();
      }
      return ret;
   }
}
