package opinion;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotReviewException;

public class SocialNetworkPremium extends SocialNetwork implements ISocialNetworkPremium {
    public void reviewOpinion(String login, String password, String itemTitle, String reviewAuthor, float mark) throws BadEntryException,
            NotMemberException, NotReviewException, NotItemException {
        Members loggedMember = findAndLogin(login, password);
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
        Review foundReview = null;
        for (Review r : foundItem.reviews) {
            if (r.addedBy.getLogin().equals(reviewAuthor)) {
                foundReview = r;
                r.addFeedback(loggedMember.getLogin(), mark);
                break;
            }
        }
        if (foundReview == null) {
            throw new NotReviewException("Review non trouvée");
        }
        for (Members m : members) {
            if (m.areYou(foundReview.addedBy.getLogin())) {
                m.updateKarma(mark);
                break;
            }
        }

    }
}
