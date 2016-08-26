package fox.alex.votingsystem.utils.exception;


/**
 * User: gkislin
 * Date: 14.05.2014
 */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static <T> T checkVotingTime(T object) {
        checkVotingTime(object != null);
        return object;
    }

    public static void checkVotingTime(boolean time){
        if (!time) {
            throw new VotingException("Time for voting expired. Please try tommorow.");
        }
    }
}
