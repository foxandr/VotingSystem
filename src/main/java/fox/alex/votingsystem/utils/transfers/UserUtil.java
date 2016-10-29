package fox.alex.votingsystem.utils.transfers;

import fox.alex.votingsystem.model.Role;
import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.to.UserTo;
import fox.alex.votingsystem.utils.PasswordUtil;
import fox.alex.votingsystem.utils.exception.WrongPasswordException;

/**
 * Created by fox on 05.09.16.
 */
public class UserUtil {
    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getPassword(), user.getEmail());
    }

    public static void updateFromTo(User user, UserTo userTo) throws WrongPasswordException {
        String pass = userTo.getPassword();
        if (pass != null && PasswordUtil.isMatch(pass, user.getPassword())) {
            user.setName(userTo.getName());
            user.setEmail(userTo.getEmail());
            String updpass = userTo.getNewpass();
            if (updpass != null) user.setPassword(updpass);
        } else {
            throw new WrongPasswordException("Wrong password");
        }

    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
