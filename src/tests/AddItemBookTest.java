package tests;

import exceptions.ItemBookAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.BadEntryException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

/**
 * This class contains test cases for the addItemBook() method in the ISocialNetwork interface.
 * 
 * @author T. Roux, E. Quivron
 * @version V1.0 - May 2024
 */
public class AddItemBookTest {

    /**
     * Tests the behavior of the addItemBook() method when given incorrect parameters.
     *
     * @param sn           The social network instance.
     * @param login        The login of the user.
     * @param pwd          The password of the user.
     * @param title        The title of the book.
     * @param kind         The kind of the book.
     * @param author       The author of the book.
     * @param nbPages      The number of pages in the book.
     * @param testId       The ID of the test.
     * @param errorMessage The error message to display if the test fails.
     * @return 1 if the test fails, 0 otherwise.
     */    
    private static int addItemBookBadEntryTest(ISocialNetwork sn, String login,
                                               String pwd, String title, String kind, String author, int nbPages, String testId, String errorMessage) {

        int nbBooks = sn.nbBooks(); // Number of books when starting to
        // run this method
        try {
            sn.addItemBook(login, pwd, title, kind, author, nbPages); // Try to add this book
            // Reaching this point means that no exception was thrown by
            // addItemBook()
            System.out.println("Err " + testId + " : " + errorMessage); // display
            // the
            // error
            // message
            return 1; // and return the "error" value
        } catch (BadEntryException e) { // BadEntry exception was thrown by
            // addItemBook() : this is a good start!
            // Let's now check if 'sn' was not
            // impacted
            if (sn.nbBooks() != nbBooks) { // The number of books has
                // changed : this is an error
                // case.
                System.out
                        .println("Err "
                                + testId
                                + " : BadEntry was thrown but the number of books was changed"); // Display
                // a
                // specific
                // error
                // message
                return 1; // return "error" value
            } else
                // The number of books hasn't changed, which is considered a
                // good indicator that 'sn' was not modified
                return 0; // return success value : everything seems OK, nothing
            // to display
        } catch (Exception e) { // An exception was thrown by addItemBook(), but
            // it was not the expected exception BadEntry
            System.out.println("Err " + testId + " : unexpected exception. "
                    + e); // Display a specific error message
            e.printStackTrace(); // Display contextual info about what happened
            return 1; // return error value
        }
    }

