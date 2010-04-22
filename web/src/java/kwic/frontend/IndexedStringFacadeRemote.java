package kwic.frontend;

import java.util.List;
import javax.ejb.Remote;
import kwic.backend.IndexedString;

/**
 *
 */
@Remote
public interface IndexedStringFacadeRemote {

    void create(IndexedString indexedString);

    void edit(IndexedString indexedString);

    void remove(IndexedString indexedString);

    IndexedString find(Object id);

    List<IndexedString> findAll();

    List<IndexedString> findRange(int[] range);

    int count();

}
