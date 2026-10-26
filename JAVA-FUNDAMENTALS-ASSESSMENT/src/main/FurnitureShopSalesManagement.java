package main;

import java.util.Scanner;

/**
 * @author : Sipho Sehlapelo
 * Project: IntelliJ IDEA
 * Date: 10/25/2026
 **/

public class FurnitureShopSalesManagement {
    static String[] arPersons = new String[4];
    static double[][] arSales = new double[4][4];
    static String searchedName = "";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Initialize();

        String displayMenuItem = "Please select a menu item below: \n" +
                "1 – See All Salespeople \n" +
                "2 – Find Salesperson \n" +
                "3 – Total Monthly Sales \n" +
                "4 – Monthly Salary \n" +
                "5 – Best Salesperson \n" +
                "6 – Monthly Turnover \n" +
                "7 – Total Salary Expenditure \n" +
                "Your Option: ";

        String continueMessage = "Do you want to continue(Y/N) ";
        char continueOption = 'Y';

        while (continueOption == 'y' || continueOption == 'Y') {
            System.out.print(displayMenuItem);
            String inputOption = sc.next();

            if (isNumeric(inputOption)) {
                int option = Integer.parseInt(inputOption);
                if (option >= 1 && option <= 7) {
                    checkOption(option);
                } else {
                    System.out.println("Error: Please enter a number between 1 and 7.");
                }
            } else {
                System.out.println("Error: Invalid input type. Please enter a valid number.");
            }


            System.out.print(continueMessage);
            String contStr = sc.next();
            if (!contStr.isEmpty()) {
                continueOption = contStr.charAt(0);
            } else {
                continueOption = 'N';
            }
        }

        sc.close();
    }

    /**
     * Function  initializes the arrays with 4 salespeople names and 4 sales
     * (weekly sales) per salesperson(arPersons and arSales)
     */
    public static void Initialize() {
        arPersons[0] = "Thabo";
        arPersons[1] = "Loraine";
        arPersons[2] = "Zama";
        arPersons[3] = "Peter";


        // Row 0 (Thabo)
        arSales[0][0] = 17550.0;
        arSales[0][1] = 14500.0;
        arSales[0][2] = 11000.0;
        arSales[0][3] = 12500.0;

        // Row 1 (Loraine)
        arSales[1][0] = 15000.0;
        arSales[1][1] = 12000.0;
        arSales[1][2] = 12500.0;
        arSales[1][3] = 16800.0;

        // Row 2 (Zama)
        arSales[2][0] = 15620.0;
        arSales[2][1] = 18100.0;
        arSales[2][2] = 21500.0;
        arSales[2][3] = 17630.0;

        // Row 3 (Peter)
        arSales[3][0] = 15755.0;
        arSales[3][1] = 13900.0;
        arSales[3][2] = 12950.0;
        arSales[3][3] = 19200.0;
    }

    /**
     * Function displays all the salespeople with their weekly sales and
     * total monthly sales.
     */
    public static void display() {
        System.out.println("Name\t" + "\t" + "Week 1\t" + "\t" + "Week 2\t" + "\t" + "Week 3\t" + "\t" + "Week 4\t" + "\t" + "Total Sales");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < arPersons.length; i++) {
            double totalSales = 0;


            for (int j = 0; j < 4; j++) {
                totalSales += arSales[i][j];
            }


            System.out.println(arPersons[i]  + "\t" + "\t" +
                    arSales[i][0] + "\t" + "\t" +
                    arSales[i][1] + "\t" + "\t" +
                    arSales[i][2] + "\t" + "\t" +
                    arSales[i][3] + "\t" + "\t" +
                    totalSales);
        }
    }

    /**
     * Method that takes the salesperson’s name as a
     * parameter, search the array and return the position of the salesperson.
      * @param name
     * @return the position of the salesperson
     */
