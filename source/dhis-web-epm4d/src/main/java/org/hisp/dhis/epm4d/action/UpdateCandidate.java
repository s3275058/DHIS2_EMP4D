package org.hisp.dhis.epm4d.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.attribute.Attribute;
import org.hisp.dhis.attribute.AttributeService;
import org.hisp.dhis.candidate.Candidate;
import org.hisp.dhis.candidate.CandidateService;
import org.hisp.dhis.system.util.AttributeUtils;
import org.hisp.dhis.user.CurrentUserService;
import org.hisp.dhis.user.User;
import org.hisp.dhis.user.UserService;

import java.text.SimpleDateFormat;
import java.util.*;

public class UpdateCandidate implements Action {

    /* DEPENDENCIES. */
    UserService userService;
    CurrentUserService currentUserService;
    CandidateService candidateService;
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

    public CandidateService getCandidateService() {
        return candidateService;
    }

    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    public AttributeService getAttributeService() {
        return attributeService;
    }

    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    /* INPUT/OUTPUT */
    private String sid;
    private String surname;
    private String lastname;
    private String dob;
    private String address;
    private String phone;
    private String college;
    private String nationality;
    private String email;
    private String gender;
    private int idC;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        Date newdob;
        newdob = sdf.parse(dob);

        Candidate candidate = candidateService.get(idC);
        candidate.setSid(sid);
        candidate.setSurname(surname);
        candidate.setLastname(lastname);
        candidate.setDob(newdob);
        candidate.setAddress(address);
        candidate.setPhone(phone);
        candidate.setCollege(college);
        candidate.setNationality(nationality);
        candidate.setEmail(email);
        candidate.setGender(gender);

        attributes = new ArrayList<Attribute>(attributeService.getCandidateAttributes());
        attributeValues = AttributeUtils.getAttributeValueMap(candidate.getAttributeValues());

        if ( jsonAttributeValues != null )
        {
            AttributeUtils.updateAttributeValuesFromJson(candidate.getAttributeValues(), jsonAttributeValues,
                    attributeService);
        }

        candidateService.update(candidate);

        return SUCCESS;
    }
}
