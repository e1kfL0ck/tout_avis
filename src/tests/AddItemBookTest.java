package tests;

import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

public class AddItemBookTest {
    private static int addItemBookNominal(ISocialNetwork sn, String login, String pwd, String title, String kind, String author, int nbPages, String testId, String errorMessage) {
        int nbBook = sn.nbBooks(); //Number of Book when the method started
        try {
            sn.addItemBook(login, pwd, title, kind, author, nbPages);
            if (sn.nbBooks() != nbBook + 1) {
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
//        nbTests++;
//        nbErrors += addMemberBadEntryTest(sn, " ", "qsdfgh", "", "1.2", "addMember() doesn't reject logins that don't contain at least one character other than space");
//        nbTests++;
//        nbErrors += addMemberBadEntryTest(sn, "B", null, "", "1.3", "addMember() doesn't reject null passwords");
//        nbTests++;
//        nbErrors += addMemberBadEntryTest(sn, "B", "   qwd ", "", "1.4", "addMember() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
//        nbTests++;
//        nbErrors += addMemberBadEntryTest(sn, "BBBB", "bbbb", null, "1.5", "addMember() doesn't reject null profiles");

//        // <=> test n°2
//
//        // populate 'sn' with 3 members
//
//        nbTests++;
//        nbErrors += addMemberOKTest(sn, "Paul", "paul", "lecteur impulsif", "2.1a");
//        nbTests++;
//        nbErrors += addMemberOKTest(sn, "Antoine", "antoine", "grand amoureux de la littérature", "2.1b");
//        nbTests++;
//        nbErrors += addMemberOKTest(sn, "Alice", "alice", "passionnée de bande dessinée", "2.1c");
//
//        // try to add already registered members
//
//        nbTests++;
//        nbErrors += addMemberAlreadyExistsTest(sn, new String("Paul"), "abcdefghij", "", "2.2", "The login of the first member was accepted as login for a new member");
//        nbTests++;
//        nbErrors += addMemberAlreadyExistsTest(sn, new String("Alice"), "abcdefghij", "", "2.3", "The login of the last member was accepted as login for a new member");
//        nbTests++;
//        nbErrors += addMemberAlreadyExistsTest(sn, new String("anToine"), "abcdefghij", "", "2.4", "An already registered login, but with different case, was accepted as login for a new member");
//        nbTests++;
//        nbErrors += addMemberAlreadyExistsTest(sn, new String(" Antoine "), "abcdefghij", "", "2.5", "An already registered login, surrounded by leading/trailing blanks, was accepted as login for a new member");
//        nbTests++;
//        nbErrors += addMemberAlreadyExistsTest(sn, "An" + "toi" + "ne", "abcdefghij", "", "2.6", "A String concatenation building an already registered login was accepted as login for a new member");

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
            System.out.println("AddMemberTest : " + tr);
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
