package cozycode.capstone;
import cozycode.capstone.parking.ParkingGarage;

import cozycode.capstone.parking.ParkingGarage;
import cozycode.capstone.parking.car.Car;
import cozycode.capstone.parking.car.CarType;
import cozycode.capstone.ticketing.Ticket;

import java.util.Scanner;

public class Main {

    /*
     * Runtime method
     * Asks for user input
     */
    public static void main(String[] args) {

        // Instantiates parking garage
        ParkingGarage jumpmanJunction = new ParkingGarage(4,198);
        jumpmanJunction.initializeParkingGarage(20,20,50,50);

        // Initializes variables for controlling the loop and reading user input
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        // * Runs until the user wants to close the program
        // ! Exiting program is not implemented
        while (!exit) {

            // Determines if user wants to enter or leave
            System.out.println("Do you want to find or leave a parking spot? (f/l)");

            String response = sc.nextLine();
            if (response.equals("f")) {
                // * Asks user for identifying information
                // ? How should we use most of this information? The project requirements need it but where can we use it?
                // ? Perhaps when checking out it could display it to the user to verify it is the correct car
                System.out.println("What is your car's registration number?");
                String reg = sc.nextLine();
                System.out.println("What is your car's color?");
                String color = sc.nextLine();
                System.out.println("What is the make?");
                String make = sc.nextLine();
                System.out.println("The model?");
                String model = sc.nextLine();
                CarType type = determineType(sc);
                System.out.println("What is your Employee ID?");
                int id = sc.nextInt();
                sc.nextLine();

                // Instantiates a Car Object using the information provided
                Car car = new Car(reg,color,model,make,type,id);

                // * Assigns the spot and returns the spot assigned
                Ticket mySpot = jumpmanJunction.assignSpace(car);

                //TODO: Tell the user what spot they were assigned

            } else if (response.equals("l")) {

                // Asks the employee for their ID if they want to leave
                System.out.println("What is your Employee ID?");
                int id = sc.nextInt();
                sc.nextLine();

                jumpmanJunction.leaveSpace(id);

                // ? Should we display the car's information corresponding with the employee ID here before freeing the space to verify it's the right one?

            } else {

                // If the user didn't input a valid option, give clearer instructions
                System.out.println("Oops, that didn't work. Make sure you are inputting 'f' or 'l'.");
                continue;
            }

        }
    }

    /*
     * determineType method used to figure out which parking spot to give the user
     * Takes hierarchy of spot types into consideration
     * Uses Scanner Object used for previous user input
     ? Might need to add paid spots in future
     */
    public static CarType determineType(Scanner sc) {
        String response = "";

        // Checks if user has a handicap permit
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

        // Checks if user has an oversized car
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

        // Checks if the user has an EV
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

        // Checks if the user is in a carpool
        while (!response.equals("n")) {
            System.out.println("Are you in a carpool? (y/n)");
            response = sc.nextLine();
            if (!(response.equals("y") || response.equals("n"))) {
                System.out.println("Oops, that didn't work. Make sure you are inputting 'y' or 'n'.");
            } else if (response.equals("y")) {
                return CarType.CARPOOL;
            }
        }

        // If no special spot is required, give them a regular spot
        return CarType.REGULAR;
    }
}
