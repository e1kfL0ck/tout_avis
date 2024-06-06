package opinion;

import exceptions.BadEntryException;

public class Film extends Item {
    // Class attributes
    private String director;
    private String scenarist;
    private int duration;

    /**
     * Constructor for the Item class
     * Throw BadEntryException if imput parameters does not match the specification (see code spec)
     *
     * @param title
     * @param kind
     * @param director
     * @param scenarist
     * @param duration
     * @param addedBy
     * @throws BadEntryException
     */
    public Film(String title, String kind, String director, String scenarist, int duration, String addedBy) throws BadEntryException {
        super(title, kind, addedBy);
        if (director == null) {
            throw new BadEntryException("Le directeur du film n'a pas été spécifié");
        }
        if (scenarist == null) {
            throw new BadEntryException("Le scénariste du film n'a pas été spécifié");
        }
        if (!(duration > 0)) {
            throw new BadEntryException("La durée du film ne peut pas être négative");
        }
        this.director = director;
        this.scenarist = scenarist;
        this.duration = duration;
    }

    /**
     * Return a formatted String containing all the attributes of the Film
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder concatenedString = new StringBuilder();
        concatenedString.append("Titre du film: ");
        concatenedString.append(this.title);
        concatenedString.append(", Catégorie: ");
        concatenedString.append(this.kind);
        concatenedString.append(", Directeur de production: ");
        concatenedString.append(this.director);
        concatenedString.append(", Durée: ");
        concatenedString.append(this.duration);
        concatenedString.append(", Scénariste: ");
        concatenedString.append(this.scenarist);
        if (!Float.isNaN(getMeanMark())) {
            concatenedString.append(", Note (Moyenne): ");
            concatenedString.append(String.format("%.2f", getMeanMark()));
        }
        concatenedString.append(", Ajouté par: ");
        concatenedString.append(this.addedBy);
        return concatenedString.toString();
    }
}
