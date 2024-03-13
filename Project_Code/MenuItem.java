package RRPSS;
/**
 * Represent a single menu item, can be ala carte or promotional set
 * @author handsome
 */
public class MenuItem {
	/**
	 * The attributes related to a menuItem: ID, itemType, itemName, description and price
	 */
    private int menuItemID;
    private int itemType; // 1-Main Course, 2-Drinks, 3-Desserts, 4-Promotional Package
    private String itemName;
    private String description;
    private double price;
    /**
     * Creates a new menuItem with the following attributes
     * @param menuItemID this item's menuItemID	
     * @param itemType this item's itemType
     * @param itemName this item's itemName
     * @param description this item's description
     * @param price this item's price
     */
    public MenuItem(int menuItemID, int itemType, String itemName, String description, double price) {
        this.setMenuItemID(menuItemID);
        this.setItemType(itemType);
        this.setItemName(itemName);
        this.setDescription(description);
        this.setPrice(price);
    }

    // GET
    /**
     * Get this item's menuItemID
     * @return this item's menuItemID
     */
    public int getMenuItemID() {
        return this.menuItemID;
    }
    /**
     * Get this item's itemType
     * @return this item's itemType
     */
    public int getItemType() {
        return this.itemType;
    }
    /**
     * Get this item's itemName
     * @return this item's itemName
     */
    public String getItemName() {
        return this.itemName;
    }
    /**
     * Get this item's description
     * @return this item's description
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Get this item's price
     * @return this item's price
     */
    public double getPrice() {
        return this.price;
    }
 
    // SET
    /**
     * set this menuItemID
     * @param menuItemID of this item
     */
    public void setMenuItemID(int menuItemID) {
        this.menuItemID = menuItemID;
    }
    /**
     * set this itemType
     * @param itemType of this item
     */
    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
    /**
     * set this itemName
     * @param itemName of this item
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    /**
     * set this description
     * @param description of this item
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * set this price
     * @param price of this item
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
