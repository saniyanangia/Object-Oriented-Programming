package RRPSS;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * This represents a single reservation
 * @author team3
 *
 */
public class Reservation {
	/**
	 * Attributes of a reservation:
	 */
	private int numPax;
	private String name;
	private long contact;
	private LocalDate reservationDate;
	private LocalTime reservationTime;
	private int tableNo;
	private int reservationID;
	private int orderID;
	/**
	 * This creates a new reservation given the following parameters
	 * @param np number of customers
	 * @param n name of customer
	 * @param c contact number of customer
	 * @param rd reservation date
	 * @param rt reservation time
	 * @param tn tableNo assigned
	 * @param rid reservationID of this reservation
	 */
	public Reservation(int np, String n, long c, LocalDate rd, LocalTime rt, int tn, int rid) {
		numPax = np;
		name = n;
		contact = c;
		reservationDate = rd;
		reservationTime = rt;
		tableNo = tn;
		reservationID = rid;
		orderID = -1;
	}
	/**
	 * Gets the reservationID of this reservation
	 * @return reservationID
	 */
	public int getReservationID() {
		return reservationID;
	}
	/**
	 * Gets the name of customer of this reservation
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the contact number of customer for this reservation
	 * @return contact
	 */
	public long getContact() {
		return contact;
	}
	/**
	 * Gets the number of diners for this reservation
	 * @return numPax
	 */
	public int getNumPax() {
		return numPax;
	}
	/**
	 * Gets the reservation date for this reservation
	 * @return reservationDate
	 */
	public LocalDate getReservationDate() {
		return reservationDate;
	}
	/**
	 * Gets the reservation time for this reservation
	 * @return reservationTime
	 */
	public LocalTime getReservationTime() {
		return reservationTime;
	}
	/**
	 * Gets the table number for this reservation
	 * @return tableNo
	 */
	public int getTableNo() {
		return tableNo;
	}
	/**
	 * Gets the orderID for this reservation
	 * @return orderID
	 */
	public int getOrderID() {
		return orderID;
	}
	/**
	 * Sets the orderID for this reservation
	 * @param oID of order
	 */
	public void setOrderID(int oID) {
		orderID = oID;
	}
}
