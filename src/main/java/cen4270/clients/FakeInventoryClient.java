package cen4270.clients;

import cen4270.models.Item;

/**
 * Created by pabloalcocer on 9/26/16.
 */
public class FakeInventoryClient implements InventoryClientInterface {
    public String itemID;
    public Item item;

    public FakeInventoryClient(){
        item = new Item("1", "Book", 5, 1);
    }

    public Item getItem(String itemID) {
        this.itemID = itemID;
        if(itemID.equals("1")){
            return item;
        } else {
            return null;
        }
    }
}
