package Program;

import java.util.TreeMap;

/**
 * This class stores info about an Organisational Unit and its resources
 *
 * @version 0
 * @see User
 */
public class OrganisationalUnit {
    public String name;
    private int credits = 0;
    private TreeMap<Asset, Integer> AssetList = new TreeMap<>();

    /**
     * Creates a new Organisational Unit
     *
     * @param name name of this OU
     */
    public OrganisationalUnit(String name) {
        this.name = name;
    }
}
