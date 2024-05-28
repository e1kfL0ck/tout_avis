package opinion;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotReviewException;

public interface ISocialNetworkPremium extends ISocialNetwork {
    /**
     * Method to review an opinion leaved by a user with a mark. Opinion to review is found with the item title and the author of the opinion
     *
     * @param login
     * @param password
     * @param mark
     * @throws BadEntryException
     * @throws NotMemberException
     * @throws NotItemException
     */
    public void reviewOpinion(String login, String password, String itemTitle, String reviewAuthor, float mark) throws BadEntryException,
            NotMemberException, NotReviewException, NotItemException;
}
