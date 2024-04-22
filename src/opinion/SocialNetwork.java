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
    private LinkedList<Members> members;

    // Constructor
    public SocialNetwork() {
        members = new LinkedList<>();
        nbMembers = 0;
    }

    //Instance method
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
        return 0;
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


    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
