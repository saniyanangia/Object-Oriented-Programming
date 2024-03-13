package RRPSS;
/**
 * Represents a single table item
 * @author Chaos
 *
 */
public class Table {
	/**
	 * Attributes of a table:
	 */
    private boolean status;
    private int tableNo;
    private int tablePax;
    private int capacity;
    /**
     * Creating a new table based on the following parameters
     * @param t table number
     * @param c capacity
     */
    public Table(int t, int c) {
        status = true;  // RESERVED - false, AVAILABLE - true
        tableNo = t;
        tablePax = 0;
        capacity = c;
    }

    // GET
    /**
     * Gets the status of this table
     * @return status
     */
    public boolean getStatus() {
        return status;
    }
    /**
     * Gets the table number of this table
     * @return tableNo
     */
    public int getTableNo() {
        return tableNo;
    }
    /**
     * Gets the number of diners on this table
     * @return tablePax
     */
    public int getTablePax() {
        return tablePax;
    }
    /**
     * Gets the capacity of this table
     * @return capacity
     */
    public int getTableCap() {
        return capacity;
    }

    // SET
    /**
     * Set the status of the table
     * @param s status of table
     */
    public void setStatus(boolean s) {
        status = s;
    }
}
