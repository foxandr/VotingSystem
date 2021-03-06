package fox.alex.votingsystem.utils.exception;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class ErrorInfo {
    private final String url;
    private final String cause;
    private final String[] details;

    public ErrorInfo(CharSequence url, Throwable ex) {
        this(url, ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }

    public ErrorInfo(CharSequence requestURL, String cause, String... details) {
        this.url = requestURL.toString();
        this.cause = cause;
        this.details = details;
    }

    public String getUrl() {
        return url;
    }

    public String getCause() {
        return cause;
    }

    public String[] getDetails() {
        return details;
    }
}
