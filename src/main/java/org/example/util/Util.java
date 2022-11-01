package org.example.util;

import java.util.Scanner;

public class Util {

    public  static int getAction() {
        while (true) {
            try {
                System.out.print("Enter the Action: ");
                Scanner scanner = new Scanner(System.in);
                int action = scanner.nextInt();
                return action;
            } catch (RuntimeException e) {
                System.out.println("Mazgi raqam kirit");
            }
        }
    }
}
