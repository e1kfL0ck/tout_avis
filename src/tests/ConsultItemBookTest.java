package tests;

import exceptions.BadEntryException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

import java.util.LinkedList;

public class ConsultItemBookTest {
    /**
     * Check nominal behaviour of the consultItem method.
     * According of the Book is added to the list or not (present parameter), will check if a book is found or if the list is empty (book not added).
     *
     * @param sn
     * @param title
     * @param present
     * @param testId
     * @param errorMessage
     * @return
     */
    private static int consultItemBookNominal(ISocialNetwork sn, String title, String mark, boolean present, String testId, String errorMessage) {
        int nbBook = sn.nbBooks(); //Number of Book when the method started
        try {
            LinkedList<String> out = null;
            out = sn.consultItems(title);
            if (sn.nbBooks() != nbBook) {
                System.out.println(errorMessage);
                System.out.println("Err " + testId + " : the number of Books (" + nbBook + ") was incremented"); // Error message displayed
                return 1; // return error code
            }
            if (present) {
                for (String s : out) {
                    // Returned linked list must return the book title and the mark
                    if (!s.toLowerCase().contains(title.toLowerCase()) && !s.toLowerCase().contains(mark)) {
                        System.out.println(errorMessage);
                        System.out.println("Le livre n'a pas été trouvé"); // Error message displayed
                        return 1; // return error code
                    }
                }
            } else if (!out.isEmpty()) {
                System.out.println(errorMessage);
                System.out.println("Un livre à été trouvé alors qu'il n'existe pas"); // Error message displayed
                return 1; // return error code
            }

            return 0; // return success code : everything is OK, nothing to
            // display
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception " + e); // Error
            // message
            // displayed
            System.out.println(e.getMessage()); // Display contextual info about what happened
            return 1; // return error code
        }
    }

    private static int consultItemBookBadEntryTest(ISocialNetwork sn, String title, String testId, String errorMessage) {

        int nbBooks = sn.nbBooks(); // Number of books when starting to
        // run this method
        try {
            sn.consultItems(title); // Try to search for a book
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
                System.out.println("Err " + testId + " : BadEntry was thrown but the number of book was changed"); // Display
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
            System.out.println("Err " + testId + " : unexpected exception. " + e); // Display a specific error message
            e.printStackTrace(); // Display contextual info about what happened
            return 1; // return error value
        }
    }

    public static TestReport test() {

        ISocialNetwork sn = new SocialNetwork();

        int nbTests = 0; // total number of performed tests
        int nbErrors = 0; // total number of failed tests

        System.out.println("Testing consultItems()");

        // Add multiple users and books
        float markBook1 = 0;
        float markBook2 = 0;
        try {
            sn.addMember("toto", "toto1234", "Ceci est le profil de Toto");
            sn.addMember("emile", "emile1234", "Ceci est le profil de Emile");
            sn.addItemBook("toto", "toto1234", "Tintin au Tibet", "BD", "Hergé", 53);
            sn.addItemBook("toto", "toto1234", "Blake et Mortimer", "BD", "Edgar P Jacob", 44);
            sn.reviewItemBook("toto", "toto1234", "Tintin au Tibet", 5, "Bien");
            markBook1 = sn.reviewItemBook("emile", "emile1234", "Tintin au Tibet", 4, "Cool");

            sn.reviewItemBook("toto", "toto1234", "Blake et Mortimer", 2, "Bien");
            markBook2 = sn.reviewItemBook("emile", "emile1234", "Blake et Mortimer", 3.5F, "Cool");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        // <=> test n°1

        // Nominal case, check if we can found a book with different search and if the note is present in the returned info

        nbTests++;
        nbErrors += consultItemBookNominal(sn, "Tintin au Tibet", String.valueOf(markBook1), true, "6.1", "Impossible de trouver le livre");

        nbTests++;
        nbErrors += consultItemBookNominal(sn, "tintin", String.valueOf(markBook1), true, "6.2", "Impossible de trouver le livre");

        nbTests++;
        nbErrors += consultItemBookNominal(sn, "morti", String.valueOf(markBook2), true, "6.2", "Impossible de trouver le livre");

        nbTests++;
        nbErrors += consultItemBookNominal(sn, "Black", String.valueOf(markBook2), true, "6.3", "Impossible de trouver le livre");

        nbTests++;
        nbErrors += consultItemBookNominal(sn, "tata", null, false, "6.4", "Une recherche d'une livre qui n'exsite pas renvoie quelque chose");

        nbTests++;
        nbErrors += consultItemBookNominal(sn, "jdn;idjn", null, false, "6.5", "Une recherche d'une livre qui n'exsite pas renvoie quelque chose");

        // Test n 2
        // Verify if an exception is raised in case of bad parameter

        nbTests++;
        nbErrors += consultItemBookBadEntryTest(sn, "", "1.1", "Exception non levée");

        nbTests++;
        nbErrors += consultItemBookBadEntryTest(sn, "   ", "1.1", "Exception non levée");


        // Display final state of 'sn'
        System.out.println("Final state of the social network : " + sn);

        // Print a summary of the tests and return test results
        try {
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("consultItems : " + tr);
            return tr;
        } catch (NotTestReportException e) { //This shouldn't happen
            System.out.println("Unexpected error in consultItems test code - Can't return valuable test results");
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
