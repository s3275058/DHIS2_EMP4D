package org.hisp.dhis.epm4d.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.certificate.Certificate;
import org.hisp.dhis.certificate.CertificateService;
import org.hisp.dhis.user.CurrentUserService;
import org.hisp.dhis.user.User;
import org.hisp.dhis.user.UserService;
import java.util.Collection;

public class DeleteCertificate implements Action {

    /* DEPENDENCIES. */
    UserService userService;
    CurrentUserService currentUserService;
    CertificateService certificateService;

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
    private int idC;

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    /* GENERAL INFORMATION. */
    private User user;
    private String id;
    private Certificate certificate;
    private Collection<Certificate> certificates;

    public Collection<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Collection<Certificate> certificates) {
        this.certificates = certificates;
    }

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

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
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

        certificate = certificateService.get(idC);

        System.out.println(certificate.getCandidate());
        System.out.println("Delete id: " + idC);
        System.out.println("Test Delete id: " + certificate.getId());
        certificateService.delete(idC);

        certificates = certificateService.getAll();
        System.out.println("Size " + certificates.size());

        return SUCCESS;
    }
}
