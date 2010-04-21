package kwic.frontend;

import java.util.List;
import javax.ejb.EJB;
import kwic.backend.CircularShifter;
import kwic.backend.IndexList;
import kwic.backend.IndexedString;

/**
 *
 */
public class SearchService {

    public SearchService() {
        _index = new IndexList(new CircularShifter());
        try {
           List<IndexedString> stuff = _indexEJB.findAll();
           System.err.println("size = " + stuff.size());
           if(stuff.size() > 0) {
               for(IndexedString is : stuff) {
                   _index.add(is);
               }
           } else {
              IndexedString parent = null;
              IndexedString is = new IndexedString(parent, "dark side http://foo.com");
              _index.add(is);
              _indexEJB.create(is);
              is = new IndexedString(parent, "yeah yeah yeahs http://yeahs.com");
              _index.add(is);
              _indexEJB.create(is);
              is = new IndexedString(parent, "this project is time consuming http://timewasters.com");
              _index.add(is);
              _indexEJB.create(is);
              is = new IndexedString(parent, "omg wtf bbq http://wtf.com");
              _index.add(is);
              _indexEJB.create(is);
           }
        } catch (Exception e) {
            //drop it like it's hot
        }
    }

    public String search(String for_) {
        StringBuilder sb = new StringBuilder();
        sb.append("Results:<br/><br/>");
        for(IndexedString is : _index.search(for_)) {
            sb.append(is.toString() + "<br/>");
        }
        //return "Searching for \"" + for_ + "\"...";
        return sb.toString();
    }
    private IndexList _index;
    @EJB
    private static IndexedStringFacadeRemote _indexEJB;
}
