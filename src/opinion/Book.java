package opinion;

import exceptions.BadEntryException;

public class Book extends Item {
    //Instance attribute
    private String author;
    private int nbPages;

    /**
     * Constructor for the Book class
     * Will throw exception if the author is not speciied or the page number of the book is negative
     * Exception condition from the super class (Item) applies.
     *
     * @param title
     * @param kind
     * @param author
     * @param nbPages
     * @param addedBy
     * @throws BadEntryException
     */
    public Book(String title, String kind, String author, int nbPages, String addedBy) throws BadEntryException {
        super(title, kind, addedBy);
        if (author == null) {
            throw new BadEntryException("L'auteur n'a pas été spécifié");
        }
        if (!(nbPages > 0)) {
            throw new BadEntryException("Le nombre de page de peux pas etre égal à 0 ou négatif");
        }
        this.author = author;
        this.nbPages = nbPages;
    }

    /**
     * Return a formatted String containing all the attributes of the Book
     *
     * @return String
     */
    @Override
    public String toString() {
        String temp;
        temp = "Titre du livre: " + this.title + ", Catégorie: " + this.kind + ", Auteur: " + this.author + ", Nombre de page: " + this.nbPages + ", Ajouté par: " + this.addedBy;
        return temp;
    }
}
