package opinion;

import exceptions.BadEntryException;

public class Review {
    // Class attribute
    protected String content;
    protected float mark;
    protected String addedBy;

    /**
     * Conctructor for the Review class
     * Throw BadEntryExcpetion if mark does not match the requirement, if addedBy or content is not specified
     *
     * @param content
     * @param addedBy
     * @param mark
     * @throws BadEntryException
     */
    public Review(String content, String addedBy, float mark) throws BadEntryException {
        if ((mark > 5.0) || mark < 0) {
            throw new BadEntryException("La note doit être supérieure à 0 et inférieure ou égale à 5.0");
        }
        if (addedBy == null) {
            throw new BadEntryException("L'auteur de l'avis n'a pas été renseigné");
        }
        if (content == null || content.replace(" ", "").isEmpty()) {
            throw new BadEntryException("L'avis n'a pas été renseigné");
        }
        this.content = content;
        this.mark = mark;
        this.addedBy = addedBy;
    }

    /**
     * toString function for Review class, return a fromatted string containing all attrubutes of the class
     *
     * @return String
     */
    public String toString() {
        String temp = "";
        temp += "Contenu: " + this.content + ", Note: " + this.mark + ", Ajouté par: " + this.addedBy;
        return temp;
    }
}
