package org.hisp.dhis.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hisp.dhis.attribute.AttributeValue;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.common.IdentifiableObject;
import org.hisp.dhis.common.view.DetailedView;
import org.hisp.dhis.common.view.ExportView;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JacksonXmlRootElement( localName = "candidate", namespace = DxfNamespaces.DXF_2_0 )
public class Candidate extends BaseIdentifiableObject {

    /**
     * Candidate attributes
     */
    private int id;
    private String sid;
    private String surname;
    private String lastname;
    private Date dob;
    private String address;
    private String phone;
    private String college;
    private String nationality;
    private String email;
    private String gender;

    /**
     * Set dynamic candidate attributes
     */
    private Set<AttributeValue> attributeValues = new HashSet<AttributeValue>();

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

    @Override
    public void mergeWith( IdentifiableObject other )
    {
        if(other.getClass().isInstance(this))
        {
            Candidate candidate = (Candidate)other;

            sid = candidate.getSid() == null ? sid : candidate.getSid();
            surname = candidate.getSurname() == null ? surname : candidate.getSurname();
            lastname = candidate.getLastname() == null ? lastname : candidate.getLastname();
            dob = candidate.getDob() == null ? dob : candidate.getDob();
            address = candidate.getAddress() == null ? address : candidate.getAddress();
            phone = candidate.getPhone() == null ? phone : candidate.getPhone();
            college = candidate.getCollege() == null ? college : candidate.getCollege();
            nationality = candidate.getNationality() == null ? nationality : candidate.getNationality();
            email = candidate.getEmail() == null ? email : candidate.getEmail();
            gender = candidate.getGender() == null ? gender : candidate.getGender();

            attributeValues.clear();
            attributeValues.addAll( candidate.getAttributeValues() );
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
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
}
