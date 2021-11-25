
package com.nextgenpos;

/**
 *
 * @author steau
 */
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Cashier {

    private String uName;
    private int id;
    private int pw;

    public Cashier() {
    }

    public Cashier(String un, int i, int p) {
        this.uName = un;
        this.id = i;
        this.pw = p;
    }

    public String getUserName() {
        return this.uName;
    }

    public static void cashierDo() throws InterruptedException, IOException {
        Scanner cashierScan = new Scanner(System.in);

        boolean done = false;
        do {
            System.out.print("Please select an option-->");
            System.out.print("[OPTIONS- 0:Process Sale, 1: Process Return, -1:Logout]\n-->");
            try {
                switch (cashierScan.nextInt()) {
                    case 0:
                        Sale trans = new Sale();
                        trans.makeTransaction();
                        break;
                    case 1:
                        System.out.println("Not supported yet!");
                        break;
                    case -1:
                        System.out.println("Logging Out!");
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid input, please try again!");
                        break;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Error reading input, please try again!");
                System.out.println();
                cashierScan.nextLine();
            }
        } while (!done);
    }
}
