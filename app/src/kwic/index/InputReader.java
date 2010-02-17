package kwic.index;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public InputReader(String file_) throws FileNotFoundException {
        this(new FileReader(new File(file_)));
    }

    public IndexedString next() {
        IndexedString ret = null;
        String line = "";
        try {
            line = this.readLine();
            if(line != null) {
                ret = new IndexedString(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}