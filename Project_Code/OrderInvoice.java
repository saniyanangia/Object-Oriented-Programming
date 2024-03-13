package RRPSS;

import java.time.LocalDate;
import java.util.*;
/**
 * Representing a single orderInvoice
 * @author lousycoder
 *
 */
public class OrderInvoice {
	/**
	 * The attributes related to a orderInvoice
	 */
    private int orderID;
    private int tableNo;
    private LocalDate orderDate;
    private boolean membershipStatus;
    private double taxes;
    private double discounts;
    private double total;
    private double nett;
    ArrayList <MenuItem> orderItems;
    /**
     * Creates an OrderInvoice
     * @param oID orderID of this order
     * @param tn tableNo of this order
     * @param od orderDate of this order
     * @param ms membershipStatus of this order
     * @param ta taxes of this order
     * @param d discounts of this order
     * @param tol total amount of this order
     * @param ne nett amount payable for this order
     * @param oi orderItems of this order
     */
    public OrderInvoice(int oID, int tn, LocalDate od, boolean ms, double ta, double d, double tol, double ne, ArrayList<MenuItem> oi) {
        orderID = oID;
        tableNo = tn;
        orderDate = od;
        membershipStatus = ms;
        taxes = ta;
        discounts = d;
        total = tol;
        nett = ne;
        orderItems = new ArrayList <MenuItem>();
        orderItems.addAll(oi);
    }

    // GET
    /**
     * Get orderID of this order
     * @return orderID 
     */
    public int getOrderID() {
        return orderID;
    }
    /**
     * Get tableNo of this order
     * @return tableNo
     */
    public int getTableNo() {
        return tableNo;
    }
    /**
     * Get orderDate of this order
     * @return orderDate
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }
    /**
     * Get membershipStatus of this order
     * @return membershipStatus
     */
    public boolean getMembershipStatus() {
        return membershipStatus;
    }
    /**
     * Get taxes of this order
     * @return taxes
     */
    public double getTaxes() {
        return taxes;
    }
    /**
     * Get discounts for this order
     * @return discounts
     */
    public double getDiscounts() {
        return discounts;
    }
    /**
     * Get total amount of this order
     * @return total
     */
    public double getTotal() {
        return total;
    }
    /**
     * Get nett amount payable for this order
     * @return nett
     */
    public double getNett() {
        return nett;
    }
    /**
     * Get orderItems of this order
     * @return orderItems
     */
    public ArrayList<MenuItem> getOrderItems() {
        return orderItems;
    }
}