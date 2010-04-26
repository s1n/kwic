package kwic.frontend;

import kwic.backend.IndexedString;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/** 
 *
 */
public class AdminController extends SimpleFormController {

    private SearchService _search;
    
    public AdminController() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(IndexedString.class);
        setCommandName("admin");
        setSuccessView("added");
        setFormView("admin");
    }

    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
        IndexedString idx = (IndexedString) command;
        ModelAndView mv = new ModelAndView(getSuccessView());
        mv.addObject("shifts", getSearchService().generateShifts(idx));
        mv.addObject("description", idx.getInput());
        mv.addObject("index", idx.getIndex());
        mv.addObject("url", idx.getURL());
        return mv;
    }

    /**
     * @return the _search
     */
    public SearchService getSearchService() {
        return _search;
    }

    /**
     * @param search the _search to set
     */
    public void setSearchService(SearchService search) {
        this._search = search;
    }
}
