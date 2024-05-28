package opinion;

import exceptions.BadEntryException;

import java.util.LinkedList;

public class Item {
    //Class attributes
    protected String title;
    protected String kind;
    protected String addedBy;
    protected LinkedList<Review> reviews;

    /**
     * Constructor for the Item class
     * Throw BadEntryException if the title is not set or more than one caracter or if kind is not set.
     *
     * @param title
     * @param kind
     * @param addedBy
     * @throws BadEntryException
     */
    public Item(String title, String kind, String addedBy) throws BadEntryException {
        if (title == null || title.replace(" ", "").isEmpty()) {
            throw new BadEntryException("Le titre n'a pas été renseigné ou n'a pas la bonne forme (1 caractère minimum)");
        }
        if (kind == null) {
            throw new BadEntryException("La catégorie du média n'a pas été renseignée");
        }
        if (addedBy == null) {
            throw new BadEntryException("L'auteur du post n'a pas été renseigné");
        }
        this.title = title;
        this.kind = kind;
        this.addedBy = addedBy;
        this.reviews = new LinkedList<>();
    }

    /**
     * Are you method for Item class
     * Check of the current object is an instance of the class type passed in argument + the title attribute must match the one passed in argument.
     *
     * @param c
     * @param title
     * @return
     */
    public boolean areYou(Class<?> c, String title) {
        if (title == null || c == null) {
            return false;
        }
        return (c.isInstance(this) && title.equalsIgnoreCase(this.title));
    }

    /**
     * Method to add reviews to the Item, if the user used to post the review already sent a review, the old one will be replaced with the new one
     *
     * @param content
     * @param mark
     * @param addedBy
     * @throws BadEntryException
     */
    public void addReview(String content, float mark, Members addedBy) throws BadEntryException {
        int index = 0;
        for (Review r : reviews) {
            if (r.addedBy.equals(addedBy)) {
                reviews.set(index, new Review(content, addedBy, mark));
                return;
            }
            index++;
        }
        reviews.add(new Review(content, addedBy, mark));
    }

    /**
     * Method to compute the mean of all marks leaved in review of the Item.
     *
     * @return float
     */
    public float getMeanMark() {
        float result = 0;
        for (Review r : reviews) {
            result += r.mark;
        }
        return result / reviews.size();
    }

    /**
     * Method to generate a string containing all the review of the Item.
     *
     * @return String
     */
    public String getReviews() {
        StringBuilder concatenedString = new StringBuilder();
        concatenedString.append("\n------------------------------ Avis ------------------------\n");
        if (!reviews.isEmpty()) {
            for (Review r : reviews) {
                concatenedString.append(r);
                concatenedString.append("\n");
            }
        } else {
            concatenedString.append("Pas d'avis");
        }
        return concatenedString.toString();
    }

    /**
     * Return a string containing all the attributes for the Item instance
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder concatenedString = new StringBuilder();
        concatenedString.append("Titre: ");
        concatenedString.append(this.title);
        concatenedString.append(", Catégorie: ");
        concatenedString.append(this.kind);
        if (!Float.isNaN(getMeanMark())) {
            concatenedString.append(", Note (Moyenne): ");
            concatenedString.append(getMeanMark());
        }
        concatenedString.append(", Ajouté par: ");
        concatenedString.append(this.addedBy);
        return concatenedString.toString();
    }
}
