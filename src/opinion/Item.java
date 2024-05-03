package opinion;

import exceptions.BadEntryException;

public class Item {
    //Class attributes
    protected String title;
    protected String kind;
    protected String addedBy;

    //Constructor
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

    // Instance method
    public boolean areYou(Class<?> c, String title) {
        if (title == null || c == null) {
            return false;
        }
        return (c.isInstance(this) && title.equals(this.title));
    }

    @Override
    public String toString() {
        String temp;
        temp = "Titre: " + this.title + ", Catégorie: " + this.kind + "Ajouté par: " + this.addedBy;
        return temp;
    }
}
