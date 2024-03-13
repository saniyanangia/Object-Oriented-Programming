package RRPSS;

import java.time.LocalDate;
import java.util.*;
/**
 * Represents a single order made by a customer
 * @author Team3
 *
 */
public class Order {
	/**
	 * The attributes relating to a order
	 */
    private int orderID;
    ArrayList<MenuItem> orderItems;
    private int staffID;
    private LocalDate orderDate;
    private double total;
    private int reservationID;
    private int tableNo;
    /**
     * Creates an orderItem with the following attributes
     * @param oID orderID of this order
     * @param sID staffID of this order
     * @param od orderDate of this order
     * @param rID reservationID of this order
     * @param tn tableNo of this order
     */
    public Order(int oID, int sID, LocalDate od, int rID, int tn) {
        orderID = oID;
        orderItems = new ArrayList<MenuItem>();
        staffID = sID;
        orderDate = od;
        total = 0.0;
        reservationID = rID;
        tableNo = tn;
    }

    // GET
    /**
     * This method gets the orderID of this order
     * @return orderID
     */
    public int getOrderID() {
        return orderID;
    }
    /**
     * This method gets the list of orderItem of this order
     * @return orderItems
     */
    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }
    /**
     * This method gets the staffID of the staff who placed this order
     * @return staffID
     */
    public int getStaffID() {
        return staffID;
    }
    /**
     * This method gets the date of this order
     * @return orderDate
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }
    /**
     * This method gets the total amount payable for this order
     * @return total
     */
    public double getTotal() {
        return total;
    }
    /**
     * This method gets the reservationID for this order
     * @return reservationID
     */
    public int getReservationID() {
        return reservationID;
    }
    /**
     * This method gets the tableNo of this order
     * @return tableNo
     */
    public int getTableNo() {
        return tableNo;
    }
    /**
     * This method adds new orderItem to the orderItems
     * @param newItem item to be added
     */
    public void addOrderItem(MenuItem newItem) {
        orderItems.add(newItem);
        calculateTotal();
    }
    /**
     * This method removes orderItem from orderItems
     * @param unwantedItem item to be removed
     */
    public void removeOrderItem(MenuItem unwantedItem) {
        orderItems.remove(unwantedItem);
        calculateTotal();
    }
    /**
     * This method returns the total amount payable for the orderItems
     * @return t
     */
    public double calculateTotal() {
        double t = 0;
        for (int i = 0; i < orderItems.size(); i++) {
            t += orderItems.get(i).getPrice();
        }
        total = t;
        return t;
    }
}