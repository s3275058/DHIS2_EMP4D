package org.hisp.dhis.epm4d.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.attribute.Attribute;
import org.hisp.dhis.attribute.AttributeService;
import org.hisp.dhis.certificate.Certificate;
import org.hisp.dhis.certificate.CertificateService;
import org.hisp.dhis.user.CurrentUserService;
import org.hisp.dhis.user.UserService;
import org.hisp.dhis.user.User;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test implements Action {

    /* DEPENDENCIES. */
    private UserService userService;
    private CurrentUserService currentUserService;
    private CertificateService certificateService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CertificateService getCertificateService() {
        return certificateService;
    }

    public void setCertificateService(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    public CurrentUserService getCurrentUserService() {
        return currentUserService;
    }

    public void setCurrentUserService(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    /* INPUT / OUTPUT. */
    private Collection<Certificate> certificates;

    public Collection<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Collection<Certificate> certificates) {
        this.certificates = certificates;
    }

    /* GENERAL INFORMATION. */
    private String id;
    private User user;

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

        certificates = certificateService.getAll();

        return SUCCESS;
    }
}