public static int findSalesPerson(String name){
    for(int i = 0; i < arPersons.length; i++){
        if (arPersons[i] != null && arPersons[i].equalsIgnoreCase(name)){
            return i;
        }
    }
    return -1;
}

    /**
     *Method takes no parameters and it calculates and returns monthly sales for salesperson.
     * @return monthly sales for salesperson
     */
    public static double calcTotalMonthlySales(){
    double totalMonthlySales = 0;
    System.out.print("Search for salesperson: ");
    searchedName = sc.next();
    int index = findSalesPerson(searchedName);

        if (index == -1) {
            System.out.println("Error: Salesperson '" + searchedName + "' not found.");
            return 0.0;
        }


    for(int i = 0; i < 4; i++){
        totalMonthlySales += arSales[index][i];
    }

    return totalMonthlySales;
}
    /**
     *Method takes no parameters and it calculates and returns the monthly salary for salesperson.
     * @return monthly salary for salesperson
     */
    public static double calcMonthlySalary(){
        double totalMonthlySales = 0;
        System.out.print("Search for salesperson: ");
        searchedName = sc.next();
        int index = findSalesPerson(searchedName);

        if (index == -1) {
            System.out.println("Error: Salesperson '" + searchedName + "' not found.");
            return 0.0;
        }

        for(int i = 0; i < 4; i++){
            totalMonthlySales += arSales[index][i];
        }

        double salary = (totalMonthlySales * 0.1) + 7500;
        return salary;
    }

    /**
     * Method that takes no parameters, search the array(s)
     * and displays the name and monthly sales of the salesperson with the highest
     * monthly sales.
     */
    public static void getBestSalesPerson() {
        double[] arrTotalSales = getTotalSalesArr();


        double max = arrTotalSales[0];
        int index  = 0;
        for (int i = 1; i < arrTotalSales.length; i++) {
            if (arrTotalSales[i] > max) {
                max = arrTotalSales[i];
                index = i;
            }
        }

        System.out.println("Best Salesperson");
        System.out.println(arPersons[index] + "\t" + max);

    }

    /**
     * ) method that takes no parameter and calculates and returns the total sale value
     * @return the total sale value
     */
    public static double calcMonthlyTurnOver(){
        double totalMonthlyTurnOver = 0;
        for (int i = 0; i < arPersons.length; i++) {

            for (int j = 0; j < 4; j++) {
                totalMonthlyTurnOver += arSales[i][j];
            }
        }
        return totalMonthlyTurnOver;
    }

    public static double calcSalaryExpenditure(){
        double[] arrTotalSales = getTotalSalesArr();
        double monthlySalaryExpenditure = 0;
        for(int i = 0; i < arrTotalSales.length; i++){
            monthlySalaryExpenditure += (((arrTotalSales[i]) * 0.1) + 7500);
        }
       return monthlySalaryExpenditure;
    }

    /**
     * Helper function to get the total sales for each sales person at their specific index
     * @return array of salaries of each salesperson
     */
    public static double[] getTotalSalesArr(){
        double arrTotalSales[] = new double[4];
        for (int i = 0; i < arPersons.length; i++) {
            double totalSales = 0;
            for (int j = 0; j < 4; j++) {
                totalSales += arSales[i][j];
            }
            arrTotalSales[i] = totalSales;

        }
        return arrTotalSales;
    }

    /**
     * Function used to decide what happens when a user chooses a specific option
     * @param option
     */
    public static void checkOption(int option){
        switch(option){
            case 1:
                display();
                break;
            case 2:
                System.out.print("Search for salesperson: ");
                String name = sc.next();
                int foundIndex = findSalesPerson(name);
                if (foundIndex != -1) {
                    System.out.println(name + " is found at index " + foundIndex);
                } else {
                    System.out.println(name + " was not found in the system.");
                }
                break;
            case 3:
                double total = calcTotalMonthlySales();
                System.out.println("Total Sales for " + searchedName + ": " + total);
                break;
            case 4:
                double salary = calcMonthlySalary();
                System.out.println("Monthly Salary for " + searchedName + ": " + salary);
                break;
            case 5:
                getBestSalesPerson();
                break;
            case 6:
                double monthlyTurnover = calcMonthlyTurnOver();
                System.out.println("Monthly Turnover " + monthlyTurnover);
                break;

            case 7:
                double salaryExpenditure = calcSalaryExpenditure();
                System.out.println("Salary Expenditure " + salaryExpenditure);
                break;
            default:
                System.out.println("Enter a proper option from 1-7");
                break;
        }
    }

    /**
     * Helper function to check if a string contains only digits (0-9)
     * and represents a valid positive integer without using exceptions.
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}

