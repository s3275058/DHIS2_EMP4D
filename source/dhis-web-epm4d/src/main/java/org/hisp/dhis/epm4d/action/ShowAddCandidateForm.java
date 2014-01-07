package org.hisp.dhis.epm4d.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.attribute.Attribute;
import org.hisp.dhis.attribute.AttributeService;
import org.hisp.dhis.college.College;
import org.hisp.dhis.college.CollegeService;
import org.hisp.dhis.user.CurrentUserService;
import org.hisp.dhis.user.User;
import org.hisp.dhis.user.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShowAddCandidateForm implements Action {

    /* DEPENDENCIES. */
    private UserService userService;
    private CurrentUserService currentUserService;
    private AttributeService attributeService;
    private CollegeService collegeService;

    public CollegeService getCollegeService() {
        return collegeService;
    }

    public void setCollegeService(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    public AttributeService getAttributeService() {
        return attributeService;
    }

    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CurrentUserService getCurrentUserService() {
        return currentUserService;
    }

    public void setCurrentUserService(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    /* INPUT / OUTPUT. */
    private Collection<College> colleges;

    public Collection<College> getColleges() {
        return colleges;
    }

    public void setColleges(Collection<College> colleges) {
        this.colleges = colleges;
    }

    /* GENERAL INFORMATION. */
    private String id;
    private User user;
    private List<Attribute> attributes;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() throws Exception {

        attributes = new ArrayList<Attribute>(attributeService.getCandidateAttributes());

        if ( id != null )
        {
            user = userService.getUser( id );
        }
        else
        {
            user = currentUserService.getCurrentUser();
        }

        colleges = collegeService.getAll();
        return SUCCESS;
    }
}
