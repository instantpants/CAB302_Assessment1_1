package Program;

import java.time.Instant;
import java.util.Date;

/**
 * This class stores info about a trade listing
 *
 * @version 0
 * @see Asset
 */
public class Listing {
    public final boolean isBuying;
    public final Asset asset;
    public int quantity;
    public int price;
    public final OrganisationalUnit organisationalUnit;
    public Date date;

    /**
     * Creates a new listing
     *
     * @param isBuying true if listing is a buy order, false if it is a sell order
     * @param asset asset to be listed
     * @param quantity quantity of assets in order
     * @param price unit price of asset
     * @param organisationalUnit OU that this order belongs to
     */
    public Listing(boolean isBuying, Asset asset, int quantity, int price, OrganisationalUnit organisationalUnit) {
        this.isBuying = isBuying;
        this.asset = asset;
        this.quantity = quantity;
        this.price = price;
        this.organisationalUnit = organisationalUnit;
        // check this line
        this.date = Date.from(Instant.now());
    }

    /**
     * Sends a notification of a successful purchase
     */
    public void PushNotification() {

    }
}
