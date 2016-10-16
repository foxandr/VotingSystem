package fox.alex.votingsystem;

import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.to.UserTo;
import fox.alex.votingsystem.utils.transfers.UserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * Created by fox on 16.10.16.
 */
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getName(), user.getPassword(), user.isActive(), true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }

    public static AuthorizedUser safeGet(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return null;
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthorizedUser) return (AuthorizedUser) principal;
        return null;
    }

    public static AuthorizedUser get(){
        AuthorizedUser authorizedUser = safeGet();
        Objects.requireNonNull(authorizedUser, "No authorized user");
        return authorizedUser;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    public void setUserTo(UserTo userTo) {
        this.userTo = userTo;
    }

    public static int id(){
        return get().userTo.getId();
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}
