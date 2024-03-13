package RRPSS;

import java.util.Scanner;
/**
 * This is the boundary class of our restaurant application
 * @author team3
 */
public class RestaurantApp {
	/**
	 * This is the main program whereby we input the parameters to get our output
	 * @param args various arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TableManager TM = new TableManager();
		ReservationManager RM = new ReservationManager();
		OrderManager OM = new OrderManager();
		MenuManager MM = new MenuManager();
		Scanner sc = new Scanner(System.in);
		SalesRevenueReport srr = new SalesRevenueReport();

		// read in the menu every time app starts running
		// MM.readMenu(CSV file);
		int choice, subchoice, subsubchoice;
		do {
			// start UI
			System.out.println("==Restaurant Reservation and Point of Sale System (RRPSS)==");
			System.out.println("(1) Menu");
			System.out.println("(2) Promotion");
			System.out.println("(3) Orders");
			System.out.println("(4) Reservations");
			System.out.println("(5) Table Availability");
			System.out.println("(6) Print Order Invoice");
			System.out.println("(7) Print sales revenue report");
			System.out.println("(8) Exit");
			choice = getChoice(8);
			switch (choice) {
			case 1: // Menu 
				do {
					MM.viewMenu();
					System.out.println("(1) Create Menu Item");
					System.out.println("(2) Update Menu Item");
					System.out.println("(3) Delete Menu Item");
					System.out.println("(4) Exit to main");
					subchoice = getChoice(4);
					switch (subchoice) {
					case 1:
						if (MM.createItem() == true) {
							System.out.println("Menu Item created successfully");
						}
						break;
					case 2:
						if (MM.updateItem() == true) {
							System.out.println("Menu Item updated successfully");
						}
						break;
					case 3:
						if (MM.removeItem() == true) {
							System.out.println("Menu Item deleted successfully");
						}
						break;
					default:
						break;
					}
				} while (subchoice != 4);
				break;
			case 2: // Promotion 
				do {
					MM.viewPromotion();
					System.out.println("(1) Create Promotion Item");
					System.out.println("(2) Update Promotion Item");
					System.out.println("(3) Delete Promotion Item");
					System.out.println("(4) Exit to main");
					subchoice = getChoice(4);
					switch (subchoice) {
					case 1:
						if (MM.createItem() == true) {
							System.out.println("Promotion Item created successfully");
						}
						break;
					case 2:
						if (MM.updatePromoItem() == true) {
							System.out.println("Promotion Item updated successfully");
						}
						break;
					case 3:
						if (MM.removePromoItem() == true) {
							System.out.println("Promotion Item deleted successfully");
						}
						break;
					default:
						break;
					}
				} while (subchoice != 4);
				break;
			case 3: // Orders 
				do {
					System.out.println("ORDERS");
					System.out.println("(1) Create Order");
					System.out.println("(2) Edit Order");
					System.out.println("(3) Exit to main");
					subchoice = getChoice(3);
					switch (subchoice) {
					case 1:
						if(OM.createOrder(RM))
						{
							System.out.println("Orders created successfully");
						}
						break;
					case 2:
						if (OM.noOfOrders() == 0) {
							System.out.println("Order List is empty!\n");
							break;
						}
						System.out.println("Select Order ID to edit");
						int oNum = sc.nextInt();
						if (!OM.validateOrderID(oNum)) {
							System.out.println("Order does not exist!\n");
							break;
						}
						OM.viewOrder(oNum);
						do {
							System.out.println("(1) Add items");
							System.out.println("(2) Remove items");
							System.out.println("(3) Exit to main");
							subsubchoice = getChoice(3);
							switch (subsubchoice) {
							case 1:
								if(OM.addOrderItems(MM, oNum))
								{
									System.out.println("Item added successfully");
								}
								break;
							case 2:
								if(OM.removeOrderItems(MM, oNum))
								{
									System.out.println("Item removed successfully");
								}
								break;
							default:
								break;
							}
						} while (subsubchoice != 3);
						break;
					default:
						break;
					}
				} while (subchoice != 3);
				break;
			case 4: // Reservations 
				do {
					System.out.println("RESERVATIONS");
					RM.viewAllReservations();
					System.out.println("(1) Create Reservation");
					System.out.println("(2) Remove Reservations");
					System.out.println("(3) View Reservations"); 
					System.out.println("(4) Exit to main");
					subchoice = getChoice(4);
					switch (subchoice) {
					case 1:
						RM.createReservation(TM);
						RM.autoDelete();
						break;
					case 2:
						if (RM.deleteReservation(TM)) {
						}
						break;
					case 3:
						RM.viewReservation();
						break;
					default:
						break;
					}
				} while (subchoice != 4);
				break;
			case 5: // Table Availability 
				System.out.println("===TABLE===");
				TM.displayAvailableTables();
				break;
			case 6: // Print Order Invoice
				OM.makePayment(RM, TM);
				break;
			case 7: // Print sales revenue report 
				int sDay, sMonth, sYear, eDay, eMonth, eYear;
				do {
					System.out.println("Frequency");
					System.out.println("(1) Day");
					System.out.println("(2) Month");
					System.out.println("(3) Year");
					System.out.println("(4) Exit to main");
					subchoice = getChoice(4);
					System.out.println("Period");
					switch (subchoice) {
					case 1:
						boolean valid = true;
						System.out.println("Input Start Day: ");
						sDay = sc.nextInt();
						System.out.println("Input Start Month: ");
						sMonth = sc.nextInt();
						System.out.println("Input Start Year: ");
						sYear = sc.nextInt();
						System.out.println("Input End Day: ");
						eDay = sc.nextInt();
						System.out.println("Input End Month: ");
						eMonth = sc.nextInt();
						System.out.println("Input End Year: ");
						eYear = sc.nextInt();
						if (sDay<1 || sMonth<1 || sYear<1 || eDay<1 || eMonth<1 || eYear<1) {
							valid = false;
						}
						else if (!validateDate(sDay, sMonth)) {
							valid = false;
						}
						else if (!validateDate(eDay, eMonth)) {
							valid = false;
						}
						// validate year
						else if (eYear<sYear) {
							valid = false;
						}
						// validate month
						else if ((eYear == sYear) && (eMonth<sMonth)) {
							valid = false;
						}
						//validate day
						else if ((eMonth == sMonth) && (eDay<sDay)) {
							valid = false;
						}
						while (valid == false) {
							System.out.println("Invalid date range! Try again!");
							valid = true;
							System.out.println("Input Start Day: ");
							sDay = sc.nextInt();
							System.out.println("Input Start Month: ");
							sMonth = sc.nextInt();
							System.out.println("Input Start Year: ");
							sYear = sc.nextInt();
							System.out.println("Input End Day: ");
							eDay = sc.nextInt();
							System.out.println("Input End Month: ");
							eMonth = sc.nextInt();
							System.out.println("Input End Year: ");
							eYear = sc.nextInt();
							if (sDay<1 || sMonth<1 || sYear<1 || eDay<1 || eMonth<1 || eYear<1) {
								valid = false;
							}
							else if (!validateDate(sDay, sMonth)) {
								valid = false;
							}
							else if (!validateDate(eDay, eMonth)) {
								valid = false;
							}
							// validate year
							if (eYear<sYear) {
								valid = false;
							}
							// validate month
							else if ((eYear == sYear) && (eMonth<sMonth)) {
								valid = false;
							}
							//validate day
							else if ((eMonth == sMonth) && (eDay<sDay)) {
								valid = false;
							}
						}
						System.out.println("SALES REVENUE REPORT");
						srr.printReport(OM, sYear, eYear, sMonth, eMonth, sDay, eDay);
						break;
					case 2:
						valid = true;
						System.out.println("Input Start Month: ");
						sMonth = sc.nextInt();
						System.out.println("Input Start Year: ");
						sYear = sc.nextInt();
						System.out.println("Input End Month: ");
						eMonth = sc.nextInt();
						System.out.println("Input End Year: ");
						eYear = sc.nextInt();
						if (sMonth<1 || sYear<1 || eMonth<1 || eYear<1) {
							valid = false;
						}
						// validate year
						else if (eYear<sYear) {
							valid = false;
						}
						// validate month
						else if ((eYear == sYear) && (eMonth<sMonth)) {
							valid = false;
						}
						while (valid == false) {
							System.out.println("Invalid date range! Try again!");
							valid = true;
							System.out.println("Input Start Month: ");
							sMonth = sc.nextInt();
							System.out.println("Input Start Year: ");
							sYear = sc.nextInt();
							System.out.println("Input End Month: ");
							eMonth = sc.nextInt();
							System.out.println("Input End Year: ");
							eYear = sc.nextInt();
							if (sMonth<1 || sYear<1 || eMonth<1 || eYear<1) {
								valid = false;
							}
							// validate year
							if (eYear<sYear) {
								valid = false;
							}
							// validate month
							else if ((eYear == sYear) && (eMonth<sMonth)) {
								valid = false;
							}
						}
						System.out.println("SALES REVENUE REPORT");
						srr.printReport(OM, sYear, eYear, sMonth, eMonth);
						break;
					case 3:
						System.out.println("Input Start Year: ");
						sYear = sc.nextInt();
						System.out.println("Input End Year: ");
						eYear = sc.nextInt();
						while (eYear<sYear || eYear<1 || sYear<1) {
							System.out.println("Invalid date range! Try again!");
							System.out.println("Input Start Year: ");
							sYear = sc.nextInt();
							System.out.println("Input End Year: ");
							eYear = sc.nextInt();
						}
						System.out.println("SALES REVENUE REPORT");
						srr.printReport(OM, sYear, eYear);
						break;
					default:
						break;
					}
				} while (subchoice != 4);
				break;
			default: // 8) quit
				break;
			}
		} while (choice != 8);
		System.exit(0);
	}
	/**
	 * This method gets the user input within the maximum int allowed
	 * @param max max int allowed
	 * @return choice user's input
	 */
	public static int getChoice(int max) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter choice: ");
		int choice = sc.nextInt();
			while (choice < 1 || choice > max) {
				System.out.println("Invalid, try again: ");
				choice = sc.nextInt();
			}
			return choice;
	}
	/**
	 * Checking the validity of the date inputed by user
	 * @param day day input
	 * @param month month input
	 * @return status of validation
	 */
	public static boolean validateDate(int day, int month) {
		// 30 days - months: 4, 6, 9, 11
		// 31 days - months: 1, 3, 5, 7, 8, 10, 12

		if (day>31 || month>12) {
			return false;
		}
		else if (month == 2) {
			if (day>28) {
				return false;
			}
		}
		else if (month == 4 || month == 6 || month == 9 || month == 11) {
			if (day>30) {
				return false;
			}
		}
		return true;
	}
}
