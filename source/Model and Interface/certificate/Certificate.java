package org.hisp.dhis.certificate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hisp.dhis.attribute.AttributeValue;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.common.IdentifiableObject;
import org.hisp.dhis.common.annotation.Scanned;
import org.hisp.dhis.common.view.DetailedView;
import org.hisp.dhis.common.view.ExportView;
import org.hisp.dhis.organisationunit.OrganisationUnit;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JacksonXmlRootElement( localName = "certificate", namespace = DxfNamespaces.DXF_2_0 )
public class Certificate extends BaseIdentifiableObject {

    /**
     * Certificate attributes
     */
    private int id;
    private String Cid;
    private String orgunit;
    private String college;
    private String candidate;
    private Date dob;
    private Date yearOfGraduation;
    private String degreeClassification;
    private String trainingSystem;
    private String major;

    /**
     * Set dynamic certificate attributes
     */
    private Set<AttributeValue> attributeValues = new HashSet<AttributeValue>();


    /**
     * Organisation units certificate should belong to
     */
    @Scanned
    private Set<OrganisationUnit> organisationUnits = new HashSet<OrganisationUnit>();

    //private Set<CertificateGroup> groups = new HashSet<CertificateGroup>();

    /*
    public Certificate(){}

    public Certificate(String Cid, String orgunit, String college, String candidate, Date dob,
                       Date yearOfGraduation, String degreeClassification, String trainingSystem, String major) {

        this.Cid = Cid;
        this.orgunit = orgunit;
        this.college = college;
        this.candidate = candidate;
        this.dob = dob;
        this.yearOfGraduation = yearOfGraduation;
        this.degreeClassification = degreeClassification;
        this.trainingSystem = trainingSystem;
        this.major = major;
    }
    */

    @JsonProperty( value = "attributes" )
    @JsonView( { DetailedView.class, ExportView.class } )
    @JacksonXmlElementWrapper( localName = "attributes", namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "attribute", namespace = DxfNamespaces.DXF_2_0 )
    public Set<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(Set<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }
    /*
    @JsonProperty
    @JsonSerialize( contentAs = BaseIdentifiableObject.class )
    @JsonView( { DetailedView.class } )
    @JacksonXmlElementWrapper( localName = "certificateGroups", namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "certificateGroup", namespace = DxfNamespaces.DXF_2_0 )
    public Set<CertificateGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<CertificateGroup> groups) {
        this.groups = groups;
    }
    */
    @JsonProperty
    @JsonSerialize( contentAs = BaseIdentifiableObject.class )
    @JsonView( { DetailedView.class, ExportView.class } )
    @JacksonXmlElementWrapper( localName = "organisationUnits", namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "organisationUnit", namespace = DxfNamespaces.DXF_2_0 )
    public Collection<OrganisationUnit> getOrganisationUnits()
    {
        return organisationUnits;
    }

    public void setOrganisationUnits( Set<OrganisationUnit> organisationUnits )
    {
        this.organisationUnits = organisationUnits;
    }

    @Override
    public void mergeWith( IdentifiableObject other )
    {
        if(other.getClass().isInstance(this))
        {
            Certificate certificate = (Certificate)other;

            Cid = certificate.getCid() == null ? Cid : certificate.getCid();
            orgunit = certificate.getOrgunit() == null ? orgunit : certificate.getOrgunit();
            college = certificate.getCollege() == null ? college : certificate.getCollege();
            candidate = certificate.getCandidate() == null ? candidate : certificate.getCandidate();
            dob = certificate.getDob() == null ? dob : certificate.getDob();
            yearOfGraduation = certificate.getYearOfGraduation() == null ? yearOfGraduation : certificate.getYearOfGraduation();
            degreeClassification = certificate.getDegreeClassification() == null ? degreeClassification : certificate.getDegreeClassification();
            trainingSystem = certificate.getTrainingSystem() == null ? trainingSystem : certificate.getTrainingSystem();
            major = certificate.getMajor() == null ? major : certificate.getMajor();

            attributeValues.clear();
            attributeValues.addAll( certificate.getAttributeValues() );

            organisationUnits.clear();
            organisationUnits.addAll( certificate.getOrganisationUnits());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setYearOfGraduation(Date yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    public String getDegreeClassification() {
        return degreeClassification;
    }

    public void setDegreeClassification(String degreeClassification) {
        this.degreeClassification = degreeClassification;
    }

    public String getTrainingSystem() {
        return trainingSystem;
    }

    public void setTrainingSystem(String trainingSystem) {
        this.trainingSystem = trainingSystem;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
