package cozycode.capstone;

import cozycode.capstone.parking.car.Car;
import cozycode.capstone.parking.car.CarType;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //TODO: waiting on ParkingGarage class
        ParkingGarage jumpmanJunction = new ParkingGarage(4,198,20,20,50,50,652);

        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            System.out.println("Do you want to find or leave a parking spot? (f/l)");

            String response = sc.nextLine();
            if (response.equals("f")) {
                System.out.println("What is your car's registration number?");
                String reg = sc.nextLine();
                System.out.println("What is your car's color?");
                String color = sc.nextLine();
                System.out.println("What is the make?");
                String make = sc.nextLine();
                System.out.println("The model?");
                String model = sc.nextLine();
                CarType type = determineType(sc);

                Car car = new Car(reg,color,model,make,type);

                /* Returns the spot number associated with the assigned spot to be given to the driver.
                If output is -1, there are no more spots remaining and the driver should be prompted to leave. */
                String mySpot = jumpmanJunction.assignSpace(car);

            } else if (response.equals("l")) {

                System.out.println("What is your car's registration number?");
                String reg = sc.nextLine();

                jumpmanJunction.leaveSpace(reg);

            } else {
                System.out.println("Oops, that didn't work. Make sure you are inputting 'f' or 'l'.");
                continue;
            }

        }


    }

    public static CarType determineType(Scanner sc) {
        String response = "";

        while (!response.equals("n")) {
            System.out.println("Do you have a handicap permit? (y/n)");
            response = sc.nextLine();
            if (!(response.equals("y") || response.equals("n"))) {
                System.out.println("Oops, that didn't work. Make sure you are inputting 'y' or 'n'.");
            } else if (response.equals("y")) {
                return CarType.HANDICAP;
            }
        }
        response = "";

        while (!response.equals("n")) {
            System.out.println("Is your car oversize? (y/n)");
            response = sc.nextLine();
            if (!(response.equals("y") || response.equals("n"))) {
                System.out.println("Oops, that didn't work. Make sure you are inputting 'y' or 'n'.");
            } else if (response.equals("y")) {
                return CarType.OVERSIZE;
            }
        }
        response = "";

        while (!response.equals("n")) {
            System.out.println("Is your car an EV? (y/n)");
            response = sc.nextLine();
            if (!(response.equals("y") || response.equals("n"))) {
                System.out.println("Oops, that didn't work. Make sure you are inputting 'y' or 'n'.");
            } else if (response.equals("y")) {
                return CarType.EV;
            }
        }
        response = "";

        while (!response.equals("n")) {
            System.out.println("Are you in a carpool? (y/n)");
            response = sc.nextLine();
            if (!(response.equals("y") || response.equals("n"))) {
                System.out.println("Oops, that didn't work. Make sure you are inputting 'y' or 'n'.");
            } else if (response.equals("y")) {
                return CarType.CARPOOL;
            }
        }

        return CarType.REGULAR;
    }

}
