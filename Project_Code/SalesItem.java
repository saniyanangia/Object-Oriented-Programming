package RRPSS;
/**
 * This represents a single SalesItem 
 * @author Team3
 *
 */
public class SalesItem {
	/**
	 * Attribute related to a SalesItem
	 */
    private String name;
    private int count;
    /**
     * Creating a salesItem count
     * @param n name of SalesItem
     */
    public SalesItem(String n) {
        name = n;
        count = 1;
    }
    /**
     * This function increase the count of an item by 1 when it is found
     */
    public void incrementCount() {
        count++;
    }
    /**
     * This method get the name of the item to be included in the SalesRevenueReport
     * @return name
     */
    public String getItemName() {
        return name;
    }
    /**
     * This method get the count of the item to be included in the SalesRevenueReport
     * @return count
     */
    public int getCount() {
        return count;
    }
}