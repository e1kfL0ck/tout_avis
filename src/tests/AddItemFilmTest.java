package tests;

import exceptions.BadEntryException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.Film;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

/**
 * This class contains test cases for the addItemFilm() method in the ISocialNetwork interface.
 *
 * @author T. Roux, E. Quivron
 * @version V1.0 - May 2024
 */
public class AddItemFilmTest {

    /**
     * Tests the behavior of the addItemFilm() method when given incorrect parameters.
     *
     * @param sn           The social network instance.
     * @param login        The login of the user.
     * @param pwd          The password of the user.
     * @param title        The title of the film.
     * @param kind         The kind of the film.
     * @param director     The director of the film.
     * @param scenarist    The scenarist of the film.
     * @param duration     The duration of the film.
     * @param testId       The ID of the test.
     * @param errorMessage The error message to display if the test fails.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int addItemFilmBadEntryTest(ISocialNetwork sn, String login,
                                               String pwd, String title, String kind, String director, String scenarist, int duration, String testId, String errorMessage) {

        int nbFilm = sn.nbFilms(); // Number of film when starting to
        // run this method
        try {
            sn.addItemFilm(login, pwd, title, kind, director, scenarist, duration); // Try to add this film
            // Reaching this point means that no exception was thrown by
            // addItemFilm()
            System.out.println("Err " + testId + " : " + errorMessage); // display
            // the
            // error
            // message
            return 1; // and return the "error" value
        } catch (BadEntryException e) { // BadEntry exception was thrown by
            // addItemFilm() : this is a good start!
            // Let's now check if 'sn' was not
            // impacted
            if (sn.nbFilms() != nbFilm) { // The number of films has
                // changed : this is an error
                // case.
                System.out
                        .println("Err "
                                + testId
                                + " : BadEntry was thrown but the number of film was changed"); // Display
                // a
                // specific
                // error
                // message
                return 1; // return "error" value
            } else
                // The number of films hasn't changed, which is considered a
                // good indicator that 'sn' was not modified
                return 0; // return success value : everything seems OK, nothing
            // to display
        } catch (Exception e) { // An exception was thrown by addItemFilm(), but
            // it was not the expected exception BadEntry
            System.out.println("Err " + testId + " : unexpected exception. "
                    + e); // Display a specific error message
            e.printStackTrace(); // Display contextual info about what happened
            return 1; // return error value
        }
    }

    /**
     * Tests the behavior of the addItemFilm() method with nominal parameters.
     *
     * @param sn           The social network instance.
     * @param login        The login of the user.
     * @param pwd          The password of the user.
     * @param title        The title of the film.
     * @param kind         The kind of the film.
     * @param director     The director of the film.
     * @param scenarist    The scenarist of the film.
     * @param duration     The duration of the film.
     * @param testId       The ID of the test.
     * @param errorMessage The error message to display if the test fails.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int addItemFilmNominal(ISocialNetwork sn, String login, String pwd, String title, String kind, String director, String scenarist, int duration, String testId, String errorMessage) {
        int nbFilm = sn.nbFilms(); //Number of film when the method started
        try {
            sn.addItemFilm(login, pwd, title, kind, director, scenarist, duration);
            if (sn.nbFilms() != nbFilm + 1) {
                System.out.println(errorMessage);
                System.out.println("Err " + testId + " : the number of films (" + nbFilm + ") was not incremented"); // Error message displayed
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
     * Tests the behavior of the addItemFilm() method when the user is not a member.
     *
     * @param sn        The social network instance.
     * @param login     The login of the user.
     * @param pwd       The password of the user.
     * @param title     The title of the film.
     * @param kind      The kind of the film.
     * @param director  The director of the film.
     * @param scenarist The scenarist of the film.
     * @param duration  The duration of the film.
     * @param testId    The ID of the test.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int addFilmNotMemberTest(ISocialNetwork sn, String login, String pwd, String title, String kind, String director, String scenarist, int duration, String testId) {
        int nbFilm = sn.nbFilms();
        try {
            sn.addItemFilm(login, pwd, title, kind, director, scenarist, duration);
            System.out.println("NotMemberTest was not thrown");
            return 1;
        } catch (NotMemberException e) {
            if (nbFilm != sn.nbFilms()) {
                System.out.println("NotMemberTest exception thrown but number of film increased");
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
     * Tests the behavior of the addItemFilm() method when the film already exists.
     *
     * @param sn        The social network instance.
     * @param login     The login of the user.
     * @param pwd       The password of the user.
     * @param title     The title of the film.
     * @param kind      The kind of the film.
     * @param director  The director of the film.
     * @param scenarist The scenarist of the film.
     * @param duration  The duration of the film.
     * @param testId    The ID of the test.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int addFilmAlreadyExistTest(ISocialNetwork sn, String login, String pwd, String title, String kind, String director, String scenarist, int duration, String testId) {
        int nbFilm = sn.nbFilms();
        try {
            sn.addItemFilm(login, pwd, title, kind, director, scenarist, duration);
            System.out.println("filmAlreadyExistTest was not thrown");
            return 1;
        } catch (ItemFilmAlreadyExistsException e) {
            if (nbFilm != sn.nbFilms()) {
                System.out.println("ItemFilmAlreadyExistsException exception thrown but number of film increased");
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
     * Tests the behavior of the areYou() method.
     *
     * @param title     The title of the film.
     * @param kind      The kind of the film.
     * @param director  The director of the film.
     * @param scenarist The scenarist of the film.
     * @param duration  The duration of the film.
     * @param addedBy   The name of the user adding the film.
     * @param testId    The ID of the test.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int areYouTest(String title, String kind, String director, String scenarist, int duration, String addedBy, String testId) {
        try {
            Film testFilm = new Film(title, kind, director, scenarist, duration, addedBy);
            // Does a film is a film ?
            if (!testFilm.areYou(Film.class, title)) {
                return 1;
            } // A film is not an exception...
            else if (testFilm.areYou(Exception.class, title)) {
                return 1;
            } else
                return 0;
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

        int nbBook = sn.nbBooks(); // number of films in 'sn' (should be 0
        // here)

        int nbTests = 0; // total number of performed tests
        int nbErrors = 0; // total number of failed tests

        System.out.println("Testing addItemFilm()");

        // Add multiple users
        try {
            sn.addMember("toto", "toto1234", "Ceci est le profil de Toto");
            sn.addMember("emile", "emile1234", "Ceci est le profil de Emile");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        // <=> test n°1
        // Check nominal behaviour of the AddItemFilm method

        nbTests++;
        nbErrors += addItemFilmNominal(sn, "toto", "toto1234", "La ligne verte", "Intrigue", "Frank Darabont", "Frank Darabont", 186, "1.1", "Unable for an user to add a film");

        nbTests++;
        if (nbBook != sn.nbBooks()) {
            System.out.println("Error : the number of films was unexepectedly changed by addMember()");
            nbErrors++;
        }

        // <=> test n°2

        // check if incorrect parameters cause addItemFilm() to throw BadEntry
        // exception

        nbTests++;
        nbErrors += addItemFilmBadEntryTest(sn, null, "passwd", "La ligne verte 2", "Intrigue", "Frank Darabont", "Frank Darabont", 186, "2.1", "addItemFilm() doesn't reject null logins");

        nbTests++;
        nbErrors += addItemFilmBadEntryTest(sn, " ", "passwd", "La ligne verte 2", "Intrigue", "Frank Darabont", "Frank Darabont", 186, "2.2",
                "addItemFilm() doesn't reject logins that don't contain at least one character other than space");

        nbTests++;
        nbErrors += addItemFilmBadEntryTest(sn, "toto", null, "La ligne verte 2", "Intrigue", "Frank Darabont", "Frank Darabont", 186, "2.3",
                "addItemFilm() doesn't reject null passwords");

        nbTests++;
        nbErrors += addItemFilmBadEntryTest(sn, "toto", "pas   ", "La ligne verte 2", "Intrigue", "Frank Darabont", "Frank Darabont", 186, "2.4",
                "addItemFilm() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");

        nbTests++;
        nbErrors += addItemFilmBadEntryTest(sn, "toto", "toto1234", null, "Intrigue", "Frank Darabont", "Frank Darabont", 186, "2.5",
                "addItemFilm() doesn't reject null titles");

        nbTests++;
        nbErrors += addItemFilmBadEntryTest(sn, "toto", "toto1234", " ", "Intrigue", "Frank Darabont", "Frank Darabont", 186, "2.6",
                "addItemFilm() doesn't reject titles that are smaller than one character");

        nbTests++;
        nbErrors += addItemFilmBadEntryTest(sn, "toto", "toto1234", "La ligne verte 2", null, "Frank Darabont", "Frank Darabont", 186, "2.7",
                "addItemFilm() doesn't reject null kind");

        nbTests++;
        nbErrors += addItemFilmBadEntryTest(sn, "toto", "toto1234", "La ligne verte 2", "Intrigue", "Frank Darabont", null, 186, "2.8",
                "addItemFilm() doesn't reject null scenarist");

        nbTests++;
        nbErrors += addItemFilmBadEntryTest(sn, "toto", "toto1234", "La ligne verte 2", "Intrigue", null, "Frank Darabont", 186, "2.8",
                "addItemFilm() doesn't reject null director");

        nbTests++;
        nbErrors += addItemFilmBadEntryTest(sn, "toto", "toto1234", "La ligne verte 2", "Intrigue", "Frank Darabont", "Frank Darabont", -478, "2.9",
                "addItemFilm() doesn't reject negative film duration");

        // <=> test n°3
        // Check if incorrect parameters cause addFilmItem() to throw NotMemberTest exception

        // Test to add a film with a wrong member password
        nbTests++;
        nbErrors += addFilmNotMemberTest(sn, "toto", "totodondojn", "La ligne verte", "Intrigue", "Frank Darabont", "Frank Darabont", 186, "3.1");
        // Test to add a film with an unknown member
        nbTests++;
        nbErrors += addFilmNotMemberTest(sn, "tata", "toto1234", "La ligne verte", "Intrigue", "Frank Darabont", "Frank Darabont", 186, "3.2");

        // <=> test n°4
        // Check for adding film while already existing
        nbTests++;
        nbErrors += addFilmAlreadyExistTest(sn, "toto", "toto1234", "La ligne verte", "Intrigue", "Frank Darabont", "Frank Darabont", 186, "4.1");

        // Test of Item areYou method.
        // Will check if the areYou method return true if an item instance match the class type passed in argument + title
        // Here check with the same title a film class type and an Exception class type.
        nbTests++;
        nbErrors += areYouTest("La ligne verte", "Intrigue", "Frank Darabont", "Frank Darabont", 478, "toto", "5.0");

        nbTests++;
        if (nbBook != sn.nbBooks()) {
            System.out.println("Error : the number of films was unexepectedly changed by addItemFilm()");
            nbErrors++;
        }

        // Display final state of 'sn'
        System.out.println("Final state of the social network : " + sn);

        // Print a summary of the tests and return test results
        try {
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("addItemFilm : " + tr);
            return tr;
        } catch (NotTestReportException e) { //This shouldn't happen
            System.out.println("Unexpected error in addItemFilmTest test code - Can't return valuable test results");
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
