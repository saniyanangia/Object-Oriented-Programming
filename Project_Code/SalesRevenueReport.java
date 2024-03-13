package RRPSS;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

import java.time.LocalDate;
/**
 * Manages SalesRevenueReport
 * @author Team3
 *
 */
public class SalesRevenueReport {
	/**
	 * Attributes related to a SalesRevenueReport
	 */
    private LocalDate sPeriod;
    private LocalDate ePeriod;
    private double revenue;
    ArrayList<SalesItem> saleList;
    private SalesItem sales;
    ArrayList<SalesItem> promoList;

    // display by year
    /**
     * This method prints the yearly SalesRevenueReport for a period
     * @param OM OrderManager
     * @param sYear start year
     * @param eYear end year
     */
    public void printReport(OrderManager OM, int sYear, int eYear) {
        sPeriod = LocalDate.of(sYear, 1, 1);
        ePeriod = LocalDate.of(eYear, 12, 31);
        saleList = new ArrayList<SalesItem>();
        promoList = new ArrayList<SalesItem>();
        ArrayList<OrderInvoice> in = OM.getInvoiceInRange(sPeriod, ePeriod);
        revenue = 0.0;
        System.out.println("==Yearly Revenue==");
        System.out.println("---PERIOD---");
        System.out.println(sPeriod + " - " + ePeriod);
        System.out.println("---REVENUE---");
        for (int i = 0; i < in.size(); i++) {
            for (MenuItem item : in.get(i).getOrderItems()) {
                boolean svalid = false;
                boolean pvalid = false;
                if (item.getItemType() != 4) {
                    for (SalesItem ite : saleList) {
                        if (ite.getItemName() == item.getItemName()) {
                            ite.incrementCount();
                            svalid = true;
                        }
                    }
                    if (!svalid) {
                        sales = new SalesItem(item.getItemName());
                        saleList.add(sales);
                    }
                } else {
                    for (SalesItem ite : promoList) {
                        if (ite.getItemName() == item.getItemName()) {
                            ite.incrementCount();
                            pvalid = true;
                        }
                    }
                    if (!pvalid) {
                        sales = new SalesItem(item.getItemName());
                        promoList.add(sales);
                    }
                }
            }
            if (i == (in.size() - 1)) {
                revenue += in.get(i).getNett();
                System.out.println("\n" + in.get(i).getOrderDate().getYear() + " : " + "$" + revenue);
                if (!saleList.isEmpty()) {
                    System.out.println("\n===Ala Carte===");
                    for (SalesItem s : saleList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                if (!promoList.isEmpty()) {
                    System.out.println("\n===Promotion Items===");
                    for (SalesItem s : promoList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                saleList.clear();
                promoList.clear();
            } else if (in.get(i).getOrderDate().getYear() == in.get(i + 1).getOrderDate().getYear()) {
                revenue += in.get(i).getNett();
            } else if (in.get(i).getOrderDate().getYear() != in.get(i + 1).getOrderDate().getYear()) {
                revenue += in.get(i).getNett();
                System.out.println("\n" + in.get(i).getOrderDate().getYear() + " : " + "$" + revenue);
                revenue = 0.0;
                if (!saleList.isEmpty()) {
                    System.out.println("\n===Ala Carte===");
                    for (SalesItem s : saleList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                if (!promoList.isEmpty()) {
                    System.out.println("\n===Promotion Items===");
                    for (SalesItem s : promoList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                saleList.clear();
                promoList.clear();
            }
        }
    }

    // display by month
    /**
     * This method prints the monthly SalesRevenueReport for a period
     * @param OM OrderManager
     * @param sYear start year
     * @param eYear end year
     * @param sMonth start month
     * @param eMonth end month
     */
    public void printReport(OrderManager OM, int sYear, int eYear, int sMonth, int eMonth) {
        sPeriod = LocalDate.of(sYear, sMonth, 1);
        ePeriod = LocalDate.of(eYear, eMonth, 31);
        saleList = new ArrayList<SalesItem>();
        promoList = new ArrayList<SalesItem>();
        ArrayList<OrderInvoice> in = OM.getInvoiceInRange(sPeriod, ePeriod);
        revenue = 0.0;
        System.out.println("==Monthly Revenue==");
        System.out.println("---PERIOD---");
        System.out.println(sPeriod + " - " + ePeriod);
        System.out.println("---REVENUE---");
        for (int i = 0; i < in.size(); i++) {
            for (MenuItem item : in.get(i).getOrderItems()) {
                boolean svalid = false;
                boolean pvalid = false;
                if (item.getItemType() != 4) {
                    for (SalesItem ite : saleList) {
                        if (ite.getItemName() == item.getItemName()) {
                            ite.incrementCount();
                            svalid = true;
                        }
                    }
                    if (!svalid) {
                        sales = new SalesItem(item.getItemName());
                        saleList.add(sales);
                    }
                } else {
                    for (SalesItem ite : promoList) {
                        if (ite.getItemName() == item.getItemName()) {
                            ite.incrementCount();
                            pvalid = true;
                        }
                    }
                    if (!pvalid) {
                        sales = new SalesItem(item.getItemName());
                        promoList.add(sales);
                    }
                }
            }
            if (i == (in.size() - 1)) {
                revenue += in.get(i).getNett();
                System.out.println("\n" + in.get(i).getOrderDate().getMonth() + in.get(i).getOrderDate().getYear() + " : " + "$" + revenue);
                if (!saleList.isEmpty()) {
                    System.out.println("\n===Ala Carte===");
                    for (SalesItem s : saleList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                if (!promoList.isEmpty()) {
                    System.out.println("\n===Promotion Items===");
                    for (SalesItem s : promoList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                saleList.clear();
                promoList.clear();
            } else if (in.get(i).getOrderDate().getMonthValue() == in.get(i + 1).getOrderDate().getMonthValue()) {
                revenue += in.get(i).getNett();
            } else if (in.get(i).getOrderDate().getMonthValue() != in.get(i + 1).getOrderDate().getMonthValue()) {
                revenue += in.get(i).getNett();
                System.out.println("\n" + in.get(i).getOrderDate().getMonth() + in.get(i).getOrderDate().getYear() + " : " + "$" + revenue);
                revenue = 0.0;
                if (!saleList.isEmpty()) {
                    System.out.println("\n===Ala Carte===");
                    for (SalesItem s : saleList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                if (!promoList.isEmpty()) {
                    System.out.println("\n===Promotion Items===");
                    for (SalesItem s : promoList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                saleList.clear();
                promoList.clear();
            }
        }
    }

    // display by day
    /**
     * This method prints the daily SalesRevenueReport for a period
     * @param OM OrderManager
     * @param sYear start year
     * @param eYear end year
     * @param sMonth start month
     * @param eMonth end month
     * @param sDay start day
     * @param eDay end day
     */
    public void printReport(OrderManager OM, int sYear, int eYear, int sMonth, int eMonth, int sDay, int eDay) {

        sPeriod = LocalDate.of(sYear, sMonth, sDay);
        ePeriod = LocalDate.of(eYear, eMonth, eDay);
        saleList = new ArrayList<SalesItem>();
        promoList = new ArrayList<SalesItem>();
        ArrayList<OrderInvoice> in = OM.getInvoiceInRange(sPeriod, ePeriod);
        revenue = 0.0;
        System.out.println("==Daily Revenue==");
        System.out.println("---PERIOD---");
        System.out.println(sPeriod + " - " + ePeriod);
        System.out.println("---REVENUE---");
        for (int i = 0; i < in.size(); i++) {
            for (MenuItem item : in.get(i).getOrderItems()) {
                boolean svalid = false;
                boolean pvalid = false;
                if (item.getItemType() != 4) {
                    for (SalesItem ite : saleList) {
                        if (ite.getItemName() == item.getItemName()) {
                            ite.incrementCount();
                            svalid = true;
                        }
                    }
                    if (!svalid) {
                        sales = new SalesItem(item.getItemName());
                        saleList.add(sales);
                    }
                } else {
                    for (SalesItem ite : promoList) {
                        if (ite.getItemName() == item.getItemName()) {
                            ite.incrementCount();
                            pvalid = true;
                        }
                    }
                    if (!pvalid) {
                        sales = new SalesItem(item.getItemName());
                        promoList.add(sales);
                    }
                }
            }
            if (i == (in.size() - 1)) {
                revenue += in.get(i).getNett();
                System.out.println("\n" + in.get(i).getOrderDate() + " : " + "$" + revenue);
                if (!saleList.isEmpty()) {
                    System.out.println("\n===Ala Carte===");
                    for (SalesItem s : saleList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                if (!promoList.isEmpty()) {
                    System.out.println("\n===Promotion Items===");
                    for (SalesItem s : promoList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                saleList.clear();
                promoList.clear();
            } else if (in.get(i).getOrderDate() == in.get(i + 1).getOrderDate()) {
                revenue += in.get(i).getNett();
            } else if (in.get(i).getOrderDate() != in.get(i + 1).getOrderDate()) {
                revenue += in.get(i).getNett();
                System.out.println("\n" + in.get(i).getOrderDate() + " : " + "$" + revenue);
                revenue = 0.0;
                if (!saleList.isEmpty()) {
                    System.out.println("\n===Ala Carte===");
                    for (SalesItem s : saleList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                if (!promoList.isEmpty()) {
                    System.out.println("\n===Promotion Items===");
                    for (SalesItem s : promoList) {
                        System.out.println(s.getItemName() + " : " + s.getCount());
                    }
                }
                saleList.clear();
                promoList.clear();
            }
        }
    }
}