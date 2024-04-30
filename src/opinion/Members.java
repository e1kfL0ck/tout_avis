package opinion;

import exceptions.BadEntryException;

public class Members {
    // Class attribute
    private String login;
    private String password;
    private String profile;

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
    public boolean areYou(String l) {
        // Remove trailing white spaces + make a non-case sensitive string comparison with the current object login field.
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

    public boolean login(String login, String password) throws BadEntryException{
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
     * @return String representative of the member (username and profile description)
     */
    public String toString() {
        // Custom toString method to show login and profile description of the object.
        return "Nom utilisateur: " + getLogin() + ", Description du profil: " + getProfile();
    }
}
