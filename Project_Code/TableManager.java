package RRPSS;
/**
 * Manages Tables
 * @author Team3
 *
 */
public class TableManager {
    private Table[] tables;
    /**
     * Creating 30 tables for our restaurant
     */
    public TableManager() {
        tables = new Table[30]; // 30 TABLES
        // 10 table of 2
        // 8 table of 4
        // 6 table of 6
        // 4 table of 8
        // 2 table of 10
        int qty, tableNo = 1;
        for (int cap = 2; cap <= 10; cap += 2) {
            qty = 12 - cap;
            for (int i = 0; i < qty; i++) {
                tables[tableNo - 1] = createTable(tableNo, cap);
                tableNo++;
            }
        }
    }
    /**
     * Creating table with the following parameters:
     * @param tableNo
     * @param capacity
     * @return newTable table being created
     */
    private Table createTable(int tableNo, int capacity) {
        Table t = new Table(tableNo, capacity);
        return t;
    }
    /**
     * This method display available tables based on the status of the table
     */
    public void displayAvailableTables() {
        String status;
        for (int i = 0; i < tables.length; i++) {
            if (tables[i].getStatus() == false) {
                status = "Reserved";
            } else {
                status = "Empty";
            }
            System.out.println("Table " + tables[i].getTableNo() + " : " + status);
        }
    }
    /**
     * Gets the status of this table
     * @param tableNo table number
     * @return status of the table
     */
    public boolean getTableStatus(int tableNo) {
        return tables[tableNo - 1].getStatus();
    }
    /**
     * Gets the table number of this table
     * @param tableNo table number
     * @return capacity of this table
     */
    public int getTableCap(int tableNo) {
        return tables[tableNo - 1].getTableCap();
    }
    /**
     * Gets the number of diners of this table
     * @param tableNo table number
     * @return tablePax number of diners on this table
     */
    public int getTablePax(int tableNo) {
        return tables[tableNo - 1].getTablePax();
    }
    /**
     * Setting the status of this table
     * @param tableNo table number
     * @param s status of the table
     */
    public void setTableStatus(int tableNo, boolean s) {
        if (s == true) {
            tables[tableNo - 1].setStatus(true);
        }

        if (s == false) {
            tables[tableNo - 1].setStatus(false);
        }
    }

}
