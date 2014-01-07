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

import java.text.SimpleDateFormat;
import java.util.*;

public class UpdateCollege implements Action {

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
    private String coid;
    private String name;
    private String address;
    private String orgunit;
    private String phone;
    private String trainingsystem;
    private String establishdate;
    private int idC;

    public String getCoid() {
        return coid;
    }

    public void setCoid(String coid) {
        this.coid = coid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrgunit() {
        return orgunit;
    }

    public void setOrgunit(String orgunit) {
        this.orgunit = orgunit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrainingsystem() {
        return trainingsystem;
    }

    public void setTrainingsystem(String trainingsystem) {
        this.trainingsystem = trainingsystem;
    }

    public String getEstablishdate() {
        return establishdate;
    }

    public void setEstablishdate(String establishdate) {
        this.establishdate = establishdate;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    /* GENERAL INFORMATION. */
    private String id;
    private User user;
    private List<Attribute> attributes;
    public Map<Integer, String> attributeValues = new HashMap<Integer, String>();
    private List<String> jsonAttributeValues;

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

    public List<String> getJsonAttributeValues() {
        return jsonAttributeValues;
    }

    public void setJsonAttributeValues(List<String> jsonAttributeValues) {
        this.jsonAttributeValues = jsonAttributeValues;
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

        //Format date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date newEstablishDate;
        newEstablishDate = sdf.parse(establishdate);

        College college = collegeService.get(idC);
        college.setCoid(coid);
        college.setName(name);
        college.setAddress(address);
        college.setOrgunit(orgunit);
        college.setPhone(phone);
        college.setTrainingsystem(trainingsystem);
        college.setEstablishdate(newEstablishDate);

        attributes = new ArrayList<Attribute>(attributeService.getCollegeAttributes());
        attributeValues = AttributeUtils.getAttributeValueMap(college.getAttributeValues());

        if ( jsonAttributeValues != null )
        {
            AttributeUtils.updateAttributeValuesFromJson(college.getAttributeValues(), jsonAttributeValues,
                    attributeService);
        }

        collegeService.update(college);

        return SUCCESS;
    }
}
