package edu.iit.sat.itmd4515.msabouri.web;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Milad
 */
@Named
@RequestScoped
public class SearchController extends AbstractController{

    private static final Logger LOG = Logger.getLogger(SearchController.class.getName());
    
    private String keyword;
    
    /**
     *
     * @return
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     *
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
}
