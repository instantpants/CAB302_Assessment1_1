package Program;

/**
 * This class stores info and methods relating to each Program.Asset
 *
 * @version 0
 * @see Listing
 * @see OrganisationalUnit
 */
public class Asset implements Comparable<Asset>{
    public final String name;

    /**
     * Creates a new Program.Asset
     *
     * @param name name of this Program.Asset
     */
    public Asset(String name) {
        // should probably make a check that 'name' is a valid asset name
        this.name = name;
    }

    /**
     * Gets the number of Assets the OU possesses
     *
     * @deprecated assets no longer store their own quantities
     * @return number of units
     */
    public int GetQuantity() {
        return 0;
    }

    /**
     * Gets the number of Assets the OU has currently listed for trading
     *
     * @deprecated
     * @return number of units
     */
    public int GetQuantityListed() {
        return 0;
    }

    // read JavaDoc comments for ideas on exceptions etc
    @Override
    public int compareTo(Asset asset) {
        return 0;
    }
}
