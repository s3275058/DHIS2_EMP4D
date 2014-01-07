package org.hisp.dhis.epm4d.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.attribute.Attribute;
import org.hisp.dhis.attribute.AttributeService;
import org.hisp.dhis.candidate.Candidate;
import org.hisp.dhis.candidate.CandidateService;
import org.hisp.dhis.certificate.Certificate;
import org.hisp.dhis.certificate.CertificateService;
import org.hisp.dhis.college.College;
import org.hisp.dhis.college.CollegeService;
import org.hisp.dhis.system.util.AttributeUtils;
import org.hisp.dhis.user.CurrentUserService;
import org.hisp.dhis.user.User;
import org.hisp.dhis.user.UserService;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AddCertificate implements Action {

    /* DEPENDENCIES. */
    UserService userService;
    CurrentUserService currentUserService;
    CertificateService certificateService;
    AttributeService attributeService;
    CandidateService candidateService;
    CollegeService collegeService;

    public CandidateService getCandidateService() {
        return candidateService;
    }

    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    public CollegeService getCollegeService() {
        return collegeService;
    }

    public void setCollegeService(CollegeService collegeService) {
        this.collegeService = collegeService;
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
    private String college;
    private String candidate;
    //private String dob;
    private String yog;
    private String classification;
    //private String trainingsystem;
    private String major;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public AttributeService getAttributeService() {
        return attributeService;
    }

    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
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
    */
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
    /*
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

    /* GENERAL INFORMATION. */
    private String id;
    private User user;
    private List<Attribute> attributes;
    private Collection<College> colleges;
    private Collection<Candidate> candidates;

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

    private List<String> jsonAttributeValues;

    public void setJsonAttributeValues( List<String> jsonAttributeValues )
    {
        this.jsonAttributeValues = jsonAttributeValues;
    }

    public List<String> getJsonAttributeValues() {
        return jsonAttributeValues;
    }

    /* HANDLE RESULT. */
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
        //Date newdob;
        //newdob = sdf.parse(dob);

        Date newyog;
        newyog = sdf.parse(yog);

        Certificate certificate = new Certificate();
        certificate.setCid(cid);
        //certificate.setOrgunit(orgunit);
        certificate.setCollege(college);
        certificate.setCandidate(candidate);
        //certificate.setDob(newdob);
        certificate.setYearOfGraduation(newyog);
        certificate.setDegreeClassification(classification);
        certificate.setMajor(major);
        //certificate.setTrainingSystem(trainingsystem);

        candidates = candidateService.getAll();
        colleges = collegeService.getAll();

        Iterator<Candidate> iterator = candidates.iterator();
        while (iterator.hasNext())
        {
            Candidate temp = iterator.next();
            String fullName = temp.getSurname() + " " + temp.getLastname();
            if (fullName.equals(candidate))
            {
                certificate.setDob(temp.getDob());
                break;
            }
        }

        Iterator<College> iterator1 = colleges.iterator();
        while (iterator.hasNext())
        {
            College temp = iterator1.next();
            if (temp.getName().equals(college))
            {
                certificate.setTrainingSystem(temp.getTrainingsystem());
                certificate.setOrgunit(temp.getOrgunit());
                break;
            }
        }


        if ( jsonAttributeValues != null )
        {
            AttributeUtils.updateAttributeValuesFromJson(certificate.getAttributeValues(), jsonAttributeValues,
                    attributeService);
        }

        certificateService.add(certificate);

        return SUCCESS;
    }
}
