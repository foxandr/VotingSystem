package fox.alex.votingsystem.testData;

import fox.alex.votingsystem.matcher.ModelMatcher;
import fox.alex.votingsystem.model.Role;
import fox.alex.votingsystem.model.User;
import fox.alex.votingsystem.utils.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static fox.alex.votingsystem.model.BaseEntity.START_INDEX;

/**
 * Created by fox on 24.08.16.
 */
public class UserTestData {
    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);

    public static final int ADMIN_ID = START_INDEX;
    public static final int USER_ID = START_INDEX + 1;
    public static final int USER_ID2 = START_INDEX + 2;

    public static final User USER1 = new User(USER_ID, "User1", "user1@votes.by", "simpleuser", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@votes.by", "megaadmin", Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User USER2 = new User(USER_ID2, "User2", "2thUser@votes.by", "user123456", Role.ROLE_USER);

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>(User.class,
            (expected, actual) -> {
                if (expected == actual) {
                    return true;
                }
                boolean cmp = comparePassword(expected.getPassword(), actual.getPassword())
                        && Objects.equals(expected.getId(), actual.getId())
                        && Objects.equals(expected.getName(), actual.getName())
                        && Objects.equals(expected.getEmail(), actual.getEmail())
                        && Objects.equals(expected.getRoles(), actual.getRoles());
                return cmp;
            }
    );


    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }
}