    /**
     * Tests the behavior of the addItemBook() method with nominal parameters.
     *
     * @param sn           The social network instance.
     * @param login        The login of the user.
     * @param pwd          The password of the user.
     * @param title        The title of the book.
     * @param kind         The kind of the book.
     * @param author       The author of the book.
     * @param nbPages      The number of pages in the book.
     * @param testId       The ID of the test.
     * @param errorMessage The error message to display if the test fails.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int addItemBookNominal(ISocialNetwork sn, String login, String pwd, String title, String kind, String author, int nbPages, String testId, String errorMessage) {
        int nbBook = sn.nbBooks(); //Number of Book when the method started
        try {
            sn.addItemBook(login, pwd, title, kind, author, nbPages);
            if (sn.nbBooks() != nbBook + 1) {
                System.out.println(errorMessage);
                System.out.println("Err " + testId + " : the number of Books (" + nbBook + ") was not incremented"); // Error message displayed
                return 1; // return error code
            } else return 0; // return success code : everything is OK, nothing to
            // display
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception " + e); // Error
            // message
            // displayed
            System.out.println(e.getMessage()); // Display contextual info about what happened
            return 1; // return error code
        }
    }

    /**
     * Tests the behavior of the addItemBook() method when the user is not a member.
     *
     * @param sn           The social network instance.
     * @param login        The login of the user.
     * @param pwd          The password of the user.
     * @param title        The title of the book.
     * @param kind         The kind of the book.
     * @param author       The author of the book.
     * @param nbPages      The number of pages in the book.
     * @param testId       The ID of the test.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int addBookNotMemberTest(ISocialNetwork sn, String login, String pwd, String title, String kind, String author, int nbPages, String testId) {
        int nbBook = sn.nbBooks();
        try {
            sn.addItemBook(login, pwd, title, kind, author, nbPages);

            System.out.println("NotMemberTest was not thrown");
            return 1;
        } catch (NotMemberException e) {
            if (nbBook != sn.nbBooks()) {
                System.out.println("NotMemberTest exception thrown but number of book increased");
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception. " + e); // Display a specific error message
            System.out.println(e.getMessage()); // Display contextual info about what happened
            return 1; // return error value
        }
    }

    /**
     * Tests the behavior of the addItemBook() method when the book already exists.
     *
     * @param sn           The social network instance.
     * @param login        The login of the user.
     * @param pwd          The password of the user.
     * @param title        The title of the book.
     * @param kind         The kind of the book.
     * @param author       The author of the book.
     * @param nbPages      The number of pages in the book.
     * @param testId       The ID of the test.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int addBookAlreadyExistTest(ISocialNetwork sn, String login, String pwd, String title, String kind, String author, int nbPages, String testId) {
        int nbBook = sn.nbBooks();
        try {
            sn.addItemBook(login, pwd, title, kind, author, nbPages);

            System.out.println("BookAlreadyExistTest was not thrown");
            return 1;
        } catch (ItemBookAlreadyExistsException e) {
            if (nbBook != sn.nbBooks()) {
                System.out.println("ItemBookAlreadyExistsException exception thrown but number of book increased");
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception. " + e); // Display a specific error message
            System.out.println(e.getMessage()); // Display contextual info about what happened
            return 1; // return error value
        }
    }

    
    /**
     * Executes a series of tests on the social network system and returns a test report.
     *
     * @return A TestReport object containing the number of tests performed and the number of errors encountered.
     */
    public static TestReport test() {

        ISocialNetwork sn = new SocialNetwork();

        int nbFilms = sn.nbFilms(); // number of books in 'sn' (should be 0
        // here)

        int nbTests = 0; // total number of performed tests
        int nbErrors = 0; // total number of failed tests

        System.out.println("Testing addItemBook()");

        // Add multiple users
        try {
            sn.addMember("toto", "toto1234", "Ceci est le profil de Toto");
            sn.addMember("emile", "emile1234", "Ceci est le profil de Emile");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        // <=> test n°1

        // check if incorrect parameters cause addMember() to throw BadEntry
        // exception

        nbTests++;
        nbErrors += addItemBookNominal(sn, "toto", "toto1234", "Livre 1", "Horreur", "John Wick", 12, "1.1", "Unable for an user to add a book");

        // <=> test n°2
        // Check if incorrect parameters cause addBookItem() to throw NotMemberTest exception

        // Test to add a book with a wrong member password
        nbTests++;
        nbErrors += addBookNotMemberTest(sn, "toto", "totodondojn", "Livre 2", "Fiction", "John Wick", 12, "2.1");
        // Test to add a book with an unknown member
        nbTests++;
        nbErrors += addBookNotMemberTest(sn, "tata", "toto1234", "Livre 2", "Fiction", "John Wick", 12, "2.2");

        // <=> test n°3
        // Check for adding book while already existing
        nbTests++;
        nbErrors += addBookAlreadyExistTest(sn, "toto", "toto1234", "Livre 1", "Horreur", "John Wick", 12, "3.1");

        nbTests++;
        if (nbFilms != sn.nbFilms()) {
            System.out.println("Error : the number of books was unexepectedly changed by addMember()");
            nbErrors++;
        }

        // <=> test n°4

        // check if incorrect parameters cause addItemBook() to throw BadEntry
        // exception

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, null, "passwd", "Notre Dame de Paris", "Horror", "Victor Hugo", 478, "4.1", "addItemBook() doesn't reject null logins");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, " ", "passwd", "Notre Dame de Paris", "Horror", "Victor Hugo", 478, "4.2",
                "addItemBook() doesn't reject logins that don't contain at least one character other than space");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", null, "Notre Dame de Paris", "Horror", "Victor Hugo", 478, "4.3",
                "addItemBook() doesn't reject null passwords");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "pas   ", "Notre Dame de Paris", "Horror", "Victor Hugo", 478, "4.4",
                "addItemBook() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "toto1234", null, "Horror", "Victor Hugo", 478, "4.5",
                "addItemBook() doesn't reject null titles");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "toto1234", " ", "Horror", "Victor Hugo", 478, "4.6",
                "addItemBook() doesn't reject titles that are smaller than one character");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "toto1234", "Notre Dame de Paris", null, "Victor Hugo", 478, "4.7",
                "addItemBook() doesn't reject null kind");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "toto1234", "Notre Dame de Paris", "Horror", null, 478, "4.8",
                "addItemBook() doesn't reject null author");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "toto1234", "Notre Dame de Paris", "Horror", "Victor Hugo", -478, "4.9",
                "addItemBook() doesn't reject null logins");

        nbTests++;
        if (nbFilms != sn.nbFilms()) {
            System.out.println("Error : the number of books was unexepectedly changed by addMember()");
            nbErrors++;
        }

        // Display final state of 'sn'
        System.out.println("Final state of the social network : " + sn);

        // Print a summary of the tests and return test results
        try {
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("addItemBook : " + tr);
            return tr;
        } catch (NotTestReportException e) { //This shouldn't happen
            System.out.println("Unexpected error in AddMemberTest test code - Can't return valuable test results");
            return null;
        }


    }


    /**
     * Launches test()
     *
     * @param args not used
     */
    public static void main(String[] args) {
        test();
    }
}
