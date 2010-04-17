package kwic.frontend;

import kwic.backend.IndexedString;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/** 
 *
 */
public class SearchController extends SimpleFormController {

    private SearchService _search;

    public SearchController() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(IndexedString.class);
        setCommandName("search");
        setSuccessView("results");
        setFormView("search");
    }
    
    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {
        IndexedString idx = (IndexedString)command;
        ModelAndView mv = new ModelAndView(getSuccessView());
        mv.addObject("result", getSearchService().search(idx.getInput()));
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