package opinion;

import exceptions.BadEntryException;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Members {
    // Class attribute
    private String login;
    private String password;
    private String profile;
    private float karma;

    /**
     * Member class constructor.
     * Include check for arguments.
     * - login: At least 1 caracter (white space not included)
     * - password: At least 4 caracters (white space not included)
     * - login, password, profile: must be defined
     *
     * @param login
     * @param password
     * @param profile
     * @throws BadEntryException
     */
    public Members(String login, String password, String profile) throws BadEntryException {
        // Remove trailing with spaces with replace method, string length = 0 => isEmpty method
        if (login == null || login.replace(" ", "").isEmpty()) {
            throw new BadEntryException("Format du login incorrect. Format: plus de 1 caractère (espaces non compris)");
        }
        if (password == null || password.replace(" ", "").length() < 4) {
            throw new BadEntryException("Format du mot de passe incorrect. Format: plus de 4 caractères (espaces non compris)");
        }
        if (profile == null) {
            throw new BadEntryException("Description du profil non renseigné");
        }
        this.login = login;
        this.password = password;
        this.profile = profile;
        this.karma = 1; //All user got 1 as karma score
    }

    //Instance method

    /**
     * Getter for login attribute
     *
     * @return member login
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Make a non-case sensitive comparaison within the current object login field and the argument
     *
     * @param l
     * @return true if login matches, false if not
     */
    public boolean areYou(String l) throws BadEntryException {
        // Remove trailing white spaces + make a non-case sensitive string comparison with the current object login field.
        if (l == null || l.replace(" ", "").isEmpty()) {
            throw new BadEntryException("Format du login incorrect. Format: plus de 1 caractère (espaces non compris)");
        }
        return l.replace(" ", "").equalsIgnoreCase(this.getLogin());
    }

    /**
     * Getter for profile arttribute
     *
     * @return profile description
     */
    public String getProfile() {
        return this.profile;
    }

    /**
     * Getter for the user karma
     *
     * @return user karma (float)
     */
    public float getKarma() {
        return this.karma;
    }

    /**
     * Method to compute the coefficient for the karma update
     * Coefficient is computed with the delta between the mark given and the mean of the mark (2.5 for marks over 5) plus one.
     * Square root is used to smooth the coefficent (logarithmic growth)
     *
     * @param mark
     * @return computed coefficient
     */
    public float computeCoeff(float mark) {
        float coeff = 1;
        if (mark > 2.5) {
            coeff = (float) sqrt(mark - 2.5 + 1);
        } else if (mark < 2.5) {
            //The absolute value is used to avoid negative values
            coeff = (float) (1 / sqrt(abs(mark - 2.5) + 1));
        }
        return coeff;
    }

    /**
     * Method to update the user karma by multiplying the karma by a computed coefficient
     *
     * @param mark
     * @throws BadEntryException
     */
    public void updateKarma(float mark, float oldMark) throws BadEntryException {
        if ((mark > 5.0) || mark < 0) {
            throw new BadEntryException("La note doit être supérieure à 0 et inférieure ou égale à 5.0");
        }
        this.karma *= 1 / computeCoeff(oldMark); //Remove the old mark coefficient
        this.karma *= computeCoeff(mark); //Add the new mark coefficient
    }

    /**
     * Method to verify the login and password of the member
     *
     * @param login    login of the member adding the review
     * @param password password of the member adding the review
     */
    public boolean login(String login, String password) throws BadEntryException {
        if (login == null || login.replace(" ", "").isEmpty()) {
            throw new BadEntryException("Format du login incorrect. Format: plus de 1 caractère (espaces non compris)");
        }
        if (password == null || password.replace(" ", "").length() < 4) {
            throw new BadEntryException("Format du mot de passe incorrect. Format: plus de 4 caractères (espaces non compris)");
        }
        return login.equals(this.login) && password.equals(this.password);
    }

    /**
     * New toString method for members
     *
     * @return String representative of the member (username ,karma, and profile description)
     */
    public String toString() {
        // Custom toString method to show login and profile description of the object.
        return "Nom utilisateur: " + getLogin() + ", Description du profil: " + getProfile() + ", Karma: " + getKarma();
    }
}
