package RRPSS;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.lang.Object;

/**
 * Manager of Reservation
 * @author Team 3
 */
public class ReservationManager {
	/**
	 * Creating list of Reservations
	 */
	ArrayList<Reservation> reservationList;
	Reservation newReservation;
	private int reservationID;
	private int currentID; // ID restarts at 1000
	private int Request = 0; //Request is 0 until a confirmed table has been allocated and no further timing changes are needed

	Scanner sc = new Scanner(System.in);

	Timer downloadTimer = new Timer();
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Timer timer = new Timer();

	/**
	 * Manage Reservation
	 */
	public ReservationManager() {
		currentID = 1;
		Request = 0;
		reservationList = new ArrayList<Reservation>();
		// testing();
	}
    /**
     * Creating a test case of reservations
     */
	public void testing() {
		// LocalDate d1 = LocalDate.of(2022, 1, 13);
		// LocalDate d2 = LocalDate.of(2022, 2, 13);
		// LocalDate d3 = LocalDate.of(2022, 11, 1);
		// LocalDate d4 = LocalDate.of(2022, 11, 1);
		// LocalDate d5 = LocalDate.of(2022, 12, 28);

		// LocalTime t1 = LocalTime.of(05, 00, 00);
		// LocalTime t2 = LocalTime.of(11, 00, 00);
		// LocalTime t3 = LocalTime.of(18, 00, 00);
		// LocalTime t4 = LocalTime.of(18, 00, 00);
		// LocalTime t5 = LocalTime.of(01, 00, 00);

		// // SAMPLE RESERVATIONS
		// Reservation r1 = new Reservation(2, "Saniya", 91111111, d1, t1, 1, 1);
		// Reservation r2 = new Reservation(4, "Junhan", 92222222, d2, t2, 11, 2);
		// Reservation r3 = new Reservation(5, "Rochana", 93333333, d3, t3, 19, 3);
		// Reservation r4 = new Reservation(6, "Xiangyu", 94444444, d4, t4, 20, 4);
		// Reservation r5 = new Reservation(9, "Rhea", 95555555, d5, t5, 29, 5);

		// reservationList.add(r1);
		// reservationList.add(r2);
		// reservationList.add(r3);
		// reservationList.add(r4);
		// reservationList.add(r5);
	}
	/**
	 * Create a new reservation
	 * @param tables
	 * @return status 
	 */
	public boolean createReservation(TableManager tables) {
		Request = 0;

		// checking for valid reservation request input
		System.out.print("\nReservation pax: ");
		int numPax = sc.nextInt();

		while (numPax < 1 || numPax > 10) {
			System.out.print("Invalid number, try again: ");
			numPax = sc.nextInt();
		}

		System.out.print("\nName of reservation holder: ");
		Scanner sd = new Scanner(System.in);
		String name = sd.nextLine();

		while (name.length() < 1) {
			System.out.print("Invalid name, try again: ");
			name = sd.nextLine();
		}

		System.out.print("\nContact no.: ");
		long contact = sc.nextLong();

		while ((contact / 10000000) < 1 || contact / 10000000 > 10) {
			System.out.print("Invalid contact, try again: ");
			contact = sc.nextLong();
		}

		Timechecker: while (Request == 0) {
			int k;

			LocalDate currentDate = LocalDate.now();
			System.out.print("\nPlease provide Reservation details. \nEnter Reservation year (YYYY): ");
			int year = sc.nextInt();
			while (!(2021 <= year && year <= 2022)) {
				{
					System.out.println(
							"Invalid date. Please enter either 2021 or 2022: (Note: We only allow advance reservations until 2022)");
					year = sc.nextInt();
				}
			}
			System.out.print("\nEnter Reservation month (MM): ");
			int month = sc.nextInt(); // 1-12 for January-December.
			while (!(1 <= month && month <= 12)) {
				{
					System.out.println("Please enter a valid month:");
					month = sc.nextInt();
				}
			}
			System.out.print("\nEnter Reservation date (DD): ");
			int date = sc.nextInt();
			while (!(1 <= date && date <= 31)) {
				{
					System.out.println("Please enter a valid date:");
					date = sc.nextInt();
				}
			}

			LocalDate reservationDate = LocalDate.of(year, month, date);

			boolean dateValidity = !(currentDate.isAfter(reservationDate));

			while (dateValidity == false || year > 2025) {
				System.out.print("Invalid date, try again. Enter Reservation year (YYYY): ");
				year = sc.nextInt();
				System.out.print("Enter Reservation month (MM): ");
				month = sc.nextInt(); // 1-12 for January-December.
				System.out.print("Enter Reservation date (DD): ");
				date = sc.nextInt();
				reservationDate = LocalDate.of(year, month, date);
				dateValidity = !(currentDate.isAfter(reservationDate));
			}

			System.out.print(
					"\nPlease provide Reservation timing details in 24 hour format. \nEnter Reservation hour (HH): ");
			int hours = sc.nextInt();
			System.out.print("\nEnter Reservation minute (MM): ");
			int minutes = sc.nextInt();
			LocalTime reservationTime = LocalTime.of(hours, minutes, 00);

			if (currentDate.equals(reservationDate)) {
				LocalTime currentTime = LocalTime.now();
				boolean timeValidity = currentTime.isBefore(reservationTime);

				while (timeValidity == false) {
					System.out.print("\nInvalid time, try again. Enter Reservation hour (HH): ");
					hours = sc.nextInt();
					System.out.print("\nEnter Reservation minute (MM): ");
					minutes = sc.nextInt();
					reservationTime = LocalTime.of(hours, minutes, 00);

					currentTime = LocalTime.now();
					timeValidity = currentTime.isBefore(reservationTime);
				}
			}

			System.out.println("Reservation timing acceptable. Checking table availability...\n");

			int minTableCap, startingTableIndex = 0, tableNo = -1;

			if (numPax % 2 == 0)
				minTableCap = numPax;
			else
				minTableCap = numPax + 1;

			if (minTableCap == 2)
				startingTableIndex = 0;
			else if (minTableCap == 4)
				startingTableIndex = 10;
			else if (minTableCap == 6)
				startingTableIndex = 18;
			else if (minTableCap == 8)
				startingTableIndex = 24;
			else if (minTableCap == 10)
				startingTableIndex = 28;

			TableAssignment: for (k = startingTableIndex; k < 30; k++) {
				if (tables.getTableStatus(k + 1) == false) {
					// check for overlaps in date and timing
					for (int num = 0; num < reservationList.size(); num++) {
						if ((((Reservation) reservationList.get(num)).getReservationDate().equals(reservationDate))) {
							if ((((Reservation) reservationList.get(num)).getReservationTime().plusHours(2)
									.isBefore(reservationTime))
									|| ((Reservation) reservationList.get(num)).getReservationTime().minusHours(2)
											.isAfter(reservationTime)) {
								tableNo = k + 1;
								break TableAssignment;
							}
						}
					}
				}
				if (tables.getTableStatus(k + 1) == true) { // table is available
					tableNo = k + 1;
					break TableAssignment;
				}
			}

			// no tables are available
			if (tableNo == -1) {
				System.out.print(
						"There are no available tables. Would you like to select a different timing or exit reservation?\n"
								+ "Select different timing: Enter 'S'\n" + "Exit Reservation: Press any other key\n");
				char input = sc.next().charAt(0);
				if (input == 's' || input == 'S') {
					continue Timechecker;
				} else {
					return false;
				}
			}

			// available table found
			else {
				// reservationID
				if (currentID == 1000) {
					currentID = 1;
					reservationID = currentID;
				} else {
					reservationID = currentID++;
				}

				tables.setTableStatus(tableNo, false);
				newReservation = new Reservation(numPax, name, contact, reservationDate, reservationTime, tableNo,
						reservationID);
				reservationList.add(newReservation);
				System.out.print("Reservation for " + reservationDate + " at " + reservationTime + " is successful.\n"
						+ "Your Reservation ID is " + reservationID + " and your assigned table is " + tableNo + "\n"
						+ "Please arrive at the restaurant within 15 minutes of your reservation time, or your table will be given to the next customer.\n");
				Request = 1;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Delete a reservation of the user's choice
	 * @param tables
	 * @return status 
	 */
	public boolean deleteReservation(TableManager tables) {
		int reservationID;

		System.out.println("\n-----Remove Reservation-----");

		if (reservationList.size() == 0) {
			System.out.println("Reservation List is empty!");
			return false;
		}

		System.out.println("Enter Reservation ID to be deleted:");
		reservationID = sc.nextInt();
		// Check reservation existence
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getReservationID() == reservationID) {
				if (reservationList.get(i).getOrderID() == -1) {
					tables.setTableStatus(reservationList.get(i).getTableNo(), true);
					reservationList.remove(i);
					return true;
				} else {
					System.out.println("Reservation has been fulfilled!");
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Delete reservation when OrderInvoice is printed
	 * @param reservationID
	 * @return status 
	 */
	public boolean deleteReservation(int reservationID) {
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getReservationID() == reservationID) {
				reservationList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * View all reservations
	 * @return
	 */
	public void viewAllReservations() {
		if (reservationList.size() == 0) {
			System.out.println("There are no existing reservations.");
		}

		else {
			for (int num = 0; num < reservationList.size(); num++) {
				// System.out.print(reservationList.get(num).getReservationID() + " "
				// + reservationList.get(num).getName() + " "
				// + reservationList.get(num).getContact() + " "
				// + reservationList.get(num).getNumPax() + " "
				// + reservationList.get(num).getReservationDate() + " "
				// + reservationList.get(num).getReservationTime() + " "
				// + reservationList.get(num).getTableNo() + " \n");
				System.out.println("Reservation " + num);
				System.out.println("\tID: " + reservationList.get(num).getReservationID());
				System.out.println("\tName: " + reservationList.get(num).getName());
				System.out.println("\tContact: " + reservationList.get(num).getContact());
				System.out.println("\tNo. of Pax: " + reservationList.get(num).getNumPax());
				System.out.println("\tReservation Date: " + reservationList.get(num).getReservationDate());
				System.out.println("\tReservation Time: " + reservationList.get(num).getReservationTime());
				System.out.println("\tTable No.: " + reservationList.get(num).getTableNo() + "\n");
			}
		}
	}
	
	/**
	 * View selected reservation or all reservations (user's choice)
	 * @return 
	 */
	public void viewReservation() {
		System.out.println("Would you like to view a specific reservation or view all reservations?\n"
				+ "(1) View specific reservation\n" + "(2) View all reservations\n" + "Press any other key to exit");

		Scanner sv = new Scanner(System.in);
		char choice = sv.next().charAt(0);
		if (choice == '1') {
			System.out.println("Enter Reservation ID:");
			Scanner sw = new Scanner(System.in);
			int ID = sw.nextInt();
			for (int i = 0; i < reservationList.size(); i++) {
				if (reservationList.get(i).getReservationID() == ID) {
					System.out.println("RESERVATION DETAILS (ID: " + ID + ")");
					System.out.println("Name: " + reservationList.get(i).getName());
					System.out.println("Contact: " + reservationList.get(i).getContact());
					System.out.println("No. of Pax: " + reservationList.get(i).getNumPax());
					System.out.println("Reservation Date: " + reservationList.get(i).getReservationDate());
					System.out.println("Reservation Time: " + reservationList.get(i).getReservationTime());
					System.out.println("Table No.: " + reservationList.get(i).getTableNo() + "\n");
					return;
				}
			}
			System.out.println("Reservation does not exist!");
		} else if (choice == '2') {
			viewAllReservations();
		}
	}
	
	/**
	 * Check if reservation exists so that orderID can be allocated
	 * @param reservationID
	 * @param orderID
	 * @return status 
	 */
	public boolean createOrder(int reservationID, int orderID) {
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getReservationID() == reservationID) {
				if (reservationList.get(i).getOrderID() == -1) {
					reservationList.get(i).setOrderID(orderID);
					return true;
				} else {
					System.out.println("Existing Order under Reservation!");
					return false;
				}
			}
		}
		System.out.println("Reservation does not exist!");
		return false;
	}
	
	/**
	 * Check for expiring reservations
	 * @return 
	 */
	public void autoDelete() {
		TimerTask deletion = new TimerTask() {
			int loop = 50;
			int AutoResult = -1;
			int CompleteResult = -1;

			@Override
			public void run() {
				Execute: while (loop != 0) {
					if (reservationList.size() == 0) {
						break;
					}
					if (reservationList.size() != 0) {
						for (int num = 0; num < reservationList.size(); num++) {
							if (reservationList.get(num).getOrderID() == -1) {
								if (reservationList.get(num).getReservationDate().equals(LocalDate.now())) {
									// 20 minutes approximated by 20 seconds
									if (LocalTime.now().minusSeconds(20)
											.equals(reservationList.get(num).getReservationTime())) {
										AutoResult = num;
										if (AutoResult != -1) {
											deleteActual(AutoResult);
											toolkit.beep();
											System.out.println("Reservation removed!");
											loop--;
										}
									}
								}

							}

							else if (reservationList.get(num).getOrderID() != -1) {
								if (reservationList.get(num).getReservationDate().equals(LocalDate.now())) {
									// 2 hours = 120 minutes approximated by 120 seconds
									if (LocalTime.now().minusSeconds(120)
											.equals(reservationList.get(num).getReservationTime())) {
										CompleteResult = num;
										if (CompleteResult != -1) {
											deleteActual(CompleteResult);
											toolkit.beep();
											System.out.println("Reservation removed!");
											loop--;
										}
									}
								}

							}
						}
					}
					continue Execute;
				}
				timer.cancel();
			}
		};

		downloadTimer.schedule(deletion, 1000, 1000);
	}
	
	/**
	 * Get reservation date associated with a reservation
	 * @param reservationID
	 * @return ReservationDate
	 */
	public LocalDate getReservationDate(int reservationID) {
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getReservationID() == reservationID) {
				return reservationList.get(i).getReservationDate();
			}
		}
		return null;
	}
	
	/**
	 * Get the table no. associated with a reservation
	 * @param reservationID
	 * @return TableNo
	 */
	public int getTableNo(int reservationID) {
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getReservationID() == reservationID) {
				return reservationList.get(i).getTableNo();
			}
		}
		return 0;
	}
	
	/**
	 * Delete expiring reservations
	 * @param num
	 * @return status
	 */
	public boolean deleteActual(int num) {
		reservationList.remove(num);
		return true;
	}
}