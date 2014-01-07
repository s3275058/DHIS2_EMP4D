package org.hisp.dhis.college;

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

@JacksonXmlRootElement( localName = "college", namespace = DxfNamespaces.DXF_2_0 )
public class College extends BaseIdentifiableObject {

    /**
     * College attributes
     */
    private int id;
    private String coid;
    private String name;
    private String address;
    private String orgunit;
    private String phone;
    private String trainingsystem;
    private Date establishdate;

    /**
     * Set dynamic college attributes
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
            College college = (College)other;

            coid = college.getCoid() == null ? coid : college.getCoid();
            name = college.getName() == null ? name : college.getName();
            address = college.getAddress() == null ? address : college.getAddress();
            orgunit = college.getOrgunit() == null ? orgunit : college.getOrgunit();
            phone = college.getPhone() == null ? phone : college.getPhone();
            trainingsystem = college.getTrainingsystem() == null ? trainingsystem : college.getTrainingsystem();
            establishdate = college.getEstablishdate() == null ? establishdate : college.getEstablishdate();

            attributeValues.clear();
            attributeValues.addAll( college.getAttributeValues() );
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getEstablishdate() {
        return establishdate;
    }

    public void setEstablishdate(Date establishdate) {
        this.establishdate = establishdate;
    }
}
