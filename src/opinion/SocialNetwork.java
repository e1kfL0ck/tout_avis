package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

/**
 * Skeleton for the SocialNetwork
 */
public class SocialNetwork implements ISocialNetwork {
    // Class attribute
    private int nbBooks;
    private LinkedList<Members> members;
    private LinkedList<Item> items;

    /**
     * Social network constructor
     * Init Members linked list and nbMembers variable to 0
     */
    public SocialNetwork() {
        members = new LinkedList<>();
        items = new LinkedList<>();
    }

    //Instance method

    /**
     * Getter for nbMembers attribute
     *
     * @return number of members
     */
    @Override
    public int nbMembers() {
        return members.size();
    }

    @Override
    public int nbFilms() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int nbBooks() {
        // TODO Auto-generated method stub
        return this.nbBooks;
    }

    /**
     * Implement addMembers method for SocialNetwork
     * First, iterate over all member list and check if the login argument match with an existing member in the list (areYou(l) method of Members).
     * Non-case sensitive match + white spaces ignored
     * Second, add the member to the list (Members constructor constraint and exceptions applies...)
     * Increment nbMembers variable.
     *
     * @param login    the new member's login
     * @param password the new member's password
     * @param profile  a free String describing the new member's profile
     * @throws BadEntryException
     * @throws MemberAlreadyExistsException
     */
    @Override
    public void addMember(String login, String password, String profile)
            throws BadEntryException, MemberAlreadyExistsException {
        for (Members m : members) {
            if (m.areYou(login)) {
                throw new MemberAlreadyExistsException();
            }
            ;
        }
        members.add(new Members(login, password, profile));
    }

    @Override
    public void addItemFilm(String login, String password, String title,
                            String kind, String director, String scriptwriter, int duration)
            throws BadEntryException, NotMemberException,
            ItemFilmAlreadyExistsException {
        // TODO Auto-generated method stub

    }

    /**
     * Add a new book to the social network
     * <p>
     * User must be logged to add a book, the method will iterate over the Member list to check if the passed login exist, if not a NotMemberException will be thrown
     * If the user is found, the password will be checked, if the password is incorrect a NotMemberException will be thrown.
     * When the user is logged, the method will iterate over the book list and check if the book does not already exist, if it is a ItemBookAlreadyExistsException exception will be thrown.
     * The book will be added in the "items" linked list, the username of the logged user will be used to fill the "addedBy" Book attribute.
     * nbBook attribute will be incremented.
     * If no member is found with the provided attrbibute, a NotMemberException will be thrown.
     *
     * @param login    login of the member adding the book
     * @param password password of the member adding the book
     * @param title    the new book's title
     * @param kind     the new book's kind (adventure, thriller, etc.)
     * @param author   the new book's author
     * @param nbPages  number of pages of the new book's
     * @throws BadEntryException
     * @throws NotMemberException
     * @throws ItemBookAlreadyExistsException
     */
    @Override
    public void addItemBook(String login, String password, String title,
                            String kind, String author, int nbPages) throws BadEntryException,
            NotMemberException, ItemBookAlreadyExistsException {
        boolean found = false;
        for (Members m : members) {
            if (m.areYou(login)) {
                found = true;
                if (!m.login(login, password)) {
                    throw new NotMemberException("Mot de passe incorrect");
                }
                for (Item i : items) {
                    if (i.areYou(Book.class, title)) {
                        throw new ItemBookAlreadyExistsException();
                    }
                }
                items.add(new Book(title, kind, author, nbPages, m.getLogin()));
                nbBooks++;
                break;
            }
        }
        if (!found) {
            throw new NotMemberException("Membre non trouvé");
        }
        ;

    }

    @Override
    public float reviewItemFilm(String login, String password, String title,
                                float mark, String comment) throws BadEntryException,
            NotMemberException, NotItemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float reviewItemBook(String login, String password, String title,
                                float mark, String comment) throws BadEntryException,
            NotMemberException, NotItemException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public LinkedList<String> consultItems(String title)
            throws BadEntryException {
        return new LinkedList<String>();
    }

    /**
     * New toString method to display all members in the social network
     * Iterate over the Members linkedlist and use StringBuilder to concatenate return of the Member toString method
     * Iterate over the book list to show existing books
     * Add an extra carrier return for better display
     * Specific message if the member list is empty.
     * Specific message if no book exists
     *
     * @return String
     */
    @Override
    public String toString() {
        if (!members.isEmpty()) {
            StringBuilder concatenedString = new StringBuilder();
            concatenedString.append("\n------------------------------ Users ------------------------\n");
            for (Members m : members) {
                concatenedString.append(m.toString());
                concatenedString.append("\n");
            }
            concatenedString.append("Nombre utilisateurs: ").append(nbMembers()).append("\n");
            concatenedString.append("-------------------- Books --------------------------\n");
            if (!items.isEmpty()) {
                for (Item i : items) {
                    if (i instanceof Book) {
                        concatenedString.append(i);
                        concatenedString.append("\n");
                    }
                }
                concatenedString.append("Nombre livres: ").append(nbBooks);
            } else {
                concatenedString.append("Pas de livres inscrits");
            }
            return concatenedString.toString();
        } else {
            return "Pas d'utilisateurs inscrits";
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
