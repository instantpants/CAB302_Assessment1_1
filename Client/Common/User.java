package Common;

/**
 * This class stores info about a user
 *
 * @version 0
 * @see Listing
 * @see OrganisationalUnit
 */
public class User {
    private String username;
    private OrganisationalUnit organisationalUnit;

    /**
     * Creates a new user
     *
     * @param username username of this user
     * @param organisationalUnit organisational unit of this user
     */
    public User(String username, OrganisationalUnit organisationalUnit) {
        this.username = username;
        this.organisationalUnit = organisationalUnit;
    }

    /**
     * Generates a new Program.Asset sell order
     *
     * @param asset Program.Asset to be listed
     * @param quantity quantity of assets to be listed
     * @param credits price per unit
     */
    public void CreateListing(Asset asset, int quantity, int credits) {

    }

    /**
     * Removes a specific listing from the DB
     *
     * @param listing listing to be removed
     */
    // may also want a separate UpdateListing()
    public void RemoveListing(Listing listing) {

    }

    /**
     * Generates a new Program.Asset buy order
     *
     * @param asset Program.Asset to be ordered
     * @param quantity quantity of assets to be ordered
     * @param credits price per unit
     */
    public void PlaceOrder(Asset asset, int quantity, int credits) {

    }

    /**
     * Creates a graphical interface for this user to interact with
     */
    public void BuildGUI() {

    }
}
