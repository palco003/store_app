package cen4270.clients;

import cen4270.models.Item;

/**
 * Created by pabloalcocer on 9/26/16.
 */
public interface InventoryClientInterface {
    Item getItem(String itemID);
}
