package exceptions;

/**
 * <i>NotReviewException</i> is raised when some item can not be found in the
 * <i>ISocialNetworkPremium</i>
 */
public class NotReviewException extends Exception {

    public NotReviewException(String message) {
        super(message);
    }
}
