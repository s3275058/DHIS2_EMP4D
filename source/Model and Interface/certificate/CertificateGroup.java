package org.hisp.dhis.certificate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hisp.dhis.attribute.AttributeValue;
import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.common.annotation.Scanned;

import java.util.HashSet;
import java.util.Set;

@JacksonXmlRootElement(localName = "certificateGroup", namespace = DxfNamespaces.DXF_2_0)
public class CertificateGroup extends BaseIdentifiableObject{

    @Scanned
    private Set<Certificate> members = new HashSet<Certificate>();

    private Set<AttributeValue> attributeValues = new HashSet<AttributeValue>();

    public CertificateGroup(){}

    public CertificateGroup(String name)
    {
        this.name = name;
    }

    public CertificateGroup(String name, Set<Certificate> member)
    {
        this.name = name;
        this.members = member;
    }
    /*
    public void addCertificate(Certificate certificate)
    {
        members.add(certificate);
        certificate.getGroups().add(this);
    }

    public void removeCertificate( Certificate certificate )
    {
        members.remove( certificate );
        user.getGroups().remove( this );
    }

    public void updateCertificate( Set<Certificate> updates )
    {
        for ( Certificate certificate : new HashSet<Certificate>( members ) )
        {
            if ( !updates.contains( certificate ) )
            {
                removeCertificate( certificate );
            }
        }

        for ( Certificate certificate : updates )
        {
            addCertificate( certificate );
        }
    }
    /*
    @JsonIgnore
    public Certificate getCertificate()
    {
        return certificate;
    }

    @JsonIgnore
    public void setCertificate( Certificate certificate )
    {
        this.certificate = certificate;
    }
    */
    /*
    public Set<Certificate> getMembers() {
        return members;
    }

    public void setMembers(Set<Certificate> members) {
        this.members = members;
    }

    public Set<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(Set<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    */
}
