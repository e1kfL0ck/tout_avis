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
    private int nbMembers;
    private int nbBooks;
    private LinkedList<Members> members;

    /**
     * Social network constructor
     * Init Members linked list and nbMembers variable to 0
     */
    public SocialNetwork() {
        members = new LinkedList<>();
        nbMembers = 0;
    }

    //Instance method

    /**
     * Getter for nbMembers attribute
     *
     * @return number of members
     */
    @Override
    public int nbMembers() {
        return nbMembers;
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

    @Override
    /**
     * Implement addMembers method for SocialNetwork
     * First, iterate over all member list and check if the login argument match with an existing member in the list (areYou(l) method of Members).
     * Non-case sensitive match + white spaces ignored
     * Second, add the member to the list (Members constructor constraint and exceptions applies...)
     * Increment nbMembers variable.
     */
    public void addMember(String login, String password, String profile)
            throws BadEntryException, MemberAlreadyExistsException {
        for (Members m : members) {
            if (m.areYou(login)) {
                throw new MemberAlreadyExistsException();
            }
            ;
        }
        members.add(new Members(login, password, profile));
        nbMembers++;
    }

    @Override
    public void addItemFilm(String login, String password, String title,
                            String kind, String director, String scriptwriter, int duration)
            throws BadEntryException, NotMemberException,
            ItemFilmAlreadyExistsException {
        // TODO Auto-generated method stub

    }

    @Override
    public void addItemBook(String login, String password, String title,
                            String kind, String author, int nbPages) throws BadEntryException,
            NotMemberException, ItemBookAlreadyExistsException {
        // TODO Auto-generated method stub

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

    @Override
    /**
     * New toString method to display all members in the social network
     * Iterate over the Members linkedlist and use StringBuilder to concatenate return of the Member toString method
     * Add an extra carrier return for better display
     * Specific message if the member list is empty.
     */
    public String toString() {
        if (!members.isEmpty()) {
            StringBuilder temp = new StringBuilder();
            for (Members m : members) {
                temp.append(m.toString());
                temp.append("\n");
            }
            return temp.toString();
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
