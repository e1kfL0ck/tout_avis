package opinion;

import exceptions.BadEntryException;

import java.util.LinkedList;

public class Review {
    // Class attribute
    protected String content;
    protected float mark;
    protected Members addedBy;
    protected LinkedList<Feedback> feedbacks;

    /**
     * Conctructor for the Review class
     * Throw BadEntryExcpetion if mark does not match the requirement, if addedBy or content is not specified
     *
     * @param content
     * @param addedBy
     * @param mark
     * @throws BadEntryException
     */
    public Review(String content, Members addedBy, float mark) throws BadEntryException {
        if ((mark > 5.0) || mark < 0) {
            throw new BadEntryException("La note doit être supérieure à 0 et inférieure ou égale à 5.0");
        }
        if (addedBy == null) {
            throw new BadEntryException("L'auteur de l'avis n'a pas été renseigné");
        }
        if (content == null) {
            throw new BadEntryException("L'avis n'a pas été renseigné");
        }
        this.content = content;
        this.mark = mark;
        this.addedBy = addedBy;
        this.feedbacks = new LinkedList<>();
    }

    /**
     * Method to add a new feedback to the review
     *
     * @param addedBy
     * @param mark
     * @throws BadEntryException
     */
    public float addFeedback(String addedBy, float mark) throws BadEntryException {
        if (addedBy == null) {
            throw new BadEntryException("Added by can't be null");
        }
        int index = 0;
        //Mean value of the possible mark, used if no feedback is found
        //2.5 will create a coefficeint of 1, not impacting the karma. This coeff computed in computedCoeff method of Members.
        float oldMark = 2.5f; 
        for (Feedback f : feedbacks) {
            if (f.addedBy.equals(addedBy)) {
                oldMark = f.mark;
                feedbacks.set(index, new Feedback(addedBy, mark));
                return oldMark; //Feedback replaced return oldMark
            }
            index++;
        }
        feedbacks.add(new Feedback(addedBy, mark));
        return oldMark;
    }

    /**
     * toString function for Review class, return a fromatted string containing all attrubutes of the class
     *
     * @return String
     */
    public String toString() {
        String temp = "";
        temp += "Contenu: " + this.content + ", Note: " + this.mark + ", Ajouté par: " + this.addedBy.getLogin();
        if (!feedbacks.isEmpty()) {
            temp += "\nFeedbacks: ";
            for (Feedback f : feedbacks) {
                temp += "\n    "+ f.toString();
            }
        }
        return temp;
    }
}
