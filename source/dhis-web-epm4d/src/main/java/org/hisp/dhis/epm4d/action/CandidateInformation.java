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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateInformation implements Action {

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
    private Candidate candidate;
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

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
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

        candidate = candidateService.get(idC);
        attributes = new ArrayList<Attribute>(attributeService.getCandidateAttributes());
        attributeValues = AttributeUtils.getAttributeValueMap(candidate.getAttributeValues());

        return SUCCESS;
    }
}
