package opinion;

import exceptions.BadEntryException;

public class Item {
    //Class attributes
    protected String title;
    protected String kind;
    protected String addedBy;

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
        this.title = title;
        this.kind = kind;
        this.addedBy = addedBy;
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
        return (c.isInstance(this) && title.equals(this.title));
    }

    /**
     * Return a string containing all the attributes for the Item instance
     *
     * @return String
     */
    @Override
    public String toString() {
        String temp;
        temp = "Titre: " + this.title + ", Catégorie: " + this.kind + "Ajouté par: " + this.addedBy;
        return temp;
    }
}
