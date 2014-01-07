package org.hisp.dhis.epm4d.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.attribute.Attribute;
import org.hisp.dhis.attribute.AttributeService;
import org.hisp.dhis.college.College;
import org.hisp.dhis.college.CollegeService;
import org.hisp.dhis.system.util.AttributeUtils;
import org.hisp.dhis.user.CurrentUserService;
import org.hisp.dhis.user.User;
import org.hisp.dhis.user.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollegeInformation implements Action {

    /* DEPENDENCIES. */
    UserService userService;
    CurrentUserService currentUserService;
    CollegeService collegeService;
    AttributeService attributeService;

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

    /* INPUT/OUTPUT */
    private int idC;

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    /* GENERAL INFORMATION. */
    private User user;
    String id;
    private College college;
    private List<Attribute> attributes;
    public Map<Integer, String> attributeValues = new HashMap<Integer, String>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Map<Integer, String> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(Map<Integer, String> attributeValues) {
        this.attributeValues = attributeValues;
    }

    @Override
    public String execute() throws Exception {
        if ( id != null )
        {
            user = userService.getUser( id );
        }
        else
        {
            user = currentUserService.getCurrentUser();
        }

        college = collegeService.get(idC);
        attributes = new ArrayList<Attribute>(attributeService.getCollegeAttributes());
        attributeValues = AttributeUtils.getAttributeValueMap(college.getAttributeValues());

        return SUCCESS;
    }
}
