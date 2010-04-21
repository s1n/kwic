/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kwic.frontend;

import java.util.List;
import javax.ejb.Remote;
import kwic.backend.IndexedString;

/**
 *
 * @author s1n
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
