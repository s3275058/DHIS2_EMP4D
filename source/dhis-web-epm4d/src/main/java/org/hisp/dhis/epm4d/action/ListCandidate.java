package org.hisp.dhis.epm4d.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.candidate.Candidate;
import org.hisp.dhis.candidate.CandidateService;
import org.hisp.dhis.user.CurrentUserService;
import org.hisp.dhis.user.User;
import org.hisp.dhis.user.UserService;

import java.util.Collection;

public class ListCandidate implements Action {

    /* DEPENDENCIES. */
    private UserService userService;
    private CurrentUserService currentUserService;
    private CandidateService candidateService;

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

    /* INPUT / OUTPUT. */
    private Collection<Candidate> candidates;

    public Collection<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Collection<Candidate> candidates) {
        this.candidates = candidates;
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

        candidates = candidateService.getAll();

        return SUCCESS;
    }
}
