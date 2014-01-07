package org.hisp.dhis.epm4d.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.attribute.Attribute;
import org.hisp.dhis.attribute.AttributeService;
import org.hisp.dhis.certificate.Certificate;
import org.hisp.dhis.certificate.CertificateService;
import org.hisp.dhis.system.util.AttributeUtils;
import org.hisp.dhis.user.CurrentUserService;
import org.hisp.dhis.user.User;
import org.hisp.dhis.user.UserService;
import java.text.SimpleDateFormat;

import java.util.*;

public class UpdateCertificate implements Action {

    /* DEPENDENCIES. */
    UserService userService;
    CurrentUserService currentUserService;
    CertificateService certificateService;
    AttributeService attributeService;

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

    public CertificateService getCertificateService() {
        return certificateService;
    }

    public void setCertificateService(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /* INPUT/OUTPUT */
    private String cid;
    //private String orgunit;
    //private String college;
    //private String candidate;
    //private String dob;
    private String yog;
    private String classification;
    //private String trainingsystem;
    private String major;
    private int idC;
    private Certificate certificate;

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    /*
    public String getOrgunit() {
        return orgunit;
    }

    public void setOrgunit(String orgunit) {
        this.orgunit = orgunit;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    */

    public String getYog() {
        return yog;
    }

    public void setYog(String yog) {
        this.yog = yog;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
    /*
    public String getTrainingsystem() {
        return trainingsystem;
    }

    public void setTrainingsystem(String trainingsystem) {
        this.trainingsystem = trainingsystem;
    }
    */
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    /* GENERAL INFORMATION. */
    private String id;
    private User user;
    private List<Attribute> attributes;


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

    public Map<Integer, String> attributeValues = new HashMap<Integer, String>();

    public Map<Integer, String> getAttributeValues()
    {
        return attributeValues;
    }

    private List<String> jsonAttributeValues;

    public void setJsonAttributeValues( List<String> jsonAttributeValues )
    {
        this.jsonAttributeValues = jsonAttributeValues;
    }

    public List<String> getJsonAttributeValues() {
        return jsonAttributeValues;
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

        System.out.println("idC from update "+idC);
        certificate = certificateService.get(idC);

        //Format date
        //System.out.println("Dob: "+dob);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        //Date newdob;
        //newdob = sdf.parse(dob);
        //System.out.println("New dob: "+newdob);


        Date newyog;
        newyog = sdf.parse(yog);
        System.out.println("yog new: "+yog);

        //System.out.println("test orgunit "+orgunit);

        certificate.setCid(cid);
        //certificate.setOrgunit(orgunit);
        //certificate.setCollege(college);
        //certificate.setCandidate(candidate);
        //certificate.setDob(newdob);
        certificate.setYearOfGraduation(newyog);
        certificate.setDegreeClassification(classification);
        certificate.setMajor(major);
        //certificate.setTrainingSystem(trainingsystem);

        attributes = new ArrayList<Attribute>(attributeService.getCertificateAttributes());
        attributeValues = AttributeUtils.getAttributeValueMap(certificate.getAttributeValues());

        if ( jsonAttributeValues != null )
        {
            AttributeUtils.updateAttributeValuesFromJson(certificate.getAttributeValues(), jsonAttributeValues,
                    attributeService);
        }

        certificateService.update(certificate);

        return SUCCESS;
    }
}
