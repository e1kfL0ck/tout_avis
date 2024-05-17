package tests;

import java.util.LinkedList;
import java.lang.Float;

import exceptions.BadEntryException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.Book;
import opinion.ISocialNetwork;
import opinion.Item;
import opinion.SocialNetwork;

public class ReviewItemBookTest {

    /**
     * Tests the behavior of the {@code reviewItemBook} with nominal parameters.
     *
     * @param sn      The social network instance.
     * @param login   The login of the user.
     * @param pwd     The password of the user.
     * @param title   The title of the book.
     * @param mark    The mark of the review.
     * @param comment The comment of the review.
     * @param testId  The ID of the test.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int ReviewItemBookTestNominal(ISocialNetwork sn, String login, String pwd, String title, String comment, float mark, String testId, String errorMessage) {
        int nbBook = sn.nbBooks(); //Number of Book when the method started
        try {
            float out = sn.reviewItemBook(login, pwd, title, mark, comment);
            if (sn.nbBooks() != nbBook) {
                System.out.println(errorMessage);
                System.out.println("Err " + testId + " : the number of Books (" + nbBook + ") was incremented"); // Error message displayed
                return 1; // return error code
            } else {
                return 0; // return success code : everything is OK, nothing to
            }
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
     * Tests the behavior of the {@code reviewItemBook} method when the user is not a member.
     *
     * @param sn      The social network instance.
     * @param login   The login of the user.
     * @param pwd     The password of the user.
     * @param title   The title of the book.
     * @param mark    The mark of the review.
     * @param comment The comment of the review.
     * @param testId  The ID of the test.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int ReviewNotMemberTest(ISocialNetwork sn, String login, String pwd, String title, float mark, String comment, Float defaultMean, String testId, String errorMessage) {
        float mean;
        try {
            mean = sn.reviewItemBook(login, pwd, title, mark, comment);
            System.out.println("Err " + testId + " : " + errorMessage); // display the error message
            return 1;
        } catch (NotMemberException e) {
            try {
                mean = sn.reviewItemBook("toto", "toto1234", "Tintin au Tibet", 4.9F, "Super BD !");
                if (defaultMean==mean) {
                    return 0;
                } else {
                    System.out.println("Err " + testId + " : NotMemberException was thrown but the mean was changed");
                    return 1;
                }
            } catch (Exception e2) {
                System.out.println("Error during the mean check, verify the code." + e2); // Display a specific error message
                return 1; // return error value
            }
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception. " + e); // Display a specific error message
            System.out.println(e.getMessage()); // Display contextual info about what happened
            return 1; // return error value
        }
    }

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
    private static int ReviewBookBadEntryTest(ISocialNetwork sn, String login, String pwd, 
                String title, float mark, String comment, Float defaultMean, String testId, String errorMessage) {

        float mean; // Number of books when starting to
        try {
            mean = sn.reviewItemBook(login, pwd, title, mark, comment); // Try to add the review
            // Reaching this point means that no exception was thrown
            System.out.println("Err " + testId + " : " + errorMessage); // display the error message
            return 1; // and return the "error" value
        } catch (BadEntryException e) { // BadEntry exception was thrown by
            // reviewItemBook() : this is a good start!
            // Let's now check if 'sn' was not impacted
            try {
                mean = sn.reviewItemBook("toto", "toto1234", "Tintin au Tibet", 4.9F, "Super BD !");
                if (defaultMean==mean) {
                    return 0; // Everything is fine : the mean was not changed
                } else {
                    // The mean just changed, which is not the expected behavior
                    System.out.println("Err " + testId + " : BadEntryException was thrown but the mean was changed");
                    return 1;
                }
            } catch (Exception e2) {
                System.out.println("Error during the mean check, verify the code." + e2); // Display a specific error message
                return 1; // return error value
            }
        } catch (Exception e) { // An exception was thrown by addItemBook(), but
            // it was not the expected exception BadEntry
            System.out.println("Err " + testId + " : unexpected exception. "
                    + e); // Display a specific error message
            e.printStackTrace(); // Display contextual info about what happened
            return 1; // return error value
        }
    }

    public static TestReport test() {
        ISocialNetwork sn = new SocialNetwork();
        int nbBook = sn.nbBooks();// number of book in 'sn' (should be 0
        // here)
        int nbFilms = sn.nbFilms(); // number of film in 'sn' (should be 0
        // here)

        int nbTests = 0; // total number of performed tests
        int nbErrors = 0; // total number of failed tests

        System.out.println("Testing ReviewItemBook()");

        // Add multiple users and books
        try {
            sn.addMember("toto", "toto1234", "Ceci est le profil de Toto");
            sn.addMember("emile", "emile1234", "Ceci est le profil de Emile");
            sn.addMember("jhon", "jhon1234", "Ceci est le profil de Jhon");
            sn.addItemBook("toto", "toto1234", "Tintin au Tibet", "BD", "Hergé", 56);
            sn.addItemBook("emile", "emile1234", "Tintin au Congo", "BD", "Hergé", 60);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // <=> test n1
        // Check nominal work of the method

        nbTests++;
        nbErrors += ReviewItemBookTestNominal(sn, "toto", "toto1234", "Tintin au Tibet", "Super BD !", 4.9F, "1.1", "User is unable to post his first review on an existing book");

        nbTests++;
        nbErrors += ReviewItemBookTestNominal(sn, "toto", "toto1234", "Tintin au Congo", "Pas mal !", 4.2F, "1.2", "User is unable to post his first review on an existing book");

        nbTests++;
        nbErrors += ReviewItemBookTestNominal(sn, "emile", "emile1234", "Tintin au Tibet", "Pas trop aimé !", 2.1F, "1.3", "User is unable to post his first review on an existing book");

        nbTests++;
        nbErrors += ReviewItemBookTestNominal(sn, "emile", "emile1234", "Tintin au Tibet", "Finalement j'aime bien", 4.9F, "1.4", "User is unable to post his second review on an existing book");

        nbTests++;
        nbErrors += ReviewItemBookTestNominal(sn, "toto", "toto1234", "Tintin au Congo", "Bof finalement !", 3.0F, "1.3", "User is unable to post his second review on an existing book");

        nbTests++;
        if (nbFilms != sn.nbFilms()) {
            System.out.println("Error : the number of books was unexepectedly changed by ReviewItemBook()");
            nbErrors++;
        }

        float defaultMean = (4.9F+4.9F)/2.0F;

        // <=> test n°2
        // Check if incorrect parameters cause addReview() to throw NotMemberException

        // Test to add a review with a wrong member password
        nbTests++;
        nbErrors += ReviewNotMemberTest(sn, "toto", "totodondojn", "Tintin au Tibet", 3.2F, "Très bon livre", defaultMean, "2.1", "The user is able to post a review with a wrong password");
        // Test to add a review with an unknown member
        nbTests++;
        nbErrors += ReviewNotMemberTest(sn, "titi", "totodondojn", "Tintin au Tibet", 3.2F, "Très bon livre", defaultMean, "2.2", "The user is able to post a review with an unknown member");


        // <=> test n°3
        // Check if incorrect parameters cause addReview() to throw BadEntryException

        //Test with null comment
        nbErrors += ReviewBookBadEntryTest(sn, "toto", "toto1234", "Tintin au Tibet", 3.2F, "", defaultMean, "3.1", "The user is able to post a review with an empty comment");

        //Test with mark superior to 5
        nbErrors += ReviewBookBadEntryTest(sn, "toto", "toto1234", "Tintin au Tibet", 10F, "Très bon livre", defaultMean, "3.2", "The user is able to post a review with a mark superior to 5");

        //Test with mark inferior to 0
        nbErrors += ReviewBookBadEntryTest(sn, "toto", "toto1234", "Tintin au Tibet", -5f, "Très bon livre", defaultMean, "3.3", "The user is able to post a review with a mark inferior to 5");

        

        // Display final state of 'sn'
        System.out.println("Final state of the social network : " + sn);

        // Print a summary of the tests and return test results
        try {
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("ReviewItemBook : " + tr);
            return tr;
        } catch (NotTestReportException e) { //This shouldn't happen
            System.out.println("Unexpected error in ReviewItemBook test code - Can't return valuable test results");
            return null;
        }
    }

    public static void main(String[] args) {
        test();
    }

}
