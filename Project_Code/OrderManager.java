package RRPSS;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
/**
 * Manages Order
 * @author team3
 *
 */
public class OrderManager {
	/**
	 * Creating list for orders, orderItems and OrderInvoice
	 */
    ArrayList<Order> orders = new ArrayList<Order>();
    ArrayList<MenuItem> orderItems;
    ArrayList<OrderInvoice> invoices = new ArrayList<OrderInvoice>();
    private int currentID = 1; // ID restarts at 1000
    /**
     * Inserting test case 
     */
    public void testing() {
        // Sample Order
        // LocalDate d1 = LocalDate.of(2021, 1, 13);
        // LocalDate d2 = LocalDate.of(2021, 1, 20);
        // LocalDate d3 = LocalDate.of(2021, 11, 1);
        // LocalDate d4 = LocalDate.of(2021, 12, 28);
        // Order o1 = new Order(1, 1, d1, 1, 10);
        // Order o2 = new Order(2, 29, d2, 4, 12);
        // Order o3 = new Order(3, 5, d3, 10, 3);
        // Order o4 = new Order(4, 10, d4, 18, 11);
        // orders.add(o1);
        // orders.add(o2);
        // orders.add(o3);
        // // orders.add(o4);
        // MenuItem m1 = new MenuItem(1, 1, "Steak", "description", 10);
        // MenuItem m2 = new MenuItem(2, 2, "Ice Lemon Tea", "description", 3);
        // MenuItem m3 = new MenuItem(3, 3, "Muffin", "description", 5);
        // MenuItem m4 = new MenuItem(4, 4, "Set A", "Steak + Ice Lemon Tea", 20);
        
        // orders.get(0).addOrderItem(m1);
        // orders.get(0).addOrderItem(m2);
        // orders.get(1).addOrderItem(m2);
        // orders.get(1).addOrderItem(m4);
        // orders.get(2).addOrderItem(m1);
        // orders.get(2).addOrderItem(m2);
        // orders.get(2).addOrderItem(m3);
        // orders.get(3).addOrderItem(m1);

        // Sample Invoice
        // ArrayList<MenuItem> oei1 = new ArrayList<MenuItem>();
        // oei1.addAll(o1.getOrderItems());
        // ArrayList<MenuItem> oei2 = new ArrayList<MenuItem>();
        // oei2.addAll(o2.getOrderItems());
        // ArrayList<MenuItem> oei3 = new ArrayList<MenuItem>();
        // oei3.addAll(o3.getOrderItems());
        // ArrayList<MenuItem> oei4 = new ArrayList<MenuItem>();
        // oei4.addAll(o4.getOrderItems());
        // LocalDate de1 = LocalDate.of(2021, 1, 13);
        // LocalDate de2 = LocalDate.of(2021, 1, 20);
        // LocalDate de3 = LocalDate.of(2021, 11, 1);
        // LocalDate de4 = LocalDate.of(2021, 12, 28);
        // OrderInvoice oi1 = new OrderInvoice(50, 1, de1, true, 5.0445, 0.05, 30, 33.5445, oei1);
        // OrderInvoice oi2 = new OrderInvoice(51, 34, de2, false, 3.54, 0.0, 20, 23.54, oei2);
        // OrderInvoice oi3 = new OrderInvoice(52, 21, de3, false, 10.62, 0.0, 60, 70.62, oei3);
        // OrderInvoice oi4 = new OrderInvoice(53, 35, de4, true, 8.4075, 0.05, 50, 55.9075, oei4);
        // invoices.add(oi1);
        // invoices.add(oi2);
        // invoices.add(oi3);
        // invoices.add(oi4);
    }
    /**
     * Creating a test case orders
     */
    public OrderManager() {
        // testing();
    }
    /**
     * This method creates a new order
     * @param RM
     * @return status
     */
    public boolean createOrder(ReservationManager RM) {
        Order newOrder;

        int orderID, staffID, reservationID, tableNo;
        LocalDate orderDate;

        System.out.println("\n-----Create Order-----");

        // orderID
        if (currentID == 1000) {
            currentID = 1;
            orderID = currentID;
        } else {
            orderID = ++currentID;
        }

        // staffID
        System.out.print("Enter Staff ID: ");
        Scanner oc = new Scanner(System.in);
        staffID = oc.nextInt();
        while (staffID < 1 || staffID > 50) {
            System.out.print("Invalid Input! Enter Staff ID: ");
            staffID = oc.nextInt();
        }

        // reservationID
        System.out.print("Enter Reservation ID: ");
        Scanner oa = new Scanner(System.in);
        reservationID = oa.nextInt();
        while (reservationID < 1 || reservationID > 50) {
            System.out.print("Invalid Input! Enter Reservation ID: ");
            reservationID = oa.nextInt();
        }
        // Check reservation existence & assign order if valid
        boolean valid = RM.createOrder(reservationID, orderID);
        if (!valid) {
            // revoke orderID
            if (orderID == 1) {
                currentID = 1000;
            } else {
                currentID = --orderID;
            }
            // oa.close();
            // oc.close();
            return false;
        }

        // orderDate
        orderDate = RM.getReservationDate(reservationID);

        // tableNo
        tableNo = RM.getTableNo(reservationID);

        newOrder = new Order(orderID, staffID, orderDate, reservationID, tableNo);
        orders.add(newOrder);
        System.out.println("Order ID: "+orderID+"\n");
        // oa.close();
        // oc.close();
        return true;
    }
    /**
     * This method is called when the customer calls for the bill
     * @param RM 
     * @param TM
     * @return status
     */
    public boolean makePayment(ReservationManager RM, TableManager TM) {
        boolean valid = false;

        Order paidOrder = null;
        int orderID;

        System.out.println("\n-----Make Order Payment-----");

        if (orders.size() == 0) {
            System.out.println("Order list is empty!");
            return false;
        }

        System.out.print("Enter Order ID: ");
        Scanner ob = new Scanner(System.in);
        orderID = ob.nextInt();
        // Check order existence
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == orderID) {
                paidOrder = orders.get(i);
                valid = true;
            }
        }
        while (!valid) {
            System.out.print("Invalid Input! Enter Order ID: ");
            orderID = ob.nextInt();
            // Check order existence
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getOrderID() == orderID) {
                    paidOrder = orders.get(i);
                    valid = true;
                }
            }
        }

        // Create invoice
        int tableNo = paidOrder.getTableNo();
        LocalDate orderDate = paidOrder.getOrderDate();
        boolean membershipStatus;
        ArrayList<MenuItem> orderItems = new ArrayList<MenuItem>();

        System.out.print("Enter Membership status (Yes/No): ");
        Scanner od = new Scanner(System.in);
        String ms = od.nextLine();
        while (!ms.equalsIgnoreCase("Yes") && !ms.equalsIgnoreCase("No")) {
            System.out.print("\nInvalid input! Enter Membership status (Yes/No): ");
            ms = od.nextLine();
        }
        membershipStatus = ms.equalsIgnoreCase("Yes");

        double discounts, total = paidOrder.getTotal();
        if (membershipStatus) {
            discounts = 0.05;
        } else {
            discounts = 0.0;
        }
        double nett = total * (1.0 - discounts) * 1.1 * 1.07;
        double taxes = nett - total * (1.0 - discounts);

        orderItems.addAll(paidOrder.getOrderItems());

        OrderInvoice newOrderInvoice = new OrderInvoice(orderID, tableNo, orderDate, membershipStatus, taxes, discounts,
                total, nett, orderItems);
        invoices.add(newOrderInvoice);

        // Print invoice
        System.out.println("==ORDER INVOICE==");
        System.out.println("Invoice ID: " + newOrderInvoice.getOrderID());
        System.out.println("Table No: " + newOrderInvoice.getTableNo());
        System.out.println("Order Date: " + newOrderInvoice.getOrderDate());
        System.out.println("Timestamp: " + LocalTime.now());
        if (newOrderInvoice.getMembershipStatus()) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
        System.out.println("Total: " + newOrderInvoice.getTotal());
        System.out.println("Discounts: " + newOrderInvoice.getDiscounts());
        System.out.println("Taxes: " + newOrderInvoice.getTaxes());
        System.out.println("Total: " + newOrderInvoice.getTotal());
        System.out.println("Nett Amount: " + newOrderInvoice.getNett());

        orderItems = newOrderInvoice.getOrderItems();
        System.out.println("\nORDER ITEMS " + newOrderInvoice.getOrderID());
        for (int i=0; i<orderItems.size(); i++) {
            System.out.println("\tMenu Item ID: " + orderItems.get(i).getMenuItemID());
            System.out.print("\tCategory: ");
            switch (orderItems.get(i).getItemType()) {
            case 1:
                System.out.println("Main Course");
                break;
            case 2:
                System.out.println("Drinks");
                break;
            case 3:
                System.out.println("Desserts");
                break;
            case 4:
                System.out.println("Promotional Package");
                break;
            }
            System.out.println("\tDescription: " + orderItems.get(i).getDescription());
            System.out.println("\tPrice: " + orderItems.get(i).getPrice());
        }

        // Update Table status
        TM.setTableStatus(RM.getTableNo(paidOrder.getReservationID()), true);

        // Remove reservation and order
        RM.deleteReservation(paidOrder.getReservationID()); 
        orders.remove(paidOrder);
        // ob.close();
        // od.close();
        return true;
    }
    /**
     * This method checks for the orderItems based on orderID
     * @param orderID
     * @return status
     */
    public boolean validateOrderID(int orderID) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == orderID) {
                return true;
            }
        }
        return false;
    }
    /**
     * This method allows user to view the details of this order
     * @param orderID
     */
    public void viewOrder(int orderID) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == orderID) {
                System.out.println("\n______________________");
                System.out.println("ORDER DETAILS (ID: " + orderID + ")\n");
                // Display all items in the order
                System.out.println("Order Items:");
                orderItems = orders.get(i).getOrderItems();
                for (int j = 0; j < orderItems.size(); j++) {
                    System.out.println("\t" + orderItems.get(j).getItemName());
                    System.out.println("\tID: " + orderItems.get(j).getMenuItemID());
                    System.out.print("\tCategory: ");
                    switch (orderItems.get(j).getItemType()) {
                    case 1:
                        System.out.println("Main Course");
                        break;
                    case 2:
                        System.out.println("Drinks");
                        break;
                    case 3:
                        System.out.println("Desserts");
                        break;
                    case 4:
                        System.out.println("Promotional Package");
                        break;
                    }
                    System.out.println("\tDescription: " + orderItems.get(j).getDescription());
                    System.out.println("\tPrice: " + orderItems.get(j).getPrice());
                }
                System.out.println("Staff ID: " + orders.get(i).getStaffID());
                System.out.println("Order Date: " + orders.get(i).getOrderDate());
                System.out.println("Total: " + orders.get(i).getTotal());
                System.out.println("Reservation ID: " + orders.get(i).getReservationID());
                System.out.println("______________________\n");
            }
        }
    }
    /**
     * This method allows user to add newItem to orderItems
     * @param MM
     * @param orderID
     * @return status
     */
    public boolean addOrderItems(MenuManager MM, int orderID) {
        MenuItem newItem = null;

        System.out.println("\n-----Add Item to Order-----");

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == orderID) {
                // Retrieve menu item
                int menuItemID;
                System.out.print("Enter Menu Item ID: ");
                Scanner oe = new Scanner(System.in);
                menuItemID = oe.nextInt();
                newItem = MM.getMenuItem(menuItemID);

                while (newItem == null) {
                    System.out.print("Invalid Input! Enter Menu Item ID: ");
                    menuItemID = oe.nextInt();
                    newItem = MM.getMenuItem(menuItemID);
                }
                // oe.close();

                // Add item in within Order class
                orders.get(i).addOrderItem(newItem);
                return true;
            }
        }
        return false;
    }
    /**
     * This method removes unwantedItem from orderItems
     * @param MM
     * @param orderID
     * @return status
     */
    public boolean removeOrderItems(MenuManager MM, int orderID) {
        MenuItem unwantedItem = null;

        System.out.println("\n-----Remove Item from Order-----");

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == orderID) {
                // Check if order has items to remove
                if (orders.get(i).getOrderItems().size() == 0) {
                    System.out.println("Order is empty!\n");
                    return false;
                }

                // Retrieve menu item
                int menuItemID;
                System.out.print("Enter Menu Item ID: ");
                Scanner of = new Scanner(System.in);
                menuItemID = of.nextInt();
                unwantedItem = MM.getMenuItem(menuItemID);
                while (unwantedItem == null) {
                    System.out.print("Invalid Input! Enter Menu Item ID: ");
                    menuItemID = of.nextInt();
                    unwantedItem = MM.getMenuItem(menuItemID);
                }
                // of.close();

                // Remove item within Order class
                orders.get(i).removeOrderItem(unwantedItem);
                return true;
            }
        }
        return false;
    }
    /**
     * This method allows user to print periodic OrderInvoices
     * @param sld
     * @param eld
     * @return dateInvoice
     */
    public ArrayList<OrderInvoice> getInvoiceInRange(LocalDate sld, LocalDate eld) {
        ArrayList<OrderInvoice> dateInvoice = new ArrayList<OrderInvoice>();

        for (int i = 0; i < invoices.size(); i++) {
            if ((invoices.get(i).getOrderDate().isAfter(sld) || invoices.get(i).getOrderDate().isEqual(sld))
                    && (invoices.get(i).getOrderDate().isBefore(eld) || invoices.get(i).getOrderDate().isEqual(eld))) {
                dateInvoice.add(invoices.get(i));
            }
        }
        return dateInvoice;
    }
    /**
     * This method allows user to get the details of this order
     * @param orderID
     * @return OrderInvoice
     */
    public OrderInvoice getInvoice(int orderID) {
        for (int i = 0; i < invoices.size(); i++) {
            if (invoices.get(i).getOrderID() == orderID) {
                return invoices.get(i);
            }
        }
        return null;
    }

    public int noOfOrders() {
        return orders.size();
    }
}
