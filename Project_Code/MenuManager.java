package RRPSS;

import java.util.*;
/**
 * Manager of menuItem
 * @author doesnt matter
 */
public class MenuManager {
	/**
	 * A list of menuItems
	 */
	ArrayList<MenuItem> menuItemList;
	Scanner sa = new Scanner(System.in);
	Scanner sb = new Scanner(System.in);
	Scanner sc = new Scanner(System.in);
	Scanner sd = new Scanner(System.in);
	Scanner se = new Scanner(System.in);
	Scanner sf = new Scanner(System.in);
	Scanner sg = new Scanner(System.in);
	Scanner sh = new Scanner(System.in);
	Scanner si = new Scanner(System.in);
	Scanner sj = new Scanner(System.in);
	Scanner sk = new Scanner(System.in);
	Scanner sl = new Scanner(System.in);
	Scanner sm = new Scanner(System.in);
	/**
	 * Adding items to a sample menu
	 */
	public void testing() {
		// Sample Menu
		MenuItem m1 = new MenuItem(1, 1, "Steak", "description", 30);
		MenuItem m2 = new MenuItem(2, 2, "Ice Lemon Tea", "description", 3);
		MenuItem m3 = new MenuItem(3, 3, "Muffin", "description", 5);
		MenuItem m4 = new MenuItem(4, 4, "Set A", "Steak + Ice Lemon Tea", 20);
		menuItemList.add(m1);
		menuItemList.add(m2);
		menuItemList.add(m3);
		menuItemList.add(m4);
	}
	/**
	 * Manage menuItem
	 */
	public MenuManager() {
		menuItemList = new ArrayList<MenuItem>();
		testing();
	}
	/**
	 * Getting the details of a menuItem
	 * @param menuItemID of this menuItem
	 * @return menuItem
	 */
	public MenuItem getMenuItem(int menuItemID) {
		// Check item existence
		for (int i = 0; i < menuItemList.size(); i++) {
			if (menuItemList.get(i).getMenuItemID() == menuItemID) {
				return menuItemList.get(i);
			}
		}
		return null;
	}
	/**
	 * This method creates a new menu item
	 * @return status 
	 */
	public boolean createItem() {
		MenuItem newMenuItem;

		int menuItemID = (menuItemList.size() + 1), itemType;
		String itemName, description;
		double price;
		System.out.println("\n-----Add Menu Item-----");

		// itemType
		System.out.println("(1) Main Course\n(2) Drinks\n(3) Desserts\n(4) Promotional Package");
		System.out.print("Enter Item Type: ");

		itemType = sc.nextInt();
		
		while (itemType < 1 || itemType > 4) {
			System.out.print("Invalid Input! Enter Item Type: ");
			itemType = sc.nextInt();
		}

		// itemName
		System.out.print("Enter Item Name: ");
		itemName = sb.nextLine().toLowerCase();
		// Check for duplicates
		for (int i = 0; i < menuItemList.size(); i++) {
			if (menuItemList.get(i).getItemName().equals(itemName)) {
				System.out.print("Item is already in menu!");
				return false;
			}
		}

		// description
		System.out.println("Enter Item Description: ");
		description = sa.nextLine().toLowerCase();

		// price
		System.out.print("Enter Item Price: ");
		// Scanner md = new Scanner(System.in);
		price = sd.nextDouble();
		while (price < 0) {
			System.out.print("Invalid Input! Enter Item Price: ");
			price = sd.nextDouble();
		}
		newMenuItem = new MenuItem(menuItemID, itemType, itemName, description, price);
		menuItemList.add(newMenuItem);
		return true;
	}
	/**
	 * This method updates the attributes of menuItem
	 * @return status
	 */
	public boolean updateItem() {
		boolean valid = false;

		MenuItem targetItem = null;
		int menuItemID, attribute;

		System.out.println("\n-----Update Menu Item-----");

		if (menuItemList.size() == 0) {
			System.out.println("Menu is empty!");
			return false;
		}

		System.out.print("Enter Item ID: ");
		menuItemID = sf.nextInt();
		// Check item existence
		for (int i = 0; i < menuItemList.size(); i++) {
			if (menuItemList.get(i).getMenuItemID() == menuItemID) {
				targetItem = menuItemList.get(i);
				valid = true;
			}
		}
		while (!valid) {
			System.out.print("Invalid Input! Enter Item ID: ");
			menuItemID = sf.nextInt();
			// Check item existence
			for (int i = 0; i < menuItemList.size(); i++) {
				if (menuItemList.get(i).getMenuItemID() == menuItemID) {
					targetItem = menuItemList.get(i);
					valid = true;
				}
			}
		}

		System.out.println("(1) Category\n(2) Name\n(3) Description\n(4) Price");
		System.out.print("Enter attribute to update: ");
		attribute = sg.nextInt();
		while (attribute < 1 || attribute > 4) {
			System.out.print("Invalid Input! Enter attribute to update: ");
			attribute = sg.nextInt();
		}
		// Scanner mg = new Scanner(System.in);
		switch (attribute) {
		case 1:
			int itemType;
			// itemType
			System.out.println("(1) Main Course\n(2) Drinks\n(3) Desserts\n(4) Promotional Package");
			System.out.print("Enter Item Type: ");
			itemType = sh.nextInt();
			while (itemType < 1 || itemType > 4) {
				System.out.print("Invalid Input! Enter Item Type: ");
				itemType = sh.nextInt();
			}
			targetItem.setItemType(itemType);
			break;
		case 2:
			String itemName;
			// itemName
			System.out.print("Enter Item Name: ");
			itemName = si.nextLine().toLowerCase();
			// Check for duplicates
			valid = true;
			for (int i = 0; i < menuItemList.size(); i++) {
				if (menuItemList.get(i) == targetItem) {
					continue;
				} else if (menuItemList.get(i).getItemName().equals(itemName)) {
					valid = false;
				}
			}
			while (!valid) {
				System.out.print("Item is already in menu! Enter Item Name: ");
				itemName = si.nextLine().toLowerCase();
				// Check for duplicates
				valid = true;
				for (int i = 0; i < menuItemList.size(); i++) {
					if (menuItemList.get(i) == targetItem) {
						continue;
					} else if (menuItemList.get(i).getItemName().equals(itemName)) {
						valid = false;
					}
				}
			}
			targetItem.setItemName(itemName);
			break;
		case 3:
			String description;
			// description
			System.out.println("Enter Item Description: ");
			description = sj.nextLine().toLowerCase();
			targetItem.setDescription(description);
			break;
		case 4:
			double price;
			// price
			System.out.print("Enter Item Price: ");
			price = sk.nextDouble();
			while (price < 0) {
				System.out.print("Invalid Input! Enter Item Price: ");
				price = sk.nextDouble();
			}
			targetItem.setPrice(price);
			break;
		}
		return true;
	}
	/**
	 * This method updates our promotional item
	 * @return status
	 */
	public boolean updatePromoItem() {
		boolean valid = false;

		MenuItem targetItem = null;
		int menuItemID, attribute;

		System.out.println("\n-----Update Menu Item-----");
		int promoSize = 0;
		for (int i = 0; i < menuItemList.size(); i++) {
			if (menuItemList.get(i).getItemType() == 4) {
				promoSize++;
			}
		}
		if (promoSize == 0) {
			System.out.println("Menu is empty!");
			return false;
		}

		System.out.print("Enter Item ID: ");
		menuItemID = sf.nextInt();
		// Check item existence
		for (int i = 0; i < menuItemList.size(); i++) {
			if (menuItemList.get(i).getMenuItemID() == menuItemID) {
				targetItem = menuItemList.get(i);
				valid = true;
			}
		}
		while (!valid) {
			System.out.print("Invalid Input! Enter Item ID: ");
			menuItemID = sf.nextInt();
			// Check item existence
			for (int i = 0; i < menuItemList.size(); i++) {
				if (menuItemList.get(i).getMenuItemID() == menuItemID) {
					targetItem = menuItemList.get(i);
					valid = true;
				}
			}
		}

		System.out.println("(1) Category\n(2) Name\n(3) Description\n(4) Price");
		System.out.print("Enter attribute to update: ");
		attribute = sg.nextInt();
		while (attribute < 1 || attribute > 4) {
			System.out.print("Invalid Input! Enter attribute to update: ");
			attribute = sg.nextInt();
		}
		// Scanner mg = new Scanner(System.in);
		switch (attribute) {
		case 1:
			int itemType;
			// itemType
			System.out.println("(1) Main Course\n(2) Drinks\n(3) Desserts\n(4) Promotional Package");
			System.out.print("Enter Item Type: ");
			itemType = sh.nextInt();
			while (itemType < 1 || itemType > 4) {
				System.out.print("Invalid Input! Enter Item Type: ");
				itemType = sh.nextInt();
			}
			targetItem.setItemType(itemType);
			break;
		case 2:
			String itemName;
			// itemName
			System.out.print("Enter Item Name: ");
			itemName = si.nextLine().toLowerCase();
			// Check for duplicates
			valid = true;
			for (int i = 0; i < menuItemList.size(); i++) {
				if (menuItemList.get(i) == targetItem) {
					continue;
				} else if (menuItemList.get(i).getItemName().equals(itemName)) {
					valid = false;
				}
			}
			while (!valid) {
				System.out.print("Item is already in menu! Enter Item Name: ");
				itemName = si.nextLine().toLowerCase();
				// Check for duplicates
				valid = true;
				for (int i = 0; i < menuItemList.size(); i++) {
					if (menuItemList.get(i) == targetItem) {
						continue;
					} else if (menuItemList.get(i).getItemName().equals(itemName)) {
						valid = false;
					}
				}
			}
			targetItem.setItemName(itemName);
			break;
		case 3:
			String description;
			// description
			System.out.println("Enter Item Description: ");
			description = sj.nextLine().toLowerCase();
			targetItem.setDescription(description);
			break;
		case 4:
			double price;
			// price
			System.out.print("Enter Item Price: ");
			price = sk.nextDouble();
			while (price < 0) {
				System.out.print("Invalid Input! Enter Item Price: ");
				price = sk.nextDouble();
			}
			targetItem.setPrice(price);
			break;
		}
		return true;
	}
	/**
	 * This method removes a menuItem
	 * @return status
	 */
	public boolean removeItem() {
		boolean valid = false;

		MenuItem unwantedItem = null;
		int menuItemID;

		System.out.println("\n-----Remove Menu Item-----");

		if (menuItemList.size() == 0) {
			System.out.println("Menu is empty!");
			return false;
		}

		System.out.print("Enter Item ID: ");
		menuItemID = sl.nextInt();
		// Check item existence
		for (int i = 0; i < menuItemList.size(); i++) {
			if (menuItemList.get(i).getMenuItemID() == menuItemID) {
				unwantedItem = menuItemList.get(i);
				valid = true;
			}
		}
		while (!valid) {
			System.out.print("Invalid Input! Enter Item ID: ");
			menuItemID = sl.nextInt();
			// Check item existence
			for (int i = 0; i < menuItemList.size(); i++) {
				if (menuItemList.get(i).getMenuItemID() == menuItemID) {
					unwantedItem = menuItemList.get(i);
					valid = true;
				}
			}
		}
		// mh.close();

		menuItemList.remove(unwantedItem);
		return true;
	}
	/**
	 * This method removes a promotional item from menu
	 * @return status
	 */
	public boolean removePromoItem() {
		boolean valid = false;

		MenuItem unwantedItem = null;
		int menuItemID;

		System.out.println("\n-----Remove Menu Item-----");
		int promoSize = 0;
		for (int i = 0; i < menuItemList.size(); i++) {
			if (menuItemList.get(i).getItemType() == 4) {
				promoSize++;
			}
		}

		if (promoSize == 0) {
			System.out.println("Menu is empty!");
			return false;
		}

		System.out.print("Enter Item ID: ");
		menuItemID = sl.nextInt();
		// Check item existence
		for (int i = 0; i < menuItemList.size(); i++) {
			if (menuItemList.get(i).getMenuItemID() == menuItemID) {
				unwantedItem = menuItemList.get(i);
				valid = true;
			}
		}
		while (!valid) {
			System.out.print("Invalid Input! Enter Item ID: ");
			menuItemID = sl.nextInt();
			// Check item existence
			for (int i = 0; i < menuItemList.size(); i++) {
				if (menuItemList.get(i).getMenuItemID() == menuItemID) {
					unwantedItem = menuItemList.get(i);
					valid = true;
				}
			}
		}
		// mh.close();

		menuItemList.remove(unwantedItem);
		return true;
	}
	/**
	 * This function prints our menu
	 */
	public void viewMenu() {
		System.out.println("\n______________________");
		System.out.println("MENU (" + menuItemList.size() + " items)");
		for (int i = 0; i < menuItemList.size(); i++) {
			System.out.println("\n" + menuItemList.get(i).getItemName());
			System.out.println("ID: " + menuItemList.get(i).getMenuItemID());
			System.out.println("Category: ");
			switch (menuItemList.get(i).getItemType()) {
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
			System.out.println("Description: " + menuItemList.get(i).getDescription());
			System.out.println("Price: " + menuItemList.get(i).getPrice());
		}
		System.out.println("______________________\n");
	}
	/**
	 * This function prints our promotional item
	 */
	public void viewPromotion() {
		System.out.println("\n______________________");
		int promoSize = 0;
		for (int i = 0; i < menuItemList.size(); i++) {
			if (menuItemList.get(i).getItemType() == 4) {
				promoSize++;
			}
		}
		System.out.println("PROMOTION (" + promoSize + " items)");
		for (int i = 0; i < menuItemList.size(); i++) {
			if (menuItemList.get(i).getItemType() == 4) {
				System.out.println("\n" + menuItemList.get(i).getItemName());
				System.out.println("ID: " + menuItemList.get(i).getMenuItemID());
				System.out.println("Category: Promotional Package");
				System.out.println("Description: " + menuItemList.get(i).getDescription());
				System.out.println("Price: " + menuItemList.get(i).getPrice());
			}
		}
		System.out.println("______________________\n");
	}
}