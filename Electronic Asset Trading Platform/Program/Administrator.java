package Program;

/**
 * This class extends on the Program.User class, with added permissions/functionality
 *
 * @version 0
 * @see User
 */
public class Administrator extends User {
    /**
     * Creates a new administrator
     *
     * @param username username of this administrator
     */
    public Administrator(String username) {
        super(username, null/*name of IT OU*/);
    }

    /**
     * Creates a new asset type
     *
     * @param name name of the new asset
     */
    public void CreateAsset(String name) {

    }

    /**
     * Creates a new organisational unit
     *
     * @param name name of the new organisational unit
     */
    public void CreateOrganisationalUnit(String name) {

    }

    /**
     * Creates a new user
     * @param username username of the new user
     */
    public void CreateNewUser(String username) {

    }

    /**
     * Creates a new administrator
     * @param username username of the new administrator
     */
    public void CreateNewAdmin(String username) {

    }

    /**
     * Creates a privileged graphical interface for this administrator
     */
    @Override
    public void BuildGUI() {
        super.BuildGUI();
    }
}
