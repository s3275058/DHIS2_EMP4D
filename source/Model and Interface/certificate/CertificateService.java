package org.hisp.dhis.certificate;


import java.util.Collection;

public interface CertificateService {

    int add(Certificate certificate);
    void update(Certificate certificate);
    void delete(int id);
    Certificate get(int id);
    Collection<Certificate> getAll();
}
