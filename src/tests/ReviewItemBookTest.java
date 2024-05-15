package tests;

import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

public class ReviewItemBookTest {
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
