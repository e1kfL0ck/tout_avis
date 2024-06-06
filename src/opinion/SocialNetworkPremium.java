package opinion;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotReviewException;

/**
 * New social network that enables the 'karma', which allow the ponderation of the mark gave to items
 */
public class SocialNetworkPremium extends SocialNetwork implements ISocialNetworkPremium {
    public void reviewOpinion(String login, String password, String itemTitle, String reviewAuthor, float mark) throws BadEntryException,
            NotMemberException, NotReviewException, NotItemException {
        Members loggedMember = findAndLogin(login, password);
        // Search for item matching the specified title
        Item foundItem = null;
        for (Item i : items) {
            if (i.areYou(Item.class, itemTitle)) {
                foundItem = i;
                break;
            }
        }
        if (foundItem == null) {
            throw new NotItemException("L'item spécifié n'existe pas");
        }
        // Search for review matching the provied author (reviewAuthor)
        Review foundReview = null;
        float oldMark = 2.5f;
        for (Review r : foundItem.reviews) {
            if (r.addedBy.getLogin().equals(reviewAuthor)) {
                foundReview = r;
                // Retrieve the old mark if the feedback was replaced by a new one
                oldMark = r.addFeedback(loggedMember.getLogin(), mark);
                break;
            }
        }
        if (foundReview == null) {
            throw new NotReviewException("Review non trouvée");
        }
        for (Members m : members) {
            if (m.areYou(reviewAuthor)) {
                // Update the user karma with the new mark, will remove the oldMark coeff (if first opinion, coeff is 1) and apply the new one.
                m.updateKarma(mark, oldMark);
                break;
            }
        }

    }
}
