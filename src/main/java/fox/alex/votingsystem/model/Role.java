package fox.alex.votingsystem.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by fox on 11.08.16.
 */

public enum Role implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}