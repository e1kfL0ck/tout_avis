package opinion;

import exceptions.BadEntryException;

public class Feedback {
    float mark;
    String addedBy;

    /**
     * Constructor for the Feedback class
     * Will throw exception if the mark is not between 0 and 5
     *
     * @param addedBy
     * @param mark
     * @throws BadEntryException
     */
    public Feedback(String addedBy, float mark) throws BadEntryException {
        if ((mark > 5.0) || mark < 0) {
            throw new BadEntryException("La note doit être supérieure à 0 et inférieure ou égale à 5.0");
        }
        this.addedBy = addedBy;
        this.mark = mark;
    }

    /**
     * toString function for Feedback class, return a fromatted string containing all attrubutes of the class
     *
     * @return String
     */
    public String toString() {
        return "Note: " + this.mark + ", Ajouté par: " + this.addedBy;
    }

}