package tests;

import exceptions.BadEntryException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;


public class AddItemBookTest {
	
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
		
		// <=> test n°1

        // check if incorrect parameters cause addItemBook() to throw BadEntry
        // exception

//        nbTests++;
//        nbErrors += addItemBookBadEntryTest(sn, null, "passwd", "Notre Dame de Paris", "Horror", "Victor Hugo", "478", "1.1",
//                "addItemBok() doesn't reject null logins");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, " ", "passwd", "Notre Dame de Paris", "Horror", "Victor Hugo", 478, "2.2",
                "addItemBook() doesn't reject logins that don't contain at least one character other than space");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", null, "Notre Dame de Paris", "Horror", "Victor Hugo", 478, "2.3",
                "addItemBook() doesn't reject null passwords");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "pas   ", "Notre Dame de Paris", "Horror", "Victor Hugo", 478, "2.4",
                "addItemBook() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "toto1234", null, "Horror", "Victor Hugo", 478, "2.5",
                "addItemBook() doesn't reject null titles");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "toto1234", "N", "Horror", "Victor Hugo", 478, "2.6",
                "addItemBook() doesn't reject titles that are smaller than one character");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "toto1234", "Notre Dame de Paris", null, "Victor Hugo", 478, "2.7",
                "addItemBok() doesn't reject null kind");

        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "toto1234", "Notre Dame de Paris", "Horror", null, 478, "2.8",
                "addItemBok() doesn't reject null author");
        
        nbTests++;
        nbErrors += addItemBookBadEntryTest(sn, "toto", "toto1234", "Notre Dame de Paris", "Horror", "Victor Hugo", -478, "2.9",
                "addItemBok() doesn't reject null logins");

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
