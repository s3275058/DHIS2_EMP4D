package org.hisp.dhis.epm4d.action;

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.user.CurrentUserService;
import org.hisp.dhis.user.User;
import org.hisp.dhis.user.UserService;

public class NoAction implements Action {

    private UserService userService;
    private CurrentUserService currentUserService;

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

        System.out.println("id index "+id);

        if ( id != null )
        {
            user = userService.getUser( id );
        }
        else
        {
            user = currentUserService.getCurrentUser();
            System.out.println("id index "+ user.getUsername());
        }

        return SUCCESS;
    }
}

